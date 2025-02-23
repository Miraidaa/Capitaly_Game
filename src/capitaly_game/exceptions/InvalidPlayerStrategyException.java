package capitaly_game.exceptions;
public class InvalidPlayerStrategyException extends Exception {
    public InvalidPlayerStrategyException(String strategy) {
        super("Unrecognized player strategy specified: " + strategy);
    }
}
