package iksde.slotbooking.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<SlotModel, Long>, JpaSpecificationExecutor<SlotModel> {
    @Query("SELECT s FROM SlotModel s WHERE s.type = :#{#slotX.type} AND s.sector = :#{#slotX.sector}")
    SlotModel findBy(SlotX slotX);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM SlotModel t " +
            "WHERE t.type = :#{#slotModel.type} " +
            "AND t.sector = :#{#slotModel.sector} ")
    boolean exists(SlotModel slotModel);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SlotModel s " +
            "WHERE s.type = :#{#slotX.type} " +
            "AND s.sector = :#{#slotX.sector}")
    boolean exists(SlotX slotX);
}
