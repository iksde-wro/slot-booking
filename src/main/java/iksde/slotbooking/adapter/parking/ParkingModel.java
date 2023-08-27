package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.domain.SlotModel;
import lombok.RequiredArgsConstructor;

record ParkingModel(ParkingType type, ParkingSector sector, String desc, Long amount) {
    static ParkingModel of(SlotModel slot) {
        return new ParkingModel(
                ParkingType.valueOf(slot.getType()),
                ParkingSector.valueOf(slot.getSector()),
                String.format("Place %s for Parking is %s",
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
