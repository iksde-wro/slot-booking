package iksde.slotbooking.adapter.parking;

import iksde.slotbooking.domain.SlotDTO;
import iksde.slotbooking.domain.SlotSimplifiedDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:parking.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingRestApiTest {
    private final static SlotSimplifiedDTO slot = new SlotSimplifiedDTO(ParkingModel.ParkingType.BUS.name(), ParkingModel.ParkingSector.A.name(), 1L);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void createSlotParkingTest() {
        ParkingModel parkingModel = ParkingModel.of(new SlotDTO(1L, 2L, ParkingModel.ParkingType.BICYCLE.name(), ParkingModel.ParkingSector.D.name()));
        ResponseEntity<ParkingModel> response = restTemplate.postForEntity("/parking", parkingModel, ParkingModel.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isEqualTo(parkingModel);
    }

    @Test
    @DirtiesContext
    void getSlotParkingTest() {
        ResponseEntity<ParkingModel> responseEntity = restTemplate.getForEntity("/parking/100", ParkingModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(2L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("BUS");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }

    @Test
    @DirtiesContext
    void reserveSlotParkingTest() {
        ResponseEntity<ParkingModel> responseEntity = restTemplate.postForEntity("/parking/reserve", slot, ParkingModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(1L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("BUS");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }

    @Test
    @DirtiesContext
    void cancelSlotParkingTest() {
        ResponseEntity<ParkingModel> responseEntity = restTemplate.postForEntity("/parking/cancel", slot, ParkingModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(3L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("BUS");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }
}
