package dragonball.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javafx.scene.paint.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class FighterSelectionView  extends JFrame {
	private ActionListener actionListener;
	
public FighterSelectionView(ActionListener a) {
	setTitle("Dragon Ball_AA");
		actionListener=a;
		setResizable(false);
		setMinimumSize(new Dimension(1366,768));
		setIconImage(new ImageIcon("dragon ball mini.png").getImage());
		setLayout(new GridLayout(1,5));

		JButton Earthling= new JButton();
		Earthling.setFocusable(true);
		Earthling.setVisible(true);
		Earthling.setSize(getWidth()/5,getHeight());
		Earthling.setIcon(new ImageIcon("EarthlingS.png"));
		Earthling.setActionCommand("Earthling");
		
		
		
		
		
		JButton Frieza= new JButton();
		Frieza.setFocusable(true);
		Frieza.setSize(getWidth()/5,getHeight());
		Frieza.setIcon(new ImageIcon("FriezaS.png"));
		Frieza.setVisible(true);
		Frieza.setActionCommand("Frieza");
		
		
		JButton Saiyan = new JButton();
		Saiyan.setFocusable(true);
		Saiyan.setIcon(new ImageIcon("SaiyanS.png"));
		Saiyan.setVisible(true);
		Saiyan.setSize(getWidth()/5,getHeight());
		Saiyan.setActionCommand("Saiyan");
		
		JButton Namekian= new JButton();
		Namekian.setIcon(new ImageIcon("NamekianS.png"));
		Namekian.setFocusable(true);
		Namekian.setVisible(true);
		Namekian.setSize(getWidth()/5,getHeight());
		Namekian.setActionCommand("Namekian");
		
		JButton Majin= new JButton();
		Majin.setIcon(new ImageIcon("MajinS.png"));
		Majin.setVisible(true);
		Majin.setSize(getWidth()-getWidth()*4/5,getHeight());
		Majin.setActionCommand("Majin");

		Earthling.addActionListener(a);
		Majin.addActionListener(a);
		Frieza.addActionListener(a);
		Namekian.addActionListener(a);
		Saiyan.addActionListener(a);

		add(Saiyan);
		add(Earthling);
		add(Namekian);
		add(Majin);
		add(Frieza);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseButtonListener());
		setVisible(true);
		
	}

public ActionListener getActionListener() {
	return actionListener;
}

}
