package ch.partybuzz.service;

import ch.partybuzz.dto.EventCategoryDto;
import ch.partybuzz.mapper.EventCategoryMapper;
import ch.partybuzz.repository.EventCategoryRepository;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventCategoryService {

    @Inject
    EventCategoryRepository repository;

    @Inject
    EventCategoryMapper mapper;


    @WithSession
    public Uni<List<EventCategoryDto>> findByIds(List<UUID> ids) {
        return repository.findByIds(ids)
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    @WithSession
    public Uni<List<EventCategoryDto>> findAll() {
        return repository.findAllCategories()
                .onItem().transform(entities -> entities.stream().map(mapper::toDto).toList());
    }

    @WithTransaction
    public Uni<EventCategoryDto> save(EventCategoryDto categoryDto) {
        return repository.createCategory(mapper.toEntity(categoryDto))
                .onItem().transform(category -> mapper.toDto(category));
    }

}
