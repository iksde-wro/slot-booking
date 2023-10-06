package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.port.Slot;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
public class KswModel implements Slot {
    @NotNull(message = "Type cannot be null")
    private final KswType type;
    @NotNull(message = "Sector cannot be null")
    private final KswSector sector;
    @NotNull(message = "Amount cannot be null")
    private final Long amount;

    static KswModel of(Slot slot) {
        return new KswModel(
                KswType.valueOf(slot.getType()),
                KswSector.valueOf(slot.getSector()),
                slot.getAmount());
    }

    private KswModel(KswType type, KswSector sector, Long amount) {
        this.type = type;
        this.sector = sector;
        this.amount = amount;
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
