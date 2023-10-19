package ch.partybuzz.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link ch.partybuzz.entity.TicketEntity}
 */
public record TicketDto(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String ticketType,
                        BigDecimal price, int totalAvailable, int sold, List<String> features,
                        UUID eventId) implements Serializable {
    @Serial
    private static final long serialVersionUID = 6122046032541603057L;
}
