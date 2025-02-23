package capitaly_game.players;

import capitaly_game.fields.Field;
import capitaly_game.game.Board;

public class Careful extends Player {

    public Careful(String playerName) {
        super(playerName, PlayerType.CAREFUL);
    }

    @Override
    public void buyProperty(Field property) {
        int maxSpend = getPlayerBalance() / 2; // Max spend is half of current balance
        if (getPlayerBalance() >= property.getFieldPrice() && property.getFieldPrice() <= maxSpend) {
            setPlayerBalance(getPlayerBalance() - property.getFieldPrice());
            property.setOwner(this);
            getOwnedProperties().add(property);
        }
    }

    @Override
    public void move(int diceRoll, Board board) {
        int numberOfFields = board.getNumberOfFields(); // Get the number of fields from the board instance
        int newPosition = (getCurrentPosition() + diceRoll) % numberOfFields; // Use the number of fields from the board
        setCurrentPosition(newPosition);
        Field currentField = board.getFieldAtPosition(newPosition);
    
        currentField.Play(this);
        
        if (currentField.getFieldOwner() == null && getPlayerBalance() >= currentField.getFieldPrice()) {
            buyProperty(currentField);
        }
    }
}
