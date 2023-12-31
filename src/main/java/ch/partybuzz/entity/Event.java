package ch.partybuzz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "event")
public class Event extends Base {

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

    @Column(name = "sale_start_date")
    private OffsetDateTime saleStartDate;

    @Column(name = "allow_ticket_purchase_after_start")
    private boolean allowTicketPurchaseAfterStart;

    @Column(name = "is_featured")
    private boolean isFeatured;

    @ManyToMany
    @JoinTable(name = "event_event_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "event_category_id"))
    private Set<EventCategory> categories = new LinkedHashSet<>();

}
