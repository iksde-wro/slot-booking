package iksde.slotbooking.domain;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SlotSpecification implements Specification<SlotModel> {
    private final SlotModel slot;

    @Override
    public Predicate toPredicate(Root<SlotModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (slot.getType() != null) {
            predicates.add(criteriaBuilder.equal(root.get("type"), slot.getType()));
        }

        if (slot.getSector() != null) {
            predicates.add(criteriaBuilder.equal(root.get("sector"), slot.getSector()));
        }

        if (slot.getAmount() != null) {
            predicates.add(criteriaBuilder.equal(root.get("isAvailable"), slot.getAmount()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
