package sephora.cartcheckout.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.file.Paths;

public class JsonUtils {
    public static <T> T getJsonAsObject(T dataTransferObject, String pathToFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Assertions.assertNotNull(dataTransferObject);
        return (T) mapper.readValue(
                Paths.get(pathToFile).toFile(),
                dataTransferObject.getClass());

    }
}
