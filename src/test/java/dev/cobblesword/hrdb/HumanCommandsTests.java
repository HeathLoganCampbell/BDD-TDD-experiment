package dev.cobblesword.hrdb;

import dev.cobblesword.hrdb.responses.*;
import dev.cobblesword.hrdb.responses.onboard.HumanOnboardRequest;
import dev.cobblesword.hrdb.responses.onboard.HumanOnboardResponse;
import dev.cobblesword.hrdb.responses.update.HumanUpdateRequest;
import dev.cobblesword.hrdb.responses.update.HumanUpdateResponse;
import dev.cobblesword.hrdb.services.HumanCommandService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HumanCommandsTests
{
    @Test
    public void whenIOnboardHuman_thenResponseSuccessful()
    {
        HumanCommandService humanCommandService = new HumanCommandService();
        HumanOnboardRequest onboardRequest = new HumanOnboardRequest();
        onboardRequest.firstName = "John";
        onboardRequest.lastName = "Smith";
        onboardRequest.birthDate = LocalDate.of(1990, 1, 1);
        onboardRequest.companyId = UUID.randomUUID();

        HumanOnboardResponse response = humanCommandService.onboard(onboardRequest);

        assertThat(response).isNotNull();
        assertThat(response.isSuccessful()).isTrue();
    }

    @Test
    public void whenUpdateHuman_thenResponseSuccessful()
    {
        HumanCommandService humanCommandService = new HumanCommandService();

        HumanOnboardRequest onboardRequest = new HumanOnboardRequest();
        onboardRequest.firstName = "John";
        onboardRequest.lastName = "Smith";
        onboardRequest.birthDate = LocalDate.of(1990, 1, 1);
        onboardRequest.companyId = UUID.randomUUID();
        HumanOnboardResponse onboardResponse = humanCommandService.onboard(onboardRequest);

        HumanUpdateRequest updateRequest = new HumanUpdateRequest();
        updateRequest.humanId = onboardResponse.humanId;
        updateRequest.firstName = "John";
        updateRequest.lastName = "Blacksmith";
        updateRequest.birthDate = LocalDate.of(1995, 1, 1);

        HumanUpdateResponse response = humanCommandService.update(updateRequest);

        assertThat(response).as("The response should not be null").isNotNull();
        assertThat(response.isSuccessful()).as("The onboarding should be successful").isTrue();

        assertThat(response.firstName).isEqualTo("John");
        assertThat(response.lastName).isEqualTo("Blacksmith");
        assertThat(response.birthDate).isEqualTo(LocalDate.of(1995, 1, 1));
    }

    @Test
    public void whenUpdateHuman_thenThereShouldBeAnAuditTrail()
    {
        HumanCommandService humanCommandService = new HumanCommandService();

        HumanOnboardRequest onboardRequest = new HumanOnboardRequest();
        onboardRequest.firstName = "John";
        onboardRequest.lastName = "Smith";
        onboardRequest.birthDate = LocalDate.of(1990, 1, 1);
        onboardRequest.companyId = UUID.randomUUID();
        HumanOnboardResponse onboardResponse = humanCommandService.onboard(onboardRequest);

        HumanUpdateRequest updateRequest = new HumanUpdateRequest();
        updateRequest.humanId = onboardResponse.humanId;
        updateRequest.firstName = "John";
        updateRequest.lastName = "Blacksmith";
        updateRequest.birthDate = LocalDate.of(1995, 1, 1);

        HumanUpdateResponse response = humanCommandService.update(updateRequest);

        HumanAuditLinesResponse auditLines = humanCommandService.getAudit();

        assertThat(auditLines).isNotNull();
        assertThat(auditLines.Lines).isNotNull();
        // Get lines
    }
}
