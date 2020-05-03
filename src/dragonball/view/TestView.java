package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import org.omg.PortableServer.ServantActivatorOperations;

public class TestView extends JFrame {
	
	private JButton b1;
	private JButton b2;
	ActionListener a;
	
	public TestView(ActionListener a) {
		setLayout(null);
		setMinimumSize(new Dimension(600, 460));
		b1 = new JButton("bpppp");
		b2 = new JButton("22777");
		b1.setForeground(Color.MAGENTA);
		b1.setActionCommand("hehhe");
		b1.setBounds(20, 20, 200, 100);
		b1.addActionListener(a);
	
		add(b1);
		this.a = a;
		setVisible(true);

	}
	
	
	public static void main(String[] args) {
		new TestView(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch(e.getActionCommand()) {
				case "hehhe":  System.out.println("heheheh");
				System.exit(0);  break;
				
				}
				
			}
		});
	}

}
