package dev.revere.validation;

import dev.revere.validation.annotations.*;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.factories.*;
import dev.revere.validation.exceptions.ConstraintViolationException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ConstraintProcessor {

    private final Map<Class<? extends Annotation>, ConstraintFactory<?>> constraintFactories = new HashMap<>();

    public ConstraintProcessor() {
        constraintFactories.put(FutureDate.class, new FutureDateConstraintFactory());
        constraintFactories.put(MinLength.class, new MinLengthConstraintFactory());
        constraintFactories.put(MaxLength.class, new MaxLengthConstraintFactory());
        constraintFactories.put(MaxValue.class, new MaxValueConstraintFactory());
        constraintFactories.put(MinValue.class, new MinValueConstraintFactory());
        constraintFactories.put(NotEmpty.class, new NotEmptyConstraintFactory());
        constraintFactories.put(PastDate.class, new PastDateConstraintFactory());
        constraintFactories.put(Pattern.class, new PatternConstraintFactory());
        constraintFactories.put(NotNull.class, new NotNullConstraintFactory());
        constraintFactories.put(Range.class, new RangeConstraintFactory());
        constraintFactories.put(Size.class, new SizeConstraintFactory());
    }

    @SuppressWarnings({"unchecked"})
    public <T> void applyConstraints(T instance) throws ConstraintViolationException, IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(instance);

            for (Annotation annotation : field.getAnnotations()) {
                ConstraintFactory<?> factory = constraintFactories.get(annotation.annotationType());
                if (factory != null) {
                    Constraint<?> constraint = createConstraint(annotation);
                    if (value != null) {
                        if (field.getType().isInstance(value) && constraint.getType().isInstance(value)) {
                            ((Constraint<Object>) constraint).validate(value);
                        } else {
                            throw new ConstraintViolationException("Field type mismatch for field " + field.getName());
                        }
                    } else if (annotation.annotationType().equals(NotNull.class)) {
                        throw new ConstraintViolationException("Field " + field.getName() + " cannot be null");
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <A extends Annotation> Constraint<?> createConstraint(A annotation) {
        ConstraintFactory<A> factory = (ConstraintFactory<A>) constraintFactories.get(annotation.annotationType());
        if (factory != null) {
            return factory.create(annotation);
        }
        throw new IllegalArgumentException("No constraint factory registered for annotation: " + annotation.annotationType().getName());
    }
}