package di.evaluacioninic;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JCheckBox;

public class LoginApp {

	private JFrame frame;
	private JTextField txtPswd;
	private JTextField txtUsername;
	private JButton btnLogin;
	private JLabel lblMsg;
	
	Map<String, String> users = new HashMap<>();
	private JCheckBox chckBoxRemember;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginApp window = new LoginApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public LoginApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(128, 255, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Username: ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		
		txtPswd = new JTextField();
		txtPswd.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(192, 192, 192));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 11));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login();
				
			}

		});
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(new Color(192, 192, 192));
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				register();
				
			}


		});
		
		
		lblMsg = new JLabel("     ");
		
		chckBoxRemember = new JCheckBox("Remember me");
		chckBoxRemember.setBackground(new Color(128, 255, 255));
		chckBoxRemember.setFont(new Font("Arial", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMsg, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(232, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblPassword, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
									.addComponent(btnRegister)))
							.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtPswd)
								.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
								.addComponent(chckBoxRemember))
							.addGap(20))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtPswd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(chckBoxRemember)
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogin)
						.addComponent(btnRegister))
					.addGap(59)
					.addComponent(lblMsg)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	/**
	 * Logs in the user
	 */
	private void login() {
		
		String username = txtUsername.getText();
		String password = txtPswd.getText();
		
		if (users.containsKey(username)) {
			lblMsg.setText("User logged in succesfull");
		} else {
			users.put(username, password);
			lblMsg.setText("User does not exist");
		}
		
//		
//		String msg = UserManagement.loginUser(username, password);
//		
//		lblMsg.setText(msg);
		
	}
	
	/**
	 * Registers user in database
	 */
	private void register() {
		
		String username = txtUsername.getText();
		String password = txtPswd.getText();
		
		if (users.containsKey(username)) {
			lblMsg.setText("User already registered");
		} else {
			users.put(username, password);
			lblMsg.setText("User logged in succesfully");
		}
		

//		
//		String msg = UserManagement.registerUser(username, password);
//		
//		lblMsg.setText(msg);
		
	}
}
