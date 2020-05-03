package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dragonball.controller.Controll;

public class WorldView extends JFrame {
	private ActionListener actionListener;
	private JLabel [][] Grid;
	private JLabel l;
	private JLabel activefighterName;
	private JLabel activefighterRace;
	private JLabel activefighterLevel;
	private JLabel activefighterEXP;
	private JLabel Nabilitypoints;
	private JLabel Nsenzubeans;
	private JLabel Ndragonballs;
	private JLabel activePlayerName;
	private JButton UP;
	private JButton Right;
	private JButton Left;
	private JButton Down;
	private JButton Save;
	private String GridTexturePath;
	private JLabel [] dballs;
	
	public WorldView(ActionListener a){
		setTitle("Dragon Ball_AA");
		actionListener=a;
		addKeyListener((Controll)a);
		setMinimumSize(new Dimension(1366,768));
		setResizable(false);
		setLayout(new BorderLayout());
		ImageIcon ii = new ImageIcon("dragon ball mini.png");
		setIconImage(ii.getImage());
		
		String [] sa= {"Desert 1.jpg","Desert 2.jpg","Grass 1.jpg","Grass 2.jpg","Snow.jpg","Fire.jpg"};
		GridTexturePath= sa[(int)(Math.random()*sa.length)];
		////////////////////////////////////////////////////	Grid
		l =new JLabel ();
		l.setLayout(new GridLayout(10,10));
		setMinimumSize(new Dimension(1024,748));
		Grid= new JLabel [10][10];
		for(int i=0;i<10;i++)
		{
			for (int j = 0; j < 10; j++) {
			
				
			JLabel b= new JLabel();
			Grid[i][j]= b;
			b.setFocusable(true);
			b.setIcon(new ImageIcon(GridTexturePath));
			l .add(b);
			b.setVisible(true);
			}
		}

		add(l,BorderLayout.CENTER);
		//////////////////////////////////////////////////// Information Panel
		JPanel panel= new JPanel();
		panel.setPreferredSize(new Dimension(200, getHeight()));
		panel.setLayout(null);
		panel.setBackground(Color.GRAY);
		panel.setVisible(true);
	
		add(panel,BorderLayout.EAST);

		//////////////////////////////////////////////////// Panel Components
		

		JLabel playername= new JLabel("Player Name : ");
		playername.setBounds(0, 10, 100, 30);
		panel.add(playername);
		
		JLabel activefighter= new JLabel("Active Fighter : ");
		activefighter.setBounds(0, 40, 100, 30);
		panel.add(activefighter);
		JLabel race= new JLabel("Race : ");
		race.setBounds(0, 70, 100, 30);
		panel.add(race);
		JLabel activefighterl= new JLabel("Level : ");
		activefighterl.setBounds(0, 100, 100, 30);
		panel.add(activefighterl);
		JLabel activefighterxp= new JLabel("EXP : ");
		activefighterxp.setBounds(0, 130, 100, 30);
		panel.add(activefighterxp);
		
		JLabel abilitypoints= new JLabel("Ability Points : ");
		abilitypoints.setBounds(0, 160, 100, 30);
		panel.add(abilitypoints);
		
		JLabel senzubeans= new JLabel("Senzu Beans : ");
		senzubeans.setBounds(0, 210, 100, 30);
		panel.add(senzubeans);
		
		
		
		
		/////////////////////		////////////////////////
		activePlayerName= new JLabel("");
		activePlayerName.setBounds(100, 10, 100, 30);	
		panel.add(activePlayerName);
		
		activefighterName= new JLabel("");
		activefighterName.setBounds(100, 40, 100, 30);
		panel.add(activefighterName);
			
		activefighterRace= new JLabel("");
		activefighterRace.setBounds(100, 70, 100, 30);	
		panel.add(activefighterRace);

		
		activefighterLevel= new JLabel("1");
		activefighterLevel.setBounds(100, 100, 100, 30);
		panel.add(activefighterLevel);
		
		activefighterEXP= new JLabel("0 / 10");
		activefighterEXP.setBounds(100, 130, 100, 30);
		panel.add(activefighterEXP);
		
		Nabilitypoints= new JLabel("0");
		Nabilitypoints.setBounds(100, 160, 100, 30);
		panel.add(Nabilitypoints);
		
		Nsenzubeans= new JLabel("x"+0);
		Nsenzubeans.setIcon(new ImageIcon("senzu mini.png"));
		Nsenzubeans.setBounds(100, 210, 100, 30);
		panel.add(Nsenzubeans);
		
		
		/////////////////////		////////////////////////
		UP =new JButton("UP");
		UP.setBounds(50, 500, 100, 20);
		UP.setForeground(new Color(220,220,220));
		Color c1= new Color(47,79,79);
		UP.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		UP.setBackground(c1);
		UP.setVisible(true);
		UP.addActionListener(a);
		UP.setActionCommand("UP");
		panel.add(UP);
		
		Down =new JButton("Down");
		Down.setBounds(50, 600, 100, 20);
		Down.setForeground(new Color(220,220,220));
		Down.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		Down.setBackground(c1);
		Down.setVisible(true);
		Down.addActionListener(a);
		Down.setActionCommand("Down");
		panel.add(Down);	
		
		Left =new JButton("Left");
		Left.setBounds(0, 550, 100, 20);
		Left.setForeground(new Color(220,220,220));
		Left.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		Left.setBackground(c1);
		Left.setVisible(true);
		Left.addActionListener(a);
		Left.setActionCommand("Left");
		panel.add(Left);
		
		Right =new JButton("Right");
		Right.setBounds(100, 550, 100, 20);
		Right.setForeground(new Color(220,220,220));
		Right.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		Right.setBackground(c1);
		Right.setVisible(true);
		Right.addActionListener(a);
		Right.setActionCommand("Right");
		panel.add(Right);
		Grid[9][9].setIcon(new ImageIcon("hd.gif"));
		//////////////////////////////////////////////////// TODO ADD BOSS
		
		Save =new JButton("Save");
		Save.setBounds(0, 360, 200, 30);
		Save.setForeground(new Color(220,220,220));
		Save.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		Save.setBackground(c1);
		Save.setVisible(true);
		Save.addActionListener(a);
		Save.setActionCommand("Save");
		panel.add(Save);
		JButton NewFighter =new JButton("NewFighter");
		NewFighter.setBounds(0, 390, 200, 30);
		NewFighter.setForeground(new Color(220,220,220));
		NewFighter.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		NewFighter.setBackground(c1);
		NewFighter.setVisible(true);
		NewFighter.addActionListener(a);
		NewFighter.setActionCommand("NewFighter");
		panel.add(NewFighter);
		JButton SelectFighter =new JButton("SelectFighter");
		SelectFighter.setBounds(0, 420, 200, 30);
		SelectFighter.setForeground(new Color(220,220,220));
		SelectFighter.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		SelectFighter.setBackground(c1);
		SelectFighter.setVisible(true);
		SelectFighter.addActionListener(a);
		SelectFighter.setActionCommand("SelectFighter");
		panel.add(SelectFighter);
		JButton AssignAttacks =new JButton("AssignAttacks");
		AssignAttacks.setBounds(0, 450, 200, 30);
		AssignAttacks.setForeground(new Color(220,220,220));
		AssignAttacks.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		AssignAttacks.setBackground(c1);
		AssignAttacks.setVisible(true);
		AssignAttacks.addActionListener(a);
		AssignAttacks.setActionCommand("AssignAttacks");
		panel.add(AssignAttacks);
		
		JButton UpgradeFighter =new JButton("UpgradeFighter");
		UpgradeFighter.setBounds(0, 280, 200, 30);
		UpgradeFighter.setForeground(new Color(220,220,220));
		UpgradeFighter.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		UpgradeFighter.setBackground(c1);
		UpgradeFighter.setVisible(true);
		UpgradeFighter.addActionListener(a);
		UpgradeFighter.setActionCommand("UpgradeFighter");
		panel.add(UpgradeFighter);
		
		JButton Menu =new JButton("Menu");
		Menu.setBounds(0, 680, 200, 30);
		Menu.setForeground(new Color(220,220,220));
		Menu.setFont(new Font("Arial", Font.ROMAN_BASELINE,10));
		Menu.setBackground(c1);
		Menu.setVisible(true);
		Menu.addActionListener(a);
		Menu.setActionCommand("Menu");
		panel.add(Menu);
		
		
		panel.repaint();
		panel.validate();
		/////////////////////////////////////////////Dragoon balls panel
		JPanel tool = new JPanel();
		tool.setPreferredSize(new Dimension(65, getHeight()));
		tool.setBackground(Color.BLACK);
		tool.setLayout(null);
		add(tool,BorderLayout.WEST);
		tool.setVisible(true);
		tool.repaint();
		JLabel d1 =new JLabel();
		d1.setBounds(5, 10, 60, 60);
		d1.setIcon(new ImageIcon ("Dragon Ball 1.gif"));
		tool.add(d1);
		d1.setVisible(false);
		JLabel d2 =new JLabel();
		d2.setBounds(5, 100, 60, 60);
		d2.setIcon(new ImageIcon ("Dragon Ball 2.gif"));
		tool.add(d2);
		d2.setVisible(false);
		JLabel d3 =new JLabel();
		d3.setBounds(5, 190, 60, 60);
		d3.setIcon(new ImageIcon ("Dragon Ball 3.gif"));
		tool.add(d3);
		d3.setVisible(false);
		JLabel d4 =new JLabel();
		d4.setBounds(5, 280, 60, 60);
		d4.setIcon(new ImageIcon ("Dragon Ball 4.gif"));
		tool.add(d4);
		d4.setVisible(false);
		JLabel d5 =new JLabel();
		d5.setBounds(5, 370, 60, 60);
		d5.setIcon(new ImageIcon ("Dragon Ball 5.gif"));
		tool.add(d5);
		d5.setVisible(false);
		JLabel d6 =new JLabel();
		d6.setBounds(5, 460, 60, 60);
		d6.setIcon(new ImageIcon ("Dragon Ball 6.gif"));
		tool.add(d6);
		d6.setVisible(false);
		JLabel d7 =new JLabel();
		d7.setBounds(5, 550, 60, 60);
		d7.setIcon(new ImageIcon ("Dragon Ball 7.gif"));
		tool.add(d7);
		d7.setVisible(false);
		
		dballs= new JLabel [7];
		dballs[0]=d1;
		dballs[1]=d2;
		dballs[2]=d3;
		dballs[3]=d4;
		dballs[4]=d5;
		dballs[5]=d6;
		dballs[6]=d7;
		
		JLabel dragonballs= new JLabel("Dragon ");
		dragonballs.setForeground(Color.WHITE);
		dragonballs.setBounds(12, 660, 65, 30);
		tool.add(dragonballs);
		
		JLabel dragonballs2= new JLabel(" Balls ");
		dragonballs2.setForeground(Color.WHITE);
		dragonballs2.setBounds(15, 685, 65, 30);
		tool.add(dragonballs2);
		
		Ndragonballs = new JLabel("0");
		Ndragonballs.setForeground(Color.WHITE);
		Ndragonballs.setBounds(30,710,35,30);
		tool.add(Ndragonballs);
		
		l.repaint();
		l.validate();
		repaint();
		validate();
		l.setVisible(true);
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseButtonListener());
	}
	
	public void updatedragonballs(int n){
		//TODO update text
	}
	
	public JLabel[][] getGrid() {
		return Grid;
	}

	public JLabel getL() {
		return l;
	}

	public JLabel getActivefighterName() {
		return activefighterName;
	}

	public JLabel getActivefighterLevel() {
		return activefighterLevel;
	}

	public JLabel getActivefighterEXP() {
		return activefighterEXP;
	}

	public JLabel getNabilitypoints() {
		return Nabilitypoints;
	}

	public JLabel getNsenzubeans() {
		return Nsenzubeans;
	}
	
	public JLabel getNdragonballs() {
		return Ndragonballs;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public JButton getSave() {
		return Save;
	}

	public String getGridTexturePath() {
		return GridTexturePath;
	}

	public JLabel[] getDballs() {
		return dballs;
	}

	public JButton getUP() {
		return UP;
	}

	public JButton getRight() {
		return Right;
	}

	public JButton getLeft() {
		return Left;
	}

	public JButton getDown() {
		return Down;
	}

	public JLabel getActivePlayerName() {
		return activePlayerName;
	}

	public JLabel getActivefighterRace() {
		return activefighterRace;
	}
	
	

}
