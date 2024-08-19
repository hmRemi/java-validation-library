package dev.revere.validation;

import dev.revere.validation.annotations.*;
import dev.revere.validation.constraints.Constraint;
import dev.revere.validation.constraints.factories.*;
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
     * @param factory The factory that creates constraints for the given annotation.
     * @param <A> The type of the annotation.
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
     * @throws IllegalAccessException If there is an error accessing a field's value.
     */
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

    /**
     * Creates a constraint instance based on the provided annotation.
     *
     * <p>This method uses the appropriate constraint factory registered for the annotation's type
     * to create a constraint instance.</p>
     *
     * @param annotation The annotation to create a constraint for.
     * @param <A> The type of the annotation.
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