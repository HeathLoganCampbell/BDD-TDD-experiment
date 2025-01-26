package dev.cobblesword.hrdb.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class HumanAuditLine
{
    public UUID auditId;
    public LocalDateTime modificationDateTime;
    public int userId;
    public UUID humanId;
}
