package Utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Meaks on 12/7/2015.
 */
public class Hash {

    public static String createPassword(String clearString) throws AppException {
        if (clearString == null) {
            throw new AppException("No password defined!");
        }
        return BCrypt.hashpw(clearString, BCrypt.gensalt());
    }

    /**
     * @param candidate         the clear text
     * @param encryptedPassword the encrypted password string to check.
     * @return true if the candidate matches, false otherwise.
     */
    public static boolean checkPassword(String candidate, String encryptedPassword) {
        if (candidate == null) {
            return false;
        }
        if (encryptedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(candidate, encryptedPassword);
    }
}
