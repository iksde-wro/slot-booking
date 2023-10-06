package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.adapter.AbstractServiceApi;
import iksde.slotbooking.domain.SlotFacade;
import org.springframework.stereotype.Component;

@Component
class ParkingLogic extends AbstractServiceApi {
    public ParkingLogic(SlotFacade facade) {
        super(facade);
    }
}
