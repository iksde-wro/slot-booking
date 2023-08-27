package iksde.slotbooking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "slot")
@NoArgsConstructor
@Getter
public class SlotModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String type;
    private String sector;
    private Long amount;

    public SlotModel(String type, String sector, Long amount) {
        this.type = type;
        this.sector = sector;
        this.amount = amount;
    }

    public boolean isAvailable() {
        return 0L < amount;
    }
}
