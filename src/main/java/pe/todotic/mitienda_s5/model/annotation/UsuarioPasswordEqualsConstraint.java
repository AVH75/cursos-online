package pe.todotic.mitienda_s5.model.annotation;

import pe.todotic.mitienda_s5.model.constraint.UsuarioPasswordEqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UsuarioPasswordEqualsValidator.class})
@Target({TYPE})
@Retention(RUNTIME)
public @interface UsuarioPasswordEqualsConstraint {

    String message() default "Las contraseñas deben coincidir.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
