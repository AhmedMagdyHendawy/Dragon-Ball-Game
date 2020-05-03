package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

abstract public class Attack implements Serializable {
private String name;
private int damage;

public String getName() {
	return name;
}
public int getDamage() {
	return damage;
}

public Attack(String name, int damage){
	this.name=name;
	this.damage=damage;
}
abstract public int getAppliedDamage(BattleOpponent attacker);

 public void onUse(BattleOpponent attacker, BattleOpponent defender, boolean defenderBlocking) throws NotEnoughKiException{
	
		int dmg= getAppliedDamage(attacker);
		Fighter f= (Fighter) (defender);
		if(defenderBlocking)
		{
			int blockDmg= ((f).getStamina())*100;
			if(dmg<=blockDmg)
			{
			f.setStamina( (f.getStamina() - (int)((Math.ceil(dmg/100.0)))));
			//dmg=0;
			}
			else
			{
				dmg-=blockDmg;
				f.setStamina(0);
				(f).setHealthPoints((f).getHealthPoints()-dmg);
			}
		}else
			(f).setHealthPoints((f).getHealthPoints()-dmg);
		
	
}
 public String toString()
 {
	 return this.getName();
 }
}
