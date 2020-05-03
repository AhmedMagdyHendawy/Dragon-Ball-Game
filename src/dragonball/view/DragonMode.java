package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

import dragonball.model.dragon.DragonWish;

public class DragonMode extends JFrame {
	private ActionListener actionListener;
	private JButton SuperAttack;
	private JButton UltimateAttack;
	private JButton AbilityPoint;
	private JButton SenzuBeans;
	public DragonMode(DragonWish [] d,ActionListener a){
		setTitle("Dragon Ball_AA");
		actionListener=a;
		setMinimumSize(new Dimension(1366,768));
		setResizable(false);
		ImageIcon i = new ImageIcon("dragon ball mini.png");
		setIconImage(i.getImage());
		JLabel mylabel= new JLabel();
		mylabel.setLayout(null);
		mylabel.setIcon(new ImageIcon("Dragon Mode.png"));
		/////////////////////////////Buttons 4
		Color c= new Color(47,79,79);
		
		SenzuBeans =new JButton();
		SenzuBeans.setBounds(100, 110, 250, 80);
		SenzuBeans.setText(d[0].getSenzuBeans()+" Senzu Bean(s)");
		SenzuBeans.setForeground(new Color(220,220,220));
		SenzuBeans.setFont(new Font("Algerian", Font.BOLD,15));
		SenzuBeans.setBackground(c);
		SenzuBeans.setVisible(true);
		SenzuBeans.setActionCommand("SenzuBeans");
		SenzuBeans.addActionListener(a);
		
		AbilityPoint =new JButton();
		AbilityPoint.setText(d[1].getAbilityPoints()+" Ability Point(s)");
		AbilityPoint.setBounds(100, 250, 250, 80);
		AbilityPoint.setForeground(new Color(220,220,220));
		AbilityPoint.setFont(new Font("Algerian", Font.BOLD,15));
		AbilityPoint.setBackground(c);
		AbilityPoint.setVisible(true);
		AbilityPoint.setActionCommand("AbilityPoint");
		AbilityPoint.addActionListener(a);
		
		SuperAttack =new JButton();
		SuperAttack.setText(d[2].getSuperAttack().getName());
		SuperAttack.setBounds(100, 390, 250, 80);
		SuperAttack.setForeground(new Color(220,220,220));
		SuperAttack.setFont(new Font("Algerian", Font.BOLD,15));
		SuperAttack.setBackground(c);
		SuperAttack.setVisible(true);
		SuperAttack.setActionCommand("SuperAttack");
		SuperAttack.addActionListener(a);
	
		UltimateAttack =new JButton();
		UltimateAttack.setText(d[3].getUltimateAttack().getName());
		UltimateAttack.setBounds(100, 530, 250, 80);
		UltimateAttack.setForeground(new Color(220,220,220));
		UltimateAttack.setFont(new Font("Algerian", Font.BOLD,15));
		UltimateAttack.setBackground(c);
		UltimateAttack.setVisible(true);
		UltimateAttack.setActionCommand("UltimateAttack");
		UltimateAttack.addActionListener(a);
		
		mylabel.add(SuperAttack);
		mylabel.add(UltimateAttack);
		mylabel.add(AbilityPoint);
		mylabel.add(SenzuBeans);
		
		
		mylabel.repaint();
		mylabel.validate();
		repaint(); 
		validate();
		getContentPane().add(mylabel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseButtonListener());
		setVisible(true);
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}
	public JButton getSuperAttack() {
		return SuperAttack;
	}
	public JButton getUltimateAttack() {
		return UltimateAttack;
	}
	public JButton getAbilityPoint() {
		return AbilityPoint;
	}
	public JButton getSenzuBeans() {
		return SenzuBeans;
	}	
	
}
