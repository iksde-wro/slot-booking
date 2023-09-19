package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.adapter.AbstractServiceApi;
import iksde.slotbooking.domain.SlotFacade;
import org.springframework.context.annotation.Configuration;

@Configuration
class KswLogic extends AbstractServiceApi {
    public KswLogic(SlotFacade facade) {
        super(facade);
    }
}
