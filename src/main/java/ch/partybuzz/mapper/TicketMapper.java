package ch.partybuzz.mapper;

import ch.partybuzz.dto.TicketDto;
import ch.partybuzz.entity.TicketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TicketMapper {

    TicketDto toDto(TicketEntity entity);

    TicketEntity toEntity(TicketDto dto);

}
