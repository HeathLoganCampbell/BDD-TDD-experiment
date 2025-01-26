package dev.cobblesword.hrdb.responses.update;

import java.time.LocalDate;

public class HumanUpdateResponse {
    public String firstName;
    public String lastName;
    public LocalDate birthDate;

    public boolean isSuccessful()
    {
        throw new UnsupportedOperationException();
    }
}
