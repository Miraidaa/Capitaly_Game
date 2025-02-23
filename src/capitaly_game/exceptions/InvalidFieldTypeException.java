package capitaly_game.exceptions;

public class InvalidFieldTypeException extends Exception {
    public InvalidFieldTypeException(String fieldType) {
        super("Invalid field type specified: " + fieldType);
    }
}

