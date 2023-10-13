package ch.partybuzz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EventDto extends BaseDto {

    private String title;
    private String description;
    private String location;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private UUID organizerId;
    private String imageUrl;
    private OffsetDateTime saleStartTime;
    private boolean allowTicketPurchaseAfterStart;
    private String category;
    private List<String> tags;
    private boolean isFeatured;
    private List<TicketDto> tickets;
}
