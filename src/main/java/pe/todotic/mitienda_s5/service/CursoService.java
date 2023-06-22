package pe.todotic.mitienda_s5.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.todotic.mitienda_s5.model.Curso;
import pe.todotic.mitienda_s5.repository.CursoRepository;

import javax.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepository;
    private final StorageService storageService;

    public void crear(Curso curso) {
        String rutaImagen = storageService.store(curso.getImagen());
        curso.setRutaImagen(rutaImagen);
        cursoRepository.save(curso);
    }

    public void actualizar(Integer idCurso, Curso curso) {
        Curso cursoFromDb = cursoRepository
                .findById(idCurso)
                .orElseThrow(EntityNotFoundException::new);

        cursoFromDb.setTitulo(curso.getTitulo());
        cursoFromDb.setDescripcion(curso.getDescripcion());
        cursoFromDb.setPrecio(curso.getPrecio());
        cursoFromDb.setFechaInicio(curso.getFechaInicio());

        if (!curso.getImagen().isEmpty()) {
            String rutaImagen = storageService.store(curso.getImagen());
            cursoFromDb.setRutaImagen(rutaImagen);
        }

        cursoRepository.save(cursoFromDb);
    }

    public void eliminar(Integer idCurso) {
        Curso curso = cursoRepository
                .findById(idCurso)
                .orElseThrow(EntityNotFoundException::new);

        storageService.delete(curso.getRutaImagen());
        cursoRepository.delete(curso);
    }

}
