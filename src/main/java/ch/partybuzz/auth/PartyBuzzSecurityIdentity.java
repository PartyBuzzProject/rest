package ch.partybuzz.auth;

import io.quarkus.security.identity.SecurityIdentity;

public class PartyBuzzSecurityIdentity {

    private final SecurityIdentity delegate;

    public PartyBuzzSecurityIdentity(SecurityIdentity delegate) {
        this.delegate = delegate;
    }

    public String getUserId() {
        return delegate.getAttribute("sub").toString();
    }

    public Boolean isInOrgContext() {
        return delegate.getAttribute("org_id") != null;
    }

    public String getOrgId() {
        Object orgId = delegate.getAttribute("org_id");
        return orgId != null ? orgId.toString() : null;
    }
}

