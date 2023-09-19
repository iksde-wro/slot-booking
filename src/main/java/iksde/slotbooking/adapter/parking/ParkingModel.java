package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.domain.SlotDTO;
import iksde.slotbooking.port.Slot;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

record ParkingModel(@NotNull ParkingType type,
                    @NotNull ParkingSector sector,
                    @NotNull String desc,
                    @NotNull Long amount) implements Slot {

    static ParkingModel of(SlotDTO slot) {
        return new ParkingModel(
                ParkingType.valueOf(slot.getType()),
                ParkingSector.valueOf(slot.getSector()),
                String.format("Place %s for Parking is %s",
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
