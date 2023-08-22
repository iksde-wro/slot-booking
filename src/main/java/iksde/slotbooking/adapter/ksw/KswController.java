package iksde.slotbooking.adapter.ksw;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("ksw")
class KswController {
    private final KswService service;

    @GetMapping
    KswModel getSlot(Long id) {
        return service.getSlot(id);
    }

    @GetMapping
    List<KswModel> getSlots(KswModel kswModel) {
        return service.getSlots(kswModel.toSlot());
    }

    @PostMapping
    KswModel save(KswModel kswModel) {
        return service.saveSlot(kswModel.toSlot());
    }

    @DeleteMapping
    KswModel delete(KswModel kswModel) {
        return service.saveSlot(kswModel.toSlot());
    }
}
