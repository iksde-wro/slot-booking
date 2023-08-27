package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.domain.SlotModel;
import lombok.RequiredArgsConstructor;

record KswModel(KswType type, KswSector sector, String desc, Long amount) {
    static KswModel of(SlotModel slot) {
        return new KswModel(
                KswType.valueOf(slot.getType()),
                KswSector.valueOf(slot.getSector()),
                String.format("Place %s for KSW is %s",
                        slot.getType(),
                        slot.isAvailable() ? "available" : "not available"),
                slot.getAmount());
    }

    SlotModel toSlot() {
        return new SlotModel(
                type.name(),
                sector.name(),
                amount);
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
