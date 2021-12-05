package main;

import listenner.Listener;
import panel.BombJMenuBar;
import panel.BombJPanel;
import panel.FaceJPanel;
import timer.TimerListener;
import tools.StaticTool;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	/**
	 * main方法
	 */
	private static final long serialVersionUID = 1L;

	//菜单栏
	private BombJMenuBar menuBar = new BombJMenuBar(this);

	//显示时间、雷的数量
	private FaceJPanel faceJPanel = new FaceJPanel(this);

	//雷区
	private BombJPanel bombJPanel = new BombJPanel(this);

	//计时器
	private TimerListener timerListener = new TimerListener(this);

	private Timer timer = new Timer(1000, timerListener);

	public FaceJPanel getFaceJPanel() {
		return faceJPanel;
	}

	public BombJPanel getBombJPanel() {
		return bombJPanel;
	}

	public Timer getTimer() {
		return timer;
	}

	//构造方法
	public MainFrame() {

		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("扫雷");
		this.setSize(new Dimension(400, 500));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		init();

		this.setResizable(false);
		/*this.setLocationRelativeTo(null);
		this.pack();*/
		this.setVisible(true);

	}

	//运行main方法时进行初始化
	private void init() {
		this.setJMenuBar(menuBar);
		this.add(faceJPanel, BorderLayout.NORTH);
		this.add(bombJPanel,BorderLayout.CENTER);
	}

	//重启游戏
	public void reStartGame() {

		this.remove(faceJPanel);
		this.remove(bombJPanel);

		StaticTool.bombCount = StaticTool.allcount;
		StaticTool.timecount = 0;
		//StaticTool.isStart = false;
		Listener.flagCount = 0;
		faceJPanel = new FaceJPanel(this);
		bombJPanel = new BombJPanel(this);
		this.add(faceJPanel, BorderLayout.NORTH);
		this.add(bombJPanel,BorderLayout.CENTER);
		//this.pack();
		this.validate();

		getTimer().stop();
	}


	//主程序
	public static void main(String[] args) {

		new MainFrame();

	}

}