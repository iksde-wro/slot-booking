package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.domain.Slot;
import lombok.RequiredArgsConstructor;

record ParkingModel(ParkingType type, ParkingSector sector, String desc, Boolean isAvailable) {
    static ParkingModel of(Slot slot) {
        return new ParkingModel(
                ParkingType.valueOf(slot.type()),
                ParkingSector.valueOf(slot.type()),
                String.format("Place %s for Parking is %s",
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
