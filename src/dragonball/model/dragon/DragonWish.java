package dragonball.model.dragon;

import java.util.EventObject;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class DragonWish extends EventObject {
	
	private DragonWishType type;
//	: Specifies the type of the current dragon wish.
	private int senzuBeans;
//	: Specifies the number of senzu beans granted by the wish.
	private int abilityPoints;
//	: Specifies the number of ability points granted by the wish.
	private SuperAttack superAttack;
//	: Specifies the super attack granted by the wish.
	private UltimateAttack ultimateAttack;
//	: Specifies the ultimate attack granted by the wish.

	public DragonWish(Dragon dragon, DragonWishType type){
		super(dragon);
		this.type=type;
		
	}
	public DragonWish(Dragon dragon, DragonWishType type, int senzuBeansOrAbilityPoints){
		this(dragon,type);
		if(type==DragonWishType.SENZU_BEANS){
			this.senzuBeans=senzuBeansOrAbilityPoints;
		}else
			if(type==DragonWishType.ABILITY_POINTS){
				this.abilityPoints=senzuBeansOrAbilityPoints;
			}
	}
	public DragonWish(Dragon dragon, DragonWishType type, SuperAttack superAttack){
		this(dragon,type);
		if(type==DragonWishType.SUPER_ATTACK)
		this.superAttack=superAttack;
	}
	public DragonWish(Dragon dragon, DragonWishType type, UltimateAttack ultimateAttack){
		this(dragon,type);
		if(type==DragonWishType.ULTIMATE_ATTACK)
		this.ultimateAttack=ultimateAttack;
	}

	public DragonWishType getType() {
		return type;
	}


	public int getSenzuBeans() {
		return senzuBeans;
	}


	public int getAbilityPoints() {
		return abilityPoints;
	}

	public SuperAttack getSuperAttack() {
		return superAttack;
	}


	public UltimateAttack getUltimateAttack() {
		return ultimateAttack;
	}

	

}
