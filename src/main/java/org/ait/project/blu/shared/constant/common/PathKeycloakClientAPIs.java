package org.ait.project.blu.shared.constant.common;

/**.
 * Class Path Keycloak Client APIs
 */
public class PathKeycloakClientAPIs {

    private PathKeycloakClientAPIs() {}

    public static final String KEYCLOAK = "/protocol/openid-connect";

    /**.
     * Endpoint for get public key with certs
     * Method type POST
     */
    public static final String PUBLIC_KEY_WITH_REALM = "/";

    /**.
     * Endpoint for get public key with certs
     * Method type POST
     */
    public static final String PUBLIC_KEY_WITH_CERTS = KEYCLOAK + "/certs";

    /**.
     * Endpoint for verification token
     * Method type POST
     */
    public static final String VERIFICATION_TOKEN = KEYCLOAK + "/token/introspect";

    /**.
     * Endpoint for refresh token
     * Method type POST
     */
    public static final String REFRESH_TOKEN = KEYCLOAK + "/token";

}
