package org.ait.project.blu.shared.utils;

import lombok.extern.slf4j.Slf4j;
import java.security.MessageDigest;

/**.
 *  Class security utils for encrypted and decrypted key
 */

@Slf4j
public class SecureUtils {

    private SecureUtils() {
    }

    /**.
     * Function for encrypted password
     * @param password text
     * @return encrypted password
     */
    public static String encrypted(String password) {
        String result = null;

        try {
            /* MessageDigest instance for MD5. */
            MessageDigest m = MessageDigest.getInstance("MD5");

            /* Add plain-text password bytes to digest using MD5 update() method. */
            m.update(password.getBytes());

            /* Convert the hash value into bytes */
            byte[] bytes = m.digest();

            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            /* Complete hashed password in hexadecimal format */
            result = s.toString();
        } catch (Exception e) {
            log.error("Error encrypted : ", e);
        }

        return result;
    }

}
