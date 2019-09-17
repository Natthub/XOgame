import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class PlayFrame2 extends JFrame{
	JButton bXO[]=new JButton[9];
	String xo="X";
	String Winner="";
	String checkWin[][]=new String[3][3];
	JPopupMenu popup=new JPopupMenu();
	
	public PlayFrame2() {
		setSize(800,400);
		setLayout(new GridLayout(3,3));
		createButton();
	}
	
	void addMouseClicked(int index) {
		bXO[index].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==bXO[index]) {
					if (isRepeat(index)) {
						
					}
					else {
						bXO[index].setText(xo);
						checkWinner();
						if (checkForWin()) {
							JOptionPane.showMessageDialog(PlayFrame2.this, xo);
							reset();
						}
						else if (isFull()) {
							JOptionPane.showMessageDialog(PlayFrame2.this, "Draw");
						}
						changeTurn();
					}
				}
			}
		});
	}
	void reset() {
		for (int j = 0; j < bXO.length; j++) {
			bXO[j].setText("");
		}
	}
	void createButton() {
		for (int j = 0; j < bXO.length; j++) {
			bXO[j]=new JButton();
			addMouseClicked(j);
			add(bXO[j]);
		}
	}
	boolean isFull() {
		for (int i = 0; i < checkWin.length; i++) {
			for (int j = 0; j < checkWin.length; j++) {
				if (checkWin[i][j]=="") {
					return false;
				}
			}
		}
		return true;
	}
	boolean isRepeat(int i) {
		String txt=bXO[i].getText();
		if (txt.equals("X")||txt.equals("O")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	void changeTurn() {
		if (xo.equals("X")) {
			xo="O";
		}
		else {
			xo="X";
		}
	}
	
	void checkWinner() {
		
		int ib=0;
		for (int i = 0; i < checkWin.length; i++) {
			for (int j = 0; j < checkWin.length; j++) {
				checkWin[i][j]=bXO[ib].getText();
				ib++;
			}
		}
	}
	
	boolean checkForWin() {
		return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}
	
	boolean checkRowsForWin() {
		for (int i = 0; i < 3; i++) {
			if (check(checkWin[i][0],checkWin[i][1],checkWin[i][2]) == true) {
				return true;
			}
		}
		return false;
	}
	boolean checkColumnsForWin() {
		for (int i = 0; i < 3; i++) {
			if (check(checkWin[0][i],checkWin[1][i],checkWin[2][i]) == true) {
				return true;
			}
		}
		return false;
	}
	boolean checkDiagonalsForWin() {
		if (check(checkWin[0][0],checkWin[1][1],checkWin[2][2]) == true||check(checkWin[0][2],checkWin[1][1],checkWin[2][0]) == true) {
			return true;
		}
		return false;
	}
	
	boolean check(String str1,String str2,String str3) {
		return (!str1.equals("")&&str1.equals(str2)&&str2.equals(str3));
	}

}
