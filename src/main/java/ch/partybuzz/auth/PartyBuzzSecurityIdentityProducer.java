package ch.partybuzz.auth;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class PartyBuzzSecurityIdentityProducer {

    @Inject
    SecurityIdentity delegate;

    @Produces
    @RequestScoped
    public PartyBuzzSecurityIdentity produceCustomIdentity() {
        return new PartyBuzzSecurityIdentity(delegate);
    }
}
