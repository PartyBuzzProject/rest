package ch.partybuzz.auth0;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "auth0-api")
public interface Auth0Client {

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Auth0TokenResponse> getAccessToken(@FormParam("grant_type") String grantType,
                                           @FormParam("client_id") String clientId,
                                           @FormParam("client_secret") String clientSecret,
                                           @FormParam("audience") String audience);

    @GET
    @Path("/api/v2/users/{id}")
    Uni<String> getUserProfile(@PathParam("id") String id, @HeaderParam("Authorization") String token);
}
