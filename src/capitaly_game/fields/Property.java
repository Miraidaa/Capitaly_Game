package capitaly_game.fields;
import capitaly_game.players.Player;


public class Property extends Field {
    private boolean hasHouse; 
    private final int buyPrice = 1000;
    private final int housePrice = 4000;
    private final int rentWithoutHouse = 500;
    private final int rentWithHouse = 2000;
    
    public Property(int position, String name) {
        super(position, name, FieldType.PROPERTY, 0);
        this.hasHouse = false;
    }

    public boolean hasHouse() { return hasHouse; }
    public int getBuyPrice() { return buyPrice; }

    @Override
    public void Play(Player player) {
        if (getFieldOwner() == null) {
            player.buyProperty(this);
        } else if (getFieldOwner() != player) {
            int rent = hasHouse ? rentWithHouse : rentWithoutHouse;
            player.setPlayerBalance(player.getPlayerBalance() - rent);
            getFieldOwner().setPlayerBalance(getFieldOwner().getPlayerBalance() + rent);
        }
    }

    public void buildHouse(Player player) {
        if (player.getPlayerBalance() >= housePrice) {
            hasHouse = true;
            player.setPlayerBalance(player.getPlayerBalance() - housePrice);
        }
    }
}
