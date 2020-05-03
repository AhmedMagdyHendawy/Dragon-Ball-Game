package dragonball.model.game;

import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.dragon.Dragon;

public interface GameListener {

	void onDragonCalled(Dragon dragon);
	void onCollectibleFound(Collectible collectible);
	void onBattleEvent(BattleEvent e);
}
