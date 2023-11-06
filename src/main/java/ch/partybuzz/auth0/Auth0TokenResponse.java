package ch.partybuzz.auth0;

import lombok.Data;

@Data
public class Auth0TokenResponse {
    private String access_token;
    private String token_type;
    private int expires_in;
    private String scope;
}

