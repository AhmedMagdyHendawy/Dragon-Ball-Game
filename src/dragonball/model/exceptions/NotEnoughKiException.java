package dragonball.model.exceptions;

public class NotEnoughKiException extends NotEnoughResourcesException{
	private int availableKi;
	private int requiredKi;
	
	
	public NotEnoughKiException(int requiredKi, int availableKi){
		super("You don't have enough ki bars. Requied: "+requiredKi+" , Available: "+availableKi+".");
		this.availableKi=availableKi;
		this.requiredKi=requiredKi;
	}
	
	
	
	
	
	
	public int getAvailableKi() {
		return availableKi;
	}
	
	public int getRequiredKi() {
		return requiredKi;
	}
	

}
