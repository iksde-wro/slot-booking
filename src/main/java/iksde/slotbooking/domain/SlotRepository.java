package iksde.slotbooking.domain;

import iksde.slotbooking.port.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface SlotRepository extends JpaRepository<SlotEntity, Long>, JpaSpecificationExecutor<SlotEntity> {
    @Query("SELECT s FROM SlotEntity s WHERE s.type = :#{#slot.getType()} AND s.sector = :#{#slot.getSector()}")
    SlotEntity findBy(Slot slot);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM SlotEntity t " +
            "WHERE t.type = :#{#slot.getType()} " +
            "AND t.sector = :#{#slot.getSector()} ")
    boolean exists(Slot slot);
}
