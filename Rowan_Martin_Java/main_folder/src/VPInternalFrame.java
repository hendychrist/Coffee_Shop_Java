import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class VPInternalFrame  extends Cpass  {
	private static final Component ERROR_MESSAGE = null;
	private JTextField PUsername;
	private JTextField tfPhoneNumber;
	
	JInternalFrame iframe = new JInternalFrame("My Profile");
	
	private JButton btnEdit = new JButton("Edit");
	private JButton btnUpdate = new JButton("Update");
	private JButton btnCancel = new JButton("Cancel");
	
	private 	JRadioButton rdbtnFemale = new JRadioButton("Female");
	private JRadioButton rdbtnMale = new JRadioButton("Male");
	
	private JPanel Panel = new JPanel();
	private JLabel lbProfile = new JLabel("Profile");
	private JLabel lbUsername = new JLabel("Username");
	private JLabel lbPhoneNumber = new JLabel("Phone Number");
	private JLabel lbGender = new JLabel("Gender");
	
	Connect conn = new Connect();
	Statement stmt;
	Register regis = new Register();
	ResultSet rs;

	String saya;
	
	public  void  ayoo()  {

		try {
			
			//problem di query ---- saya mau ambil 'UserName' di table 'users' yang dimana userID nya paling tinggi
			String query = "SELECT UserName FROM users ";
			
			ResultSet hy = stmt.executeQuery(query);
			
		if (hy.next() ) {	
			
			saya = hy.getString("UserName");

		}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public VPInternalFrame()   {
		
		iframe.setClosable(true);
		iframe.setResizable(true);
		iframe.setMaximizable(true);
		iframe.setIconifiable(true);
		iframe.setSize(320,273);

		iframe.getContentPane().add(Panel, BorderLayout.CENTER);
		Panel.setLayout(null);
		
		lbProfile.setLocation(119, 11);
		lbProfile.setSize(54, 21);
		lbProfile.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		
		lbUsername.setLocation(34, 65);
		lbUsername.setSize( 82, 21);
		lbUsername.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));

		lbPhoneNumber.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		
		lbPhoneNumber.setLocation(34,97);
		lbPhoneNumber.setSize(102,14);
		
		lbGender.setLocation(34,135);
		lbGender.setSize(46,14);
		
		rdbtnMale.setLocation(164,132);
		rdbtnMale.setSize(58,23);
		
		rdbtnFemale.setLocation(224,134);
		rdbtnFemale.setSize(80,21);
		
		btnEdit.setLocation(34,183);
		btnEdit.setSize(65,23);
		
		btnUpdate.setLocation(108,184);
		btnUpdate.setSize(89,23);
		
		btnCancel.setLocation(213,184);
		btnCancel.setSize(72,23);
		
		PUsername = new JTextField( saya);
		PUsername.setColumns(10);
		PUsername.setLocation(160,65);
		PUsername.setSize(125,20);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setColumns(10);
		tfPhoneNumber.setLocation(160,94);
		tfPhoneNumber.setSize(125,21);

		lbGender.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		rdbtnMale.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		rdbtnFemale.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		btnEdit.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		btnUpdate.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		
		Panel.add(lbUsername);
		Panel.add(lbProfile);
		Panel.add(lbPhoneNumber);
		Panel.add(PUsername);
		Panel.add(tfPhoneNumber);
		Panel.add(lbGender);
		Panel.add(rdbtnMale);
		Panel.add(btnCancel);
		Panel.add(btnEdit);
		Panel.add(rdbtnFemale);
		Panel.add(btnUpdate);
		
		//Buat dia siable
		btnUpdate.setEnabled(false);
		btnCancel.setEnabled(false);
		PUsername.setEnabled(false);
		tfPhoneNumber.setEnabled(false);
		rdbtnMale.setEnabled(false);
		rdbtnFemale.setEnabled(false);
		btnEdit.setEnabled(true);
		
		Actionperform();
		ayoo();
		
	// 	String[] saya = ayoo();
	//	 System.out.println(saya);

	}//close cons
	
						/*					public void coba() throws SQLException {
												
												Connection kinuk = null;
												Statement stmt = (Statement) kinuk.createStatement();
												
												String query = "SELECT MAX(UserName) FROM users ";
												
											try {
										
											stmt.executeQuery(query);
											
											}catch (Exception e) {
													System.out.println(e);
											}
											
											} 	*/
	
	public void Actionperform() {
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == btnEdit) {
					
					tfPhoneNumber.setEnabled(true);
					rdbtnMale.setEnabled(true);
					rdbtnFemale.setEnabled(true);
					btnUpdate.setEnabled(true);
					btnCancel.setEnabled(true);
				    btnEdit.setEnabled(false);
				    
				}
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				// Jika validatePhone itu true maka dia eksekusi code di bawah nya
				if(validatePhone() ) {
						UpdatePhone();
					
				JOptionPane.showMessageDialog(null, "Update Success");
					iframe.dispose();

			}//close actionPerformed
			}
		}); 
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				if(event.getSource() == btnCancel) {
					
					btnUpdate.setEnabled(false);
					btnCancel.setEnabled(false);
					PUsername.setEnabled(false);
					tfPhoneNumber.setEnabled(false);
					rdbtnMale.setEnabled(false);
					rdbtnFemale.setEnabled(false);
					btnEdit.setEnabled(true);
				    
				}
				
			}
		});
		
	}
	
	private boolean UpdatePhone() {
		
		try {
	 //	Connection kon = null;
	 	//Statement satai =  (Statement) kon.createStatement(); 
	
			String phone = "UPDATE users SET UserPhoneNumber = '"+ tfPhoneNumber.getText() +" ' "
					+ "WHERE MAX(UserName) =  ' "+ regis.Rnama +" ' " ;
			
		//	rs =  satai.executeUpdate(phone);
			conn.executeUpdate(phone);

				return true;
				
		}catch(Exception e) {
			e.printStackTrace();
		}
			return false;
	}//close method udpatePhone  
	
	private boolean validatePhone() {
		
		if( tfPhoneNumber.getText() == null || "".equalsIgnoreCase(tfPhoneNumber.getText())  )  {
			
			JOptionPane.showMessageDialog(null, "Phone must be filled.",null, JOptionPane.ERROR_MESSAGE);
				return false;
				
		}else if( tfPhoneNumber.getText().length() < 10 || tfPhoneNumber.getText().length() > 12 )  {
			
			JOptionPane.showMessageDialog(null, "Phone number must be 11 digits.",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if( !tfPhoneNumber.getText().matches("[0-9]+") ) {
			
			JOptionPane.showMessageDialog(null, "Phone number must be numeric. ",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else {
			return true;
		}
	}
	
	//Launch Application
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VPInternalFrame frame = new VPInternalFrame();
						frame.iframe.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}


}
