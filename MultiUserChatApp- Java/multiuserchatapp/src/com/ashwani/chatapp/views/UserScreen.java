package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.brainmentors.chatapp.dao.UserDAO;
import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	
	Logger logger = LogManager.getLogger(UserScreen.class);
	private JTextField useridtxt;
	
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
		logger.debug("Inside the Do Login");
		String userid = useridtxt.getText(); // getting the user textbox value and store in String
		//String password = passwordField.getText();
		char []password = passwordField.getPassword(); // ClassName @HashCode (HexaDecimal)
		
		UserDTO userDTO = new UserDTO(userid , password); // Create object of DTO and Store the values 
		try {
			String message  = "";
			logger.debug("Calling DAO");
			if(userDAO.isLogin(userDTO)) { // Pass the dto object which contains user info to the dao
					message = "Welcome "+userid;
					UserInfo.USER_NAME = userid;
					JOptionPane.showMessageDialog(this, message);
					setVisible(false);
					dispose();
					DashBoard dashBoard = new DashBoard(message);
					dashBoard.setVisible(true);
					logger.debug("In a New Screen .... Login SuccessFull "+userid);
			}
			else {
				message = "Invalid Userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			logger.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void register() {
		String userid = useridtxt.getText();
		//String password = passwordField.getText();
		char []password = passwordField.getPassword(); // ClassName @HashCode (HexaDecimal)
		//UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid , password);
		try {
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register SuccessFully");
			//System.out.println("Record Added....");
		}
		else {
			JOptionPane.showMessageDialog(this, "Register Fail");
		}
		}
		catch(ClassNotFoundException |SQLException ex) {
			System.out.println("DB Issue ....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some Generic exception Raised...");
			ex.printStackTrace(); // Where is the Exception
		}
		System.out.println("userid "+userid+" Password "+password+" "+password.toString()); // ClassName@HashCode(Hexa)
	}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(319, 40, 175, 75);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(409, 134, 301, 50);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		pwdlbl.setBounds(196, 214, 107, 33);
		getContentPane().add(pwdlbl);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		useridlbl.setBounds(196, 151, 107, 33);
		getContentPane().add(useridlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(409, 219, 311, 41);
		getContentPane().add(passwordField);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		registerbt.setBounds(436, 324, 132, 41);
		getContentPane().add(registerbt);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		loginbt.setBounds(278, 324, 132, 41);
		getContentPane().add(loginbt);
		setBackground(Color.WHITE);
		setSize( 778, 428);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
