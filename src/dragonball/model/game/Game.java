package dragonball.model.game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.lang.model.element.UnknownAnnotationValueException;

import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.InvalidFormatException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public class Game implements PlayerListener, WorldListener , BattleListener , Serializable{
	private GameState state;
	private	Player player;
	private	World world;
	private	ArrayList<NonPlayableFighter> weakFoes;
	private	ArrayList<NonPlayableFighter> strongFoes;
	private	ArrayList<Attack> attacks;
	private	ArrayList<Dragon> dragons;
	
	private transient Battle b;
	private transient GameListener gameListener;
	private String lastSavePath;


	public Game() throws IOException{
		
		player= new Player("student");
		int foesRange = (player.getMaxFighterLevel() - 1) / 10 + 1;
		try{loadAttacks("Database-Attacks.csv");}catch(IOException e){
			loadAttacks("Database-Attacks-aux.csv");
		}
		try{loadFoes("Database-Foes-Range"+foesRange+".csv");} catch (IOException e){
			this.loadFoes("Database-Foes-aux.csv");

		}
		try{loadDragons("Database-Dragons.csv");} catch (IOException e){
			this.loadDragons("Database-Dragons-aux.csv");

		}


		world=new World();
		world.generateMap(weakFoes, strongFoes);
	for (int i = 0; i < 10; i++) {
		for (int j = 0; j < 10; j++) {
			System.out.print(world.getMap()[i][j]);
		}
		System.out.println();
	}
		
		player.setExploredMaps(1);
		
		state=GameState.WORLD;
		player.setPlayerListener(this);
		world.setWorldListener(this);

		//world.generateMap(weakFoes,strongFoes);
	}

	public  ArrayList<String> loadCSV(String filePath) throws IOException{ ////////////////////////////////////////////////////////////////////
		ArrayList<String> res = new ArrayList<String>();
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null)	
			res.add(currentLine);

		br.close();
		return res;


	}

	public void loadAttacks(String filePath) throws IOException{
		ArrayList<String> res= loadCSV(filePath);
		attacks= new ArrayList<Attack>();
		for (int i = 0; i < res.size(); i++) {
			String s= res.get(i);
			String [] result= s.split(",");

			if(result.length>2)
				if(result[0].equals("SA"))
					attacks.add(new SuperAttack(result[1], Integer.parseInt(result[2])));
				else if(result[0].equals("UA"))
					attacks.add(new UltimateAttack(result[1], Integer.parseInt(result[2])));
				else if(result[0].equals("MC"))
					attacks.add(new MaximumCharge());
				else if(result[0].equals("SS"))
					attacks.add(new SuperSaiyan());
				else
					throw new UnknownAttackTypeException("There is an unknown attack type at file "+filePath+" at line "+i+" of type "+result[0], filePath, i, result[0]);
			else
				throw new MissingFieldException("The file "+filePath+" contains "+(3-result.length)+" missing fields at line "+(i+1),filePath, i+1, 3-result.length);

		}




	}
	public  void loadFoes(String filePath) throws IOException{/////////////Database-Foes-Range1.csv////////////////////////////////////////////////
		ArrayList<String> res= loadCSV(filePath);
		weakFoes= new ArrayList<NonPlayableFighter>();
		strongFoes= new ArrayList<NonPlayableFighter>();

		for (int i = 0; i < res.size(); i+=3) {
			String s=res.get(i);
			String [] result= s.split(",");
			if (result.length<8)
				throw new MissingFieldException("The file "+filePath+" contains "+(8-result.length)+" missing fields at line "+(i+1),filePath, i+1, 8-result.length);



			s= res.get(i+1);
			ArrayList <SuperAttack> foeSA= new ArrayList<SuperAttack>();
			String [] sAttacks= s.split(",");
			if(!s.equals("")){
				///// empty line

				for (int j = 0; j < sAttacks.length; j++) {
					for (int j2 = 0; j2 < attacks.size(); j2++) {
						if (attacks.get(j2).getName().equals(sAttacks[j]))
						{
							foeSA.add((SuperAttack) attacks.get(j2));
							break;
						}
					}
				}
			}



			s= res.get(i+2);
			ArrayList <UltimateAttack> foeUA= new ArrayList<UltimateAttack>();
			String [] uAttacks= s.split(",");
			if(!s.equals("")){


				for (int j = 0; j < uAttacks.length; j++) {
					for (int j2 = 0; j2 < attacks.size(); j2++) {
						if (attacks.get(j2).getName().equals(uAttacks[j]))
						{
							foeUA.add((UltimateAttack) attacks.get(j2));
							break;
						}
					}
				}
			}
			if(result[7].equals("TRUE"))
				strongFoes.add(new NonPlayableFighter(result[0], Integer.parseInt(result[1]), Integer.parseInt(result[2]),Integer.parseInt(result[3]), Integer.parseInt(result[4]), Integer.parseInt(result[5]), Integer.parseInt(result[6]), true, foeSA, foeUA));
			else
				if (result[7].equals("FALSE"))
					weakFoes.add(new NonPlayableFighter(result[0], Integer.parseInt(result[1]), Integer.parseInt(result[2]),Integer.parseInt(result[3]), Integer.parseInt(result[4]), Integer.parseInt(result[5]), Integer.parseInt(result[6]), false, foeSA, foeUA));
				else
					throw new MissingFieldException("Could not determine the type of the fighter in file: "+filePath+" Line: "+(i+1),filePath,i+1,1);



		}
	}
	public  void loadDragons(String filePath) throws IOException{ //Database-Dragons.csv
		ArrayList<String> res= loadCSV(filePath);
		dragons= new ArrayList<Dragon>();
		for (int i = 0; i < res.size(); i+=3) {
			String s= res.get(i);
			String [] line1= s.split(",");
			if (line1.length!=3)
				throw new MissingFieldException("The file "+filePath+" contains "+(3-line1.length)+" missing fields at line "+(i+1),filePath, i+1, 3-line1.length);


			s= res.get(i+1);
			String [] sAttacks= s.split(","); ///// empty line
			ArrayList <SuperAttack> SA= new ArrayList<SuperAttack>();
			for (int j = 0; j < sAttacks.length; j++) {
				for (int j2 = 0; j2 < attacks.size(); j2++) {
					if (attacks.get(j2).getName().equals(sAttacks[j]))
					{
						SA.add((SuperAttack) attacks.get(j2));
						break;
					}
				}
			}

			s= res.get(i+2);
			String [] uAttacks= s.split(",");
			ArrayList <UltimateAttack> UA= new ArrayList<UltimateAttack>();
			for (int j = 0; j < uAttacks.length; j++) {
				for (int j2 = 0; j2 < attacks.size(); j2++) {
					if ((attacks.get(j2)).getName().equals(uAttacks[j]))
					{
						UA.add((UltimateAttack) attacks.get(j2));
						break;
					}
				}
			}
			dragons.add(new Dragon(line1[0], SA,UA, Integer.parseInt(line1[1]),Integer.parseInt(line1[2])));
		}

	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		

		if(e.getType()==BattleEventType.ENDED){
			
			
			Fighter myfighter = player.getActiveFighter();
			if ((Fighter) e.getWinner()==myfighter)
			{
				world.clearCurrentCell();
				NonPlayableFighter f=(NonPlayableFighter) b.getFoe();
				
				if(f.isStrong()){
					world=new World();
					world.setWorldListener(this);
					int foesRange = (player.getMaxFighterLevel() - 1) / 10 + 1;
					try{loadFoes("Database-Foes-Range"+foesRange+".csv");} catch (IOException e1){
//					w	this.loadFoes("Database-Foes-aux.csv");
						System.out.println("ERROR in load foes");
					}
					world.generateMap(weakFoes, strongFoes);
					player.setExploredMaps(player.getExploredMaps()+1);
				}
				state=GameState.WORLD;
				((PlayableFighter) myfighter).setXp(((PlayableFighter) myfighter).getXp()+((NonPlayableFighter)b.getFoe()).getLevel()*5);
				
				if(gameListener!=null)
					gameListener.onBattleEvent(e);
				ArrayList<SuperAttack> sattacks= f.getSuperAttacks();
				ArrayList<UltimateAttack> uattacks= f.getUltimateAttacks();
				for (int i = 0; i < sattacks.size(); i++) {
					if(!player.getSuperAttacks().contains(sattacks.get(i)))
						player.getSuperAttacks().add(sattacks.get(i));
				}
				for (int i = 0; i < uattacks.size(); i++) {

					if(!player.getUltimateAttacks().contains(uattacks.get(i)))
						player.getUltimateAttacks().add(uattacks.get(i));
				}
				
			}else{
				if (lastSavePath!=null)
					load(lastSavePath);
				else{
					world=new World();
					world.generateMap(weakFoes, strongFoes);
					player.setExploredMaps(1);
					state=GameState.WORLD;
					player.setPlayerListener(this);
					world.setWorldListener(this);
					if(gameListener!=null)
						gameListener.onBattleEvent(e);
				}
			}
		}
		if(e.getType()!=BattleEventType.ENDED){
			if(gameListener!=null)
				gameListener.onBattleEvent(e);
		}
	}

	public void onDragonCalled(){
		this.state=GameState.DRAGON;
		int x=(int) (Math.random()*dragons.size());
		Dragon d=dragons.get(x);

		if(gameListener!=null)
			gameListener.onDragonCalled(d);

	}

	public void onWishChosen(DragonWish wish){
		this.state=GameState.WORLD;
	}
	public void onFoeEncountered(NonPlayableFighter foe){
//		System.out.println("on foe"+ getPlayer().getActiveFighter().getSuperAttacks());
		state=GameState.BATTLE;
		b= new Battle(player.getActiveFighter(), foe);
		b.setListener(this);
		
//		System.out.println("on foe2"+ getPlayer().getActiveFighter().getSuperAttacks());
		
		onBattleEvent(new BattleEvent(b, BattleEventType.STARTED));
//		System.out.println("on foe3"+ getPlayer().getActiveFighter().getSuperAttacks());
		
	}

	public void onCollectibleFound(Collectible collectible){
		switch (collectible){
		case DRAGON_BALL: player.setDragonBalls(player.getDragonBalls()+1);
		if(player.getDragonBalls()==7){

			player.callDragon();
		}
		case SENZU_BEAN: player.setSenzuBeans(player.getSenzuBeans()+1);
		default: break;
		}

		if(gameListener!=null)
			gameListener.onCollectibleFound(collectible);
	}

	public void save(String fileName){

		try
		{
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			lastSavePath= fileName;
			out.writeObject(this);
			out.close();
			fileOut.close();

		}catch(IOException i)
		{
			i.printStackTrace();
		}



	}

	public void load(String fileName){
		Game g = null;
		try
		{
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			g = (Game) in.readObject();

			this.state=g.state;
			this.player=g.player;
			this.world=g.world;
			this.weakFoes=g.weakFoes;
			this.strongFoes=g.strongFoes;
			this.attacks=g.attacks;
			this.dragons=g.dragons;
			this.gameListener=g.gameListener;
			this.lastSavePath=g.lastSavePath;
			player.setPlayerListener(this);
			world.setWorldListener(this);
			System.out.println("Succesfully Loaded!");
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
			i.printStackTrace();
			return;
		}catch(ClassNotFoundException c)
		{
			System.out.println("Game class not found");
			c.printStackTrace();
			return;
		}



	}


	public GameListener getListener() {
		return gameListener;
	}
	public void setListener(GameListener gameListener) {
		this.gameListener = gameListener;
	}
	public GameState getState() {
		return state;
	}


	public Player getPlayer() {
		return player;
	}


	public World getWorld() {
		return world;
	}


	public ArrayList<NonPlayableFighter> getWeakFoes() {
		return weakFoes;
	}


	public ArrayList<NonPlayableFighter> getStrongFoes() {
		return strongFoes;
	}


	public ArrayList<Attack> getAttacks() {
		return attacks;
	}


	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

	public String getLastSavePath() {
		return lastSavePath;
	}
	

	public Battle getBattle() {
		return b;
	}

	public static void main(String[]args) throws IOException{

		Game g= new Game();

		g.save("X");
		System.out.println();
		g.load("X");
	}


}
