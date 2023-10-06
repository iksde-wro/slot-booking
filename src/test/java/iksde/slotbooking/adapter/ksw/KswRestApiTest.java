package iksde.slotbooking.adapter.ksw;

import iksde.slotbooking.domain.SlotDTO;
import iksde.slotbooking.domain.SlotSimplifiedDTO;
import iksde.slotbooking.port.Slot;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:ksw.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class KswRestApiTest {
    private final static Slot slot = new SlotSimplifiedDTO(KswModel.KswType.VIP.name(), KswModel.KswSector.A.name(), 1L);

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    void createSlotKswTest() {
        KswModel kswModel = KswModel.of(new SlotDTO(1L, 2L, KswModel.KswType.TRIBUNE.name(), KswModel.KswSector.D.name()));
        ResponseEntity<KswModel> response = restTemplate.postForEntity("/ksw", kswModel, KswModel.class);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(response.getBody()).isEqualTo(kswModel);
    }

    @Test
    @DirtiesContext
    void getSlotKswTest() {
        ResponseEntity<KswModel> responseEntity = restTemplate.getForEntity("/ksw/100", KswModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(2L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("VIP");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }

    @Test
    @DirtiesContext
    void reserveSlotKswTest() {
        ResponseEntity<KswModel> responseEntity = restTemplate.postForEntity("/ksw/reserve", slot, KswModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(1L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("VIP");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }

    @Test
    @DirtiesContext
    void cancelSlotKswTest() {
        ResponseEntity<KswModel> responseEntity = restTemplate.postForEntity("/ksw/cancel", slot, KswModel.class);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody().getAmount()).isEqualTo(3L);
        Assertions.assertThat(responseEntity.getBody().getType()).isEqualTo("VIP");
        Assertions.assertThat(responseEntity.getBody().getSector()).isEqualTo("A");
    }
}
