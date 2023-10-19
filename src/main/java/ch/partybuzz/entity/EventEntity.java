package ch.partybuzz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private OffsetDateTime startDate;

    @Column(name = "end_date")
    private OffsetDateTime endDate;

    @Column(name = "organizer_id")
    private String organizerId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sale_start_time")
    private OffsetDateTime saleStartTime;

    @Column(name = "allow_ticket_purchase_after_start")
    private boolean allowTicketPurchaseAfterStart;

    @Column(name = "category")
    private String category;

    @ManyToMany
    @JoinTable(
            name = "event_event_tag",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "event_tag_id")
    )
    private List<EventTagEntity> tags = new ArrayList<>();

    @Column(name = "is_featured")
    private boolean isFeatured;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TicketEntity> tickets = new ArrayList<>();
}
