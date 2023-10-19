package ch.partybuzz.mapper;

import ch.partybuzz.dto.EventTagDto;
import ch.partybuzz.entity.EventTagEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface EventTagMapper {
    EventTagEntity toEntity(EventTagDto eventTagDto);

    EventTagDto toDto(EventTagEntity eventTagEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventTagEntity partialUpdate(EventTagDto eventTagDto, @MappingTarget EventTagEntity eventTagEntity);
}
