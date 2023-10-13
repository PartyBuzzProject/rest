package ch.partybuzz.resource;

import ch.partybuzz.dto.TicketDto;
import ch.partybuzz.service.TicketService;
import io.quarkus.security.Authenticated;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Authenticated
@RouteBase(path = "/ticket")
public class TicketResource {

    @Inject
    TicketService service;

    @RolesAllowed("read:data")
    @Route(path = "/all", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<TicketDto>>> listAll() {
        return service.listAll()
                .map(RestResponse::ok);
    }

    @Route(path = "/find/:id", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<TicketDto>> byId(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.byId(id)
                .map(RestResponse::ok);
    }

    @Route(path = "/type/:ticketType", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<TicketDto>>> findByTicketType(RoutingContext context) {
        String ticketType = context.pathParam("ticketType");
        return service.findByTicketType(ticketType)
                .map(RestResponse::ok);
    }

    @Route(path = "/event/:eventId", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<TicketDto>>> findByEvent(RoutingContext context) {
        UUID eventId = UUID.fromString(context.pathParam("eventId"));
        return service.findByEvent(eventId)
                .map(RestResponse::ok);
    }

    @RolesAllowed("write:data")
    @Route(path = "/save", methods = Route.HttpMethod.POST)
    public Uni<RestResponse<TicketDto>> save(RoutingContext context) {
        JsonObject json = (JsonObject) context.body();
        TicketDto ticketDto = json.mapTo(TicketDto.class);
        return service.save(ticketDto)
                .map(data -> RestResponse.status(RestResponse.Status.CREATED, data));
    }

    @RolesAllowed("write:data")
    @Route(path = "/update/:id", methods = Route.HttpMethod.PUT)
    public Uni<RestResponse<TicketDto>> update(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        JsonObject json = (JsonObject) context.body();
        TicketDto updatedTicketDto = json.mapTo(TicketDto.class);
        return service.update(id, updatedTicketDto)
                .map(RestResponse::ok);
    }

    @RolesAllowed("delete:data")
    @Route(path = "/delete/:id", methods = Route.HttpMethod.DELETE)
    public Uni<RestResponse<Boolean>> delete(RoutingContext context) {
        UUID id = UUID.fromString(context.pathParam("id"));
        return service.delete(id)
                .map(RestResponse::ok);
    }
}
