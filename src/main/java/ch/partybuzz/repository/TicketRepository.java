package ch.partybuzz.repository;

import ch.partybuzz.entity.TicketEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class TicketRepository implements PanacheRepositoryBase<TicketEntity, UUID> {

    // Fetch a ticket by its ID
    public Uni<TicketEntity> findById(UUID id) {
        return find("id", id).firstResult();
    }

    // Fetch all tickets
    public Uni<List<TicketEntity>> findAllTickets() {
        return listAll();
    }

    // Fetch all tickets of a specific type
    public Uni<List<TicketEntity>> findByTicketType(String ticketType) {
        return list("ticket_type", ticketType);
    }

    // Fetch all tickets for a specific event
    public Uni<List<TicketEntity>> findByEvent(UUID eventId) {
        return list("event_id", eventId);
    }

    // Save a new ticket
    public Uni<TicketEntity> createTicket(TicketEntity ticket) {
        return persistAndFlush(ticket).onItem().transform(ignore -> ticket);
    }

    // Update a ticket
    public Uni<TicketEntity> updateTicket(UUID id, TicketEntity updatedTicket) {
        return findById(id).onItem().ifNotNull().transformToUni(existingTicket -> {
            existingTicket.setTicketType(updatedTicket.getTicketType());
            existingTicket.setPrice(updatedTicket.getPrice());
            existingTicket.setTotalAvailable(updatedTicket.getTotalAvailable());
            existingTicket.setSold(updatedTicket.getSold());
            existingTicket.setFeatures(updatedTicket.getFeatures());
            // Add any other fields that need updating here
            return persistAndFlush(existingTicket).onItem().transform(ignore -> existingTicket);
        });
    }

    // Delete a ticket based on its ID
    public Uni<Boolean> deleteTicket(UUID id) {
        return deleteById(id);
    }
}
