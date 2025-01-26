package dev.cobblesword.hrdb.responses.onboard;

import java.time.LocalDate;
import java.util.UUID;

public class HumanOnboardRequest
{
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
    public UUID companyId;
}
