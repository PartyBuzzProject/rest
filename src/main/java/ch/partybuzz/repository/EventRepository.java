package ch.partybuzz.repository;

import ch.partybuzz.entity.EventEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventRepository implements PanacheRepositoryBase<EventEntity, UUID> {

    // Fetch a single event by its title
    public Uni<List<EventEntity>> findByTitle(String title, int pageNumber, int pageSize) {
        return find("title LIKE ?1", "%" + title + "%")
                .page(pageNumber, pageSize)
                .list();
    }

    // Fetch a single event by its ID
    public Uni<EventEntity> findById(UUID id) {
        return find("id", id).firstResult();
    }

    // Fetch events with pagination
    public Uni<List<EventEntity>> findEvents(int pageNumber, int pageSize) {
        return find("FROM EventEntity").page(pageNumber, pageSize).list();
    }

    // Fetch all events
    public Uni<List<EventEntity>> findAllEvents() {
        return listAll();
    }

    // Fetch events by an organizer
    public Uni<List<EventEntity>> findByOrganizer(UUID organizerId) {
        return list("organizer_id", organizerId);
    }

    // Fetch events by category
    public Uni<List<EventEntity>> findByCategory(String category) {
        return list("category", category);
    }

    // Fetch live events
    public Uni<List<EventEntity>> findLiveEvents() {
        return list("is_live", true);
    }

    // Fetch featured events
    public Uni<List<EventEntity>> findFeaturedEvents() {
        return list("is_featured", true);
    }

    // Fetch events happening between specific dates
    public Uni<List<EventEntity>> findEventsByDateRange(LocalDateTime start, LocalDateTime end) {
        return list("start_date BETWEEN ?1 AND ?2", start, end);
    }

    // Fetch upcoming events
    public Uni<List<EventEntity>> findUpcomingEvents(LocalDateTime currentDate) {
        return list("start_date > ?1", currentDate);
    }

    // Mark an event as live
    public Uni<Integer> markAsLive(UUID id) {
        return update("SET is_live = TRUE WHERE id = ?1", id);
    }

    // Mark an event as featured
    public Uni<Integer> markAsFeatured(UUID id) {
        return update("SET is_featured = TRUE WHERE id = ?1", id);
    }

    // Save a new event
    public Uni<EventEntity> createEvent(EventEntity event) {
        return persistAndFlush(event).onItem().transform(ignore -> event);
    }

    // Update an event based on its ID
    public Uni<EventEntity> updateEvent(UUID id, EventEntity updatedEvent) {
        return findById(id).onItem().ifNotNull().transformToUni(existingEvent -> {
            existingEvent.setTitle(updatedEvent.getTitle());
            // Add other fields that need updating here
            return persistAndFlush(existingEvent).onItem().transform(ignore -> existingEvent);
        });
    }

    // Delete an event based on its ID
    public Uni<Boolean> deleteEvent(UUID id) {
        return deleteById(id);
    }
}
