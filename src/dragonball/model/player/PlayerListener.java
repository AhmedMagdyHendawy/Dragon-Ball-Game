package dragonball.model.player;

import dragonball.model.dragon.DragonWish;

public interface PlayerListener {

	void onDragonCalled();
	void onWishChosen(DragonWish wish);
}
