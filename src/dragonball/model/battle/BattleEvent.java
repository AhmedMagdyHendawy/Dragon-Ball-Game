package dragonball.model.battle;

import java.util.EventObject;

import dragonball.model.attack.Attack;
import dragonball.model.cell.Collectible;

public class BattleEvent extends EventObject {
	private BattleEventType type;
//	: Specifies the type of the current battle event which is fired by the
//	Battle class. The START event is fired whenever the battle starts while the END is fired upon the
//	winning of any battle party.>>>>>>>>>>>>>>> Each of the remaining events is fired in the end of the method in the
//	Battle class with the same name as the event.
	private BattleOpponent currentOpponent;
	private BattleOpponent winner;
	private Attack attack;
//	The performed attack within the current battle event. It denotes the attack
//	that caused the ATTACK event to be fired. It is an extra piece of information in the BattleEvent
//	object and is changed each time the ATTACK event is fired to reflect the attack that was done.
	private Collectible collectible;
	
	public BattleEvent(Battle battle, BattleEventType type){
		
		super(battle);
		this.type=type;
		currentOpponent= battle.getAttacker();
	}

	public BattleEvent(Battle battle, BattleEventType type, BattleOpponent winner){
		this(battle,type);
		this.winner=winner;
	}
	public BattleEvent(Battle battle, BattleEventType type, Attack attack){
		this(battle,type);
		this.attack=attack;
	}
	public BattleEvent(Battle battle, BattleEventType type, Collectible collectible){
		this(battle,type);
		this.collectible=collectible;
	}
	public BattleEventType getType() {
		return type;
	}


	public BattleOpponent getCurrentOpponent() {
		return currentOpponent;
	}


	public BattleOpponent getWinner() {
		return winner;
	}


	public Attack getAttack() {
		return attack;
	}


	public Collectible getCollectible() {
		return collectible;
	}

	

}
