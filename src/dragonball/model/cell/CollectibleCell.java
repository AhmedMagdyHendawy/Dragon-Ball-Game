package dragonball.model.cell;

public class CollectibleCell extends Cell{
private Collectible collectible;





public void onStep(){
	getCellListener().onCollectibleFound(collectible);
}

public CollectibleCell(Collectible collectible) {
	this.collectible = collectible;
}



public String toString() {
	if(collectible==Collectible.DRAGON_BALL)
		return "[d]";
	return "[s]";
}



public Collectible getCollectible() {
	return collectible;
}


}
