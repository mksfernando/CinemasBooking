package gic.cinema.input.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class CinemaInput<T> {
    private final Type type;

    public CinemaInput() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }

    public abstract String getInputMsg();

    public abstract T getValue();

    public abstract void setValue(T value);

    public abstract boolean isValid(Integer... conditions);

    public abstract String[] getErrorMsg();

    public List<CinemaInput<?>> getChildren(){
        return List.of();
    }
}
