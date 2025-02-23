
package capitaly_game.exceptions;

public class BankruptException extends Exception {
    public BankruptException(String playerName) {
        super(playerName + " has gone bankrupt and cannot continue the game.");
    }
}

