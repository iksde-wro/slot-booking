package iksde.slotbooking.port;


import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotModel;
import iksde.slotbooking.domain.SlotX;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Slot {
    SlotModel delete(Long id) throws SlotNotFoundException;

    SlotModel get(Long id) throws SlotNotFoundException;

    SlotModel save(SlotModel slotModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException;

    Page<SlotModel> get(SlotModel slot, Pageable pageable);

    SlotModel reserve(SlotX slotx) throws SlotAlreadyExistException, SlotHasEmptyFieldException;

    SlotModel cancel(SlotX slotX) throws SlotAlreadyExistException, SlotHasEmptyFieldException;
}
