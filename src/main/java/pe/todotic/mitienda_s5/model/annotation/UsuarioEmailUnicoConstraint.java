package pe.todotic.mitienda_s5.model.annotation;

import pe.todotic.mitienda_s5.model.constraint.UsuarioEmailUnicoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UsuarioEmailUnicoValidator.class})
@Target({FIELD, METHOD})
@Retention(RUNTIME)
public @interface UsuarioEmailUnicoConstraint {

    String message() default "El email ya está siendo usado por alguien más.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
