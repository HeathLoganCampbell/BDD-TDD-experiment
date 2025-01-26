package dev.cobblesword.hrdb;

import dev.cobblesword.hrdb.responses.fetch.HumanFetchRequest;
import dev.cobblesword.hrdb.responses.fetch.HumanFetchResponse;
import dev.cobblesword.hrdb.responses.fetch.SearchType;
import dev.cobblesword.hrdb.responses.onboard.HumanOnboardRequest;
import dev.cobblesword.hrdb.responses.onboard.HumanOnboardResponse;
import dev.cobblesword.hrdb.services.HumanCommandService;
import dev.cobblesword.hrdb.services.HumanQueryService;
import org.junit.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HumanFetchTests
{
    @Test
    public void whenFetchHumanByName_ThenShouldReturnHumanResponse()
    {
        HumanCommandService humanCommandService = new HumanCommandService();

        HumanOnboardRequest onboardRequest = new HumanOnboardRequest();
        onboardRequest.firstName = "John";
        onboardRequest.lastName = "Smith";
        onboardRequest.birthDate = LocalDate.of(1990, 1, 1);
        onboardRequest.companyId = UUID.randomUUID();
        HumanOnboardResponse onboardResponse = humanCommandService.onboard(onboardRequest);

        HumanQueryService humanQueryService = new HumanQueryService();
        // By First Name
        // By Last Name
        // By Age
        // By Company
        // As of
        // Util
        // Probably need a DSL
        // Let try keep it basic at first
        HumanFetchRequest humanFetchRequest = new HumanFetchRequest();
        humanFetchRequest.searchType = SearchType.SIMPLE;
        humanFetchRequest.firstName = "john";

        HumanFetchResponse humanFetchResponse = humanQueryService.fetchHumans(humanFetchRequest);
        assertThat(humanFetchResponse).isNotNull();
        assertThat(humanFetchResponse.humans.size()).isOne();
        assertThat(humanFetchResponse.humans.getFirst().humanId).isEqualTo(onboardResponse.humanId);
        assertThat(humanFetchResponse.humans.getFirst().firstName).isEqualTo(onboardRequest.firstName);
        assertThat(humanFetchResponse.humans.getFirst().lastName).isEqualTo(onboardRequest.lastName);
    }
}
