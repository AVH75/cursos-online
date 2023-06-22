package pe.todotic.mitienda_s5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.todotic.mitienda_s5.controller.form.RegistroUsuarioForm;
import pe.todotic.mitienda_s5.model.Usuario;
import pe.todotic.mitienda_s5.model.group.RegistrarUsuarioGroup;
import pe.todotic.mitienda_s5.repository.UsuarioRepository;

@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    ModelAndView index() {
        return new ModelAndView("registro")
                .addObject("registroUsuarioForm", new RegistroUsuarioForm());
    }

    @PostMapping
    ModelAndView crear(
            @Validated RegistroUsuarioForm registroUsuarioForm,
            BindingResult bindingResult,
            RedirectAttributes ra
    ) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("registro")
                    .addObject("registroUsuarioForm", registroUsuarioForm);
        }
        // validar si el email ya está siendo usado
//        String email = usuario.getEmail();
//        boolean usuarioYaExiste = usuarioRepository.existsByEmail(email);
//
//        if (usuarioYaExiste) {
//            bindingResult.rejectValue("email", "EmailAlreadyExists");
//        }

        // validar si las contraseñas coinciden
//        if (!usuario.getPassword1().equals(usuario.getPassword2())) {
//            bindingResult.rejectValue("password1", "PasswordNotEquals");
//        }

//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("registro")
//                    .addObject("usuario", usuario);
//        }

        Usuario usuario = new Usuario();
        usuario.setNombres(registroUsuarioForm.getNombres());
        usuario.setApellidos(registroUsuarioForm.getApellidos());
        usuario.setEmail(registroUsuarioForm.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroUsuarioForm.getPassword1()));
        usuario.setRol(Usuario.Rol.ESTUDIANTE);

        usuarioRepository.save(usuario);

        ra.addAttribute("registroExitoso", "");

        return new ModelAndView("redirect:/login");
    }

}
