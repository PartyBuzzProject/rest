package ch.partybuzz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket_feature")
@Getter
@Setter
public class TicketFeatureEntity extends BaseEntity {

    @Column(name = "feature", nullable = false, unique = true)
    private String feature;

    @ManyToMany(mappedBy = "features")
    private List<TicketEntity> tickets = new ArrayList<>();
}

