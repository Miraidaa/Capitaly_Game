package capitaly_game.players;

import capitaly_game.fields.Field;
import capitaly_game.game.Board;

public class Greedy extends Player {

    public Greedy(String playerName) {
        super(playerName, PlayerType.GREEDY);
    }

    @Override
    public void buyProperty(Field property) {
        if (getPlayerBalance() >= property.getFieldPrice()) {
            setPlayerBalance(getPlayerBalance() - property.getFieldPrice());
            property.setOwner(this);
            getOwnedProperties().add(property);
        }
    }

    @Override
    public void move(int diceRoll, Board board) {
        int numberOfFields = board.getNumberOfFields(); // Get the number of fields dynamically
        int newPosition = (getCurrentPosition() + diceRoll) % numberOfFields; // Use the dynamic count
        setCurrentPosition(newPosition);
        Field currentField = board.getFieldAtPosition(newPosition);
 
        currentField.Play(this);
        if (currentField.getFieldOwner() == null && getPlayerBalance() >= currentField.getFieldPrice()) {
            buyProperty(currentField);
        }
    }
}
