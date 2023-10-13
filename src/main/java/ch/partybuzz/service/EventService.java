package ch.partybuzz.service;

import ch.partybuzz.dto.EventDto;
import ch.partybuzz.entity.EventEntity;
import ch.partybuzz.mapper.EventMapper;
import ch.partybuzz.repository.EventRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventService {

    @Inject
    EventRepository repository;

    @Inject
    EventMapper mapper;

    @WithSession
    public Uni<List<EventDto>> listAll() {
        return repository.findAllEvents()
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    @WithSession
    public Uni<EventDto> byId(UUID id) {
        return repository.findById(id)
                .onItem().transform(mapper::toDto);
    }

    @WithTransaction
    public Uni<EventDto> save(EventDto eventDto) {
        return repository.createEvent(mapper.toEntity(eventDto))
                .onItem().transform(mapper::toDto);
    }

    // ... additional methods to cover all functionalities of EventRepository ...

    public Uni<List<EventDto>> findByTitle(String title, int pageNumber, int pageSize) {
        return repository.findByTitle(title, pageNumber, pageSize)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findByOrganizer(UUID organizerId) {
        return repository.findByOrganizer(organizerId)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findByCategory(String category) {
        return repository.findByCategory(category)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findLiveEvents() {
        return repository.findLiveEvents()
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findFeaturedEvents() {
        return repository.findFeaturedEvents()
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findEventsByDateRange(LocalDateTime start, LocalDateTime end) {
        return repository.findEventsByDateRange(start, end)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<List<EventDto>> findUpcomingEvents(LocalDateTime currentDate) {
        return repository.findUpcomingEvents(currentDate)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    public Uni<Boolean> markEventAsLive(UUID id) {
        return repository.markAsLive(id)
                .onItem().transform(rowsUpdated -> rowsUpdated > 0);
    }

    public Uni<Boolean> markEventAsFeatured(UUID id) {
        return repository.markAsFeatured(id)
                .onItem().transform(rowsUpdated -> rowsUpdated > 0);
    }

    public Uni<EventDto> updateEvent(UUID id, EventDto updatedEventDto) {
        EventEntity updatedEvent = mapper.toEntity(updatedEventDto);
        return repository.updateEvent(id, updatedEvent)
                .onItem().transform(mapper::toDto);
    }

    public Uni<Boolean> deleteEvent(UUID id) {
        return repository.deleteEvent(id);
    }

}