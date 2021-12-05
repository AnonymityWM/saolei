package panel;

import listenner.Listener;
import main.MainFrame;
import tools.StaticTool;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Random;

public class BombJPanel extends Container {
	/**
	 * 雷区
	 */
	private static final long serialVersionUID = 1L;
	int[][] data = new int[StaticTool.allrow][StaticTool.allcol];
	boolean[][] flag = new boolean[StaticTool.allrow][StaticTool.allcol];
	JButton[][] btns = new JButton[StaticTool.allrow][StaticTool.allcol];
	private Listener listener;
	private MainFrame mainFrame;

	public BombJPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new GridLayout(StaticTool.allrow, StaticTool.allcol));
		addBomb();
		init();
	}

	private void init() {

		for (int i = 0; i < StaticTool.allrow; i++) {
			for (int j = 0; j < StaticTool.allcol; j++) {
				JButton btn = new JButton(StaticTool.iconBlank);
				//data[i][j] = 0;
				flag[i][j] = true;
				btn.setMargin(new Insets(0,0,0,0));
				btn.addMouseListener(new Listener(data,flag,btns,mainFrame));
				this.add(btn);
				btns[i][j] = btn;
			}
		}
	}

	public void addBomb(){
		int count = 0;
		Random random = new Random();
		while (count < StaticTool.allcount) {
			int x = random.nextInt(StaticTool.allrow);
			int y = random.nextInt(StaticTool.allcol);
			if (data[x][y] != -1) {
				data[x][y] = -1;
				count++;
			}
		}
		//给data赋值，计算周边雷的数量
		computeBomb(data);
	}

	public static void computeBomb(int data[][]) {
		// 计算周边雷的数量
		for (int i = 0; i < StaticTool.allrow; i++) {
			for (int j = 0; j < StaticTool.allcol; j++) {
				if (data[i][j] != -1) {
					data[i][j] = 0;
					if ((i-1)>=0 && (j-1)>=0){
						if (data[i-1][j-1] == -1) data[i][j]++;
					}
					if ((i-1)>=0){
						if (data[i-1][j] == -1) data[i][j]++;
					}
					if ((j-1)>=0){
						if (data[i][j-1] == -1) data[i][j]++;
					}
					if ((i+1)<StaticTool.allrow && (j-1)>=0){
						if (data[i+1][j-1] == -1) data[i][j]++;
					}
					if ((i+1)<StaticTool.allrow){
						if (data[i+1][j] == -1) data[i][j]++;
					}
					if ((j+1)<StaticTool.allcol){
						if (data[i][j+1] == -1) data[i][j]++;
					}
					if ((i-1)>=0 && (j+1)<StaticTool.allcol){
						if (data[i-1][j+1] == -1) data[i][j]++;
					}
					if ((i+1)<StaticTool.allrow && (j+1)<StaticTool.allcol){
						if (data[i+1][j+1] == -1) data[i][j]++;
					}
				}
				//System.out.println("data"+i+"行"+j+"列："+data[i][j]+"\n");
			}
		}
	}

}
