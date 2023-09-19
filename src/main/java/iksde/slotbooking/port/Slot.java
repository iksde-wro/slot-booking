package iksde.slotbooking.port;

import java.util.function.Function;

public interface Slot {
    String getSector();

    String getType();

    Long getAmount();

    default <T, U> U access(T t, Function<T, U> f1) {
        return t == null ? null : f1.apply(t);
    }
}
