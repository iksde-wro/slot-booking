package iksde.slotbooking.adapter.parking;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("parking")
class ParkingController {
    private final ParkingService service;

    @GetMapping("{id}")
    ParkingModel getSlot(Long id) {
        return service.getSlot(id);
    }

    @GetMapping("{id}")
    List<ParkingModel> getSlots(ParkingModel parkingModel) {
        return service.getSlots(parkingModel.toSlot());
    }

    @PostMapping
    ParkingModel save(ParkingModel parkingModel) {
        return service.saveSlot(parkingModel.toSlot());
    }

    @DeleteMapping
    ParkingModel delete(ParkingModel parkingModel) {
        return service.saveSlot(parkingModel.toSlot());
    }
}
