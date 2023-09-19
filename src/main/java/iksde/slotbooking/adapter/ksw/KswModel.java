package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.domain.SlotDTO;
import iksde.slotbooking.port.Slot;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

record KswModel(@NotNull(message = "Type cannot be null") KswType type,
                @NotNull(message = "Sector cannot be null") KswSector sector,
                @NotNull(message = "Description cannot be null") String desc,
                @NotNull(message = "Amount cannot be null") Long amount) implements Slot {

    static KswModel of(SlotDTO slot) {
        return new KswModel(
                KswType.valueOf(slot.getType()),
                KswSector.valueOf(slot.getSector()),
                String.format("Place %s for KSW is %s",
                        slot.getType(),
                        slot.getAmount() <= 0L ? "not available" : "available"),
                slot.getAmount());
    }

    @Override
    public String getSector() {
        return access(sector, Enum::name);
    }

    @Override
    public String getType() {
        return access(type, Enum::name);
    }

    @Override
    public Long getAmount() {
        return amount;
    }

    @RequiredArgsConstructor
    enum KswType {
        TRIBUNE("Tribune"),
        FIELD("Field"),
        VIP("VIP Area"),
        STAGE("Stage Area"),
        GENERAL("General Area");

        private final String displayName;
    }

    enum KswSector {
        A,
        B,
        C,
        D,
        E,
        F,
        G
    }
}
