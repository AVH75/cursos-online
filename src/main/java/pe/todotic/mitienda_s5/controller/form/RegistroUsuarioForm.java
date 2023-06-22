package pe.todotic.mitienda_s5.controller.form;

import lombok.Getter;
import lombok.Setter;
import pe.todotic.mitienda_s5.model.annotation.UsuarioEmailUnicoConstraint;
import pe.todotic.mitienda_s5.model.annotation.UsuarioPasswordEqualsConstraint;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@UsuarioPasswordEqualsConstraint
public class RegistroUsuarioForm {
    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @UsuarioEmailUnicoConstraint
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Transient
    private String password1;

    @NotBlank
    @Transient
    private String password2;
}
