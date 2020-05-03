package dragonball.model.exceptions;

import java.io.IOException;

 public abstract class InvalidFormatException extends IOException {
	private String sourceFile;
    private int sourceLine;
	
	
	

	public InvalidFormatException(String sourceFile, int sourceLine){
    	super();
    	this.sourceLine=sourceLine;
    	this.sourceFile=sourceFile;
    }
	
    public InvalidFormatException(String message, String sourceFile, int sourceLine){
    	super(message);
    	this.sourceFile=sourceFile;
    	this.sourceLine=sourceLine;
    }

	
	public String getSourceFile() {
		return sourceFile;
	}

	public int getSourceLine() {
		return sourceLine;
	}

}
