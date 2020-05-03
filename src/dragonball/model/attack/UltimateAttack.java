package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class UltimateAttack extends Attack {

	public UltimateAttack(String name, int damage) {
		super(name,damage);
	}
	
	
	
	
	public int getAppliedDamage(BattleOpponent attacker) {
		Fighter f=	(Fighter) attacker;
		if (this.getClass()==SuperSaiyan.class){
		
			return 0;
		}
		double	dmg=f.getBlastDamage()+ this.getDamage();
		if(attacker.getClass()==Saiyan.class && ((Saiyan) (f)).isTransformed()){
			dmg*=1.25;
		}
	
		return (int) dmg;
	
		
	}

	public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
		Fighter f=	(Fighter) attacker;
		if(attacker instanceof Saiyan && ((Saiyan) attacker).isTransformed())
		super.onUse(attacker, defender, defenderBlocking);
		else
					if(f.getKi()>2)
					{
						f.setKi(f.getKi()-3);
						super.onUse(attacker, defender, defenderBlocking);
					}else
						throw new NotEnoughKiException(3,f.getKi());
				
			
		}
	
	

}
