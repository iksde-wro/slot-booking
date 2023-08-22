package iksde.slotbooking.port;


import iksde.slotbooking.domain.Slot;

import java.util.List;

public interface SlotServiceApi<T> {
    T getSlot(Long id);

    List<T> getSlots(Slot slot);

    T saveSlot(Slot slot);
}
