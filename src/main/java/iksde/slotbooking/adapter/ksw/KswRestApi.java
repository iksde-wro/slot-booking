package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotSearch;
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
class KswRestApi {
    private final KswLogic service;

    @GetMapping("{id}")
    private ResponseEntity<KswModel> get(@PathVariable Long id) throws SlotNotFoundException {
        return ResponseEntity.ok(KswModel.of(service.get(id)));
    }

    @PostMapping("search")
    private ResponseEntity<Page<KswModel>> search(@RequestBody KswModel kswModel, Pageable pageable) throws SlotAlreadyExistException {
        return ResponseEntity.ok((service.get(kswModel, pageable).map(KswModel::of)));
    }

    @PostMapping
    private ResponseEntity<KswModel> create(@RequestBody KswModel kswModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return new ResponseEntity<>(KswModel.of(service.save(kswModel)), HttpStatus.CREATED);
    }

    @PostMapping("reserve")
    private ResponseEntity<KswModel> reserve(@RequestBody SlotSearch slotSearch) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(KswModel.of(service.reserve(slotSearch)));
    }

    @PostMapping("cancel")
    private ResponseEntity<KswModel> cancel(@RequestBody SlotSearch slotSearch) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return ResponseEntity.ok(KswModel.of(service.cancel(slotSearch)));
    }

    @DeleteMapping("{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) throws SlotNotFoundException, SlotAlreadyExistException {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
