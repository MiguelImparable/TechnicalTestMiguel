package Host.Validators;

import java.lang.reflect.Field;

public class ValidatorRequest<TEntity> {

    /**
     * Punto de entrada para validar un objeto.
     * - Si es primitivo o un envoltorio (Wrapper): valida que no sea nulo o vacío.
     * - Si es un objeto complejo: utiliza reflexión para validar que los campos no sean nulos o vacíos.
     *
     * @param request El objeto a validar.
     * @return true si es válido; false si no lo es.
     */
    public Boolean Validate(TEntity request) {
        if (request == null) {
            return false;
        }

        // Validar si es primitivo o un tipo envoltorio
        if (isPrimitiveOrWrapperOrString(request)) {
            return !isNullOrEmpty(request);
        }

        // Validar objetos complejos utilizando reflexión
        return validateComplexObject(request);
    }

    /**
     * Comprueba si el objeto es un tipo primitivo, envoltorio o String.
     *
     * @param obj El objeto a comprobar.
     * @return true si es primitivo, un envoltorio o una cadena; false en caso contrario.
     */
    private boolean isPrimitiveOrWrapperOrString(TEntity obj) {
        return obj instanceof String ||
               obj instanceof Number ||
               obj instanceof Boolean ||
               obj instanceof Character;
    }

    /**
     * Valida si un objeto primitivo o envoltorio está vacío o es nulo.
     *
     * @param obj El objeto a validar.
     * @return true si es nulo o vacío; false en caso contrario.
     */
    private boolean isNullOrEmpty(TEntity obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        return false; 
    }

    /**
     * Valida un objeto complejo asegurándose de que todos sus campos no sean nulos ni estén vacíos.
     *
     * @param obj El objeto complejo a validar.
     * @return true si todos los campos son válidos; false en caso contrario.
     */
    private boolean validateComplexObject(TEntity obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                TEntity value = (TEntity) field.get(obj);

                if (!isPrimitiveOrWrapperOrString(value) && value != null) {
                    if (!validateComplexObject(value)) {
                        return false;
                    }
                }

                if (isNullOrEmpty(value)) {
                    return false;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
