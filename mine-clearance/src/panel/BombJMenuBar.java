package panel;

import main.MainFrame;
import tools.StaticTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BombJMenuBar extends JMenuBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JMenu menuGame = new JMenu("游戏");
	JMenuItem menuItemStart = new JMenuItem("开局");
	JMenuItem menuItemC = new JMenuItem("初级");
	JMenuItem menuItemZ = new JMenuItem("中级");
	JMenuItem menuItemG = new JMenuItem("高级");
	JMenuItem menuItemExit = new JMenuItem("退出");


	MainFrame mainFrame;

	public BombJMenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {

		menuGame.add(menuItemC); //初级加入游戏菜单
		menuItemC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 9;
				StaticTool.allcol = 9;
				StaticTool.allcount = 10;
				mainFrame.setSize(400, 500);
				mainFrame.reStartGame();
			}
		});

		menuGame.add(menuItemZ);
		menuItemZ.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 16;
				StaticTool.allcol = 16;
				StaticTool.allcount = 40;
				mainFrame.setSize(700,650);
				mainFrame.reStartGame();
			}
		});

		menuGame.add(menuItemG);
		menuItemG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.allrow = 16;
				StaticTool.allcol = 30;
				StaticTool.allcount = 99;
				mainFrame.setSize(1050,650);
				mainFrame.reStartGame();
			}
		});

		menuGame.addSeparator();// 菜单加入分割线
		menuGame.add(menuItemExit);
		menuItemExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);

			}
		});

		// 菜单加入到菜单条
		this.add(menuGame);
	}

}