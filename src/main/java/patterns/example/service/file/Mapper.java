package patterns.example.service.file;

import com.fasterxml.jackson.core.type.TypeReference;

public interface Mapper {

    <T> T getInstanceFromString(String text, Class<T> cls);
    <T> String getStringFromInstance(T obj);
    <T> T getInstanceFromString(String text, TypeReference<T> type);
}
