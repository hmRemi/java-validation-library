package dev.revere.validation;

import dev.revere.validation.annotations.NotNull;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.exceptions.ConstraintViolationException;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Remi
 * @project java-validation-library
 * @date 8/19/2024
 */
public class ConstraintProcessor {

    private final Map<Class<? extends Annotation>, ConstraintFactory<?>> constraintFactories = new HashMap<>();

    public ConstraintProcessor() {
        registerDefaultFactories();
    }

    @SuppressWarnings("rawtypes")
    private void registerDefaultFactories() {
        Reflections reflections = new Reflections("dev.revere.validation.constraints.factories");
        Set<Class<? extends ConstraintFactory>> factoryClasses = reflections.getSubTypesOf(ConstraintFactory.class);
        for (Class<? extends ConstraintFactory> factoryClass : factoryClasses) {
            try {
                ConstraintFactory<?> factory = factoryClass.getDeclaredConstructor().newInstance();
                Class<? extends Annotation> annotationType = factory.getAnnotationType();
                if (annotationType != null) {
                    constraintFactories.put(annotationType, factory);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to instantiate constraint factory: " + factoryClass.getName(), e);
            }
        }
    }

    /**
     * Registers a custom constraint factory for a specific annotation type.
     *
     * <p>Users can extend the library by registering their own constraint factories.
     * This allows adding new constraints without modifying the core library code.</p>
     *
     * @param annotationClass The annotation class associated with the constraint.
     * @param factory         The factory that creates constraints for the given annotation.
     * @param <A>             The type of the annotation.
     */
    public <A extends Annotation> void registerFactory(Class<A> annotationClass, ConstraintFactory<A> factory) {
        constraintFactories.put(annotationClass, factory);
    }

    /**
     * Applies constraints to the fields of the given object instance.
     *
     * <p>This method iterates over all fields of the object, checks for annotations,
     * and applies the appropriate constraints. If a field's value does not meet the
     * constraint criteria, a {@link ConstraintViolationException} is thrown.</p>
     *
     * @param instance The object instance to validate.
     * @throws ConstraintViolationException If a field's value violates a constraint.
     * @throws IllegalAccessException       If there is an error accessing a field's value.
     */
    public <T> void applyConstraints(T instance) throws ConstraintViolationException, IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(instance);
            validateFieldConstraints(field, value);
        }
    }

    /**
     * Validates constraints on a specific field of an object.
     *
     * <p>This method checks all annotations on the field, retrieves the associated
     * constraint factory, creates a constraint, and then validates the field's value
     * against this constraint.</p>
     *
     * @param field The field to validate.
     * @param value The value of the field.
     * @throws ConstraintViolationException If the field's value does not meet the constraint.
     */
    private void validateFieldConstraints(Field field, Object value) throws ConstraintViolationException {
        for (Annotation annotation : field.getAnnotations()) {
            ConstraintFactory<?> factory = constraintFactories.get(annotation.annotationType());
            if (factory == null) {
                continue;
            }

            Constraint<?> constraint = createConstraint(annotation);
            validateFieldValue(field, value, constraint);
        }
    }

    /**
     * Validates a field's value against a given constraint.
     *
     * <p>This method checks if the field's value matches the constraint type and applies
     * the constraint validation. If the value is null and the field has a {@link NotNull}
     * annotation, a {@link ConstraintViolationException} is thrown.</p>
     *
     * @param field      The field to validate.
     * @param value      The value of the field.
     * @param constraint The constraint to apply.
     * @throws ConstraintViolationException If the field's value does not meet the constraint or if the field is null when it shouldn't be.
     */
    @SuppressWarnings("unchecked")
    private void validateFieldValue(Field field, Object value, Constraint<?> constraint) throws ConstraintViolationException {
        if (value == null) {
            if (isNotNullAnnotation(field.getAnnotations())) {
                throw new ConstraintViolationException("Field " + field.getName() + " cannot be null");
            }
            return;
        }

        if (!field.getType().isInstance(value) || !constraint.getType().isInstance(value)) {
            throw new ConstraintViolationException("Field type mismatch for field " + field.getName());
        }

        ((Constraint<Object>) constraint).validate(value);
    }

    /**
     * Checks if a field has a {@link NotNull} annotation.
     *
     * <p>This method iterates over the field's annotations to determine if it has
     * the {@link NotNull} annotation, which requires the field's value to be non-null.</p>
     *
     * @param annotations The annotations present on the field.
     * @return True if the field has a {@link NotNull} annotation, false otherwise.
     */
    private boolean isNotNullAnnotation(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(NotNull.class)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a constraint instance based on the provided annotation.
     *
     * <p>This method uses the appropriate constraint factory registered for the annotation's type
     * to create a constraint instance.</p>
     *
     * @param annotation The annotation to create a constraint for.
     * @param <A>        The type of the annotation.
     * @return The created constraint instance.
     * @throws IllegalArgumentException If no factory is registered for the annotation's type.
     */
    @SuppressWarnings("unchecked")
    public <A extends Annotation> Constraint<?> createConstraint(A annotation) {
        ConstraintFactory<A> factory = (ConstraintFactory<A>) constraintFactories.get(annotation.annotationType());
        if (factory != null) {
            return factory.create(annotation);
        }
        throw new IllegalArgumentException("No constraint factory registered for annotation: " + annotation.annotationType().getName());
    }
}