package utils;

import models.EncodingSchemaE;
import models.Header;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HeaderUtils {

    public static List<EncodingSchemaE> getAcceptedEncodingSchemas(List<Header> headers) {
        return headers.stream()
                // [TODO] public enum for the std header names
                .filter(header -> "accept-encoding".equals(header.key))
                // [TODO] provide getter method in classes and use method reference instead of lambda expression
                .map(header -> header.value)
                .map(encodingSchemas -> encodingSchemas.split(","))
                .flatMap(Arrays::stream)
                .map(encodingSchema -> encodingSchema.trim())
                .map(EncodingSchemaE::constantOf)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static void updateHeaderValue(List<Header> headers, String key, String newValue) {
        headers.stream()
                .filter(header -> key.equalsIgnoreCase(header.key))
                .findFirst()
                .ifPresent(header -> header.value = newValue);
    }
}
