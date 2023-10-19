package ch.partybuzz.mapper;

import ch.partybuzz.dto.TicketDto;
import ch.partybuzz.entity.TicketEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface TicketMapper {
    TicketEntity toEntity(TicketDto ticketDto);

    TicketDto toDto(TicketEntity ticketEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TicketEntity partialUpdate(TicketDto ticketDto, @MappingTarget TicketEntity ticketEntity);
}
