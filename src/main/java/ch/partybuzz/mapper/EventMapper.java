package ch.partybuzz.mapper;

import ch.partybuzz.dto.EventDto;
import ch.partybuzz.entity.EventEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface EventMapper {

    EventDto toDto(EventEntity entity);

    EventEntity toEntity(EventDto dto);

}
