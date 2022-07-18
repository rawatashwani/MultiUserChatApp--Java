package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JWindow {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
					frame.runProgressBar();
				
	}
	private Timer timer ; // Instance Variable (Initialize with null value)
	private void runProgressBar() {
		timer = new Timer(10, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				progressBar.setValue(count);
				count++;
				if(count>100) {
					if(timer!=null) {
					timer.stop();
					SplashScreen.this.setVisible(false);
					SplashScreen.this.dispose();
					UserScreen userScreen = new UserScreen();
					userScreen.setVisible(true);
					}
				}
				
			}
		});
		timer.start();
	}
	private int count = 0;
	JProgressBar progressBar ;
	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        UIManager.put("ProgressBar.background", Color.BLACK); //colour of the background    

UIManager.put("ProgressBar.foreground", Color.RED);  //colour of progress bar
 UIManager.put("ProgressBar.selectionBackground",Color.GREEN);  //colour of percentage counter on black background    
UIManager.put("ProgressBar.selectionForeground",Color.RED); 
        progressBar = new JProgressBar();
        //progressBar.setBackground(Color.RED);
		progressBar.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		progressBar.setStringPainted(true);
		progressBar.setBounds(49, 382, 822, 38);
		contentPane.add(progressBar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(SplashScreen.class.getResource("/images/chat-benefits.png")));
		lblNewLabel.setBounds(6, 0, 939, 515);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}
}
