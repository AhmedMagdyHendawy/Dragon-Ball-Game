package dragonball.model.exceptions;

public class UnknownAttackTypeException extends InvalidFormatException{
	private String unknownType;

	public UnknownAttackTypeException(String sourceFile, int sourceLine, String type){
		super(sourceFile,sourceLine);
		this.unknownType=type;
	}
	
	
	public UnknownAttackTypeException(String message, String sourceFile,int sourceLine, String type){
		super(message,sourceFile,sourceLine);
		this.unknownType=type;
	}
	
	
	public String getUnknownType() {
		return unknownType;
	}

	
}
