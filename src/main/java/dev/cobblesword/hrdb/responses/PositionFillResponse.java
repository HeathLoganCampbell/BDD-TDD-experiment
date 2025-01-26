package dev.cobblesword.hrdb.responses;

import java.util.UUID;

public class PositionFillResponse {
    public UUID positionId;
    public UUID humanId;

    public boolean isSuccessful()
    {
        throw new UnsupportedOperationException();
    }
}
