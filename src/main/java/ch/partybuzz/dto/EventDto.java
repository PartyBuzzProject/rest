package ch.partybuzz.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link ch.partybuzz.entity.EventEntity}
 */
public record EventDto(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String title, String description,
                       String location, OffsetDateTime startDate, OffsetDateTime endDate, String organizerId,
                       String imageUrl, OffsetDateTime saleStartTime, boolean allowTicketPurchaseAfterStart,
                       String category, List<EventTagDto> tags, boolean isFeatured,
                       List<TicketDto> tickets) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4014636359540521829L;
}
