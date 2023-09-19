package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.adapter.AbstractServiceApi;
import iksde.slotbooking.domain.SlotFacade;
import org.springframework.stereotype.Service;

@Service
class ParkingLogic extends AbstractServiceApi {
    public ParkingLogic(SlotFacade facade) {
        super(facade);
    }
}
