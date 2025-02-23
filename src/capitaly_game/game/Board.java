package capitaly_game.game;

import capitaly_game.fields.Field;
import java.util.List;

public class Board {
    private int numberOfFields; 
    private List<Field> fields;

    public Board(List<Field> fields) {
        if (fields == null || fields.size() <= 0) {
            throw new IllegalArgumentException("Number of fields must be greater than zero.");
        }
        this.numberOfFields = fields.size(); 
        this.fields = fields; 
    }

    public Field getFieldAtPosition(int position) {
        return fields.get(position % numberOfFields); 
    }

    public int getNumberOfFields() {
        return numberOfFields; 
    }

  
    public void setFieldAtPosition(int position, Field field) {
        fields.set(position % numberOfFields, field); 
    }

    @Override
    public String toString() {
        StringBuilder boardDescription = new StringBuilder();
        for (int i = 0; i < fields.size(); i++) {
            boardDescription.append("Position ").append(i).append(": ").append(fields.get(i)).append("\n");
        }
        return boardDescription.toString();
    }
}
