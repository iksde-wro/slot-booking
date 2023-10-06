package iksde.slotbooking.domain;

import iksde.slotbooking.port.Slot;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
public class SlotSimplifiedDTO implements Slot {
    private final String type;
    private final String sector;
    private final Long amount;

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
