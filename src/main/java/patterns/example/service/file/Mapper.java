package patterns.example.service.file;

public interface Mapper {

    <T> T getInstanceFromString(String text, Class<T> cls);

    <T> String getStringFromInstance(T obj);
}
