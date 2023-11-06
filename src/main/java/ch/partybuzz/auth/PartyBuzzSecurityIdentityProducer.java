package ch.partybuzz.auth;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class PartyBuzzSecurityIdentityProducer {

    @Inject
    SecurityIdentity delegate;

    @Inject
    JsonWebToken jsonWebToken;

    @Produces
    @RequestScoped
    public PartyBuzzSecurityIdentity produceCustomIdentity() {
        return new PartyBuzzSecurityIdentity(delegate, jsonWebToken);
    }
}
