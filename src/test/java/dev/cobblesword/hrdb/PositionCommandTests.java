package dev.cobblesword.hrdb;

import dev.cobblesword.hrdb.responses.*;
import dev.cobblesword.hrdb.services.PositionService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionCommandTests
{
    @Test
    public void whenCreatePosition_thenCorrect()
    {
        PositionService positionService = new PositionService();

        PositionCreateRequest positionCreateRequest = new PositionCreateRequest();
        positionCreateRequest.title = "Software Engineer";
        positionCreateRequest.payRate = 100;
        positionCreateRequest.payRateType = PayRateType.HOURLY;
        // To do entitlments ( Terms & conditions )
//        positionCreateRequest.workPattern = null;

        PositionCreatedResponse positionCreatedResponse = positionService.createPosition(positionCreateRequest);

        assertThat(positionCreatedResponse).isNotNull();
        assertThat(positionCreatedResponse.isSuccessful()).isTrue();
        assertThat(positionCreatedResponse.positionId).isNotNull();
    }

    @Test
    public void whenPositionIsSet_thenPositionIsSet()
    {
        PositionService positionService = new PositionService();

        PositionCreateRequest positionCreateRequest = new PositionCreateRequest();
        positionCreateRequest.title = "Software Engineer";
        positionCreateRequest.payRate = 100;
        positionCreateRequest.payRateType = PayRateType.HOURLY;
        PositionCreatedResponse positionCreatedResponse = positionService.createPosition(positionCreateRequest);

        PositionFillRequest positionFillRequest = new PositionFillRequest();
        positionFillRequest.humanId = UUID.randomUUID();
        positionFillRequest.positionId = positionCreatedResponse.positionId;
        positionFillRequest.startDate =  LocalDate.of(1990, 1, 1);
        positionFillRequest.finishDate =  LocalDate.of(1990, 5, 1);
        positionFillRequest.capacity = 100;

        PositionFillResponse positionFillResponse = positionService.fillPosition(positionFillRequest);

        assertThat(positionFillResponse).isNotNull();
        assertThat(positionFillResponse.isSuccessful()).isTrue();
        assertThat(positionFillResponse.positionId).isEqualTo(positionFillRequest.positionId);
        assertThat(positionFillResponse.humanId).isEqualTo(positionFillRequest.humanId);
    }
}
