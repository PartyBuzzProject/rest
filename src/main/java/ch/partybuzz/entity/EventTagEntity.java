package ch.partybuzz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "event_tag")
@Getter
@Setter
public class EventTagEntity extends BaseEntity {

    @Column(name = "tag", nullable = false, unique = true)
    private String tag;

    @ManyToMany(mappedBy = "tags")
    private List<EventEntity> events = new ArrayList<>();
}

