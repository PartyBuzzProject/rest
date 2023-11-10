package ch.partybuzz.repository;

import ch.partybuzz.entity.EventCategory;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventCategoryRepository implements PanacheRepositoryBase<EventCategory, UUID> {

    public Uni<List<EventCategory>> findAllCategories() {
        return listAll();
    }

    public Uni<List<EventCategory>> findByIds(List<UUID> ids) {
        return list("id IN ?1", ids);
    }

    public Uni<EventCategory> createCategory(EventCategory category) {
        return persistAndFlush(category).onItem().transform(ignore -> category);
    }
}
