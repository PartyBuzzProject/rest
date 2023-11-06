package ch.partybuzz.auth;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;
import jakarta.json.Json;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.UUID;

public class PartyBuzzSecurityIdentity {

    private final SecurityIdentity delegate;

    private final JsonWebToken jsonWebToken;

    private final String state;

    public PartyBuzzSecurityIdentity(SecurityIdentity delegate, JsonWebToken jwtCallerPrincipal) {
        this.delegate = delegate;
        this.jsonWebToken = jwtCallerPrincipal;
        this.state = UUID.randomUUID().toString();
    }

    public String getUserId() {
        return delegate.getAttribute("sub").toString();
    }

    public Boolean isInOrgContext() {
        return jsonWebToken.getClaim("org_id") != null;
    }

    public String getOrgId() {
        return jsonWebToken.getClaim("org_id");
    }

    public String getState() {
        return state;
    }
}

