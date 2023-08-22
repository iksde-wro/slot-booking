package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.domain.Slot;
import iksde.slotbooking.domain.SlotRepository;
import iksde.slotbooking.port.SlotServiceApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class ParkingService implements SlotServiceApi<ParkingModel> {
    private final SlotRepository repo;

    @Override
    public ParkingModel getSlot(Long id) {
        return ParkingModel.of(repo.getSlot(id));
    }

    @Override
    public List<ParkingModel> getSlots(Slot slot) {
        return null;
    }

    @Override
    public ParkingModel saveSlot(Slot slot) {
        return null;
    }
}
