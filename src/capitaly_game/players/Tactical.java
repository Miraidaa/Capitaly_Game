package capitaly_game.players;

import capitaly_game.fields.Field;
import capitaly_game.game.Board; 

public class Tactical extends Player {

    private boolean canBuy = true;

    public Tactical(String playerName) {
        super(playerName, PlayerType.TACTICAL);
    }

    @Override
    public void buyProperty(Field property) {
        if (canBuy && getPlayerBalance() >= property.getFieldPrice()) {
            setPlayerBalance(getPlayerBalance() - property.getFieldPrice());
            property.setOwner(this);
            getOwnedProperties().add(property);
            canBuy = false; 
        } else {
            canBuy = true; 
        }
    }

 @Override
 public void move(int diceRoll, Board board) {
    int numberOfFields = board.getNumberOfFields(); 
    int newPosition = (getCurrentPosition() + diceRoll) % numberOfFields; 
    setCurrentPosition(newPosition);
    Field currentField = board.getFieldAtPosition(newPosition); 
    currentField.Play(this);
}

    
}
