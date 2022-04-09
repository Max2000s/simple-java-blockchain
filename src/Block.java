import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;



public class Block {
    private String hash;
    private String previousHash;    // hash of the previous block
    private String data;            // actual data with information
    private long timestamp;         // timestamp of block creation
    private int nonce;              // arbitrary number used in cryptography

    public Block(String data, String previousHash, long timestamp) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.hash = calculateBlockHash();   // hash of this block
    }

    public String calculateBlockHash() {
        String dataToHash = previousHash    // concatenate different parts of the block to generate hash from
                + Long.toString(timestamp)
                + Integer.toString(nonce)
                + data;

        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");  // get an instance of the SHA-256 hash function
            bytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8)); // generate hash value of input data
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        }
        StringBuffer buffer = new StringBuffer();
        for (byte b: bytes) {   // transform byte array into hex string -> hash is represented as 32-digit hex number
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {   // check if solution is found
            nonce++;    // if solution not found increment nonce
            hash = calculateBlockHash();    // calculate the hash
        }
        return hash;
    }

    List<Block> blockchain = new ArrayList<>();
    int prefix = 4;
    String prefixString = new String(new char[prefix]).replace('\0', '0');

    @Test
    public void givenBlockchain_whenNewBlockAdded_thenSuccess() {
        Block newBlock = new Block(
                "This is a new Block",
                blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        assertTrue(newBlock.getHash().substring
        )
    }

    // getters and setters
}
