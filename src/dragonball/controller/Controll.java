package dragonball.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;










import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.player.Player;
import dragonball.view.BattleView;
import dragonball.view.DragonMode;
import dragonball.view.FighterSelectionView;
import dragonball.view.MainMenuView;
import dragonball.view.PlayerCreationView;
import dragonball.view.StartView;
import dragonball.view.UpgradeView;
import dragonball.view.WorldView;

public class Controll implements ActionListener, GameListener , KeyListener {
	Game game;
	PlayerCreationView pcv;
	StartView sv;
	FighterSelectionView fsv;
	WorldView wv;
	DragonMode dm;
	String fighterName;
	UpgradeView uv;
	BattleView bv;
	MainMenuView mmv;
	private boolean insane;

	
	public Controll(){
//		(new Thread(new MediaPlayer("D:\\Work files\\Hierarchy\\Audio\\test.wav"))).start();
		sv= new StartView(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton b= (JButton) e.getSource();
		b.setFocusable(false);
		switch (e.getActionCommand()) 
			{
//////////////// start view
			case "New Game": try {

				String [] difficulty= {"Easy","Hard"};

				String s = (String) JOptionPane.showInputDialog(sv,"Select The difficulty",
						"Choose Your Mode",
						JOptionPane.YES_NO_OPTION,
						new ImageIcon(),
						difficulty,
						difficulty[0]);
				if(s!=null)
					try {
						if(s.equals("Easy"))
							insane=false;
						
						game= new Game();
						game.setListener(this);

						pcv=new PlayerCreationView(this);
						if(sv!=null)
							sv.dispose();
						if(mmv!=null)
							mmv.dispose();
					} catch (IOException e1) {
						e1.printStackTrace();
					}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
			
			case "Load Game":
			{
				File savefolder = new File("Saves");
				File[] listOfFiles = savefolder.listFiles();
				String [] listOfFilesAsString= new String [listOfFiles.length];
				for (int i = 0; i < listOfFilesAsString.length; i++) {
					listOfFilesAsString[i]=listOfFiles[i].getName();
				}
				if (listOfFiles.length!=0)
				{
					String s = (String) JOptionPane.showInputDialog(sv,"Choose The Save File:\n",
		                    "Load Game",
		                    JOptionPane.YES_NO_OPTION,
		                    new ImageIcon(),
		                    listOfFilesAsString,
		                    listOfFilesAsString[0]);
					if(s!=null)
					try {
						game= new Game();
						game.load("Saves\\"+s);	
						game.setListener(this);
						if(wv==null)
							wv=new WorldView(this);
						
						updateWorld();
						if(sv!=null)
						sv.dispose();
						JOptionPane.showMessageDialog(wv, "Load Successful","Load", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
				else{
					JOptionPane.showMessageDialog(wv, "No Previous Saves :(","Load Unsuccessful", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			break;
			case "Back":
			{
				wv.setVisible(true);
				mmv.dispose();
			}break;
			
/////////////////player creation view
			case "Fighter Chosen":
				String playerName=pcv.getPlayerName().getText();
				fighterName= pcv.getFighterName().getText();
				if (!playerName.equals("") )
				{
					if(!fighterName.equals(""))
					{
						game.getPlayer().setName(playerName);
						pcv.dispose();
						fsv=new FighterSelectionView(this);
					}else
						JOptionPane.showMessageDialog(pcv, "Please Enter Your Fighter's Name." ,"Missing Information", JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(pcv, "Please Enter Your Name." ,"Missing Information", JOptionPane.INFORMATION_MESSAGE);
				}break;
//////////////////Fighter selection view
			case "Earthling": ////////////////////////////////////////// choose fighter icon
			{
				game.getPlayer().createFighter('E', fighterName);
				if(wv==null)
				createWorld();
				else{
					updateWorld();
					wv.setVisible(true);
				}
				
				fsv.dispose();
			}break;
			case "Frieza":
			{
				game.getPlayer().createFighter('F', fighterName);
				if(wv==null)
				createWorld();
				else
				{
					updateWorld();
					wv.setVisible(true);
				}
				
				fsv.dispose();
			}break;
			case "Saiyan":
			{
				game.getPlayer().createFighter('S', fighterName);
				if(wv==null)
				createWorld();
				else
				{
					updateWorld();
					wv.setVisible(true);
				}
				fsv.dispose();
			}break;
			case "Majin":
			{
				game.getPlayer().createFighter('M', fighterName);
				if(wv==null)
				createWorld();
				else
				{
					updateWorld();
					wv.setVisible(true);
				}
				fsv.dispose();
			}break;
			case "Namekian":
			{
				game.getPlayer().createFighter('N', fighterName);
				if(wv==null)
				createWorld();
				else
				{
					updateWorld();
					wv.setVisible(true);
				}
				fsv.dispose();
			}break;
			
//////////////////Dragon mode view
			case "SenzuBeans":
			{
				StringTokenizer st= new StringTokenizer(b.getText());
				String n= st.nextToken();
				ImageIcon i= new ImageIcon("Senzu.png");
				JOptionPane.showMessageDialog(wv, "You Have Gained "+n+" Senzu Beans!","Your Wish Has Been Granted!", JOptionPane.INFORMATION_MESSAGE, i);
				game.getPlayer().chooseWish(new DragonWish(new Dragon("", null, null, 0, 0), DragonWishType.SENZU_BEANS,Integer.parseInt(n)));
				updatePlayerCollectibles();
				wv.setVisible(true);
				dm.dispose();
			}break;
			case "AbilityPoint":
			{
				StringTokenizer st= new StringTokenizer(b.getText());
				String n= st.nextToken();
				ImageIcon i= new ImageIcon();
				JOptionPane.showMessageDialog(wv, "Your Fighter Has Gained "+n+" Ability Points!","Your Wish Has Been Granted!", JOptionPane.INFORMATION_MESSAGE, i);
				game.getPlayer().chooseWish(new DragonWish(new Dragon("", null, null, 0, 0), DragonWishType.ABILITY_POINTS,Integer.parseInt(n)));
				updateActiveFighter();
				wv.setVisible(true);
				dm.dispose();
			}break;
			case "SuperAttack":
			{
				String n= b.getText();
				ImageIcon i= new ImageIcon();
				JOptionPane.showMessageDialog(wv, "You Have Learned The "+n+" Super Attack!","Your Wish Has Been Granted!", JOptionPane.INFORMATION_MESSAGE, i);
				for (int j2 = 0; j2 < game.getAttacks().size(); j2++) {
					if ((game.getAttacks().get(j2)).getName().equals(n))
					{
						game.getPlayer().chooseWish(new DragonWish(new Dragon("", null, null, 0, 0), DragonWishType.SUPER_ATTACK,(SuperAttack) game.getAttacks().get(j2)));
						break;
					}
				}
				wv.setVisible(true);
				dm.dispose();
			}break;
			case "UltimateAttack":
			{
//				System.out.println("here");
				String n= b.getText();
				ImageIcon i= new ImageIcon("Senzu.png");
				JOptionPane.showMessageDialog(wv, "You Have Learned The "+n+" Ultimate Attack!","Your Wish Has Been Granted!", JOptionPane.INFORMATION_MESSAGE, i);
				for (int j2 = 0; j2 < game.getAttacks().size(); j2++) {
					if ((game.getAttacks().get(j2)).getName().equals(n))
					{
						game.getPlayer().chooseWish(new DragonWish(new Dragon("", null, null, 0, 0), DragonWishType.ULTIMATE_ATTACK,(UltimateAttack) game.getAttacks().get(j2)));
						break;
					}
				}
				wv.setVisible(true);
				dm.dispose();
			}break;
///////////////////World View	
			/////// MOVE BUTTONS
			case "UP":
			{
				try {
					game.getWorld().moveUp();
					updateFighterPos();
				} catch (Exception e2) {
				}

			}break;
			//
			case "Right":
			{
				try {
					game.getWorld().moveRight();
					updateFighterPos();
				} catch (Exception e2) {
				}
				
			}break;
			//
			case "Left":
			{	
				try {
					game.getWorld().moveLeft();
					updateFighterPos();
				} catch (Exception e2) {
				}
				
			}break;
			//
			case "Down":
			{	
				try {
					game.getWorld().moveDown();
					updateFighterPos();
				} catch (Exception e2) {
				}
				
			}break;
			///////////// 6 Option buttons 
			case "Menu":
			{
				mmv= new MainMenuView(this);
				wv.setVisible(false);
			}break;
			case "Save":
			{ 
				String s = (String) JOptionPane.showInputDialog(wv,"Enter Save Name:\n",
						"Save",
						JOptionPane.YES_NO_OPTION,
						new ImageIcon(),
						null,
						"");
				Boolean overWrite=false;
				int n=100;
				//If a string was returned, Create the fighter.
				if ((s != null) && (s.length() > 0)) 
				{
					File savefolder = new File("Saves");
					File[] listOfFiles = savefolder.listFiles();
					String [] listOfFilesAsString= new String [listOfFiles.length];
					for (int i = 0; i < listOfFilesAsString.length; i++) {
						listOfFilesAsString[i]=listOfFiles[i].getName();
					}
					for (int i = 0; i < listOfFilesAsString.length; i++) {
						if(listOfFilesAsString[i].equals(s))
						{
							overWrite=true;
							Object[] options = {"Yes",
			                "No","Cancel"};
							 n = JOptionPane.showOptionDialog(bv,
									"A Previous Save Has The Same Name \n"+
									"Overwrite Last Save?",
									"Save",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE,
									new ImageIcon(),     
									options,  
									options[0]); 
							
							break;
						}
					}
				}
				if(!overWrite || n==JOptionPane.YES_OPTION)
				try {
					game.save("Saves\\"+s);
					JOptionPane.showMessageDialog(wv, "Game Successfully Saved!","Save", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(wv, "For Some Reason, Your Game Was Not Saved :(","Save", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
				}
				if(n==JOptionPane.NO_OPTION){
					wv.getSave().doClick();
				}
			}break;
			case "NewFighter":
			{
//				System.out.println("new");
				String s = (String) JOptionPane.showInputDialog(wv,"Enter Fighter Name:\n",
				                    "Create New Fighter",
				                    JOptionPane.YES_NO_OPTION,
				                    new ImageIcon(),
				                    null,
				                    "");
				//If a string was returned, Create the fighter.
				if ((s != null) && (s.length() > 0)) 
				{
					
					if(game.getPlayer()!=null){
						boolean valid=true;
						for (int i = 0; i < game.getPlayer().getFighters().size(); i++) {
							if(game.getPlayer().getFighters().get(i).getName().equals(s)){
								JOptionPane.showMessageDialog(wv, "You Can not Create More Than One Fighter With The Same Name","Duplicate Fighter Name", JOptionPane.INFORMATION_MESSAGE);
							valid=false;}
							}
//						System.out.println("here");
					if(valid){
					fighterName=s;
					fsv= new FighterSelectionView(this);
					wv.setVisible(false);
						}
					} 
				}
			}break;
			case "SelectFighter":
			{
				ArrayList<PlayableFighter> al=game.getPlayer().getFighters();
				String [] fighters= new String [al.size()];
				for (int i = 0; i < fighters.length; i++) {
					fighters[i]=al.get(i).getName();
				}
				
				String s = (String) JOptionPane.showInputDialog(wv,"Enter Fighter Name:\n",
	                    "Select Fighter",
	                    JOptionPane.YES_NO_OPTION,
	                    new ImageIcon(),
	                    fighters,
	                    fighters[0]);
				if(s!=null)
				for (int i = 0; i < fighters.length; i++) {
					if (s.equals(fighters[i])) {
						game.getPlayer().setActiveFighter(al.get(i));
						updateActiveFighter();
						updateFighterPos();
						break;
					}
				}
			}break;
			case "AssignAttacks":
			{
				ArrayList<String> unlockedSattacks = new ArrayList<>();
				 
		        unlockedSattacks.add("Select a Super Attack");
		        for (SuperAttack s : game.getPlayer().getSuperAttacks()){
		            unlockedSattacks.add(s.getName());
		        }
		        JComboBox sattacksBox = new JComboBox<>(unlockedSattacks.toArray());
		 
		 
		        ArrayList<String> currentSattacks = new ArrayList<>();
		        currentSattacks.add("No Attack.");
		        for (SuperAttack s : game.getPlayer().getActiveFighter().getSuperAttacks()){
		            currentSattacks.add(s.getName());
		        }
		        JComboBox currSattacksBox = new JComboBox<>(currentSattacks.toArray());
		 
		 
		        ArrayList<String> unlockedUattacks = new ArrayList<>();
		 
		        unlockedUattacks.add("Select an Ultimate Attack");
		        for (UltimateAttack s : this.game.getPlayer().getUltimateAttacks()){
		            unlockedUattacks.add(s.getName());
		        }
		        JComboBox uattacksBox = new JComboBox<>(unlockedUattacks.toArray());
		 
		        ArrayList<String> currUattacks = new ArrayList<>();
		        currUattacks.add("No Attack.");
		        for (UltimateAttack s : this.game.getPlayer().getActiveFighter().getUltimateAttacks()){
		            currUattacks.add(s.getName());
		        }
		        JComboBox currUattacksBox = new JComboBox<>(currUattacks.toArray());
		 
		 
		        Object[] message = {
		                "Unlocked Super Attacks:", sattacksBox,
		                "Replace The Super Attack:", currSattacksBox,
		                "Unlocked Ultimate Attacks:", uattacksBox,
		                "Replace The Ultimate Attacks:", currUattacksBox,
		        };
		        
		        int option = JOptionPane.showConfirmDialog(wv, message, "Assign Attacks", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,new ImageIcon());
		        if (option == JOptionPane.OK_OPTION)
		        {
		            int x = sattacksBox.getSelectedIndex();
		            int y= currSattacksBox.getSelectedIndex();
		 
		            int z1 = uattacksBox.getSelectedIndex();
		            int z2 = currUattacksBox.getSelectedIndex();
		            boolean Assigned=false;
		            if(x != 0 ){ //////  an Attack is selected
		                if (y == 0){
		                    try {
		                        game.getPlayer().assignAttack(
		                        		game.getPlayer().getActiveFighter(),
		                        		game.getPlayer().getSuperAttacks().get(x-1),
		                        		null);
		                        Assigned=true;
		 
		                    } catch (MaximumAttacksLearnedException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (DuplicateAttackException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                }else{
		                    try {
		                    	game.getPlayer().assignAttack(
		                    			game.getPlayer().getActiveFighter(),
		                                game.getPlayer().getSuperAttacks().get(x-1),
		                                game.getPlayer().getActiveFighter().getSuperAttacks().get(y-1));
		                                Assigned=true;
		                    } catch (MaximumAttacksLearnedException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (DuplicateAttackException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } 
		                }
		            }
		 
		            if(z1 != 0 ){ //////  an Attack is selected
		                if (z2 == 0){
		                    try {
		                        game.getPlayer().assignAttack(
		                                this.game.getPlayer().getActiveFighter(),
		                                this.game.getPlayer().getUltimateAttacks().get(z1-1),
		                                null);
		                                Assigned=true;
		                    } catch (MaximumAttacksLearnedException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (DuplicateAttackException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (NotASaiyanException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                }else{
		                    try {
		                        game.getPlayer().assignAttack(
		                                this.game.getPlayer().getActiveFighter(),
		                                this.game.getPlayer().getUltimateAttacks().get(z1-1),
		                                this.game.getPlayer().getActiveFighter().getUltimateAttacks().get(z2-1));
		                            Assigned=true;
		                    } catch (MaximumAttacksLearnedException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (DuplicateAttackException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    } catch (NotASaiyanException e2) {
		                    	JOptionPane.showMessageDialog(uv, e2.getMessage(),"Attack Not Assigned", JOptionPane.INFORMATION_MESSAGE);
		                    }
		                }
		            }
		            if (Assigned){
		                JOptionPane.showMessageDialog(wv,"Attacks Assigned.");
//		                System.out.println(game.getPlayer().getActiveFighter().getSuperAttacks());
//		                System.out.println(game.getPlayer().getActiveFighter().getUltimateAttacks());
		            }
		        }
		 
				
				
			}break;
			case "UpgradeFighter":
			{
				uv=new UpgradeView(this,game.getPlayer().getActiveFighter());
				wv.setVisible(false);
			}break;
/////////////////////////////Upgrade View
			case "MaxHP":
			{
				Player p = game.getPlayer();
				boolean upgraded=false;
				try {
					p.upgradeFighter(p.getActiveFighter(), 'H');
					updateActiveFighter();
					upgraded=true;
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(uv, "You Fighter Has No More Ability Points :(" ,"Upgrade Not Possible", JOptionPane.INFORMATION_MESSAGE);
				}
				if(upgraded){
					uv.updateUpGrade(p.getActiveFighter());
					JOptionPane.showMessageDialog(uv, "Max Hp has Increased by 50! \nYour Fighter's Current Max Hp is:"+p.getActiveFighter().getMaxHealthPoints()+" HP","It's Training Time!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Trained.png"));
				
				}
				
			}break;
			case "PhysicalDammage":
			{
				Player p = game.getPlayer();
				boolean upgraded=false;
				try {
					p.upgradeFighter(p.getActiveFighter(), 'P');
					updateActiveFighter();
					upgraded=true;
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(uv, "You Fighter Has No More Ability Points :(" ,"Upgrade Not Possible", JOptionPane.INFORMATION_MESSAGE);
				}
				if(upgraded){
					uv.updateUpGrade(p.getActiveFighter());
					JOptionPane.showMessageDialog(uv, "Physical Dammage has Increased by 50! \nYour Fighter's Current Physical Damage is: "+ p.getActiveFighter().getPhysicalDamage(),"It's Training Time!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Trained.png"));
				}
			}break;
			case "BlastDammage":
			{
				Player p = game.getPlayer();
				boolean upgraded=false;
				try {
					p.upgradeFighter(p.getActiveFighter(), 'B');
					updateActiveFighter();
					upgraded=true;
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(uv, "You Fighter Has No More Ability Points :(" ,"Upgrade Not Possible", JOptionPane.INFORMATION_MESSAGE);
				}
				if(upgraded)
				{
					uv.updateUpGrade(p.getActiveFighter());
					JOptionPane.showMessageDialog(uv, "Blast Dammage has Increased by 50! \nYour Fighter's Current Blast Damage is: "+ p.getActiveFighter().getBlastDamage(),"It's Training Time!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Trained.png"));
				}
			}break;
			case "MaxKI":
			{
				Player p = game.getPlayer();
				boolean upgraded=false;
				try {
					p.upgradeFighter(p.getActiveFighter(), 'K');
					updateActiveFighter();
					upgraded=true;
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(uv, "You Fighter Has No More Ability Points :(" ,"Upgrade Not Possible", JOptionPane.INFORMATION_MESSAGE);
				}
				if(upgraded){
					uv.updateUpGrade(p.getActiveFighter());
					JOptionPane.showMessageDialog(uv, "Max Ki has Increased by 1! \nYour Fighter's Current Max Ki is: "+ p.getActiveFighter().getMaxKi(),"It's Training Time!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Trained.png"));
				}
			}break;
			case "MaxStamina":
			{
				Player p = game.getPlayer();
				boolean upgraded=false;
				try {
					p.upgradeFighter(p.getActiveFighter(), 'S');
					updateActiveFighter();
					upgraded=true;
				} catch (NotEnoughAbilityPointsException e1) {
					JOptionPane.showMessageDialog(uv, "You Fighter Has No More Ability Points :(" ,"Upgrade Not Possible", JOptionPane.INFORMATION_MESSAGE);
				}
				if(upgraded){
					uv.updateUpGrade(p.getActiveFighter());
					JOptionPane.showMessageDialog(uv, "Max Stamina has Increased by 1! \nYour Fighter's Current Max Stamina is: "+ p.getActiveFighter().getMaxStamina(),"It's Training Time!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Trained.png"));
				}
			}break;
			case "BackToWorld":
			{
				wv.setVisible(true);
				uv.dispose();
			}break;
///////////////////////////////////////////// Battle View
			case "Attack":
			{
				ArrayList<String> AssignedAttacks = new ArrayList<>();
				 
				AssignedAttacks.add("Physical Attack");
//				System.out.println("i am not");
//				System.out.println(game.getPlayer().getName());
//				System.out.println(game.getPlayer().getActiveFighter().getName());
//				System.out.println(game.getPlayer().getActiveFighter().getSuperAttacks());
//				System.out.println(game.getPlayer().getActiveFighter().getSuperAttacks().size());
		        for (SuperAttack s : game.getPlayer().getActiveFighter().getSuperAttacks()){
		        	AssignedAttacks.add(s.getName());
//		        	System.out.println("i am thor");
		        } 

		        for (UltimateAttack s : this.game.getPlayer().getActiveFighter().getUltimateAttacks()){
		        	AssignedAttacks.add(s.getName());
		        }
		        String [] assignedAttackslist= new String [AssignedAttacks.size()];
//		        System.out.println(AssignedAttacks.size());
		        
		        for (int i = 0; i < AssignedAttacks.size(); i++) {
		        	assignedAttackslist[i]=AssignedAttacks.get(i);
//		        	System.out.println(i);
				}

				String s = (String) JOptionPane.showInputDialog(bv,"Select The Attack",
	                    "Attack",
	                    JOptionPane.YES_NO_OPTION,
	                    new ImageIcon(),
	                    assignedAttackslist,
	                    assignedAttackslist[0]);
				if(s!=null)
				{

					if(s.equals("Physical Attack"))
						try {
							game.getBattle().attack(new PhysicalAttack());
							if(game.getBattle()!=null)
							bv.updateBattle((PlayableFighter) game.getBattle().getMe(),(NonPlayableFighter)game.getBattle().getFoe());
						} catch (NotEnoughKiException e1) {
						}
					else{
						for (int i = 0; i < game.getPlayer().getActiveFighter().getSuperAttacks().size(); i++) {
							if(s.equals(game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName()))
								try {
									game.getBattle().attack(game.getPlayer().getActiveFighter().getSuperAttacks().get(i));
									bv.updateBattle((PlayableFighter) game.getBattle().getMe(),(NonPlayableFighter)game.getBattle().getFoe());
									break;
								} catch (NotEnoughKiException e1) {
									JOptionPane.showMessageDialog(bv, "You Do Not Have Enough Ki Bars :(" ,"Can Not Use This Attack", JOptionPane.INFORMATION_MESSAGE);
								}
						}
						for (int i = 0; i < game.getPlayer().getActiveFighter().getUltimateAttacks().size(); i++) {
							if(s.equals(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName()))
								try {
									game.getBattle().attack(game.getPlayer().getActiveFighter().getUltimateAttacks().get(i));
									bv.updateBattle((PlayableFighter) game.getBattle().getMe(),(NonPlayableFighter)game.getBattle().getFoe());
									break;
								} catch (NotEnoughKiException e1) {
									JOptionPane.showMessageDialog(bv, "You Do Not Have Enough Ki Bars :(" ,"Can Not Use This Attack", JOptionPane.INFORMATION_MESSAGE);
								}
						}
					}
				}
			}break;
			case "Block":
			{
				Object[] options = {"Yes, Block.",
                "Cancel"};
				int n = JOptionPane.showOptionDialog(bv,
						"Use Your Action To Block?",
						"Block",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,     
						options,  
						options[0]); 
				
				if(n==JOptionPane.YES_OPTION){
					game.getBattle().block();
				}
				
			}break;
			case "Use":
			{
				game.getBattle().getMe();
				game.getBattle().getFoe();
				Object[] options = {"Yes, Use.",
                "Cancel"};
				int n = JOptionPane.showOptionDialog(bv,
						"Use a Senzu Bean to Restore Health and Stamina to Max?",
						"Use",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						new ImageIcon("senzu beani use.png"),     
						options,  
						options[0]); 
				
				if(n==JOptionPane.YES_OPTION){
					try {
						
						game.getBattle().use(game.getPlayer(), Collectible.SENZU_BEAN);
						bv.updateBattle((PlayableFighter) game.getBattle().getMe(),(NonPlayableFighter)game.getBattle().getFoe());
//						JOptionPane.showMessageDialog(bv, "You Consumed a Senzu Bean! \n"+
//								  "Your Health and Stamina are Now Full!","Use", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("senzu beani use.png"));
					} catch (NotEnoughSenzuBeansException e1) {
						JOptionPane.showMessageDialog(bv, "You Do Not Have Enough Senzu Beans :(" ,"Can Not Use a Senzu Bean", JOptionPane.INFORMATION_MESSAGE);
						}
					}
			}break;
			
				
			
			default: System.out.println("you forgot a case");
			System.out.println(e.getActionCommand());
			break;
			}
		
	}
	public static void main(String[] args) {
		new Controll();
	}
	public void createWorld(){
		wv= new WorldView(this);
		wv.addKeyListener(this);
		updateWorld();
		wv.getGrid()[0][0].setIcon(new ImageIcon("Boss "+wv.getGridTexturePath()+".gif")); 
		}
	public void updateWorld(){
		
		wv.getActivePlayerName().setText(game.getPlayer().getName());
		updatePlayerCollectibles();
		updateActiveFighter();
		updateFighterPos();
		wv.getGrid()[0][0].setIcon(new ImageIcon("Boss "+wv.getGridTexturePath()+".gif")); 
		
		wv.addKeyListener(this);
	}
	public void updatePlayerCollectibles(){
		Player p= game.getPlayer();
		wv.getNdragonballs().setText(""+p.getDragonBalls());
		wv.getNsenzubeans().setText("x"+p.getSenzuBeans());
		
		ArrayList<Integer> invisible= new ArrayList<Integer>();
		if(p.getDragonBalls()!=0)
		{
			for (int i = 0; i < wv.getDballs().length; i++) {
				if(!	wv.getDballs()[i].isVisible())
					invisible.add(i);
			}
//			System.out.println(p.getDragonBalls());
//			System.out.println( invisible.size());
			int n	= invisible.size();
			int vis= 7-n;
//			System.out.println( vis);
			for (int i = 0; i < p.getDragonBalls()-vis; i++) {
					int x=(int) (Math.random()*invisible.size());
				wv.getDballs()[invisible.get(x)].setVisible(true);;
				invisible.remove(x);
				
			}
		}
		else
		{
			for (int i = 0; i < wv.getDballs().length; i++) {
				wv.getDballs()[i].setVisible(false);
			}
		}
		
	}
	public void updateActiveFighter(){
		PlayableFighter p= game.getPlayer().getActiveFighter();
		
		wv.getActivefighterRace().setText(""+p.getRace());
		wv.getActivefighterName().setText(p.getName());
		wv.getActivefighterLevel().setText(""+p.getLevel());
		wv.getActivefighterEXP().setText(""+p.getXp()+"/"+p.getTargetXp());
		wv.getNabilitypoints().setText(""+p.getAbilityPoints());
	}
	
	public void updateFighterPos(){//////////////////////////////////////////////
		wv.getGrid()[game.getWorld().getPlayerRow()][game.getWorld().getPlayerColumn()].setIcon(new ImageIcon(game.getPlayer().getActiveFighter().getRace()+" "+wv.getGridTexturePath()+".gif"));
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if( !(i== game.getWorld().getPlayerRow() && j==game.getWorld().getPlayerColumn()))
					wv.getGrid()[i][j].setIcon(new ImageIcon(wv.getGridTexturePath()));
				
			}
		}
		wv.getGrid()[0][0].setIcon(new ImageIcon("Boss "+ wv.getGridTexturePath()+".gif"));
		
		
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		updateWorld();
		DragonWish [] dw= dragon.getWishes();
		wv.setVisible(false);
		dm= new DragonMode(dw,this);
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		updateWorld();
		ImageIcon i= (collectible.equals(Collectible.SENZU_BEAN))? new ImageIcon("Senzu.png"):new ImageIcon("Dragon Ball.png");
		JOptionPane.showMessageDialog(wv, "You Have Found a "+collectible+"!","Collectible Found!!!", JOptionPane.INFORMATION_MESSAGE, i);
		updatePlayerCollectibles();
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
//		System.out.println(e.getType()+ " "+ game.getPlayer().getActiveFighter().getSuperAttacks());
		switch (e.getType())
		{
		case STARTED:
		{
			updateWorld();
			game.getBattle().setInsane(insane);
			NonPlayableFighter op=	((NonPlayableFighter)game.getBattle().getFoe());
			JOptionPane.showMessageDialog(wv,"You Encountered The "+ ( (op.isStrong())? "Strong":"Weak")+" Foe "+op.getName()+"! \n"
					+"Get Ready For a Fight!","Foe Encountered!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
			bv= new BattleView(this,(PlayableFighter)game.getBattle().getMe(),(NonPlayableFighter)game.getBattle().getFoe());
			wv.setVisible(false);
		}break;
		case ENDED:
		{
			bv.updateBattle((PlayableFighter)game.getBattle().getMe(), (NonPlayableFighter)game.getBattle().getFoe());
			
			NonPlayableFighter op=	((NonPlayableFighter)game.getBattle().getFoe());
			if (((Fighter)e.getWinner()).equals((Fighter) game.getPlayer().getActiveFighter())){ //////I WON!!
				JOptionPane.showMessageDialog(wv, "You Have Beaten The " + ( (op.isStrong())? "Strong":"Weak")+" Foe "+op.getName()+"!","You Won!!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
				
				ArrayList<SuperAttack> sattacks= op.getSuperAttacks();
				ArrayList<UltimateAttack> uattacks= op.getUltimateAttacks();
				for (int i = 0; i < sattacks.size(); i++) 
				{
					if(game.getPlayer().getSuperAttacks().contains(sattacks.get(i))){
						sattacks.remove(i);
					i--;
					}
				}
				for (int i = 0; i < uattacks.size(); i++) 
				{
					if(game.getPlayer().getUltimateAttacks().contains(uattacks.get(i))){
						uattacks.remove(i);
					i--;}
				}
//				System.out.println(sattacks.toString());
				JOptionPane.showMessageDialog(wv, "You Gained "+op.getLevel()*5 +" XP! \n"
												+(	(!sattacks.isEmpty())? "You Unlocked The Super Attacks:"+sattacks.toString()+"! \n":"")
												+((!uattacks.isEmpty())? "You Unlocked The Ultimate Attacks:"+uattacks.toString()+"!":"")
												,"You Won!!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
			}else
			{
				JOptionPane.showMessageDialog(wv, "You Were Defeated by The " + ( (op.isStrong())? "Strong":"Weak")+" Foe "+op.getName()+"! :(","You Lost!!!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
			}
			updateWorld();
			wv.setVisible(true);
		
			bv.dispose();
		}break;
		case ATTACK:
		{
//			if(game.getBattle().getAttacker()==game.getBattle().getFoe())
//			System.out.println("foe attacked");
			bv.updateBattle((PlayableFighter)game.getBattle().getMe(), (NonPlayableFighter)game.getBattle().getFoe());
			if(e.getAttack() instanceof SuperAttack && (e.getAttack()) instanceof MaximumCharge)
				JOptionPane.showMessageDialog(bv, ((Fighter) game.getBattle().getAttacker()).getName()+ " Used Maximum Charge!","Attack!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
			else
				if((e.getAttack()) instanceof SuperSaiyan){
				JOptionPane.showMessageDialog(bv, ((Fighter) game.getBattle().getAttacker()).getName()+ " Has Transformed into a Super Saiyan!","Transformation!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon());
				if(((Fighter) game.getBattle().getAttacker()).getName().equals(		((Fighter)game.getBattle().getMe()).getName()	)){
					bv.getF1().setIcon(new ImageIcon("SuperSaiyan.png"));
				}
				}else
			JOptionPane.showMessageDialog(bv, ((Fighter) game.getBattle().getAttacker()).getName()+ " used "+ e.getAttack().getName(),"Attack!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Attack Icon.png"));
		
		}break;
		case BLOCK:
		{
			JOptionPane.showMessageDialog(bv, ((Fighter) game.getBattle().getAttacker()).getName()+ " used block!","Block!", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Block Icon.png"));
		}break;
		case NEW_TURN:
		{
			Battle b = game.getBattle();
//			JOptionPane.showMessageDialog(bv,"It is now "+ ((Fighter) b.getAttacker()).getName()+ "'s Turn.","New Turn", JOptionPane.INFORMATION_MESSAGE, 
			updateBattle();
			if(((Fighter)b.getAttacker()).equals((Fighter)b.getFoe()))
				try {
					game.getBattle().play();
				} catch (NotEnoughKiException e1) {
					if(Math.random()*2==0)
					b.block();
					else
						try {
							b.attack(new PhysicalAttack());
						} catch (NotEnoughKiException e2) {///NEVER GONNA HAPPEN!
						}
				}
		}break;
		case USE:
		{
//			System.out.println("used");
			bv.updateBattle((PlayableFighter)game.getBattle().getMe(), (NonPlayableFighter)game.getBattle().getFoe());
		}break;
		}
	}
	public void updateBattle(){
		Battle b= game.getBattle();
		bv.getTurn().setIcon(new ImageIcon(	(	((Fighter)b.getAttacker()).equals((Fighter) b.getMe())	)? "Your Turn.png":"opponent's turn.png" ));
		if (((Fighter)b.getAttacker()).equals((Fighter) b.getMe())){
		bv.getOpturnText().setVisible(false);
		bv.getMyTurnText().setVisible(true);
		}else{
		bv.getMyTurnText().setVisible(false);
		bv.getOpturnText().setVisible(true);
		}
			
	}
	///////////////////////////////////////////////////////////////////////////////////

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_UP :{
			wv.getUP().doClick();
		}break;
		case KeyEvent.VK_DOWN :{
			wv.getDown().doClick();
		}break;
		case KeyEvent.VK_LEFT :{
			wv.getLeft().doClick();
		}break;
		case KeyEvent.VK_RIGHT :{
			wv.getRight().doClick();;
		}break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
