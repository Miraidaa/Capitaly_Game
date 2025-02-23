package capitaly_game.game;

import capitaly_game.fields.Field;
import capitaly_game.players.Player;
import java.util.ArrayList;
import java.util.List;
import capitaly_game.exceptions.BankruptException;

public class Game {
    public List<Field> fields;
    public List<Player> players;
    public int currentPlayerIndex;
    public Board board;

  
    public Game(List<Field> fields) {
        this.fields = fields;
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.board = new Board(fields); 
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start(List<Integer> diceRolls) {
        int round = 0;
        for (Integer roll : diceRolls) {
            if (players.isEmpty()) {
                System.out.println("No players left in the game.");
                break;
            }
            Player currentPlayer = players.get(currentPlayerIndex);
            currentPlayer.move(roll, board);
            
            if (currentPlayer.getPlayerBalance() < 0) {
                try {
                    throw new BankruptException(currentPlayer.getPlayerName());
                } catch (BankruptException e) {
                    System.out.println(e.getMessage());
                    players.remove(currentPlayerIndex);
                    if (players.isEmpty()) {
                        System.out.println("All players have gone bankrupt. Game over.");
                        break;
                    }
                    if (currentPlayerIndex >= players.size()) {
                        currentPlayerIndex = 0;
                    }
                }
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
            System.out.println("After round " + (round + 1) + ":");
            printPlayersStatus();
            round++;
        }
    }
    
  
    public Field getFieldAtPosition(int position) {
        return fields.get(position % fields.size());
    }

    private void printPlayersStatus() {
        for (Player player : players) {
            System.out.println("Player: " + player.getPlayerName());
            System.out.println("Balance: " + player.getPlayerBalance());
            System.out.print("Owned Properties: ");
            if (player.getOwnedProperties().isEmpty()) {
                System.out.println("None");
            } else {
                for (Field property : player.getOwnedProperties()) {
                    System.out.print(property.getFieldName() + " ");
                }
                System.out.println();
            }
            System.out.println(); 
        }
    }
}
