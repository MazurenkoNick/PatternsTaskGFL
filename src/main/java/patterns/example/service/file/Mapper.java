package patterns.example.service.file;

public interface Mapper {

    <T> T getInstanceFromString(String json, Class<T> cls);

    <T> String format(T obj);
}
