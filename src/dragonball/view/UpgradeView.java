package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dragonball.controller.Controll;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.DragonWish;

public class UpgradeView extends JFrame {
	private ActionListener actionListener;
	private JLabel CAbilityPoints;
	private JLabel lMaxHP;
	private JLabel lBlastDamage;
	private JLabel lPhysicalDamage;
	private JLabel lMaxKi;
	private JLabel lMaxStamina;

	
	
	public UpgradeView(ActionListener a, PlayableFighter p){
		setTitle("Dragon Ball_AA");
		actionListener=a;
		setMinimumSize(new Dimension(1366,768));
		setResizable(false);
		ImageIcon i = new ImageIcon("dragon ball mini.png");
		setIconImage(i.getImage());
		JLabel mylabel= new JLabel();
		mylabel.setLayout(null);
		mylabel.setIcon(new ImageIcon("Son-Goku-Tranforms-Super-Saiyan-3-Image-Wallpaper - Copy.jpg"));
		/////////////////////////////Buttons4
		Color c= new Color(47,79,79);
		
		JButton MaxHP =new JButton();
		MaxHP.setBounds(290, 10, 250, 80);
		MaxHP.setText("+50 Max HP");
		MaxHP.setForeground(new Color(220,220,220));
		MaxHP.setFont(new Font("Algerian", Font.BOLD,20));
		MaxHP.setBackground(c);
		MaxHP.setVisible(true);
		MaxHP.setActionCommand("MaxHP");
		MaxHP.addActionListener(a);
		
		JButton PhysicalDammage =new JButton();
		PhysicalDammage.setText("+50 Physical Dammage");
		PhysicalDammage.setBounds(560, 10, 250, 80);
		PhysicalDammage.setForeground(new Color(220,220,220));
		PhysicalDammage.setFont(new Font("Algerian", Font.BOLD,16));
		PhysicalDammage.setBackground(c);
		PhysicalDammage.setVisible(true);
		PhysicalDammage.setActionCommand("PhysicalDammage");
		PhysicalDammage.addActionListener(a);
		
		JButton BlastDammage =new JButton();
		BlastDammage.setText("+50 Blast Dammage");
		BlastDammage.setBounds(830, 10, 250, 80);
		BlastDammage.setForeground(new Color(220,220,220));
		BlastDammage.setFont(new Font("Algerian", Font.BOLD,17));
		BlastDammage.setBackground(c);
		BlastDammage.setVisible(true);
		BlastDammage.setActionCommand("BlastDammage");
		BlastDammage.addActionListener(a);
	
		JButton MaxKI =new JButton();
		MaxKI.setText("+1 Max Ki");
		MaxKI.setBounds(1100, 10, 250, 80);
		MaxKI.setForeground(new Color(220,220,220));
		MaxKI.setFont(new Font("Algerian", Font.BOLD,20));
		MaxKI.setBackground(c);
		MaxKI.setVisible(true);
		MaxKI.setActionCommand("MaxKI");
		MaxKI.addActionListener(a);
		
		JButton MaxStamina =new JButton();
		MaxStamina.setText("+1 Max Stamina");
		MaxStamina.setBounds(20, 10, 250, 80);
		MaxStamina.setForeground(new Color(220,220,220));
		MaxStamina.setFont(new Font("Algerian", Font.BOLD,20));
		MaxStamina.setBackground(c);
		MaxStamina.setVisible(true);
		MaxStamina.setActionCommand("MaxStamina");
		MaxStamina.addActionListener(a);
		
		
		CAbilityPoints =new JLabel();
		CAbilityPoints.setText("Ability Points Left to Spend : "+p.getAbilityPoints());
		CAbilityPoints.setFont(new Font ("Algerian",Font.BOLD,26));
		CAbilityPoints.setBounds(30, 130, 500, 50);
		CAbilityPoints.setVisible(true);
		CAbilityPoints.validate();
		CAbilityPoints.repaint();
		
		 lMaxHP =new JLabel();
		lMaxHP.setText("Max HP : "+p.getMaxHealthPoints());
		lMaxHP.setFont(new Font ("Algerian",Font.BOLD,26));
		lMaxHP.setBounds(30, 160, 500, 50);
		lMaxHP.setVisible(true);
		lMaxHP.validate();
		lMaxHP.repaint();
		
		
		 lBlastDamage =new JLabel();
		lBlastDamage.setText("Blast Damage : "+p.getBlastDamage());
		lBlastDamage.setFont(new Font ("Algerian",Font.BOLD,26));
		lBlastDamage.setBounds(30,190, 500, 50);
		lBlastDamage.setVisible(true);
		lBlastDamage.validate();
		lBlastDamage.repaint();
		
		 lPhysicalDamage =new JLabel();
		lPhysicalDamage.setText("Physical Damage : "+p.getPhysicalDamage());
		lPhysicalDamage.setFont(new Font ("Algerian",Font.BOLD,26));
		lPhysicalDamage.setBounds(30, 220, 500, 50);
		lPhysicalDamage.setVisible(true);
		lPhysicalDamage.validate();
		lPhysicalDamage.repaint();
		
		 lMaxKi =new JLabel();
		lMaxKi.setText("Max Ki : "+p.getMaxKi());
		lMaxKi.setFont(new Font ("Algerian",Font.BOLD,26));
		lMaxKi.setBounds(30, 250, 500, 50);
		lMaxKi.setVisible(true);
		lMaxKi.validate();
		lMaxKi.repaint();
		
		
		 lMaxStamina =new JLabel();
		lMaxStamina.setText("Max Stamina : "+p.getMaxStamina());
		lMaxStamina.setFont(new Font ("Algerian",Font.BOLD,26));
		lMaxStamina.setBounds(30, 280, 500, 50);
		lMaxStamina.setVisible(true);
		lMaxStamina.validate();
		lMaxStamina.repaint();
		
		
		
		
		JButton BackToWorld =new JButton();
		BackToWorld.setText("Back To Map");
		BackToWorld.setBounds(30, 400, 150, 50);
		BackToWorld.setForeground(new Color(220,220,220));
		BackToWorld.setFont(new Font("Algerian", Font.BOLD,15));
		BackToWorld.setBackground(c);
		BackToWorld.setVisible(true);
		BackToWorld.setActionCommand("BackToWorld");
		BackToWorld.addActionListener(a);
		
		
		
		mylabel.add(BlastDammage);
		mylabel.add(MaxKI);
		mylabel.add(PhysicalDammage);
		mylabel.add(MaxHP);
		mylabel.add(MaxStamina);
		mylabel.add(CAbilityPoints);
		mylabel.add(BackToWorld);
		mylabel.add(lMaxHP);
		mylabel.add(lBlastDamage);
		mylabel.add(lPhysicalDamage);
		mylabel.add(lMaxKi);
		mylabel.add(lMaxStamina);

		
		mylabel.repaint();
		mylabel.validate();
		repaint(); 
		validate();
		getContentPane().add(mylabel);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	public JLabel getCAbilityPoints() {
		return CAbilityPoints;
	}

	public static void main(String[] args) {
		new UpgradeView(new Controll(),new Majin(""));
	}

	public JLabel getlPhysicalDamage() {
		return lPhysicalDamage;
	}


	public JLabel getlMaxKi() {
		return lMaxKi;
	}

	public JLabel getlMaxStamina() {
		return lMaxStamina;
	}


	public JLabel getlBlastDamage() {
		return lBlastDamage;
	}

	

	public JLabel getlMaxHP() {
		return lMaxHP;
	}
	public void updateUpGrade( PlayableFighter p){
       getlBlastDamage().setText("Blast Damage : "+p.getBlastDamage());
       getlMaxKi().setText("Max Ki : "+p.getMaxKi());
       getlMaxHP().setText("Max HP : "+p.getMaxHealthPoints());
       getlPhysicalDamage().setText("Physical Damage : "+p.getPhysicalDamage());
       getCAbilityPoints().setText("Ability Points Left to Spend : "+p.getAbilityPoints());
       getlMaxStamina().setText("Max Stamina : "+p.getMaxStamina());
	}

	
}
