
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
    }
}

