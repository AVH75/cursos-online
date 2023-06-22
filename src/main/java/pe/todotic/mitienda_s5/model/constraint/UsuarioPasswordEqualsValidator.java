package pe.todotic.mitienda_s5.model.constraint;

import org.springframework.util.StringUtils;
import pe.todotic.mitienda_s5.controller.form.RegistroUsuarioForm;
import pe.todotic.mitienda_s5.model.annotation.UsuarioPasswordEqualsConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioPasswordEqualsValidator implements ConstraintValidator<UsuarioPasswordEqualsConstraint, RegistroUsuarioForm> {

    @Override
    public void initialize(UsuarioPasswordEqualsConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RegistroUsuarioForm usuarioForm, ConstraintValidatorContext context) {
        if (!StringUtils.hasText(usuarioForm.getPassword1()) || !StringUtils.hasText(usuarioForm.getPassword2())) {
            return true;
        }
        return usuarioForm.getPassword1().equals(usuarioForm.getPassword2());
    }
}
