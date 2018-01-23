package javacore;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Loading extends Thread {
	private String message;
	private boolean isRunning;
	private int numOfDot;
	
	JFrame frame;
	JLabel lbMsg;
	JPanel panel;
	
	int frame_width, frame_height;
	
	public Loading(String message) {
		this.message = message;
		isRunning = true;
		numOfDot = 1;
	}

	public Loading(String message, int frame_width, int frame_height) {
		this.message = message;
		isRunning = true;
		numOfDot = 1;
		this.frame_width = frame_width;
		this.frame_height = frame_height;
	}

	@Override
	public void run() {
		while(isRunning) {
			showMessage2();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

//	private void showMessage() {
//		System.out.print(this.message);
//		for(int i = 1; i <= numOfDot; i++) System.out.print(".");
//		System.out.println();
//		numOfDot++;
//		if(numOfDot == 4) numOfDot = 1;
//	}
	
	private void showMessage2() {
		if(frame == null) {
			frame = new JFrame();
			panel = new JPanel(new BorderLayout());
			JPanel p1 = new JPanel();
			p1.setPreferredSize(new Dimension(60, 20));
			//p1.setBorder(new LineBorder(Color.blue));
			panel.add(p1, BorderLayout.WEST);
			
			lbMsg = new JLabel(this.message);
			panel.add(lbMsg, BorderLayout.CENTER);
			frame.add(panel);
			
			frame.setSize(frame_width, frame_height);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		String str = this.message;
		for(int i = 1; i <= numOfDot; i++) str += ".";
		numOfDot++;
		if(numOfDot == 4) numOfDot = 0;
		lbMsg.setText(str);
	}

	public void stopLoading() {
		isRunning = false;
		frame.setVisible(false);
		frame = null;
	}
	
	public static void main(String[] args) {
		Loading ld = new Loading("Loading, please wait", 250, 70);
		ld.start();
	}
}
