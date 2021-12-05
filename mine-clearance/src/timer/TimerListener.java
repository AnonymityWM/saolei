package timer;

import main.MainFrame;
import tools.StaticTool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
	MainFrame mainFrame;

	//计时器构造方法
	public TimerListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StaticTool.timecount++;
		mainFrame.getFaceJPanel().setTime(StaticTool.timecount);
	}

}