package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Frieza extends PlayableFighter{
	  
	
	
	public Frieza(String name)
	{
		super(name, 1100, 75 ,75, 4, 4, new ArrayList<SuperAttack>(), new ArrayList<UltimateAttack>());
		
	}
	
	public Frieza(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks)
	{ 
		super(name, level, xp, targetXp, maxHealthPoints, blastDamage,
				physicalDamage, abilityPoints, maxKi, maxStamina, superAttacks,
				ultimateAttacks);
	}

	public void onAttackerTurn() {
		setStamina(getStamina()+1);
	}

	public void onDefenderTurn() {
		setStamina(getStamina()+1);
	}
	
	
	
}
