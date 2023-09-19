package iksde.slotbooking.domain;

import iksde.slotbooking.config.exception.SlotAlreadyExistException;
import iksde.slotbooking.config.exception.SlotHasEmptyFieldException;
import iksde.slotbooking.port.Slot;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SlotValidator {
    private final Validator validator;

    public void isEmptyField(Slot slot) throws SlotHasEmptyFieldException {
        Set<ConstraintViolation<Slot>> violations = validator.validate(slot);
        if (!violations.isEmpty()) {
            throw new SlotHasEmptyFieldException(violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n")));
        }
    }

    public void exist(Slot slot, SlotRepository repo) throws SlotAlreadyExistException {
        if (repo.exists(slot))
            throw new SlotAlreadyExistException(String.format("This slot %s sector %s is already in the list.",
                    slot.getType(),
                    slot.getSector()));
    }

    public void notExist(Slot slot, SlotRepository repo) throws SlotAlreadyExistException {
        if (!repo.exists(slot))
            throw new SlotAlreadyExistException(String.format("This slot %s sector %s is not in the list.",
                    slot.getType(),
                    slot.getSector()));
    }

    public void hasNotFreeSeat(Slot slot) throws SlotAlreadyExistException {
        if (slot.getAmount()<=0L)
            throw new SlotAlreadyExistException(String.format("This slot %s sector %s has no vacancies",
                    slot.getType(),
                    slot.getSector()));
    }
}
