package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("parking")
class ParkingRestApi {
    private final ParkingLogic service;

    @GetMapping("{id}")
    private ResponseEntity<ParkingModel> get(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(ParkingModel.of(service.get(id)));
    }

    @PostMapping("search")
    private ResponseEntity<Page<ParkingModel>> search(@RequestBody ParkingModel parkingModel, Pageable pageable) throws SlotAlreadyExistException {
        return ResponseEntity.ok((service.get(parkingModel, pageable).map(ParkingModel::of)));
    }

    @PostMapping
    private ResponseEntity<ParkingModel> create(@RequestBody ParkingModel parkingModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return new ResponseEntity<>(ParkingModel.of(service.save(parkingModel)), HttpStatus.CREATED);
    }

    @PostMapping("reserve")
    private ResponseEntity<ParkingModel> reserve(@RequestBody ParkingModel parkingModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(ParkingModel.of(service.reserve(parkingModel)));
    }

    @PostMapping("cancel")
    private ResponseEntity<ParkingModel> cancel(@RequestBody ParkingModel parkingModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(ParkingModel.of(service.cancel(parkingModel)));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) throws SlotNotFoundException, SlotAlreadyExistException {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
