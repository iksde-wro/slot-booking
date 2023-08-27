package iksde.slotbooking.adapter;


import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.config.exception.SlotNotFoundException;
import iksde.slotbooking.domain.SlotModel;
import iksde.slotbooking.domain.SlotRepository;
import iksde.slotbooking.domain.SlotSpecification;
import iksde.slotbooking.domain.SlotX;
import iksde.slotbooking.port.Slot;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstractServiceApi implements Slot {
    private final SlotRepository repo;
    private final Validator validator;

    protected AbstractServiceApi(SlotRepository repo, Validator validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public Page<SlotModel> get(SlotModel slot, Pageable pageable) {
        return repo.findAll(new SlotSpecification(slot), pageable);
    }

    public SlotModel get(Long id) throws SlotNotFoundException {
        return repo.findById(id).orElseThrow(() -> new SlotNotFoundException(String.format("Not found Slot with ID %s", id)));
    }

    public SlotModel delete(Long id) throws SlotNotFoundException {
        SlotModel slotModel = get(id);
        repo.delete(slotModel);
        return slotModel;
    }

    public SlotModel reserve(SlotX slotx) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        isExists(slotx);
        SlotModel slotModel = repo.findBy(slotx);
        isFreeSlot(slotModel);

        //todo co robi
        repo.delete(slotModel);
        return save(new SlotModel(slotModel.getType(), slotModel.getSector(), slotModel.getAmount() - 1L));
    }

    public SlotModel cancel(SlotX slotx) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        isExists(slotx);
        SlotModel slotModel = repo.findBy(slotx);
        repo.delete(slotModel);
        return save(new SlotModel(slotModel.getType(), slotModel.getSector(), slotModel.getAmount() + 1L));
    }

    private void isExists(SlotX slotx) throws SlotAlreadyExistException {
        if (!repo.exists(slotx))
            throw new SlotAlreadyExistException(String.format("This slot %s sector %s is already in the list.",
                    slotx.type(),
                    slotx.sector()));
    }

    private void isFreeSlot(SlotModel slotModel) throws SlotAlreadyExistException {
        if (!slotModel.isAvailable())
            //todo
            throw new SlotAlreadyExistException(String.format("todo"));
    }

    @Override
    public SlotModel save(SlotModel slotModel) throws SlotAlreadyExistException, SlotHasEmptyFieldException {
        if (repo.exists(slotModel))
            throw new SlotAlreadyExistException(String.format("This slot %s sector %s is already in the list.",
                    slotModel.getType(),
                    slotModel.getSector()));

        Set<ConstraintViolation<SlotModel>> violations = validator.validate(slotModel);

        if (!violations.isEmpty()) {
            throw new SlotHasEmptyFieldException(violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }

        return repo.save(slotModel);
    }
}
