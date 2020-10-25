import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StatementImpl;

import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class Register implements  ActionListener{
	
	 JFrame frame = new JFrame();
	JRadioButton rbMale = new JRadioButton("Male");
	JLabel lblUsername = new JLabel("UserName");
	JLabel lbPassword = new JLabel("Password");
	JLabel label = new JLabel("Register");
	JPasswordField pfConfirmPassword = new JPasswordField();
	JLabel lbConfirmPassword = new JLabel("Confirm Password");
	JLabel lbPhoneNumber = new JLabel("Phone Number");
	JLabel lbGender = new JLabel("Gender");
	JPanel pnlGender = new JPanel();
	JTextField tfUsername = new JTextField();
	JButton btnSubmit = new JButton("Register");
	JButton btnCancel = new JButton("Cancel");
	JRadioButton rbFemale = new JRadioButton("Female");
	JCheckBox chckbxAgree = new JCheckBox("I Agree With Terms");
	
	JPanel pnlButton = new JPanel();
	JPanel panelMid = new JPanel();
	JPanel panelBot = new JPanel();
	
	int txtID = 0;
	
	Connect con = new Connect();
	
	String Rnama = tfUsername.getText();

	private JPasswordField pfPassword;
	private JTextField tfPhone;
	
	//Create Application
	public Register() {
		createView();
		createButtonListener();
	}

	// Inisialisasi 
	private void createView() {
		
		frame = new JFrame();
		frame.setLocation(326,357);
		frame.setSize(326,347);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelTop = new JPanel();
		frame.getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		panelTop.add(label);
		
		frame.getContentPane().add(panelMid, BorderLayout.CENTER);
		panelMid.setLayout(new GridLayout(5, 2));
	//	panelMid.add(group);
		
		lblUsername.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(lblUsername);
		
	
		tfUsername.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		tfUsername.setColumns(10);
		panelMid.add(tfUsername);
		
		lbPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(lbPassword);
		
		pfPassword = new JPasswordField();
		panelMid.add(pfPassword);
		
		lbConfirmPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(lbConfirmPassword);
		
		pfConfirmPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(pfConfirmPassword);
		
		lbPhoneNumber.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(lbPhoneNumber);
		
		tfPhone = new JTextField();
		tfPhone.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		tfPhone.setColumns(10);
		panelMid.add(tfPhone);
		
		lbGender.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelMid.add(lbGender);
		
		panelMid.add(pnlGender);
		pnlGender.add(rbMale);
		rbMale.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		
		rbFemale.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		pnlGender.add(rbFemale);

		frame.getContentPane().add(panelBot, BorderLayout.SOUTH);
		panelBot.setLayout(new GridLayout(0, 1));

		chckbxAgree.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelBot.add(chckbxAgree);

		panelBot.add(pnlButton);
		
		pnlButton.add(btnSubmit);
		btnSubmit.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		
		pnlButton.add(btnCancel);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		
	}
	
	private void createButtonListener() {
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == btnSubmit) {
			
			String[] data = validateData();
			
			System.out.println(data);

			if( data != null) {

				
					String password = new String(pfPassword.getPassword());
					
					String insertQuery = " INSERT INTO users VALUES (' " + txtID +" ' ,'" + tfUsername.getText()+ " ', ' " + password + "', '" + tfPhone.getText() + "', '" + 
															(rbMale.isSelected() ? "Male" : "Female")+ "' , ' Role ')";
					
					con.executeUpdate(insertQuery);
					
					JOptionPane.showMessageDialog(frame, "Register Success.");
					
					frame.dispose();

					MainForm mf = new MainForm();
					mf.frame.setVisible(true);
				
					//	generatedID();
					}
				}else if(event.getSource() == btnCancel ) {
					
					frame.dispose();
					
					Login log = new Login();
					log.dialog.setVisible(true);
					
				}//close else if 
	}//close action
	
	private String[]  validateData() {
		
	if(tfUsername.getText() == null || "".equalsIgnoreCase(tfUsername.getText())) {
			
			JOptionPane.showMessageDialog(frame, "Name must be filled.");
			return null;
		
		}else if(tfUsername.getText().length() < 5 ||  tfUsername.getText().length() > 20) {
	
			JOptionPane.showMessageDialog(frame, "Username  must be between 5 and 20 characters");
			return null;
			
		}else if(new String(pfPassword.getPassword()) == null || "".equals(new String(pfPassword.getPassword()))) {
		
			JOptionPane.showMessageDialog(frame, "Password must be filled.");
			return null;
			
		}else if( pfPassword.getPassword().length  <  5 || pfPassword.getPassword().length > 20  ) {
				
			JOptionPane.showMessageDialog(frame, "Password  must be between 5 and 20 characters");
			return null;
		
		}else if(!new String(pfPassword.getPassword()).equals(new String(pfConfirmPassword.getPassword()))) {
		
			JOptionPane.showMessageDialog(frame, "Confirm password must same with password.");
			return null;
			
		}else if (tfPhone.getText() == null || "".equalsIgnoreCase(tfPhone.getText())) {
		
			JOptionPane.showMessageDialog(frame, "Phone must be filled.");
			return null;
			
		}else if( tfPhone.getText().length() < 10 || tfPhone.getText().length() > 12 ) {
		
			JOptionPane.showMessageDialog(frame, "Phone number must be 11 digits.");
			return null;
		
		}else if(!tfPhone.getText().matches("[0-9]+") ) {
		
			JOptionPane.showMessageDialog(frame, "Phone number must be numeric.");
			return null;
			
		}else if(!rbMale.isSelected() && !rbFemale.isSelected()) {
		 
			JOptionPane.showMessageDialog(frame, "Gender must be selected.");
			return null;

		}else {

			try {
					
					String query = "SELECT * FROM users  WHERE UserName = ' " + tfUsername.getText() + " ' ";   
					ResultSet rs = con.executeQuery(query);
					
				if(rs.getRow() >1) {
					
					String[] data = {"UserName"};
					System.out.println(rs);
					
					return null;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}//close catch
				
			String[] data = {"UserName"};
			return data;
}
		
	}//close method validateData
	
	public void autonumber() {
		try {
			
			Connection konek = null;
			Statement state = (Statement) konek.createStatement();
			String kueri = "SELECT MAX(UserID) FROM users ";
			ResultSet rs = state.executeQuery(kueri);
			
			if(rs.next()) {
				
				txtID = + 1;
				 
			}
			rs.close();
			state.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//close autonumber
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
