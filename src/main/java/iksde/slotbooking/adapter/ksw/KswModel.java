package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.domain.Slot;
import lombok.RequiredArgsConstructor;

record KswModel(KswType type, KswSector sector, String desc, Boolean isAvailable) {
    static KswModel of(Slot slot) {
        return new KswModel(
                KswType.valueOf(slot.type()),
                KswSector.valueOf(slot.type()),
                String.format("Place %s for KSW is %s",
                        slot.type(),
                        slot.isAvailable() ? "available" : "not available"),
                slot.isAvailable());
    }

    Slot toSlot() {
        return new Slot(
                null,
                type.name(),
                sector.name(),
                isAvailable);
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
