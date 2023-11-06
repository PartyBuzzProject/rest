package ch.partybuzz.mapper;

import ch.partybuzz.dto.EventCategoryDto;
import ch.partybuzz.entity.EventCategory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface EventCategoryMapper {
    EventCategory toEntity(EventCategoryDto eventCategoryDto);

    EventCategoryDto toDto(EventCategory eventCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventCategory partialUpdate(EventCategoryDto eventCategoryDto, @MappingTarget EventCategory eventCategory);
}
