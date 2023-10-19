package ch.partybuzz.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * DTO for {@link ch.partybuzz.entity.EventTagEntity}
 */
public record EventTagDto(UUID id, OffsetDateTime createdAt, OffsetDateTime updatedAt,
                          String tag) implements Serializable {
    @Serial
    private static final long serialVersionUID = -4488431168146423347L;
}
