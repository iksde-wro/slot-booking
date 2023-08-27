package iksde.slotbooking.adapter.ksw;

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
@RequestMapping("ksw")
class KswController {
    private final KswServiceApi service;

    @GetMapping("{id}")
    ResponseEntity<KswModel> get(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(KswModel.of(service.get(id)));
    }

    @GetMapping
    ResponseEntity<Page<KswModel>> get(@RequestBody KswModel kswModel, @RequestBody Pageable pageable) {
        return ResponseEntity.ok((service.get(kswModel.toSlot(), pageable).map(KswModel::of)));
    }

    @PostMapping
    ResponseEntity<KswModel> create(@RequestBody KswModel kswModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return new ResponseEntity<>(KswModel.of(service.save(kswModel.toSlot())), HttpStatus.CREATED);
    }

    @PostMapping("reserve")
    ResponseEntity<KswModel> reserve(@RequestBody SlotX slotX) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(KswModel.of(service.reserve(slotX)));
    }

    @PostMapping("cancel")
    ResponseEntity<KswModel> cancel(@RequestBody SlotX slotX) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(KswModel.of(service.cancel(slotX)));
    }

    @DeleteMapping("{id}")
    ResponseEntity<KswModel> delete(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(KswModel.of(service.delete(id)));
    }
}
