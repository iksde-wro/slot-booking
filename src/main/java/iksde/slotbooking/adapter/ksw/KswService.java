package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.domain.Slot;
import iksde.slotbooking.domain.SlotRepository;
import iksde.slotbooking.port.SlotServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class KswService implements SlotServiceApi<KswModel> {
    private final SlotRepository repo;

    @Override
    public KswModel getSlot(Long id) {
        return KswModel.of(repo.getSlot(id));
    }


    public List<KswModel> getSlots(Slot slot) {
        return null;
    }

    @Override
    public KswModel saveSlot(Slot slot) {
        return null;
    }
}
