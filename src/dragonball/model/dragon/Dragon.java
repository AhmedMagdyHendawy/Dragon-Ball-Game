package dragonball.model.dragon;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements Serializable{
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;
	public Dragon(String name, ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int abilityPoints) {
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}
	public DragonWish[] getWishes(){
		////<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
		DragonWish w=null;
		DragonWish z=null;
		DragonWish x=new DragonWish(this,DragonWishType.SENZU_BEANS,senzuBeans);
		DragonWish y=new DragonWish(this,DragonWishType.ABILITY_POINTS,abilityPoints);
		if(superAttacks!=null && superAttacks.size()>0)
		 w=new DragonWish(this, DragonWishType.SUPER_ATTACK,superAttacks.get((int)(Math.random()*superAttacks.size())));
		if(ultimateAttacks!=null && ultimateAttacks.size()>0)
		 z=new DragonWish(this, DragonWishType.ULTIMATE_ATTACK,ultimateAttacks.get((int)(Math.random()*ultimateAttacks.size())));
		DragonWish [] d={x,y,w,z};
		return d;
		
	}
	public String getName() {
		return name;
	}
	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}
	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public int getAbilityPoints() {
		return abilityPoints;
	}
	
	
}
