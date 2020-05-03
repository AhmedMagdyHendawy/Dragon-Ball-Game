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
import javax.swing.JTextField;


public class PlayerCreationView extends JFrame {
	private ActionListener actionListener;
	private JTextField playerName;
	private JTextField fighterName;

	public PlayerCreationView(ActionListener a) {
		setTitle("Dragon Ball_AA");
		setLayout(null);
		JLabel mylabel= new JLabel(new ImageIcon("Dragon-Ball-Z-Wallpaper-HD-download-free.jpg"));
		mylabel.setLayout(null);
		setMinimumSize(new Dimension(1366,768));
		setResizable(false);
		ImageIcon i = new ImageIcon("dragon ball mini.png");
		setIconImage(i.getImage());
		mylabel.setBounds(0, 0, 1366, 768);
		mylabel.setVisible(true);
		
		//////////////////////////////////////////////////
		JPanel mypanel= new JPanel();
		mypanel.setLayout(null);
		mypanel.setBounds(0, 0, 600	, 768);
		mypanel.setOpaque(false);
		mypanel.setVisible(true);
		////////////////////////////////////////////////// Panel Components
		JLabel namelabel= new JLabel("Enter Your Name :");
		namelabel.setFont(new Font ("Algerian",Font.CENTER_BASELINE,15));
		namelabel.setBounds(100, 160, 200, 30);
		mypanel.add(namelabel);
		
		playerName = new JTextField();
		playerName.setFont(new Font("Times new roman", Font.BOLD,20));
		mypanel.add(playerName);
		playerName.setBounds(100, 190, 200, 30);


		JLabel fighterlabel= new JLabel("Enter Fighter's Name :");
		fighterlabel.setFont(new Font ("Algerian",Font.CENTER_BASELINE,15));
		fighterlabel.setBounds(100, 370, 200, 30);
		mypanel.add(fighterlabel);
		
		fighterName= new JTextField();
		fighterName.setFont(new Font("Times new roman", Font.BOLD,20));
		mypanel.add(fighterName);
		fighterName.setBounds(100, 400, 200, 30);
		/////////////////////////////////////////////////
		JButton chooseFighter =new JButton("Choose Your Fighter");
		chooseFighter.setBounds(100, 500, 250, 50);
		chooseFighter.setForeground(new Color(220,220,220));
		Color c1= new Color(47,79,79);
		chooseFighter.setFont(new Font("Algerian", Font.BOLD,15));
		chooseFighter.setBackground(c1);
		chooseFighter.setFocusable(false);
		chooseFighter.setVisible(true);
		chooseFighter.setActionCommand("Fighter Chosen");
		chooseFighter.addActionListener(a);
		mypanel.add(chooseFighter);

		
		
		mylabel.add(mypanel);
		add(mylabel);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new CloseButtonListener());
		setVisible(true);
		 
	}
	
	public ActionListener getActionListener() {
		return actionListener;
	}
	public JTextField getPlayerName() {
		return playerName;
	}

	public JTextField getFighterName() {
		return fighterName;
	}

	public void setActionListener(ActionListener aL) {
		actionListener = aL;
	}
}
