package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class PhysicalAttack extends Attack{

	public PhysicalAttack() {
		super("Physical Attack",50);
		
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f= (Fighter) (attacker);
		double dmg= 50+ f.getPhysicalDamage();
	if(attacker.getClass()==Saiyan.class && ((Saiyan) (f)).isTransformed()){
		dmg*=1.25;
	}
		return (int) dmg;
		
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,boolean defenderBlocking) throws NotEnoughKiException {
		Fighter f= (Fighter) (attacker);
		f.setKi(f.getKi()+1);
		super.onUse(attacker, defender, defenderBlocking);
	}

	
}
