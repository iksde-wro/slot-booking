package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.adapter.AbstractServiceApi;
import iksde.slotbooking.domain.SlotRepository;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

@Service
class KswServiceApi extends AbstractServiceApi {
    public KswServiceApi(SlotRepository repo, Validator validator) {
        super(repo, validator);
    }
}
