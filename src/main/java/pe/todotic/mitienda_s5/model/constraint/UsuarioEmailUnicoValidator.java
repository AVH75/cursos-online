package pe.todotic.mitienda_s5.model.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pe.todotic.mitienda_s5.model.annotation.UsuarioEmailUnicoConstraint;
import pe.todotic.mitienda_s5.model.annotation.UsuarioPasswordEqualsConstraint;
import pe.todotic.mitienda_s5.repository.UsuarioRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsuarioEmailUnicoValidator implements ConstraintValidator<UsuarioEmailUnicoConstraint, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.hasText(email)) {
            return !usuarioRepository.existsByEmail(email);
        }
        return true;
    }

}
