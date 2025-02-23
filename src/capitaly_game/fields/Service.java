package capitaly_game.fields;
import capitaly_game.players.Player;

public class Service extends Field {
    public int serviceCost;

    public Service(int position, String name, int serviceCost) {
        super(position, name, FieldType.SERVICE, 0);
        this.serviceCost = serviceCost;
    }

    @Override
    public void Play(Player player) {
        player.setPlayerBalance(player.getPlayerBalance() - serviceCost);
        System.out.println(player.getPlayerName() + " paid " + serviceCost + " at " + getFieldName());
    }
}
