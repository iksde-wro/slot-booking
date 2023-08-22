package iksde.slotbooking.domain;

import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository {
    Slot getSlot(Long id);
}
