import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class Cpass    {
	
	Connect conn = new Connect();
	Statement stmt;
	ResultSet rs;
	
	 JInternalFrame frame2 = new JInternalFrame("View Profile");
	 private JPasswordField tfOldPass;
	 private JPasswordField tfNewPass;
	 private JPasswordField tfConfirmPass;

	public Cpass() {
		
		frame2.setClosable(true);
		frame2.setResizable(true);
		frame2.setMaximizable(true);
		frame2.setIconifiable(true);
		
		frame2.setLocation(100,100);
		frame2.setSize(368,279);
		
		JPanel pnlTop = new JPanel();
		frame2.getContentPane().add(pnlTop, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Change Password");
		label.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		pnlTop.add(label);
		
		JPanel pnlBot = new JPanel();
		frame2.getContentPane().add(pnlBot, BorderLayout.SOUTH);
		pnlBot.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		pnlBot.add(btnSave);
		
		JPanel pnlMid = new JPanel();
		frame2.getContentPane().add(pnlMid, BorderLayout.CENTER);
		pnlMid.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_1 = new JLabel("Old Password");
		label_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		pnlMid.add(label_1);
		
		tfOldPass = new JPasswordField();
		pnlMid.add(tfOldPass);
		
		JLabel label_2 = new JLabel("New Password");
		label_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		pnlMid.add(label_2);
		
		tfNewPass = new JPasswordField();
		pnlMid.add(tfNewPass);
		
		JLabel label_3 = new JLabel("Confirm Password");
		label_3.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		pnlMid.add(label_3);
		
		tfConfirmPass = new JPasswordField();
		pnlMid.add(tfConfirmPass);
		
		actionPeformed();
		
	}//close cons
	
	private boolean validasi() {
		
		//saya mau ambil 'userPassword' dari table 'users' dari 'userID' yang paling tinggi atau baru di input  
		 
		if(new String(tfOldPass.getPassword()) == null || "".equals(new String(tfOldPass.getPassword()))) {
			
			JOptionPane.showMessageDialog(frame2,"Old password must be filled",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if(new String(tfNewPass.getPassword()) == null || "".equals(new String(tfNewPass.getPassword()))) {
			
			JOptionPane.showMessageDialog(frame2,"New Password must be filled",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if(tfNewPass.getPassword().equals("[a-zA-Z0-9]+") ) {
			
			JOptionPane.showMessageDialog(frame2,"password must be Alphanumeric",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if( tfNewPass.getPassword().length < 5 || tfNewPass.getPassword().length > 20 ){
			
			JOptionPane.showMessageDialog(frame2,"password  length must be between 5 and 20 characters",null, JOptionPane.ERROR_MESSAGE);
			return false;
			
		}else if(!new String(tfNewPass.getPassword()).equals(new String(tfConfirmPass.getPassword()))) {
			
			JOptionPane.showMessageDialog(frame2,"Confirm password must same with New password.",null, JOptionPane.ERROR_MESSAGE);
			return false;	
		}	
		return true;
	}//close validate
	
	public void actionPeformed() {
	}//close method actionPerformed

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cpass frame = new Cpass();
					frame.frame2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
