package capitaly_game.fields;
import capitaly_game.players.Player;

public class Lucky extends Field {
    public int playerRewardewardAmount;

    public Lucky(int position, String name, int reward) {
        super(position, name, FieldType.LUCKY, 0);
        this.playerRewardewardAmount = reward;
    }

    public int getPlayerRewardewardAmount(){return this.playerRewardewardAmount;}
    
    @Override
    public void Play(Player player) {
        player.setPlayerBalance(player.getPlayerBalance() + playerRewardewardAmount);
    }
}