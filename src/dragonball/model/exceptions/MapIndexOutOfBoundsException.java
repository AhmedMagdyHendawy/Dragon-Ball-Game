package dragonball.model.exceptions;

public class MapIndexOutOfBoundsException extends IndexOutOfBoundsException{
	private int row;
	private  int column;
	
	
	public MapIndexOutOfBoundsException(int row, int column){
		super("you are trying to ender an invalid of potiion ("+row+","+column+").");
		this.column=column;
		this.row=row;
		
	}
	
	
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	

}
