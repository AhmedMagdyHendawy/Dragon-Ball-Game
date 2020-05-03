package dragonball.model.character.fighter;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.Character;


 abstract public class Fighter extends Character implements BattleOpponent , Serializable{

	private int level;
	private int blastDamage;
	private int physicalDamage;
	private int healthPoints;
	private int maxHealthPoints;
	private int ki;
	private int maxKi;
	private int stamina;
	private int maxStamina;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	 
	 public Fighter(String name, int level, int maxHealthPoints, int blastDamage,
			 int physicalDamage, int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks,
			 ArrayList<UltimateAttack> ultimateAttacks){
		 super(name);
		 this.level=level;
		 this.maxHealthPoints= maxHealthPoints;
		 this.blastDamage=blastDamage;
		 this.physicalDamage=physicalDamage;
		 this.maxKi=maxKi;
		 this.maxStamina=maxStamina;
		 this.superAttacks=superAttacks;
		 this.ultimateAttacks=ultimateAttacks;
	 }
	 public Fighter(){
		 super("s");
	 }
	 
	abstract public void onAttackerTurn();
	abstract public void onDefenderTurn();
	
	 public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBlastDamage() {
		return blastDamage;
	}

	public void setBlastDamage(int blastDamage) {
		this.blastDamage = blastDamage;
	}

	public int getPhysicalDamage() {
		return physicalDamage;
	}

	public void setPhysicalDamage(int physicalDamage) {
		this.physicalDamage = physicalDamage;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		if(healthPoints>maxHealthPoints)
			this.healthPoints=maxHealthPoints;
		else
			if (healthPoints<0)
				this.healthPoints=0;
			else
				this.healthPoints = healthPoints;
	}

	public int getMaxHealthPoints() {
		return maxHealthPoints;
	}

	public void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public int getKi() {
		return ki;
	}

	public void setKi(int ki) {
		if(ki>maxKi)
			this.ki=maxKi;
		else
			if (ki<0)
				this.ki=0;
			else
				this.ki = ki;
	}

	public int getMaxKi() {
		return maxKi;
	}

	public void setMaxKi(int maxKi) {
		this.maxKi = maxKi;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		if(stamina>maxStamina)
			this.stamina=maxStamina;
		else
			if (stamina<0)
				this.stamina=0;
			else
				this.stamina = stamina;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	 }

