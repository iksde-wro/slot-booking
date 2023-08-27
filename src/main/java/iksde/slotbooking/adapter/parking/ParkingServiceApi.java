package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.adapter.AbstractServiceApi;
import iksde.slotbooking.domain.SlotRepository;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

@Service
class ParkingServiceApi extends AbstractServiceApi {
    public ParkingServiceApi(SlotRepository repo, Validator validator) {
        super(repo, validator);
    }
}
