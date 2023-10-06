package iksde.slotbooking.adapter;


import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotDTO;
import iksde.slotbooking.domain.SlotFacade;
import iksde.slotbooking.port.ServiceApi;
import iksde.slotbooking.port.Slot;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public abstract class AbstractServiceApi implements ServiceApi {
    private final SlotFacade facade;

    public Page<SlotDTO> get(Slot slot, Pageable pageable) throws SlotAlreadyExistException {
        return facade.findAll(slot, pageable);
    }

    public SlotDTO get(Long id) throws SlotNotFoundException {
        return facade.get(id);
    }

    public void delete(Long id) throws SlotNotFoundException, SlotAlreadyExistException {
        facade.delete(facade.get(id));
    }

    public SlotDTO reserve(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        SlotDTO dto = facade.findBy(slot);
        return facade.reserve(dto);
    }

    public SlotDTO cancel(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        SlotDTO dto = facade.findBy(slot);
        return facade.cancel(dto);
    }

    public SlotDTO save(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        return facade.save(slot);
    }
}
