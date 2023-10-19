package ch.partybuzz.mapper;

import ch.partybuzz.dto.EventDto;
import ch.partybuzz.entity.EventEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface EventMapper {
    EventEntity toEntity(EventDto eventDto);

    @AfterMapping
    default void linkTickets(@MappingTarget EventEntity eventEntity) {
        eventEntity.getTickets().forEach(ticket -> ticket.setEvent(eventEntity));
    }

    EventDto toDto(EventEntity eventEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EventEntity partialUpdate(EventDto eventDto, @MappingTarget EventEntity eventEntity);
}
