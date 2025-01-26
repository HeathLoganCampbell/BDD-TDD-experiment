package dev.cobblesword.hrdb.responses;

import java.time.LocalDate;
import java.util.UUID;

public class PositionFillRequest {
    public UUID humanId;
    public UUID positionId;
    public LocalDate startDate;
    public LocalDate finishDate;
    public int capacity;
}
