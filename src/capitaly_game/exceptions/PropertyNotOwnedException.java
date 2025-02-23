package capitaly_game.exceptions;

public class PropertyNotOwnedException extends Exception {
    public PropertyNotOwnedException(String playerName, String propertyName) {
        super(playerName + " attempted to access a property they do not own: " + propertyName);
    }
}
