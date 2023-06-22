package pe.todotic.mitienda_s5.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.todotic.mitienda_s5.controller.form.AdminUsuarioForm;
import pe.todotic.mitienda_s5.model.Usuario;
import pe.todotic.mitienda_s5.model.group.AdminCrearUsuarioGroup;
import pe.todotic.mitienda_s5.repository.UsuarioRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
@AllArgsConstructor
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;


    @GetMapping()
    public String index(Model model, @RequestParam(required = false) String nombreCompleto) {
        List<Usuario> usuarios;

        if (StringUtils.hasText(nombreCompleto)) {
            usuarios = usuarioRepository.findByNombreCompletoContaining(nombreCompleto);
        } else {
            usuarios = usuarioRepository.findAll();
        }

        model.addAttribute("usuarios", usuarios);
        return "usuarios/index";
    }

    @GetMapping("/nuevo")
    String nuevo(Model model) {
        model.addAttribute("adminUsuarioForm", new AdminUsuarioForm());
        return "usuarios/formulario";
    }

    @PostMapping("/nuevo")
    String crear(
            @ModelAttribute @Validated AdminUsuarioForm adminUsuarioForm,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra
    ) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("adminUsuarioForm", adminUsuarioForm);
            return "usuarios/formulario";
        }
        Usuario usuario = new Usuario();
        usuario.setNombres(adminUsuarioForm.getNombres());
        usuario.setApellidos(adminUsuarioForm.getApellidos());
        usuario.setRol(adminUsuarioForm.getRol());
        usuario.setEmail(adminUsuarioForm.getEmail());
        usuario.setPassword(passwordEncoder.encode(adminUsuarioForm.getPassword1()));

        usuarioRepository.save(usuario);

        ra.addFlashAttribute("msgExito", "El usuario se ha sido creado correctamente");

        return "redirect:/admin/usuarios";
    }

    @GetMapping("/{id}/editar")
    String editar(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        model.addAttribute("usuario", usuario);

        return "usuarios/formulario";
    }

    @PostMapping("/{id}/editar")
    String actualizar(@PathVariable Integer id,
                      @ModelAttribute @Validated Usuario usuario,
                      BindingResult bindingResult,
                      Model model,
                      RedirectAttributes ra) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "usuarios/formulario";
        }
        Usuario usuarioFromDb = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        usuarioFromDb.setNombres(usuario.getNombres());
        usuarioFromDb.setApellidos(usuario.getApellidos());
        usuarioFromDb.setEmail(usuario.getEmail());
        usuarioFromDb.setPassword(usuario.getPassword());
        usuarioFromDb.setRol(usuario.getRol());

        usuarioRepository.save(usuarioFromDb);

        ra.addFlashAttribute("msgExito", "El usario se ha sido actualizado correctamente");

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/{id}/eliminar")
    String eliminar(@PathVariable Integer id, RedirectAttributes ra) {
        Usuario usuario = usuarioRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        usuarioRepository.delete(usuario);

        ra.addFlashAttribute("msgExito", "El usuario se ha sido eliminado correctamente");

        return "redirect:/admin/usuarios";
    }

}
