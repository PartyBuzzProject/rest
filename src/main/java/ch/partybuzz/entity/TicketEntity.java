package ch.partybuzz.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity extends BaseEntity {

    @Column(name = "ticket_type", nullable = false)
    private String ticketType;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "total_available", nullable = false)
    private int totalAvailable;

    @Column(name = "sold")
    private int sold;

    @ManyToMany
    @JoinTable(
            name = "ticket_ticket_feature",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_feature_id")
    )
    private List<TicketFeatureEntity> features = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EventEntity event;
}
