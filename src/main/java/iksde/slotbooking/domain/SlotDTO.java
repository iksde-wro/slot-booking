package iksde.slotbooking.domain;

import iksde.slotbooking.port.Slot;

public record SlotDTO(Long id, Long amount, String type, String sector) implements Slot {
    @Override
    public String getSector() {
        return sector;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Long getAmount() {
        return amount;
    }
}
