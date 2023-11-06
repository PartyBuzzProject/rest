package ch.partybuzz.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ch.partybuzz.entity.Event}
 */
public record EventDto(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String title, String description,
                       String location, OffsetDateTime startTime, OffsetDateTime endTime, String organizerId,
                       String imageUrl, OffsetDateTime saleStartTime, boolean allowTicketPurchaseAfterStart,
                       boolean isFeatured, Set<EventCategoryDto> categories) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4014636359540521829L;
}
