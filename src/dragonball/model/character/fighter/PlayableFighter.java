package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;

abstract public class PlayableFighter extends Fighter implements PlayableCharacter {
	private int xp;
	private int targetXp;
	private int abilityPoints;
	
	public PlayableFighter(String name, int level, int xp, int targetXp, int maxHealthPoints,
			int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
			ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		
		super( name,  level, maxHealthPoints,  blastDamage,
			 physicalDamage, maxKi, maxStamina,  superAttacks,
			 ultimateAttacks);
		
		this.xp=xp;
		this.targetXp=targetXp;
		this.abilityPoints=abilityPoints;
		setHealthPoints(maxHealthPoints);
		setStamina(maxStamina);
		setKi(0);
	}
	
	
	public PlayableFighter(String name, int maxHealthPoints, int blastDamage, int physicalDamage,
			int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
			ultimateAttacks)
	{
		this( name, 1,  0, 10, maxHealthPoints,
			blastDamage,  physicalDamage, 0,  maxKi, maxStamina,
			 superAttacks, ultimateAttacks);
		setHealthPoints(maxHealthPoints);
		setStamina(maxStamina);
		setKi(0);
	
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		boolean leveled=false;
		while(xp>=targetXp){
			
			setLevel(getLevel()+1);
			setAbilityPoints(getAbilityPoints()+2);
			xp-=targetXp;
			setTargetXp(getTargetXp()+20);
			
			leveled=true;
		}
		if(leveled)
		this.xp = 0;
		else
			this.xp=xp;
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}
	public String getRace(){
		
		if (this instanceof Earthling)
			return "Earthling";
		if (this instanceof Saiyan)
			return "Saiyan";
		if (this instanceof Frieza)
			return "Frieza";
		if (this instanceof Majin)
			return "Majin";
		return "Namekian";
							
	}
	
	

}
