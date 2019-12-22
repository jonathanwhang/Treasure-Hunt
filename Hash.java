import java.util.*;
import java.security.*;

public class Hash {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] message = md.digest(input.getBytes());
            return bytesToHex(message);
        }
        catch(NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}