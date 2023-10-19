package ch.partybuzz.resource;

import ch.partybuzz.dto.EventDto;
import ch.partybuzz.service.EventService;
import io.quarkus.security.Authenticated;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Authenticated
@RouteBase(path = "/event")
public class EventResource {

    @Inject
    EventService service;

    @RolesAllowed("read:events")
    @Route(path = "/all", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> listAll() {
        return service.listAll()
                .map(RestResponse::ok);
    }

    @RolesAllowed("read:events")
    @Route(path = "/find/:id", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<EventDto>> byId(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.byId(id)
                .map(RestResponse::ok);
    }

    @RolesAllowed("read:events")
    @Route(path = "/title/:title", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findByTitle(RoutingContext context) {
        String title = context.pathParam("title");
        int pageNumber = Integer.parseInt(context.request().getParam("pageNumber"));
        int pageSize = Integer.parseInt(context.request().getParam("pageSize"));
        return service.findByTitle(title, pageNumber, pageSize)
                .map(RestResponse::ok);
    }

    @Route(path = "/organizer/:organizerId", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findByOrganizer(RoutingContext context) {
        UUID organizerId = UUID.fromString(context.pathParam("organizerId"));
        return service.findByOrganizer(organizerId)
                .map(RestResponse::ok);
    }

    @Route(path = "/category/:category", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findByCategory(RoutingContext context) {
        String category = context.pathParam("category");
        return service.findByCategory(category)
                .map(RestResponse::ok);
    }

    @RolesAllowed("read:events")
    @Route(path = "/featured", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findFeaturedEvents() {
        return service.findFeaturedEvents()
                .map(RestResponse::ok);
    }

    @RolesAllowed("read:events")
    @Route(path = "/daterange", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findEventsByDateRange(RoutingContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(context.request().getParam("start"), formatter);
        LocalDateTime end = LocalDateTime.parse(context.request().getParam("end"), formatter);
        return service.findEventsByDateRange(start, end)
                .map(RestResponse::ok);
    }

    @RolesAllowed("read:events")
    @Route(path = "/upcoming", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventDto>>> findUpcomingEvents(RoutingContext context) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime currentDate = LocalDateTime.parse(context.request().getParam("currentDate"), formatter);
        return service.findUpcomingEvents(currentDate)
                .map(RestResponse::ok);
    }

    @RolesAllowed("edit:events")
    @Route(path = "/marklive/:id", methods = Route.HttpMethod.PUT)
    public Uni<RestResponse<Boolean>> markEventAsLive(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.markEventAsLive(id)
                .map(RestResponse::ok);
    }

    @RolesAllowed("edit:events")
    @Route(path = "/markfeatured/:id", methods = Route.HttpMethod.PUT)
    public Uni<RestResponse<Boolean>> markEventAsFeatured(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.markEventAsFeatured(id)
                .map(RestResponse::ok);
    }

    @RolesAllowed("create:events")
    @Route(path = "/save", methods = Route.HttpMethod.POST)
    public Uni<RestResponse<EventDto>> save(RoutingContext context) {
        JsonObject json = (JsonObject) context.body();
        EventDto eventDto = json.mapTo(EventDto.class);
        return service.save(eventDto)
                .map(data -> RestResponse.status(RestResponse.Status.CREATED, data));
    }

    @RolesAllowed("edit:events")
    @Route(path = "/update/:id", methods = Route.HttpMethod.PUT)
    public Uni<RestResponse<EventDto>> updateEvent(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        JsonObject json = (JsonObject) context.body();
        EventDto updatedEventDto = json.mapTo(EventDto.class);
        return service.updateEvent(id, updatedEventDto)
                .map(RestResponse::ok);
    }

    @RolesAllowed("delete:events")
    @Route(path = "/delete/:id", methods = Route.HttpMethod.DELETE)
    public Uni<RestResponse<Boolean>> deleteEvent(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.deleteEvent(id)
                .map(RestResponse::ok);
    }
}
