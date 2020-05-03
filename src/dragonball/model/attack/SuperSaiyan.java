package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack{

	public SuperSaiyan() {
		super("Super Saiyan",0);
	}
	
	public void onUse(BattleOpponent attacker, BattleOpponent
			defender, boolean defenderBlocking) throws NotEnoughKiException{
		Fighter f=	(Fighter) attacker;
		if(attacker instanceof Saiyan && f.getKi()>2)
					((Saiyan) attacker).setTransformed(true);
		else
			throw new NotEnoughKiException(3,f.getKi());
		
	}

}
