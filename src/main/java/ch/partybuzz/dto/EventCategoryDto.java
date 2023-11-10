package ch.partybuzz.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link ch.partybuzz.entity.EventCategory}
 */
public record EventCategoryDto(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt, String name) implements Serializable {
    @Serial
    private static final long serialVersionUID = -8908503208361563500L;
}
