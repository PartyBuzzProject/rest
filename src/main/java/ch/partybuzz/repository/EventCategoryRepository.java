package ch.partybuzz.repository;

import ch.partybuzz.entity.EventCategory;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventCategoryRepository implements PanacheRepositoryBase<EventCategory, UUID> {

    public Uni<List<EventCategory>> findByIds(List<UUID> ids) {
        return find("from event_category where id in ?1", ids).list();
    }

}
