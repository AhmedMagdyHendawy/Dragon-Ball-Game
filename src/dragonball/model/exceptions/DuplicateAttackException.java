package dragonball.model.exceptions;

import dragonball.model.attack.Attack;

public class DuplicateAttackException extends InvalidAssignAttackException{
	private Attack attack;

	public DuplicateAttackException(Attack attack){
		super("there is a duplicate assigning as "+attack+" is already assigned before.");
		this.attack=attack;
	}
	
	
	public Attack getAttack() {
		return attack;
	}


}
