import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class PlayFrame extends JFrame{
	JButton bXO[]=new JButton[9];
	String xo="X";
	String Winner="";
	String checkWin[][]=new String[3][3];
	int iDenger=-1;
	JPopupMenu popup=new JPopupMenu();
	
	public PlayFrame() {
		setSize(800,400);
		setLayout(new GridLayout(3,3));
		createButton();
		bXO[4].setText("O");
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
							JOptionPane.showMessageDialog(PlayFrame.this, xo+" is the Winner");
							reset();
						}
						else if (isFull()) {
							JOptionPane.showMessageDialog(PlayFrame.this, "Draw");
							reset();
						}
						else {
							botPlay();
						}
					}
				}
			}
		});
	}
	void reset() {
		for (int j = 0; j < bXO.length; j++) {
			bXO[j].setText("");
		}
		bXO[4].setText("O");
	}
	void createButton() {
		for (int j = 0; j < bXO.length; j++) {
			bXO[j]=new JButton();
			bXO[j].setFont(new Font("Tahoma", Font.BOLD, 100));
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
	
	void botPlay() {
		int rand=(int)(Math.random()*9);
		if (isRepeat(rand)) {
			botPlay();
		}
		else {
			if (isCloseWinRow()||isCloseWinColum()||isCloseWinDiagonals()) {
				rand=iDenger;
			}
			bXO[rand].setText("O");
			checkWinner();
			if (checkForWin()) {
				JOptionPane.showMessageDialog(PlayFrame.this, "O is The Winner");
				reset();
			}
			else if (isFull()) {
				JOptionPane.showMessageDialog(PlayFrame.this, "Draw");
				reset();
			}
		}
	}
	
	boolean isCloseWinRow() {
		for (int i = 0; i < 3; i++) {
			if ((check(checkWin[i][0],checkWin[i][1],"X") == true || check(checkWin[i][0],checkWin[i][1],"O") == true ) && checkWin[i][2].equals("")) {
				if (i==0) {
					iDenger=2;
				}
				else if (i==1) {
					iDenger=5;
				}
				else {
					iDenger=8;
				}
				return true;
			}
			if ((check(checkWin[i][0],checkWin[i][2],"X") == true || check(checkWin[i][0],checkWin[i][2],"O") == true )&& checkWin[i][1].equals("")) {
				if (i==0) {
					iDenger=1;
				}
				else if (i==1) {
					iDenger=4;
				}
				else {
					iDenger=7;
				}
				return true;
			}
			if ((check(checkWin[i][2],checkWin[i][1],"X") == true || check(checkWin[i][1],checkWin[i][2],"O") == true )&& checkWin[i][0].equals("")) {
				if (i==0) {
					iDenger=0;
				}
				else if (i==1) {
					iDenger=3;
				}
				else {
					iDenger=6;
				}
				return true;
			}
		}
		return false;
	}
	boolean isCloseWinColum() {
		for (int i = 0; i < 3; i++) {
			if ((check(checkWin[0][i],checkWin[1][i],"X") == true||check(checkWin[0][i],checkWin[1][i],"O") == true) && checkWin[2][i].equals("")) {
				if (i==0) {
					iDenger=6;
				}
				else if (i==1) {
					iDenger=7;
				}
				else {
					iDenger=8;
				}
				return true;
			}
			if ((check(checkWin[0][i],checkWin[2][i],"X") == true||check(checkWin[0][i],checkWin[2][i],"O") == true)  && checkWin[1][i].equals("")) {
				if (i==0) {
					iDenger=3;
				}
				else if (i==1) {
					iDenger=4;
				}
				else {
					iDenger=5;
				}
				return true;
			}
			if ((check(checkWin[2][i],checkWin[1][i],"X") == true||check(checkWin[2][i],checkWin[1][i],"O") == true)  && checkWin[0][i].equals("")) {
				if (i==0) {
					iDenger=0;
				}
				else if (i==1) {
					iDenger=1;
				}
				else {
					iDenger=2;
				}
				return true;
			}
		}
		return false;
	}
	boolean isCloseWinDiagonals() {
		if ((check(checkWin[0][0],checkWin[1][1],"X") == true || check(checkWin[0][0],checkWin[1][1],"O") == true ) && checkWin[2][2].equals("")) {
			iDenger=8;
			return true;
		}
		if ((check(checkWin[0][0],checkWin[2][2],"X") == true || check(checkWin[0][0],checkWin[2][2],"O") == true ) && checkWin[1][1].equals("")) {
			iDenger=4;
			return true;
		}
		if ((check(checkWin[2][2],checkWin[1][1],"X") == true || check(checkWin[2][2],checkWin[1][1],"O") == true ) && checkWin[0][0].equals("")) {
			iDenger=0;
			return true;
		}
	
		return false;
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
