package iksde.slotbooking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "slot")
@Getter
@RequiredArgsConstructor
class SlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long amount;

    private String type;
    private String sector;

    public SlotEntity(Long amount, String type, String sector) {
        this.amount = amount;
        this.type = type;
        this.sector = sector;
    }

    public SlotEntity(Long id, Long amount, String type, String sector) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.sector = sector;
    }
}
