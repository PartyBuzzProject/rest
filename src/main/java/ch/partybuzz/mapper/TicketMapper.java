package ch.partybuzz.mapper;

import ch.partybuzz.dto.TicketDto;
import ch.partybuzz.entity.EventEntity;
import ch.partybuzz.entity.TicketEntity;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface TicketMapper {

    @Mapping(target = "event", qualifiedByName = "eventIdToEvent")
    TicketEntity toEntity(TicketDto ticketDto);

    @Mapping(target = "eventId", source = "event.id")
    TicketDto toDto(TicketEntity ticketEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "event", qualifiedByName = "eventIdToEvent")
    TicketEntity partialUpdate(TicketDto ticketDto, @MappingTarget TicketEntity ticketEntity);

    @Named("eventIdToEvent")
    default EventEntity eventIdToEvent(UUID eventId) {
        if (eventId == null) {
            return null;
        }
        EventEntity event = new EventEntity();
        event.setId(eventId);
        return event;
    }
}

