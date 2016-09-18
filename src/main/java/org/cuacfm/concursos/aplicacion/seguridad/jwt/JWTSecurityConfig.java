package org.cuacfm.concursos.aplicacion.seguridad.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwtSecurity", ignoreUnknownFields = false)
public class JWTSecurityConfig {

    private final Security security = new Security();

    public static class Security {

        private final Authentication authentication = new Authentication();

        public Authentication getAuthentication() {
            return authentication;
        }

        public static class Authentication {

            private final Jwt jwt = new Jwt();

            public Jwt getJwt() {
                return jwt;
            }

            public static class Jwt {

                private String secret;

                private long tokenValidityInSeconds = 1800;
                private long tokenValidityInSecondsForRememberMe = 2592000;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getTokenValidityInSecondsForRememberMe() {
                    return tokenValidityInSecondsForRememberMe;
                }

                public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
                    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
                }
            }
        }
    }

    public Security getSecurity() {
        return security;
    }
}
