package patterns.example.service.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper implements Mapper {

    public static final JsonMapper INSTANCE = new JsonMapper();
    private ObjectMapper objectMapper;

    private JsonMapper() {
        this.objectMapper = getDefaultObjectMapper();
    }

    @Override
    public <T> T getInstanceFromString(String json, Class<T> cls) {
        try {
            JsonNode node = parseToJsonNode(json);
            return getInstanceFromJsonNode(node, cls);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> String getStringFromInstance(T obj) {
        try {
            return objectMapper.writer().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode parseToJsonNode(String src) throws JsonProcessingException {
        try {
            return objectMapper.readTree(src);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getInstanceFromJsonNode(JsonNode node, Class<T> cls) throws JsonProcessingException {
        return objectMapper.treeToValue(node, cls);
    }

    private ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        // todo: object mapper config
        return defaultObjectMapper;
    }
}
