import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String hash;
    private String previousHash;    // hash of the previous block
    private String data;            // actual data with information
    private long timestamp;         // timestamp of block creation
    private int index;              // index of the block inside the chain

    public Block(String data, String previousHash, long timestamp) throws NoSuchAlgorithmException {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.hash = hashBlock();
    }

    private String hashBlock() throws NoSuchAlgorithmException {
        String hashData = previousHash
                + Long.toString(timestamp)
                + data
                + Integer.toString(index);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");    // SHA-256 hashing algorithm
        byte[] encodedHash = digest.digest(hashData.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedHash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

