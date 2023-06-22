package pe.todotic.mitienda_s5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.todotic.mitienda_s5.model.Curso;
import pe.todotic.mitienda_s5.model.Inscripcion;
import pe.todotic.mitienda_s5.model.Usuario;
import pe.todotic.mitienda_s5.repository.CursoRepository;
import pe.todotic.mitienda_s5.repository.InscripcionRepository;
import pe.todotic.mitienda_s5.repository.UsuarioRepository;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inscribir(Integer idCurso, Principal principal) {
        Curso curso = cursoRepository
                .findById(idCurso)
                .orElseThrow(EntityNotFoundException::new);

        Usuario usuario = usuarioRepository
                .findByEmail(principal.getName())
                .orElseThrow(EntityNotFoundException::new);

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(usuario);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDateTime.now());

        inscripcionRepository.save(inscripcion);
    }

}
