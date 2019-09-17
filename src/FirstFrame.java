import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstFrame extends JFrame{
	public FirstFrame() {
		setSize(400, 200);
		setLayout(new GridLayout(0, 2));
		JButton b1=new JButton("1 Player");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				play(1);
				
			}
		});
		add(b1);
		JButton b2=new JButton("2 Player");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				play(2);
			}
		});
		add(b2);
	}
	
	void play(int player) {
		if (player==1) {
			PlayFrame pFrame=new PlayFrame();
			pFrame.setVisible(true);
		}
		else if (player==2) {
			PlayFrame2 pFrame2=new PlayFrame2();
			pFrame2.setFont(new Font("Tahoma", Font.BOLD, 100));
			pFrame2.setVisible(true);
		}
		
	}
}
