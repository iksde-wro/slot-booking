package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotX;
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
class ParkingController {
    private final ParkingServiceApi service;

    @GetMapping("{id}")
    ResponseEntity<ParkingModel> get(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(ParkingModel.of(service.get(id)));
    }

    @GetMapping
    ResponseEntity<Page<ParkingModel>> get(@RequestBody ParkingModel parkingModel, @RequestBody Pageable pageable) {
        return ResponseEntity.ok((service.get(parkingModel.toSlot(), pageable).map(ParkingModel::of)));
    }

    @PostMapping
    ResponseEntity<ParkingModel> create(@RequestBody ParkingModel parkingModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return new ResponseEntity<>(ParkingModel.of(service.save(parkingModel.toSlot())), HttpStatus.CREATED);
    }

    @PostMapping("reserve")
    ResponseEntity<ParkingModel> reserve(@RequestBody SlotX slotX) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(ParkingModel.of(service.reserve(slotX)));
    }

    @PostMapping("cancel")
    ResponseEntity<ParkingModel> cancel(@RequestBody SlotX slotX) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(ParkingModel.of(service.cancel(slotX)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<ParkingModel> delete(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(ParkingModel.of(service.delete(id)));
    }
}
