package capitaly_game.players;

import capitaly_game.fields.Field;
import capitaly_game.game.Board; 
import java.util.ArrayList;
import java.util.List;


public abstract class Player {
    public String playerName; 
    public int playerBalance; 
    final PlayerType playerType; 
    List<Field> ownedProperties; 
    public int currentPosition; 

    public Player(String playerName, PlayerType playerType) {
        this.playerName = playerName; 
        this.playerBalance = 10000; 
        this.playerType = playerType; 
        this.ownedProperties = new ArrayList<>(); 
        this.currentPosition = 0; 
    }

    public String getPlayerName() {return playerName; }
    public int getPlayerBalance() {return playerBalance; }
    public PlayerType getPlayerType() {return playerType; }
    public List<Field> getOwnedProperties() { return ownedProperties; }
    public int getCurrentPosition() { return currentPosition; }

    public void setPlayerBalance(int playerBalance) { this.playerBalance = playerBalance; }
    public void setCurrentPosition(int currentPosition) { this.currentPosition = currentPosition; }

    public abstract void buyProperty(Field property); 
    public abstract void move(int diceRoll, Board board); 

}
