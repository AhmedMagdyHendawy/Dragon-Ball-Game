package dragonball.model.player;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;

public class Player implements Serializable{
	private String name;
	private ArrayList<PlayableFighter> fighters ;
	private ArrayList<SuperAttack> superAttacks ;
	private ArrayList<UltimateAttack> ultimateAttacks ;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter ;
	private int exploredMaps ;
	private PlayerListener playerListener;
	
	public Player (String name){
		this.name =name;
		fighters= new ArrayList<PlayableFighter>();
		
		superAttacks= new ArrayList<SuperAttack>();
		ultimateAttacks= new ArrayList<UltimateAttack>();
//		ultimateAttacks.add(new SuperSaiyan());
//		setDragonBalls(6);
	}
	
	public int getMaxFighterLevel(){
		int maxLevel=0;
		if (fighters!=null)
		for (PlayableFighter p: fighters) {
			maxLevel=Math.max(maxLevel, p.getLevel());
		}
		return maxLevel;
	}
	public void callDragon(){
		
		if(playerListener!=null){
			playerListener.onDragonCalled();
			setDragonBalls(0);
		}
	}
	public void chooseWish(DragonWish wish){
		switch(wish.getType()){
		case SENZU_BEANS: setSenzuBeans(getSenzuBeans()+wish.getSenzuBeans());break;
		case ABILITY_POINTS:getActiveFighter().setAbilityPoints((getActiveFighter().getAbilityPoints()+wish.getAbilityPoints()));break;
		case SUPER_ATTACK:	getSuperAttacks().add(wish.getSuperAttack());break;
		case ULTIMATE_ATTACK:	getUltimateAttacks().add(wish.getUltimateAttack());break;
		default: break;
		}
		if(playerListener!=null)
			playerListener.onWishChosen(wish);
	}
	
	public void createFighter(char race, String name){
		PlayableFighter f = null;
		switch (race){
		case 'E': f= new Earthling(name);break;
		case 'S': f= new Saiyan(name);break;
		case 'N': f= new Namekian(name);break;
		case 'F': f= new Frieza(name);break;
		case 'M': f= new Majin(name);break;
		default: break;
			
		}
		if(f!=null){
		if(fighters.size()==0){
			setActiveFighter(f);
		}
		fighters.add(f);
		}
	}
	
	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute) throws NotEnoughAbilityPointsException{
		if(fighter!=null)
		if(fighter.getAbilityPoints()>0){
		switch (fighterAttribute){
		case 'H': fighter.setMaxHealthPoints(fighter.getMaxHealthPoints()+50);break;
		case 'B': fighter.setBlastDamage(fighter.getBlastDamage()+50);break;
		case 'P': fighter.setPhysicalDamage(fighter.getPhysicalDamage()+50);break;
		case 'K': fighter.setMaxKi(fighter.getMaxKi()+1);break;
		case 'S': fighter.setMaxStamina(fighter.getMaxStamina()+1);break;
		default: break;
		}
		fighter.setAbilityPoints(fighter.getAbilityPoints()-1);
		}else
			throw new NotEnoughAbilityPointsException("Not enough ability points, Required: 1, available: 0");
	}
	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack) throws MaximumAttacksLearnedException, DuplicateAttackException{
		if (fighter.getSuperAttacks().contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		
		if(fighter.getSuperAttacks().size()<4){
			if(oldAttack!=null)
				fighter.getSuperAttacks().remove(oldAttack);	
			
			fighter.getSuperAttacks().add(newAttack);
		}else
			throw new MaximumAttacksLearnedException("Fighter's Super Attacks have reached a maximum capacity of 4");
		
		
	}
	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack oldAttack) throws DuplicateAttackException, MaximumAttacksLearnedException, NotASaiyanException{
		if (fighter.getUltimateAttacks().contains(newAttack))
			throw new DuplicateAttackException(newAttack);
		if(newAttack instanceof SuperSaiyan)
			if(! (fighter instanceof Saiyan))
				throw new NotASaiyanException("The current fighter is not of type saiyan.");
		
		if(fighter.getUltimateAttacks().size()<2){
			if(oldAttack!=null)
				fighter.getUltimateAttacks().remove(oldAttack);	
			
			fighter.getUltimateAttacks().add(newAttack);
		}else
			throw new MaximumAttacksLearnedException("Fighter's Ultimate Attacks have reached a maximum capacity of 2");
	}
	
	
	public PlayerListener getPlayerListener() {
		return playerListener;
	}

	public void setPlayerListener(PlayerListener playerListener) {
		this.playerListener = playerListener;
	}

	public Player(String name, ArrayList<PlayableFighter> fighters,
			ArrayList<SuperAttack> superAttacks,
			ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans,
			int dragonBalls, PlayableFighter activeFighter, int exploredMaps) {
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}
	public void setFighters(ArrayList<PlayableFighter> fighters) {
		
		this.fighters = fighters;
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
	public int getSenzuBeans() {
		return senzuBeans;
	}
	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}
	public int getDragonBalls() {
		return dragonBalls;
	}
	public void setDragonBalls(int dragonBalls) {
		if(this!=null)
		this.dragonBalls = dragonBalls;
	}
	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}
	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}
	public int getExploredMaps() {
		return exploredMaps;
	}
	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}

	public String getName() {
		return name;
	}
	

}
