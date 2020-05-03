package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperAttack extends Attack {

	public SuperAttack(String name, int damage) {
		super(name,damage);
	}

	
	
	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f=	(Fighter) attacker;
		if (this.getClass()==MaximumCharge.class){
		
			return 0;
		}
	double	dmg=f.getBlastDamage()+ this.getDamage();
		if(attacker.getClass()==Saiyan.class && ((Saiyan) (f)).isTransformed()){
			dmg*=1.25;
		}
	
		return (int) dmg;
		
	}

	public void onUse(BattleOpponent attacker, BattleOpponent
			defender, boolean defenderBlocking) throws NotEnoughKiException{
		Fighter f=	(Fighter) attacker;
		if (this.getClass()==MaximumCharge.class){
			f.setKi(f.getKi()+3);
		}
		else
			if((attacker.getClass()==Saiyan.class&&((Saiyan) attacker).isTransformed())){
				super.onUse(attacker, defender, defenderBlocking);
			}else
		if(f.getKi()>0)
		{
			f.setKi(f.getKi()-1);
			super.onUse(attacker, defender, defenderBlocking);
		}else
			throw new NotEnoughKiException(1,0);
		
	}
	
}
