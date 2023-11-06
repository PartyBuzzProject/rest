package ch.partybuzz.service;

import ch.partybuzz.auth0.Auth0Client;
import ch.partybuzz.auth0.Auth0TokenResponse;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class Auth0Service {

    private final Config config = ConfigProvider.getConfig();
    private final String clientId = config.getValue("partybuzz.oidc.client-id", String.class);
    private final String clientSecret = config.getValue("partybuzz.oidc.credentials.secret", String.class);
    @Inject
    @RestClient
    Auth0Client auth0Client;

    public Uni<String> getManagementApiToken() {
        return auth0Client.getAccessToken(
                "client_credentials",
                clientId,
                clientSecret,
                "https://partybuzz.eu.auth0.com/api/v2/"
        ).map(Auth0TokenResponse::getAccess_token);
    }
}
