package pe.todotic.mitienda_s5.controller.form;

import lombok.Getter;
import lombok.Setter;
import pe.todotic.mitienda_s5.model.Usuario;
import pe.todotic.mitienda_s5.model.annotation.UsuarioEmailUnicoConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AdminUsuarioForm {
    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @UsuarioEmailUnicoConstraint
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password1;

    private Usuario.Rol rol;
}
