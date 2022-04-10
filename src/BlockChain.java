import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class BlockChain {
    List<Block> blockchain = new ArrayList<Block>();

    private void addGenesisBlock() throws NoSuchAlgorithmException {
        Block genesisBlock = new Block(
                "",
                "0000000000000000000000000000000000000000000000000000000000000000",
                123);
        blockchain.add(genesisBlock);
    }

    private void addBlockToChain(Block currentBlock) {
        blockchain.add(currentBlock);
    }
}
