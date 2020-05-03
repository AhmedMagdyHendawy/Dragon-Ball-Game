package dragonball.model.cell;

import java.io.Serializable;

abstract public class Cell implements Serializable{
	private CellListener cellListener;
	
	
	
	public CellListener getCellListener() {
		return cellListener;
	}



	public void setCellListener(CellListener cellListener) {
		this.cellListener = cellListener;
	}
	
	abstract public String toString();
		
	
	abstract void onStep();
}
