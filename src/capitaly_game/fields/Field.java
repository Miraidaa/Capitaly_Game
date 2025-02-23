
package capitaly_game.fields;
import capitaly_game.players.Player;


public abstract class Field {
    public int fieldPosition; 
    public String fieldName;
    public FieldType type; 
    public Player fieldOwner; 
    public int fieldPrice; 


    public Field (int position, String name, FieldType type, int price) {
        this.fieldPosition = position; 
        this.fieldName = name; 
        this.type = type; 
        this.fieldPrice = price;
    }

    public abstract void Play(Player player); 
    
    public int getFieldPosition(){return this.fieldPosition;}
    public String getFieldName(){return this.fieldName;}
    public Player getFieldOwner(){return this.fieldOwner;}
    public FieldType getFieldType(){return this.type;}
    public int getFieldPrice(){return this.fieldPrice;}

    public void setOwner(Player owner){this.fieldOwner = owner; }

    @Override 
    public String toString() {
        return "Field{" +
                "position=" + fieldPosition +
                ", name='" + fieldName + '\'' +
                ", type=" + type +
                ", owner=" + (fieldOwner != null ? fieldOwner.getPlayerName() : "none") +
                '}';
    }
}
