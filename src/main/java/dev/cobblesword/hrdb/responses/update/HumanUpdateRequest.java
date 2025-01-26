package dev.cobblesword.hrdb.responses.update;

import java.time.LocalDate;
import java.util.UUID;

public class HumanUpdateRequest {
    public UUID humanId;
    public String firstName;
    public String lastName;
    public LocalDate birthDate;
}
