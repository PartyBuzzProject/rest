package ch.partybuzz.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class BaseDto {
    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
