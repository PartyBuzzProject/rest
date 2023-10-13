package ch.partybuzz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TicketDto extends BaseDto {

    private String ticketType;
    private BigDecimal price;
    private int totalAvailable;
    private int sold;
    private List<String> features;
    private EventDto event;
}
