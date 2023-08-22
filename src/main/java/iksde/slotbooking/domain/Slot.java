package iksde.slotbooking.domain;

public record Slot(Long id, String type, String sector, Boolean isAvailable) {
}
