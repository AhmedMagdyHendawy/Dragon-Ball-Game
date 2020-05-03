package dragonball.model.world;

import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;

public interface WorldListener {
	void onFoeEncountered(NonPlayableFighter foe);
	void onCollectibleFound(Collectible collectible);
}
