package listenner;

import main.MainFrame;
import tools.StaticTool;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Listener implements MouseListener {
	int[][] data; //数组，存储周边雷的数量
	boolean[][] flag;  //布尔数组，标记方块是否被打开
	JButton[][] btns;  //Button数组，每一个方块都是一个按钮
	JButton btn;
	MainFrame mainFrame;
	public static int flagCount = 0; //标记方块的个数，若是大于雷的个数，则游戏失败

	public Listener(int[][] data, boolean[][] flag, JButton[][] btns, MainFrame mainFrame){
		this.flag = flag;
		this.btns = btns;
		this.mainFrame = mainFrame;
		this.data = data;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * 鼠标释放时判断游戏进程
	 * @param e
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

		btn = (JButton) e.getSource();
		mainFrame.getTimer().start();
		for (int i = 0; i < StaticTool.allrow; i++) {
			for (int j = 0; j < StaticTool.allcol; j++) {
				if (btn == btns[i][j]){
					//单击鼠标右键时发生的动作
					if (e.getModifiers() == InputEvent.BUTTON3_MASK && flag[i][j]==true){
						btn.setIcon(StaticTool.flagIcon);
						flag[i][j] = false;
						flagCount++;
						mainFrame.getFaceJPanel().setNumber(flagCount);
						if (flagCount>StaticTool.allcount){
							mainFrame.getTimer().stop();
							JOptionPane.showMessageDialog(mainFrame,"标错啦！\n重新开始吧","标错啦",JOptionPane.PLAIN_MESSAGE);
							try {
								Thread.sleep(2000);
							}catch (Exception ex){
								ex.printStackTrace();
							}

							mainFrame.reStartGame();
						}
						isWin();
						return;
					}

					//单击鼠标左键时发生的动作
					if (e.getModifiers() == e.BUTTON1_MASK && flag[i][j]==true){
						if (data[i][j] == -1) {
							lose(i,j);
						}
						else {
							if (data[i][j] == 0) {
								openCell(i,j);
							}
							if (data[i][j] == 1) {
								btn.setIcon(StaticTool.whilt_1_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 2) {
								btn.setIcon(StaticTool.whilt_2_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 3) {
								btn.setIcon(StaticTool.whilt_3_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 4) {
								btn.setIcon(StaticTool.whilt_4_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 5) {
								btn.setIcon(StaticTool.whilt_5_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 6) {
								btn.setIcon(StaticTool.whilt_6_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 7) {
								btn.setIcon(StaticTool.whilt_7_Icon);
								flag[i][j] = false;
							}
							if (data[i][j] == 8) {
								btn.setIcon(StaticTool.whilt_8_Icon);
								flag[i][j] = false;
							}
							isWin();
							return;
						}
					}
					return;
				}
			}
		}
	}

	//当第i行第j列data为0时，递归开启周边方块
	private void openCell(int i,int j){
		btn = btns[i][j];
		if (!flag[i][j]) return;
		btn.setIcon(StaticTool.whiltIcon);
		flag[i][j] = false;

		if ((i-1)>=0 && flag[i-1][j]){
			if (data[i-1][j] == 0) openCell(i-1,j);
			else open(btns[i-1][j],i-1,j);
		}
		if ((j-1)>=0 && flag[i][j-1]){
			if (data[i][j-1] == 0) openCell(i,j-1);
			else open(btns[i][j-1],i,j-1);
		}
		if ((i+1)<StaticTool.allrow && flag[i+1][j]){
			if (data[i+1][j] == 0) openCell(i+1,j);
			else open(btns[i+1][j],i+1,j);
		}
		if ((j+1)<StaticTool.allcol && flag[i][j+1]){
			if (data[i][j+1] == 0) openCell(i,j+1);
			else open(btns[i][j+1],i,j+1);
		}
	}

	private void open(JButton btn,int i, int j){
		if (data[i][j] == 1) {
			btn.setIcon(StaticTool.whilt_1_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 2) {
			btn.setIcon(StaticTool.whilt_2_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 3) {
			btn.setIcon(StaticTool.whilt_3_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 4) {
			btn.setIcon(StaticTool.whilt_4_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 5) {
			btn.setIcon(StaticTool.whilt_5_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 6) {
			btn.setIcon(StaticTool.whilt_6_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 7) {
			btn.setIcon(StaticTool.whilt_7_Icon);
			flag[i][j] = false;
		}
		if (data[i][j] == 8) {
			btn.setIcon(StaticTool.whilt_8_Icon);
			flag[i][j] = false;
		}
		return;
	}

	//游戏失败时发生的动作
	private void lose(int row,int col){
		btns[row][col].setIcon(StaticTool.bloodIcon);
		flag[row][col] = false;
		for (int i = 0; i < StaticTool.allrow; i++) {
			for (int j = 0; j < StaticTool.allcol; j++) {
				btn = btns[i][j];
				if (data[i][j]==-1 && flag[i][j]==true){
					btn.setIcon(StaticTool.boomIcon);
				}
				flag[i][j] = false;
			}
		}
		mainFrame.getTimer().stop();
		JOptionPane.showMessageDialog(mainFrame,"踩雷啦！\n重新开始吧","踩雷啦",JOptionPane.PLAIN_MESSAGE);
		try {
			Thread.sleep(2000);
		}catch (Exception e){
			e.printStackTrace();
		}

		mainFrame.reStartGame();
	}

	//判断游戏是否成功
	private void isWin() {

		int needCount = StaticTool.allrow * StaticTool.allcol - StaticTool.allcount;
		int leiCount = 0;
		int expendCount = 0;
		for (int i = 0; i < StaticTool.allrow; i++) {
			for (int j = 0; j <StaticTool.allcol; j++) {
				if (flag[i][j] == false){
					if (data[i][j] == -1) leiCount++;
					else expendCount++;
				}
			}
		}

		if (leiCount == StaticTool.allcount) {
			mainFrame.getTimer().stop();
			JOptionPane.showMessageDialog(mainFrame,"你赢了","胜利",JOptionPane.INFORMATION_MESSAGE,StaticTool.victoryIcon);
			try {
				Thread.sleep(2000);
			}catch (Exception e){
				e.printStackTrace();
			}
			mainFrame.reStartGame();
		}
	}

}