package org.ait.project.blu.shared.constant.common;

/**.
 * Class Path blu Client APIs
 */
public class PathBluClientAPIs {

    private PathBluClientAPIs() {}

    /**.
     * Endpoint for get balance
     * Method type GET
     */
    public static final String BALANCE = "/balance/{accountNo}";

    /**.
     * Endpoint for posting transfer on us
     * Method type POST
     */
    public static final String TRANSFER_ONUS = "/v2/transfers";

    /**.
     * Endpoint for posting transfer other bank
     * Method type POST
     */
    public static final String TRANSFER_OTHER_BANK = "/v2/transfers/to-another-bank/{accountNo}";

    /**.
     * Endpoint for get transaction history
     * Method type POST
     */
    public static final String TRANSACTION_HISTORY = "/v3/transaction/history";

}
