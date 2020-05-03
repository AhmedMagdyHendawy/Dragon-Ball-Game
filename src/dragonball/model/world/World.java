package dragonball.model.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;

public class World implements CellListener, Serializable{
	private Cell[][] map;
	private int playerColumn;
	private int playerRow;
	private WorldListener worldListener;
	public World(){
		this.map= new Cell[10][10];
	}
	public void resetPlayerPosition(){
		playerColumn=9;
		playerRow=9;
	}
	
	public void clearCurrentCell(){
		map[playerRow][playerColumn]=new EmptyCell();
		map[playerRow][playerColumn].setCellListener(this);
	}
	public void handleCell(int r, int c){

		if(map[r][c].getClass()==FoeCell.class)
			worldListener.onFoeEncountered(((FoeCell)map[r][c]).getFoe());
		else
			if (map[r][c].getClass()==CollectibleCell.class)
			{
				worldListener.onCollectibleFound(((CollectibleCell)map[r][c]).getCollectible());
				map[r][c]=new EmptyCell();
				map[r][c].setCellListener(this);
			}
	}
	/////////////////////////////////
	public void moveUp(){
		if(playerRow-1>=0){
			playerRow--;
			handleCell(playerRow, playerColumn);
		}else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	public void moveDown(){
		if(playerRow+1<=9){
			playerRow++;
			handleCell(playerRow, playerColumn);
		}else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	public void moveRight(){
		if(playerColumn+1<=9){
			playerColumn++;
			handleCell(playerRow, playerColumn);
		}else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	public void moveLeft(){
		if(playerColumn-1>=0){
			playerColumn--;
			handleCell(playerRow, playerColumn);
		}else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	///////////////////////
	public WorldListener getWorldListener() {
		return worldListener;
	}
 
	public void setWorldListener(WorldListener worldListener) {
		this.worldListener = worldListener;
	}

	public void onFoeEncountered(NonPlayableFighter foe) {
		if(worldListener!=null)
			worldListener.onFoeEncountered(foe);
		
	//	map[playerRow][playerColumn]=new EmptyCell();
	}

	public void onCollectibleFound(Collectible collectible) {
		
		map[playerRow][playerColumn]=new EmptyCell();
		map[playerRow][playerColumn].setCellListener(this);
		if(worldListener!=null)
			worldListener.onCollectibleFound(collectible);
	}

	public Cell[][] getMap() {
		return map;
	}

	

	public int getPlayerColumn() {
		return playerColumn;
	}

	

	public int getPlayerRow() {
		return playerRow;
	}

	 public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter>
	 strongFoes){
		 playerColumn=9;
		 playerRow=9;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j]=new EmptyCell();
				map[i][j].setCellListener(this);
			}
		}
		Random rng= new Random();
		
		map[0][0]= new FoeCell((NonPlayableFighter) strongFoes.get(rng.nextInt(strongFoes.size()))); /////////////////////////////////////////////
		for (int i = 0; i < 15; i++) {
			
			int cellnum = rng.nextInt(98) + 1;
			int row= cellnum/10;
			int column= cellnum%10;
			if(map[row][column].toString().equals("[ ]"))
				map[row][column]= new FoeCell((NonPlayableFighter) weakFoes.get( rng.nextInt(weakFoes.size())));
			else
				i--;
		}
		
		for (int i = 0; i < (int) rng.nextInt(3)+3; i++) {
			
			int cellnum = (int) rng.nextInt(98) + 1;
			int row= cellnum/10;
			int column= cellnum%10;
			if(map[row][column].toString().equals("[ ]"))
				map[row][column]= new CollectibleCell(Collectible.SENZU_BEAN);
			else
				i--;
		}
		while(true){
		int cellnum = (int) rng.nextInt(98) + 1;
		int row= cellnum/10;
		int column= cellnum%10;
		if(map[row][column].toString().equals("[ ]")){
			map[row][column]= new CollectibleCell(Collectible.DRAGON_BALL);
			break;
		}
		}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j].setCellListener(this);
			}
		}
		
		
		
		
	}

	@Override
	public String toString() {
		String res="";
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				res+= map[i][j].toString();
			}
			res+= "\n";
		}
		return "World map : \n" + res;
	}


	 
	
	
	
}
