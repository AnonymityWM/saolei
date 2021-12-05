package panel;

import main.MainFrame;
import tools.StaticTool;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FaceJPanel extends JPanel {

	/**
	 * 任务栏
	 */
	private static final long serialVersionUID = 1L;

	JLabel label1 = new JLabel("待开:"+StaticTool.bombCount);
	JLabel label2 = new JLabel("已开:"+(StaticTool.allcount-StaticTool.bombCount));
	JLabel label3 = new JLabel("用时:"+StaticTool.timecount+"s");

	MainFrame mainFrame;

	public FaceJPanel(MainFrame frame) {
		this.mainFrame = frame;
		this.setLayout(new BorderLayout());
		init();
	}

	private void init() {
		// 创建一块画布
		JPanel panel = new JPanel(new GridBagLayout());

		FaceLableListener faceLableListener= new FaceLableListener();
		panel.addMouseListener(faceLableListener);

		label1.setOpaque(true);
		label1.setBackground(Color.white);
		label1.setFont(new Font(null,Font.BOLD,12));
		label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label2.setOpaque(true);
		label2.setBackground(Color.white);
		label2.setFont(new Font(null,Font.BOLD,12));
		label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		label3.setOpaque(true);
		label3.setBackground(Color.white);
		label3.setFont(new Font(null,Font.BOLD,12));
		label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		GridBagConstraints c2 = new GridBagConstraints(0,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
		panel.add(label1,c2);
		GridBagConstraints c3 = new GridBagConstraints(1,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
		panel.add(label2,c3);
		GridBagConstraints c4 = new GridBagConstraints(2,1,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(0,0,0,0),0,0);
		panel.add(label3,c4);

		this.add(panel);
	}

	//设置时间
	public void setTime(int count) {
		label3.setText("用时："+count+"s");
	}

	//设置雷打开的数量
	public void setNumber(int count) {
		label2.setText("已开:"+count);
		label1.setText("待开:"+(StaticTool.allcount-count));
	}

	public class FaceLableListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainFrame.getTimer().stop();
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainFrame.reStartGame();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainFrame.getTimer().start();
			}
		}

	}

}
