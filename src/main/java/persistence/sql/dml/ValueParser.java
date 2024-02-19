package persistence.sql.dml;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.lang.reflect.Field;

public class ValueParser {

    public static String parse(Field field, Object object) {
        if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
            final H2KeyGenerator h2KeyGenerator = new H2KeyGenerator();
            return h2KeyGenerator.generator(field.getAnnotation(GeneratedValue.class).strategy());
        }

        field.setAccessible(true);
        Object value;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        if (field.getType().equals(String.class)) {
            return String.format("'%s'", value);
        }

        return String.valueOf(value);
    }
}
