package pe.todotic.mitienda_s5.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.todotic.mitienda_s5.model.Curso;
import pe.todotic.mitienda_s5.repository.CursoRepository;
import pe.todotic.mitienda_s5.service.CursoService;
import pe.todotic.mitienda_s5.service.StorageService;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/admin/cursos")
@AllArgsConstructor
public class AdminCursoController {

//    @Autowired
//    private CursoRepository cursoRepository;
//
//    @Autowired
//    private StorageService storageService;

    private final CursoRepository cursoRepository;
    private final CursoService cursoService;

//    public AdminCursoController(CursoRepository cursoRepository, StorageService storageService) {
//        this.cursoRepository = cursoRepository;
//        this.storageService = storageService;
//    }

    @GetMapping
    public String index(
            Model model,
            @RequestParam(required = false) String titulo,
            @PageableDefault(size = 5, sort = "titulo", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<Curso> cursoPage;

        if (StringUtils.hasText(titulo)) {
            cursoPage = cursoRepository.findByTituloContaining(titulo, pageable);
        } else {
            cursoPage = cursoRepository.findAll(pageable);
        }

        model.addAttribute("cursoPage", cursoPage);
        return "cursos/index";
    }

    @GetMapping("/nuevo")
    String nuevo(Model model) {
        model.addAttribute("curso", new Curso());
        return "cursos/formulario";
    }

    @PostMapping("/nuevo")
    String crear(@ModelAttribute @Validated Curso curso, BindingResult bindingResult, RedirectAttributes ra, Model model) {
        if (curso.getImagen().isEmpty()) {
            bindingResult.rejectValue("imagen", "MultipartNotEmpty");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("curso", curso);
            return "cursos/formulario";
        }
        cursoService.crear(curso);
        ra.addFlashAttribute("msgExito", "El curso ha sido creado correctamente");

        return "redirect:/admin/cursos";
    }

    @GetMapping("/{id}/editar")
    String editar(@PathVariable Integer id, Model model) {
        Curso curso = cursoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        model.addAttribute("curso", curso);

        return "cursos/formulario";
    }

    @PostMapping("/{id}/editar")
    String actualizar(@PathVariable Integer id,
                      @ModelAttribute @Validated Curso curso,
                      BindingResult bindingResult,
                      RedirectAttributes ra,
                      Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("curso", curso);
            return "cursos/formulario";
        }
        cursoService.actualizar(id, curso);
        ra.addFlashAttribute("msgExito", "El curso ha sido actualizado correctamente");

        return "redirect:/admin/cursos";
    }

    @PostMapping("/{id}/eliminar")
    String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        cursoService.eliminar(id);
        ra.addFlashAttribute("msgExito", "El curso ha sido eliminado correctamente");

        return "redirect:/admin/cursos";
    }

}
