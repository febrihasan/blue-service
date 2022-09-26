package org.ait.project.blu.modules.auth.common;

/**.
 * Class Path Authentication APIs
 */
public class PathAuthApi {

    /**.
     * Base endpoint for authentication
     */
    public static final String BASE_AUTHENTICATION = "/auth";

    /**.
     * Endpoint for create user
     * Method type POST
     */
    public static final String CREATE_USER = BASE_AUTHENTICATION + "/create";

    /**.
     * Endpoint for login user
     * Method type POST
     */
    public static final String LOGIN_USER = BASE_AUTHENTICATION +  "/login";

    /**.
     * Endpoint for refresh token user
     * Method type POST
     */
    public static final String VERIFICATION_TOKEN = BASE_AUTHENTICATION +  "/verify-token";

    /**.
     * Endpoint for refresh token user
     * Method type POST
     */
    public static final String REFRESH_TOKEN = BASE_AUTHENTICATION +  "/refresh-token";

}
