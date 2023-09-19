package iksde.slotbooking.domain;

import iksde.slotbooking.port.Slot;

public record SlotSearch(String type, String sector, Long amount) implements Slot {
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
