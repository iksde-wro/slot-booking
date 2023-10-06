package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.port.Slot;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
public class ParkingModel implements Slot {
    @NotNull(message = "Type cannot be null")
    private final ParkingType type;
    @NotNull(message = "Sector cannot be null")
    private final ParkingSector sector;
    @NotNull(message = "Amount cannot be null")
    private final Long amount;

    static ParkingModel of(Slot slot) {
        return new ParkingModel(
                ParkingType.valueOf(slot.getType()),
                ParkingSector.valueOf(slot.getSector()),
                slot.getAmount());
    }

    private ParkingModel(ParkingType type, ParkingSector sector, Long amount) {
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
    enum ParkingType {
        STANDARD("Standard"),
        DISABLED("Disabled"),
        FAMILY("Family"),
        BICYCLE("Bicycle"),
        ELECTRIC("Electric"),
        BUS("Bus"),
        SHORT_TERM("Short Term"),
        LONG_TERM("Long Term"),
        ELECTRIC_CHARGING("Electric Charging"),
        CAR_SHARING("Car Sharing"),
        TRUCK("Truck"),
        CAMPER("Camper");

        private final String displayName;
    }

    enum ParkingSector {
        A,
        B,
        C,
        D,
        E,
        F,
        G
    }
}
