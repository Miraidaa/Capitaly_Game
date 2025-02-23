package capitaly_game;

import capitaly_game.fields.Field;
import capitaly_game.fields.Property;
import capitaly_game.fields.Lucky;
import capitaly_game.fields.Service;
import capitaly_game.players.Player;
import capitaly_game.players.Careful;
import capitaly_game.players.Greedy;
import capitaly_game.players.Tactical;
import capitaly_game.game.Game;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Field> fields = new ArrayList<>();
        List<Player> players = new ArrayList<>();
        List<Integer> diceRolls = new ArrayList<>();
        String filePath = "D:\\HP\\Desktop\\Programming Technology\\Assignments\\Capitaly_Game\\src\\capitaly_game\\tests\\input.txt";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            
            if ((line = br.readLine()) == null || line.trim().isEmpty()) {
                throw new IllegalArgumentException("Input file is empty.");
            }
            
           
            int numberOfFields = Integer.parseInt(line.trim());
            if (numberOfFields <= 0) {
                throw new IllegalArgumentException("The number of fields must be greater than zero.");
            }
            System.out.println("Number of fields: " + numberOfFields);
            
            
            for (int i = 0; i < numberOfFields && (line = br.readLine()) != null; i++) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split(" ");
                    String fieldType = parts[0].toLowerCase();
                    switch (fieldType) {
                        case "property" -> {
                            String propertyName = "Property " + fields.size();
                            fields.add(new Property(fields.size(), propertyName));
                        }
                        case "service" -> {
                            if (parts.length < 2) {
                                throw new IllegalArgumentException("Service field is missing the cost.");
                            }
                            try {
                                int serviceCost = Integer.parseInt(parts[1]);
                                fields.add(new Service(fields.size(), fieldType, serviceCost));
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Invalid cost for service field: " + parts[1]);
                            }
                        }
                        case "lucky" -> {
                            if (parts.length < 2) {
                                throw new IllegalArgumentException("Lucky field is missing the bonus/penalty amount.");
                            }
                            try {
                                int luckyCost = Integer.parseInt(parts[1]);
                                if (luckyCost < 0) {
                                    throw new IllegalArgumentException("Lucky field cost must not be negative.");
                                }
                                fields.add(new Lucky(fields.size(), fieldType, luckyCost));
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException("Invalid cost for lucky field: " + parts[1]);
                            }
                        }
                        default -> throw new IllegalArgumentException("Unknown field type: " + fieldType);
                    }
                } else {
                    throw new IllegalArgumentException("Field definition missing or incomplete.");
                }
            }
            
         
            line = br.readLine();
            if (line != null && !line.trim().isEmpty()) {
                int numberOfPlayers = Integer.parseInt(line.trim());
                System.out.println("Number of players specified: " + numberOfPlayers);
                
              
                for (int i = 0; i < numberOfPlayers && (line = br.readLine()) != null; i++) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.trim().split(" ");
                        String playerName = parts[0];
                        String strategy = parts[1].toLowerCase();
                        switch (strategy) {
                            case "tactical" -> players.add(new Tactical(playerName));
                            case "greedy" -> players.add(new Greedy(playerName));
                            case "careful" -> players.add(new Careful(playerName));
                            default -> throw new IllegalArgumentException("Unknown strategy for player: " + playerName);
                        }
                    } else {
                        System.out.println("Warning: Fewer players provided than expected. Stopping player creation.");
                        break;
                    }
                }
            } else {
                throw new IllegalArgumentException("Number of players is missing.");
            }
            
          
            line = br.readLine();
            if (line != null && !line.trim().isEmpty()) {
                try {
                    diceRolls.addAll(Arrays.stream(line.trim().split(" "))
                            .map(Integer::parseInt)
                            .peek(roll -> {
                                if (roll < 0) {
                                    throw new IllegalArgumentException("Dice roll contains a negative number: " + roll);
                                }
                            })
                            .toList());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Error parsing dice roll numbers: " + e.getMessage());
                }
            } else {
                throw new IllegalArgumentException("Dice rolls are missing or empty.");
            }
            
           
            Game game = new Game(fields);
            for (Player player : players) {
                game.addPlayer(player);
            }
            game.start(diceRolls);
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
