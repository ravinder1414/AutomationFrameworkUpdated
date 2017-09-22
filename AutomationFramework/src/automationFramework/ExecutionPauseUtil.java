package automationFramework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.apache.commons.lang3.Validate;
import org.openqa.selenium.remote.html5.AddApplicationCache;

import utility.Constant;

public class ExecutionPauseUtil {

	private static boolean isPaused = false;
	
	private static boolean isStopped=false;
	
	private static JFrame frame = null;

	public static final void closeExecutionFrame() {
		if (frame != null) {
			frame.dispose();
		}
	}

	public static boolean isExecutionStopped()
	{
		return isStopped;
	}

	public static boolean isExecutionPaused() {
		return isPaused;
	}

	public static void createExecutionPauseGUI() {
		 frame = new JFrame("Execution Controller");

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JButton play = new JButton();
		try {
		    play.setIcon(new ImageIcon(new File(Constant.play).getAbsolutePath()));
		    play.setPreferredSize(new Dimension(40, 40));
		    play.setEnabled(false);
		    play.setBackground(Color.BLACK);
			play.setOpaque(true);
			play.setToolTipText("Play");
		    panel.add(play);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }

		JButton pause = new JButton();
		try {
		    pause.setIcon(new ImageIcon(new File(Constant.pause).getAbsolutePath()));
		    pause.setPreferredSize(new Dimension(40, 40));
		    pause.setEnabled(true);
		    pause.setBackground(Color.BLACK);
			pause.setOpaque(true);
			pause.setToolTipText("Pause");
		    panel.add(pause);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		JButton stop = new JButton();
		try {
		    stop.setIcon(new ImageIcon(new File(Constant.stop).getAbsolutePath()));
		    stop.setPreferredSize(new Dimension(40, 40));
		    stop.setEnabled(true);
		    stop.setBackground(Color.BLACK);
			stop.setOpaque(true);
			stop.setToolTipText("Stop");
		    panel.add(stop);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }

		play.addActionListener((ActionEvent e) -> {
			System.out.println("Play Has been clicked!");
			final Object obj = LockableObject.getLockableObject();
			synchronized (obj) {
				isPaused = false;
				obj.notifyAll();
			}
			play.setEnabled(false);
			play.setBackground(Color.BLACK);
			play.setOpaque(true);
			pause.setEnabled(true);
			stop.setEnabled(true);
			pause.setBackground(Color.BLACK);
			stop.setBackground(Color.BLACK);
		});

		pause.addActionListener((ActionEvent e) -> {
			System.out.println("pause Has been clicked!");
			isPaused = true;
			play.setEnabled(true);
			pause.setEnabled(false);
			stop.setEnabled(false);
			pause.setBackground(Color.BLACK);
			pause.setOpaque(true);
			stop.setBackground(Color.BLACK);
			stop.setOpaque(true);
			play.setBackground(Color.BLACK);
		});

		stop.addActionListener((ActionEvent e) -> {
			System.out.println("stop Has been clicked!");
			isStopped=true;
			play.setEnabled(false);
			pause.setEnabled(false);
			stop.setEnabled(false);
			frame.dispose();
		});

		
		panel.add(pause);
		panel.add(stop);

		panel.setBackground(Color.BLACK);
		panel.setOpaque(true);
		frame.add(panel);
		frame.getContentPane().setBackground( Color.BLACK );
		frame.setMinimumSize(new Dimension(220,80));
		frame.setMaximumSize(new Dimension(220,80));
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        frame.setLocation((int) (dimension.getWidth() - 400),
                (int) (dimension.getHeight() - 200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
