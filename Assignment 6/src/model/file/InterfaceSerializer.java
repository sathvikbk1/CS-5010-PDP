package model.file;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;


/**
 * These helper functions are used to inform the GSON library on which implementation
 * of the interface to serialize or deserialize when the class contains interface variables.
 */
public final class InterfaceSerializer<T>
        implements JsonSerializer<T>, JsonDeserializer<T> {

  private final Class<T> implementationClass;

  private InterfaceSerializer(final Class<T> implementationClass) {
    this.implementationClass = implementationClass;
  }

  public static <T> InterfaceSerializer<T> interfaceSerializer(
          final Class<T> implementationClass) {
    return new InterfaceSerializer<>(implementationClass);
  }

  @Override
  public JsonElement serialize(
          final T value, final Type type, final JsonSerializationContext context) {
    final Type targetType = value != null
            ? value.getClass()
            : type;
    return context.serialize(value, targetType);
  }


  public T deserialize(
          final JsonElement jsonElement, final Type typeOfT,
          final JsonDeserializationContext context) {
    return context.deserialize(jsonElement, implementationClass);
  }


}
