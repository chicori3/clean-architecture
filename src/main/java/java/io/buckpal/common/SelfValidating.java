package java.io.buckpal.common;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidating<T> {

    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator;

    public SelfValidating() {
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
