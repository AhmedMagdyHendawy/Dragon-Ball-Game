package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public interface CellListener {

	void onFoeEncountered(NonPlayableFighter foe);
	void onCollectibleFound(Collectible collectible);
}
