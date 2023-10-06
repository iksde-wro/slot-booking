package iksde.slotbooking.domain;

import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.port.Slot;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlotFacade {
    private final SlotRepository repo;
    private final SlotValidator valid;

    public Page<SlotDTO> findAll(Slot slot, Pageable pageable) throws SlotAlreadyExistException {
        valid.notExist(slot, repo);
        SlotEntity entity = new SlotEntity(slot.getAmount(), slot.getType(), slot.getSector());
        return repo.findAll(new SlotSpecification(entity), pageable).map(this::entityToDto);
    }

    public SlotDTO get(Long id) throws SlotNotFoundException {
        return entityToDto(repo.findById(id).orElseThrow(() -> new SlotNotFoundException(String.format("Not found Slot with ID %s", id))));
    }

    private SlotDTO entityToDto(SlotEntity entity) {
        return new SlotDTO(entity.getId(), entity.getAmount(), entity.getType(), entity.getSector());
    }

    public void delete(SlotDTO slot) throws SlotAlreadyExistException {
        valid.notExist(slot, repo);
        repo.delete(dtoToEntity(slot));
    }

    private SlotEntity dtoToEntity(SlotDTO dto) {
        return new SlotEntity(dto.id(), dto.getAmount(), dto.getType(), dto.getSector());
    }

    public SlotDTO findBy(Slot slot) throws SlotAlreadyExistException {
        valid.notExist(slot, repo);
        return entityToDto(repo.findBy(slot));
    }

    public SlotDTO save(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        valid.isEmptyField(slot);
        valid.exist(slot, repo);

        return entityToDto(repo.save(new SlotEntity(
                slot.getAmount(),
                slot.getType(),
                slot.getSector()
        )));
    }

    public SlotDTO reserve(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        validAndEnsureAvailability(slot);

        repo.delete(new SlotEntity(
                slot.getAmount(),
                slot.getType(),
                slot.getSector()
        ));


        return entityToDto(repo.save(new SlotEntity(
                slot.getAmount() - 1L,
                slot.getType(),
                slot.getSector()
        )));
    }

    private void validAndEnsureAvailability(Slot slot) throws SlotHasEmptyFieldException, SlotAlreadyExistException {
        valid.isEmptyField(slot);
        valid.notExist(slot, repo);
        valid.hasNotFreeSeat(slot);
    }

    public SlotDTO cancel(Slot slot) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        validAndEnsureAvailability(slot);

        repo.delete(new SlotEntity(
                slot.getAmount(),
                slot.getType(),
                slot.getSector()
        ));

        return entityToDto(repo.save(new SlotEntity(
                slot.getAmount() + 1L,
                slot.getType(),
                slot.getSector()
        )));
    }
}
