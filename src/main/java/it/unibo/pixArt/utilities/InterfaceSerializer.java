package it.unibo.pixArt.utilities;

import java.lang.reflect.Type;

import com.google.gson.*;

final class InterfaceSerializer<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private final Class<T> implementationClass;

    private InterfaceSerializer(final Class<T> implementationClass) {
        this.implementationClass = implementationClass;
    }

    static <T> InterfaceSerializer<T> interfaceSerializer(final Class<T> implementationClass) {
        return new InterfaceSerializer<>(implementationClass);
    }

    @Override
    public JsonElement serialize(final T value, final Type type, final JsonSerializationContext context) {
        final Type targetType = value != null 
                ? value.getClass() 
                : type;            
        return context.serialize(value, targetType);
    }

    @Override
    public T deserialize(final JsonElement jsonElement, final Type typeOfT, final JsonDeserializationContext context) {
        return context.deserialize(jsonElement, implementationClass);
    }

}