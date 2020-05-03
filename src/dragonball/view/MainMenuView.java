package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainMenuView extends JFrame {
	private ActionListener actionListener;
	public MainMenuView(ActionListener a) {
		setTitle("Dragon Ball_AA");
		actionListener=a;
		setMinimumSize(new Dimension(1366,768));
		setResizable(false);
		ImageIcon i = new ImageIcon("dragon ball mini.png");
		setIconImage(i.getImage());
		
		
		JLabel mylabel= new JLabel();
		mylabel.setLayout(null);
		mylabel.setIcon(new ImageIcon("Dragon-Ball-Z-Wallpaper-HD-download-free.jpg"));
		
		///////////////////////////////////////////////// Buttons
		JButton startButton =new JButton("New Game");	
		startButton.setBounds(100, 190, 350, 100);
		startButton.setForeground(new Color(220,220,220));
		Color c= new Color(47,79,79);
		startButton.setFont(new Font("Algerian", Font.BOLD,50));
		startButton.setBackground(c);
		startButton.setVisible(true);
		startButton.setActionCommand("New Game");
	
		JButton loadButton =new JButton("Load Game");
		loadButton.setBounds(100, 335, 350, 100);
		loadButton.setForeground(new Color(220,220,220));
		loadButton.setFont(new Font("Algerian", Font.BOLD,50));
		loadButton.setBackground(c);
		loadButton.setVisible(true);
		
		JButton back =new JButton("Back");
		back.setBounds(100, 480, 350, 100);
		back.setForeground(new Color(220,220,220));
		back.setFont(new Font("Algerian", Font.BOLD,50));
		back.setBackground(c);
		back.setVisible(true);
		////////////////////////////////////////////////
		
		startButton.addActionListener(a);
		loadButton.addActionListener(a);
		back.addActionListener(a);
		mylabel.add(startButton);
		mylabel.add(loadButton);
		mylabel.add(back);
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
	public MainMenuView() {
		// TODO Auto-generated constructor stub
		new MainMenuView();
	}

}
