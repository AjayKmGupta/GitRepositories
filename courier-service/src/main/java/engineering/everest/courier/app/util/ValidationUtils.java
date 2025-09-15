package engineering.everest.courier.app.util;

import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Utility class containing validation helper methods.
 * <p>
 * Provides reusable static validation methods commonly used across the application.
 * Designed as a non-instantiable utility class.
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ValidationUtils {

    /**
     * Checks if a given collection is not null and contains at least one element.
     *
     * @param collection the collection to check, may be null
     * @param <T>        the type of elements in the collection
     * @return {@code true} if the collection is non-null and not empty, {@code false} otherwise
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }

}
