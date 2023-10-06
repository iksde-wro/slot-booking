package iksde.slotbooking.port;


import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceApi {
    void delete(Long id) throws SlotNotFoundException, SlotAlreadyExistException;

    SlotDTO get(Long id) throws SlotNotFoundException;

    SlotDTO save(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException;

    Page<SlotDTO> get(Slot slot, Pageable pageable) throws SlotAlreadyExistException;

    SlotDTO reserve(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException;

    SlotDTO cancel(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException;
}
