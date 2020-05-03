package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import dragonball.controller.Controll;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;

public class BattleView extends JFrame {
	ActionListener actionListener;
	private JProgressBar myHP;
	private JProgressBar myKI;
	private JProgressBar myStamina;
	private JProgressBar opponentHP;
	private JProgressBar opponentKI;
	private JProgressBar opponentStamina;
	private JButton Attack;
	private JButton Block;
	private JButton Use;
	private JLabel turn;
	private JLabel myturnText;
	private JLabel opturnText;
	private JLabel f1;
	private JLabel MyLevel;
	private JLabel oppLevel;
	
	public BattleView(ActionListener a,PlayableFighter p, NonPlayableFighter f) {
		setTitle("Dragon Ball_AA");
		actionListener=a;
	setMinimumSize(new Dimension(1366,768));
	setResizable(false);
	
	ImageIcon i = new ImageIcon("dragon ball mini.png");
	setIconImage(i.getImage());
	
	
	JLabel mylabel= new JLabel();
	mylabel.setLayout(null);
	mylabel.setIcon(new ImageIcon("Battle Field.jpg"));

	////////////////////////////////////////////////////Fighter Positions
	JPanel myFighter= new JPanel();
	myFighter.setLayout(null);
	myFighter.setBounds(10, 85, 500, 700);
	myFighter.setOpaque(false);
	myFighter.setVisible(true);
	f1 =new JLabel();
	f1.setBounds(0, 0, 460, 650);
	//TODO picture according to race
	f1.setIcon(new ImageIcon(p.getRace()+".png"));
    f1.setOpaque(false);
	f1.repaint();
	f1.validate();
	myFighter.add(f1);
	mylabel.add(myFighter);
	JPanel myOpponent= new JPanel();
	myOpponent.setLayout(null);
	myOpponent.setBounds(866, 100, 500, 700);
	myOpponent.setOpaque(false);
	myOpponent.setVisible(true);
	JLabel f2 =new JLabel();
	f2.setBounds(0, 20, 460, 650);
	String [] weakfoes= {"WeakFoe1.png","WeakFoe2.png","WeakFoe3.png","WeakFoe4.png","WeakFoe5.png"};
	String [] boss= {"Boss 1.png","Boss 2.png","Boss 3.png","Boss 4.png"};
	f2.setIcon(new ImageIcon((f.isStrong())? boss[(int)(Math.random()*boss.length)]:weakfoes[(int)(Math.random()*weakfoes.length)]));
    f2.setOpaque(false);
	f2.repaint();
	f2.validate();
	myOpponent.add(f2);
	mylabel.add(myOpponent);

	setContentPane(mylabel);
	
//////////////////////////////////////	MY STATS
	JLabel hplabel= new JLabel("HP");
	hplabel.setFont(new Font("Algerian",Font.BOLD,20));
	hplabel.setForeground(Color.white);
	hplabel.setBounds(12, 10, 50, 30);
	add(hplabel);
	JLabel kilabel= new JLabel("KI");
	kilabel.setFont(new Font("Algerian",Font.BOLD,20));
	kilabel.setForeground(Color.white);
	kilabel.setBounds(12, 45, 50, 20);
	add(kilabel);
	JLabel staminalabel= new JLabel("Stamina");
	staminalabel.setFont(new Font("Algerian",Font.BOLD,17));
	staminalabel.setForeground(Color.white);
	staminalabel.setBounds(12, 70, 90, 20);
	add(staminalabel);
	 MyLevel= new JLabel("Level :"+p.getLevel());
	 MyLevel.setFont(new Font("Algerian",Font.BOLD,25));
	 MyLevel.setForeground(Color.white);
	 MyLevel.setBounds(102, 110, 150, 20);
	add(MyLevel);
	
	//50,205,50  GREEN
	Color green= new Color(50,205,50);
	myHP= new JProgressBar(0, p.getMaxHealthPoints());
	myHP.setBounds(102, 10, 500, 30);
	myHP.setBorderPainted(true);
	myHP.setString(p.getMaxHealthPoints()+" / "+ p.getMaxHealthPoints());
	myHP.setStringPainted(true);
	myHP.setForeground(green);
	myHP.setBackground(Color.RED);
	myHP.setValue(p.getMaxHealthPoints());
	myHP.setVisible(true);
	add(myHP);
	
	
	myKI= new JProgressBar(0, p.getMaxKi());
	myKI.setBounds(102, 45, 400, 20);
	myKI.setBorderPainted(true);
	myKI.setString("0 / "+p.getMaxKi());
	myKI.setStringPainted(true);
	myKI.setForeground(Color.yellow);
	myKI.setBackground(Color.RED);
	myKI.setValue(0);
	myKI.setVisible(true);
	add(myKI);
	
	myStamina= new JProgressBar(0, p.getMaxStamina());
	myStamina.setBounds(102, 70, 350, 20);
	myStamina.setBorderPainted(true);
	myStamina.setString(p.getMaxStamina()+" / "+p.getMaxStamina());
	myStamina.setStringPainted(true);
	myStamina.setForeground(Color.blue);
	myStamina.setBackground(Color.RED);
	myStamina.setValue(p.getMaxStamina());
	myStamina.setVisible(true);
	add(myStamina);
	////////////////////////////////////// Opponent STATS
	JLabel ophplabel= new JLabel("HP");
	ophplabel.setFont(new Font("Algerian",Font.BOLD,20));
	ophplabel.setForeground(Color.white);
	ophplabel.setBounds(1260, 10, 50, 30);
	add(ophplabel);
	JLabel opkilabel= new JLabel("KI");
	opkilabel.setFont(new Font("Algerian",Font.BOLD,20));
	opkilabel.setForeground(Color.white);
	opkilabel.setBounds(1260, 45, 50, 20);
	add(opkilabel);
	JLabel opstaminalabel= new JLabel("Stamina");
	opstaminalabel.setFont(new Font("Algerian",Font.BOLD,17));
	opstaminalabel.setForeground(Color.white);
	opstaminalabel.setBounds(1260, 70, 90, 20);
	add(opstaminalabel);
	
	oppLevel= new JLabel("Level :"+f.getLevel());
	oppLevel.setFont(new Font("Algerian",Font.BOLD,25));
	oppLevel.setForeground(Color.white);
	oppLevel.setBounds(1130, 110, 150, 20);
	add(oppLevel);
	
	
	
	

	opponentHP= new JProgressBar(0, f.getMaxHealthPoints());
	opponentHP.setBounds(756, 10, 500, 30);
	opponentHP.setBorderPainted(true);
	opponentHP.setString(f.getMaxHealthPoints()+" / "+f.getMaxHealthPoints());
	opponentHP.setStringPainted(true);
	opponentHP.setForeground(Color.RED);
	opponentHP.setBackground(green);
	opponentHP.setValue(0);
	opponentHP.setVisible(true);
	add(opponentHP);
	
	opponentKI= new JProgressBar(0, f.getMaxKi());
	opponentKI.setBounds(856, 45, 400, 20);
	opponentKI.setBorderPainted(true);
	opponentKI.setString("0 / "+f.getMaxKi());
	opponentKI.setStringPainted(true);
	opponentKI.setForeground(Color.RED);
	opponentKI.setBackground(Color.yellow);
	opponentKI.setValue(f.getMaxKi());
	opponentKI.setVisible(true);
	add(opponentKI);
	
	opponentStamina= new JProgressBar(0, f.getMaxStamina());
	opponentStamina.setBounds(906, 70,350, 20);
	opponentStamina.setBorderPainted(true);
	opponentStamina.setString(f.getMaxStamina()+" / "+f.getMaxStamina());
	opponentStamina.setStringPainted(true);
	opponentStamina.setForeground(Color.RED);
	opponentStamina.setBackground(Color.blue);
	opponentStamina.setValue(0);
	opponentStamina.setVisible(true);
	add(opponentStamina);
	///////////////// state Label
	
	turn =new JLabel();
    turn.setBounds(512, 60, 300, 300);
    turn.setVisible(true);
    turn.setIcon(new ImageIcon("Your Turn.png"));
    add(turn);
    
    opturnText= new JLabel();
    opturnText.setBounds(512, 570, 450, 30);
    opturnText.setVisible(false);
    opturnText.setForeground(Color.white);
    opturnText.setFont(new Font("Algerian",Font.BOLD,30));
    opturnText.setText("Opponent's Turn");
    add(opturnText);
	
    myturnText= new JLabel();
    myturnText.setBounds(560, 570, 450, 30);
    myturnText.setVisible(true);
    myturnText.setForeground(Color.white);
    myturnText.setFont(new Font("Algerian",Font.BOLD,30));
    myturnText.setText("Your Turn");
    add(myturnText);
	
	
	
	/////////////////////////////////////////// Action Buttons
	Attack =new JButton("Attack");
	Attack.setBounds(10, 665, 100, 50);
	Attack.setForeground(Color.black);
	Attack.setFont(new Font("Algerian", Font.BOLD,15));
	Attack.setVisible(true);
	Attack.setActionCommand("Attack");
	add(Attack);
	Attack.addActionListener(a);
	Block =new JButton("Block");
	Block.setBounds(130, 665, 100, 50);
	Block.setForeground(Color.black);
	Block.setFont(new Font("Algerian", Font.BOLD,15));
	Block.setVisible(true);
	Block.setActionCommand("Block");
	add(Block);
	Block.addActionListener(a);
	Use =new JButton("Use");
	Use.setBounds(250, 665, 100, 50);
	Use.setForeground(Color.black);
	Use.setFont(new Font("Algerian", Font.BOLD,15));
	Use.setVisible(true);
	Use.setActionCommand("Use");
	add(Use);
	Use.addActionListener(a);
	
	
	mylabel.repaint();
	mylabel.validate();
	repaint(); 
	validate();
	
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new CloseButtonListener());
	setVisible(true);
	
	}
	public ActionListener getActionListener() {
		return actionListener;
	}
	public void updateBattle(PlayableFighter p, NonPlayableFighter f){
	myHP.setValue(p.getHealthPoints());
	myHP.setString(p.getHealthPoints()+" / "+ p.getMaxHealthPoints());
	myKI.setValue(p.getKi());
	myKI.setString(p.getKi()+" / "+p.getMaxKi());
	myStamina.setValue(p.getStamina());
	myStamina.setString(p.getStamina()+" / "+p.getMaxStamina());
	
		
	opponentHP.setValue(f.getMaxHealthPoints()-f.getHealthPoints());
	opponentHP.setString(f.getHealthPoints()+" / "+f.getMaxHealthPoints());
	
	opponentKI.setValue(f.getMaxKi()-f.getKi());
	opponentKI.setString(f.getKi()+" / "+f.getMaxKi());
	
	opponentStamina.setValue(f.getMaxStamina()-f.getStamina());
	opponentStamina.setString(f.getStamina()+" / "+f.getMaxStamina());
	
	if(p instanceof Saiyan ){
		if(((Saiyan) p).isTransformed()){
			f1.setIcon(new ImageIcon("SuperSaiyan.png"));
		}
		else{
			f1.setIcon(new ImageIcon("Saiyan.png"));
		}
	}
	
	
	}
	public JLabel getTurn() {
		return turn;
	}
	public JLabel getMyTurnText() {
		return myturnText;
	}
	public JLabel getOpturnText() {
		return opturnText;
	}
	public JLabel getF1() {
		return f1;
	}
	public JLabel getOppLevel() {
		return oppLevel;
	}
	public void setOppLevel(JLabel oppLevel) {
		this.oppLevel = oppLevel;
	}
	public JLabel getMyLevel() {
		return MyLevel;
	}
	public void setMyLevel(JLabel myLevel) {
		MyLevel = myLevel;
	}
	

}
