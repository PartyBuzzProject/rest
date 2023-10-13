package ch.partybuzz.service;

import ch.partybuzz.dto.TicketDto;
import ch.partybuzz.entity.TicketEntity;
import ch.partybuzz.mapper.TicketMapper;
import ch.partybuzz.repository.TicketRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TicketService {

    @Inject
    TicketRepository repository;

    @Inject
    TicketMapper mapper;

    @WithSession
    public Uni<List<TicketDto>> listAll() {
        return repository.findAllTickets()
                .onItem().transform(tickets -> tickets.stream().map(mapper::toDto).toList());
    }

    @WithSession
    public Uni<TicketDto> byId(UUID id) {
        return repository.findById(id)
                .onItem().transform(mapper::toDto);
    }

    @WithSession
    public Uni<List<TicketDto>> findByTicketType(String ticketType) {
        return repository.findByTicketType(ticketType)
                .onItem().transform(tickets -> tickets.stream().map(mapper::toDto).toList());
    }

    @WithSession
    public Uni<List<TicketDto>> findByEvent(UUID eventId) {
        return repository.findByEvent(eventId)
                .onItem().transform(tickets -> tickets.stream().map(mapper::toDto).toList());
    }

    @WithTransaction
    public Uni<TicketDto> save(TicketDto ticketDto) {
        TicketEntity ticket = mapper.toEntity(ticketDto);
        return repository.createTicket(ticket)
                .onItem().transform(mapper::toDto);
    }

    @WithTransaction
    public Uni<TicketDto> update(UUID id, TicketDto updatedTicketDto) {
        TicketEntity updatedTicket = mapper.toEntity(updatedTicketDto);
        return repository.updateTicket(id, updatedTicket)
                .onItem().transform(mapper::toDto);
    }

    @WithTransaction
    public Uni<Boolean> delete(UUID id) {
        return repository.deleteTicket(id);
    }
}
