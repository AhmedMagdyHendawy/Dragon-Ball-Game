package dragonball.model.cell;

import dragonball.model.character.fighter.NonPlayableFighter;

public class FoeCell extends Cell {
private NonPlayableFighter foe;


	

public void onStep(){
	getCellListener().onFoeEncountered(foe);
}

	public FoeCell( NonPlayableFighter foe) {
	this.foe=foe;
}

	

	public NonPlayableFighter getFoe() {
		return foe;
	}


	public String toString() {
		if(foe.isStrong())
		return "[b]";
		return "[w]";
	}

}
