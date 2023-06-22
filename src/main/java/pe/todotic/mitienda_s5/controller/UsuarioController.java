package pe.todotic.mitienda_s5.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.todotic.mitienda_s5.service.UsuarioService;

import java.security.Principal;

@Controller
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/inscribir")
    String inscribir(@RequestParam("c") Integer idCurso, Principal principal) {
        usuarioService.inscribir(idCurso, principal);
        return "redirect:/usuario/inscribir/exito";
    }

    @GetMapping("/inscribir/exito")
    String exito() {
        return "exito-inscripcion";
    }

}
