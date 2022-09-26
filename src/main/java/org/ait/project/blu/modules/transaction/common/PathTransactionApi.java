package org.ait.project.blu.modules.transaction.common;

/**.
 * Class Path Transaction APIs
 */
public class PathTransactionApi {

    /**.
     * Base endpoint for transaction
     */
    public static final String BASE_TRANSACTION = "/transactions";

    /**.
     * Endpoint for transaction balance
     * Method type GET
     */
    public static final String BALANCE = BASE_TRANSACTION + "/balance/{accountNo}";

    /**.
     * Endpoint for transaction transfer on us
     * Method type POST
     */
    public static final String TRANSFER_ONUS = BASE_TRANSACTION +  "/transfers";

    /**.
     * Endpoint for transaction transfer other bank
     * Method type POST
     */
    public static final String TRANSFER_OTHER_BANK = BASE_TRANSACTION + "/transfers/other-bank/{accountNo}";

    /**.
     * Endpoint for get transaction history
     * Method type POST
     */
    public static final String TRANSACTION_HISTORY = BASE_TRANSACTION + "/history";

}
