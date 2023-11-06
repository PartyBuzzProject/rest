package ch.partybuzz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "event_category")
public class EventCategory extends Base {

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Event> events = new LinkedHashSet<>();

}
