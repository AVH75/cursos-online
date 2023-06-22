package pe.todotic.mitienda_s5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import pe.todotic.mitienda_s5.model.Curso;
import pe.todotic.mitienda_s5.repository.CursoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    String index(Model model) {
//        if (true) {
//            throw new RuntimeException("Este es un ejemplo de una excepción lanzada intencionalmente");
//        }
        List<Curso> ultimosCursos = cursoRepository.findFirst8ByOrderByFechaCreacionDesc();
        model.addAttribute("ultimosCursos", ultimosCursos);
        return "index";
    }

    @GetMapping("/cursos")
    ModelAndView cursos(
            @PageableDefault(size = 8, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Curso> cursoPage = cursoRepository.findAll(pageable);

        return new ModelAndView("cursos")
                .addObject("cursoPage", cursoPage);
    }

    @GetMapping("/cursos/{idCurso}")
    ModelAndView detallesCurso(@PathVariable Integer idCurso) {
        Curso curso = cursoRepository
                .findById(idCurso)
                .orElseThrow(EntityNotFoundException::new);

        return new ModelAndView("detalles-curso")
                .addObject("curso", curso);
    }

//    @ExceptionHandler({EntityNotFoundException.class})
//    String handleEntityNotFoundException(EntityNotFoundException ex) {
//        return "error-404";
//    }

}
