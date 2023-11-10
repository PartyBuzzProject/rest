package ch.partybuzz.resource;

import ch.partybuzz.dto.EventCategoryDto;
import ch.partybuzz.service.EventCategoryService;
import io.quarkus.security.Authenticated;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@ApplicationScoped
@Authenticated
@RouteBase(path = "/event/category")
public class EventCategoryResource {

    @Inject
    EventCategoryService service;

    @Route(path = "/findAll", methods = Route.HttpMethod.GET)
    public Uni<RestResponse<List<EventCategoryDto>>> findAll(RoutingContext context) {
        return service.findAll()
                .map(data -> RestResponse.status(RestResponse.Status.OK, data));
    }

    @Route(path = "/save", methods = Route.HttpMethod.POST)
    public Uni<RestResponse<EventCategoryDto>> save(RoutingContext context) {
        JsonObject json = context.body().asJsonObject();
        EventCategoryDto categoryDto = json.mapTo(EventCategoryDto.class);
        return service.save(categoryDto)
                .map(data -> RestResponse.status(RestResponse.Status.CREATED, data));
    }
}
