package dragonball.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;

	private boolean INSANE= false;
	private PlayableFighter knownPlayer;
	private ArrayList<SuperAttack>  knownSuperAttacks;
	private  ArrayList<UltimateAttack> knownUltimateAttacks;

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;
		Fighter meFighter = (Fighter) me;
		PlayableFighter meplayable= (PlayableFighter) me;
		knownSuperAttacks	= (INSANE)? (ArrayList<SuperAttack>)meFighter.getSuperAttacks().clone():new ArrayList<SuperAttack>();
		knownUltimateAttacks	= (INSANE)?(ArrayList<UltimateAttack>) meFighter.getUltimateAttacks().clone():new ArrayList<UltimateAttack>();
		knownPlayer= (meplayable.getRace().equals("Saiyan"))? new Saiyan(""):(meplayable.getRace().equals("Earthling"))? new Earthling(""):new Majin("");
		knownPlayer.setSuperAttacks(knownSuperAttacks);
		knownPlayer.setUltimateAttacks(knownUltimateAttacks);
				
		// set current values appropriately
		
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a previous battle
		if (me instanceof Saiyan) {
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public BattleOpponent getDefender() {
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks() {
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn() {
		attacker = getDefender();
	}

	public void endTurn()  {
		// reset block mode
		if (attacker == me && foeBlocking) {
			foeBlocking = false;
		} else if (attacker == foe && meBlocking) {
			meBlocking = false;
		}

		// if i'm dead
		if (((Fighter) me).getHealthPoints() == 0) {
			// tell everyone my opponent won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, foe));
			// if my opponent is dead
		} else if (((Fighter) foe).getHealthPoints() == 0) {
			// tell everyone i won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
		} else {
			switchTurn();

			getAttacker().onDefenderTurn();
			getDefender().onAttackerTurn();

			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
		}
	}

	public void start() {
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}

//	 used to automate turn for opponent a.k.a. ai
//	public void play() throws NotEnoughKiException {
//		if (new Random().nextInt(100) > 15) {
//			ArrayList<Attack> attacks = getAssignedAttacks();
//			Attack randomAttack;
//			randomAttack = attacks.get(new Random().nextInt(attacks.size()));
//
//			attack(randomAttack);
//		} else {
//			block();
//		}
//	}
	// used to automate turn for opponent a.k.a. ai
		public void play() throws NotEnoughKiException { //An assumption is made, that is the player plays using the optimal strategy.
			NonPlayableFighter nfoe= (NonPlayableFighter) foe;
			Attack a= getBestAttack((Fighter) nfoe);
			PhysicalAttack pa= new PhysicalAttack();
////////////// Finishing Blow: if i can kill the opponent in 1 shot.
			if(pa.getAppliedDamage(foe)>=getEffectiveHP(me)){//if a physical attack is enough.
				attack(pa);
				return;
			}
			if(getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)>=getEffectiveHP(me) && nfoe.getKi()>0){//if my best super attack is enough.
				attack(getBestSuperAttack(nfoe.getSuperAttacks()));
				return;
			}
			if(getBestUltimateAttack(nfoe.getUltimateAttacks()).getAppliedDamage(foe)>=getEffectiveHP(me) && nfoe.getKi()>=3){//if my best ultimate attack is enough.
				attack(getBestUltimateAttack(nfoe.getUltimateAttacks()));
				return;
			}
			
////////////// Anti Finishing Blow: if the opponent can kill me in 1 shot.
			// If he can kill me with a physical attack. I'm doomed, i'll just attack. or if he can kill me with a super and is an earthling(he will always have enough ki to use it on his next turn)
			if(pa.getAppliedDamage(me)>=getEffectiveHP(foe) || getBestSuperAttack(knownSuperAttacks).getAppliedDamage(me)>=getEffectiveHP(foe) &&( (me) instanceof Earthling))
			{
				//skip to attacks.
			}else{ //if he can kill me with a super attack and will have enough ki to use it in next turn.
				if((getBestSuperAttack(knownSuperAttacks).getAppliedDamage(me)>=getEffectiveHP(foe) &&enemyWillHaveAtleastXKi((PlayableFighter)me, 1))
				//if he can kill me with an ultimate attack and will have enough ki to use it in next turn.
				||	getBestUltimateAttack(knownUltimateAttacks).getAppliedDamage(me)>=getEffectiveHP(foe) && enemyWillHaveAtleastXKi(( PlayableFighter)me, 3))
				{
					block();
					return;
				}
			}
////////////// If the opponent poses a great threat. block.
			//Threat definition: if the opponent is a transformed Saiyan with enough ki for a super or an ultimate attack.
			//					 or if the opponent will have enough ki for a damage dealing ultimate attack (i.e not super Saiyan).
			
			//if the opponents best attack is a physical attack.  //do not block.
			if(getBestAttack( knownPlayer) instanceof PhysicalAttack)
			{
				 //Enemy poses no threat. Skip to attacks.
			}
			else // if the opponent is a transformed Saiyan with enough ki for a super or an ultimate attack.
				if(me instanceof SuperSaiyan && ((Saiyan)me).isTransformed())
				{
					if(enemyWillHaveAtleastXKi((PlayableFighter)me, 1))
					{
						if(nfoe.getStamina()>=nfoe.getMaxStamina()/2){ //if the stamina i have is enough to block a good amount of damage.
							block();
							return;
						}else
						{
							//skip to attacks.
						}
					}
					else
					{
						// enemy transformation will end. skip to attacks.
					}

				}else{
					//opponent is not a transformed saiyan.
					//if the opponent can use a damage dealing ultimate attack.(not a super Saiyan transformation).
					// an assumption is made that is the best strategy is to use super saiyan before using your best attacks. as long as it is not the finishing blow.
					if (getBestAttack(knownPlayer) instanceof UltimateAttack && !hasSuperSaiyan(knownPlayer) && enemyWillHaveAtleastXKi((PlayableFighter)me, ((Fighter)me).getMaxKi())){
						if(nfoe.getStamina()>=nfoe.getMaxStamina()/2){ //if the stamina i have is enough to block a good amount of damage.
							block();
							return;
						}
					}else{
						//opponent will not use a damage dealing ultimate attack.
						//skip to attacks.
					}
				}
				
				
			//If my best attack is a physical attack.
			if (a instanceof PhysicalAttack){
				attack(a);
				return;
			}
			if(a instanceof SuperAttack){
				if(!hasMaximumCharge(foe)){ //If my best attack is super & i don't have maximum charge.
//					System.out.println("nfoe super");
					if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
						attack(a);
					else			// If not. use a physical attack.
						attack(pa);
					
					return;
				}else{//If my best attack is super & i have maximum charge.
					
					//if it is better to use a maximum charge and 3 super attacks than a physical attack then a super(twice).
					if(a.getAppliedDamage(foe)*3> (a.getAppliedDamage(foe)+pa.getAppliedDamage(foe))*2)
					{
						if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
							attack(a);
						else	
							if (nfoe.getKi()-nfoe.getMaxKi()>1) 
								attack(new MaximumCharge());
							else
								attack(pa);

						return;
						
					}else{ //if it is better to use a physical attack then a super attack.
						if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
							attack(a);
						else			// If not. use a physical attack.
							attack(pa);
						
						return;
					}
						
				}
			}if (a instanceof UltimateAttack){//If my best attack is ultimate & i don't have maximum charge.
				
				if(!hasMaximumCharge(foe)){
					//if using 3 attacks and an ultimate is better than 2 attacks and 2 supers.
					if(a.getAppliedDamage(foe)+3*pa.getAppliedDamage(foe)>getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)*2+2*pa.getAppliedDamage(foe))
					{
					if ((nfoe.getKi()>2 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi()/3)>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
						attack(a);
					else			// If not. use a physical attack.
						attack(pa);
					}else
					{//using a pa then sa is more logical.
						if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
							attack(getBestSuperAttack(nfoe.getSuperAttacks()));
						else			// If not. use a physical attack.
							attack(pa);
					}
					//TODO
					return;
				}else{//If my best attack is ultimate & i have maximum charge.
					///CHOISES BASED ON A 4 TURN INTERVAL.
					int[] choices= new int[4];
					//ua is better than super without max charge.
					
					//super without max charge.
					choices[0]= getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)*2+2*pa.getAppliedDamage(foe);
					//ultimate without max charge.
					choices[1]= a.getAppliedDamage(foe)+3*pa.getAppliedDamage(foe);
					// super with max charge.
					choices[2]= (getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)*3);
					//ultimate with max charge.
					choices[3]= a.getAppliedDamage(foe)*2;
					
					int max= Math.max(Math.max(choices[0], choices[1]), Math.max(choices[2], choices[3]));
					
					int i;
					for ( i = 0; i < choices.length; i++) {
						if(choices[i]==max)
							break;
					}
					
					//if it is better to (use a maximum charge and an ultimate attack)
					if(i==3){
						if ((nfoe.getKi()>2 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi()/3)>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
							 //If i can use the ultimate attack.use it.
							attack(a);
						else			// If not. use a maximum charge.
							if (nfoe.getKi()-nfoe.getMaxKi()>1) 
								attack(new MaximumCharge());
							else
								attack(pa);
						
					}else{//if it is better to use 3 physical attacks and an ultimate.
						if(i==1){

							if ((nfoe.getKi()>2 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(a.getAppliedDamage(foe)/100.0)*nfoe.getKi()/3)>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
								 //If i should use the ultimate attack.use it.
								attack(a);
							else			// If not. use a physical attack.
								attack(pa);
						}else
						{	//super without max charge.
							if(i==0){
								if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
									//If i can use it. Do so.
									attack(getBestSuperAttack(nfoe.getSuperAttacks()));
								else			// If not. use a physical attack.
									attack(pa);
							}else
							{// super with max charge.
								if ((nfoe.getKi()>0 && !meBlocking)||(nfoe.getKi()==nfoe.getMaxKi()) ||((Math.ceil(getBestSuperAttack(nfoe.getSuperAttacks()).getAppliedDamage(foe)/100.0)*nfoe.getKi())>(((Fighter)me).getStamina()+2*nfoe.getKi()))) 
									 //If i can use it. Do so.
									attack(getBestSuperAttack(nfoe.getSuperAttacks()));
								else			// If not. use a maximum charge.
									if (nfoe.getKi()-nfoe.getMaxKi()>1) 
										attack(new MaximumCharge());
									else
										attack(pa);
								
							}
						}
					}
					
					return;
				}
			}
			
			
		}
	public boolean enemyWillHaveAtleastXKi(PlayableFighter p,int targetki){
		if((p instanceof Saiyan && ((Saiyan)p).isTransformed()))//transformed saiyan
			return p.getKi()>=targetki+1;
		if(p instanceof Earthling)
			return p.getKi()>=targetki-1;
		
		return p.getKi()>=targetki;
	}
	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException  {
		attack.onUse(attacker, getDefender(),
				(attacker == me && foeBlocking) || (attacker == foe && meBlocking));
		if(attacker==me)
			if(attack instanceof SuperAttack){
				if(!knownSuperAttacks.contains((SuperAttack)attack))
					knownSuperAttacks.add((SuperAttack)attack);
					knownPlayer.setSuperAttacks(knownSuperAttacks);
				System.out.println("known information updated");
			}else
				if(attack instanceof UltimateAttack){
					if(!knownUltimateAttacks.contains((UltimateAttack)attack))
						knownUltimateAttacks.add((UltimateAttack)attack);
					knownPlayer.setUltimateAttacks(knownUltimateAttacks);
					System.out.println("known information updated");
				}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));

		endTurn();
	}

	// perform a block and end turn
	public void block()   {
		if (attacker == me) {
			meBlocking = true;
		} else if (attacker == foe) {
			foeBlocking = true;
		}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn
	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException  {
		switch (collectible) {
		case SENZU_BEAN:
			if (player.getSenzuBeans() > 0) {
				PlayableFighter activeFighter = player.getActiveFighter();
				activeFighter.setHealthPoints(activeFighter.getMaxHealthPoints());
				activeFighter.setStamina(activeFighter.getMaxStamina());

				player.setSenzuBeans(player.getSenzuBeans() - 1);

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.USE, collectible));
			} else {
				throw new NotEnoughSenzuBeansException();
			}
			break;
		default:
			break;
		}

		endTurn();
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e) {
		if (listener != null) {
			listener.onBattleEvent(e);
		}
		
	}
	public int getEffectiveHP(BattleOpponent f){
		Fighter p= (Fighter) f;
		if (f == me ) {
			if(meBlocking)
			return p.getHealthPoints() + p.getStamina()*100;
		}
		else{
			if(foeBlocking)
				return p.getHealthPoints() + p.getStamina()*100;
		}
		
		return p.getHealthPoints();
	}
	
	public SuperAttack getBestSuperAttack(ArrayList<SuperAttack> p){ ///returns the highest damage super attack.
		SuperAttack best= new SuperAttack("None", 0);	///returns this if no damage dealing super attack exists.
		for(SuperAttack s: p){
			if (s.getDamage()>best.getDamage())
					best=s;
		}
		return best;
	}
	public UltimateAttack getBestUltimateAttack(ArrayList<UltimateAttack> p){ ///returns the highest damage ultimate attack.
		UltimateAttack best= new UltimateAttack("None", 0);	///returns this if no damage dealing ultimate attack exists.
		for(UltimateAttack s: p){
			if (s.getDamage()>best.getDamage())
					best=s;
		}
		return best;
	}
	public Attack getBestAttack(Fighter p){
		PhysicalAttack pa= new PhysicalAttack();
		SuperAttack sa= getBestSuperAttack(p.getSuperAttacks());
		UltimateAttack ua = getBestUltimateAttack(p.getUltimateAttacks());
		if(2*ua.getAppliedDamage(foe)>3*sa.getAppliedDamage(foe)){
			if(ua.getAppliedDamage(foe)>2*pa.getAppliedDamage(foe))
				return ua;
			else
				return pa;
		}else{
				if(sa.getAppliedDamage(foe)>pa.getAppliedDamage(foe))
					return sa;
				else
					return pa;
		}
	}
	public boolean hasSuperSaiyan(BattleOpponent f){
		Fighter p= (Fighter) f;
		for(UltimateAttack s: p.getUltimateAttacks()){
			if (s instanceof SuperSaiyan)
				return true;
		}
		return false;
	}
	
	public boolean hasMaximumCharge(BattleOpponent f){
		Fighter p= (Fighter) f;
		for(SuperAttack s: p.getSuperAttacks()){
			if (s instanceof MaximumCharge)
					return true;
		}
		
		return false;
	}
	public void setInsane(boolean b){
		INSANE=b;
	}
	
}
