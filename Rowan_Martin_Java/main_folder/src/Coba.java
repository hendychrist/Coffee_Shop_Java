import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class Coba   {
	
	JFrame frame = new JFrame();
	JLabel lbTitle,lbUsername,lbPassword,lbRegister ;
	JTextField txtUsername;
	JPasswordField pswPassword;
	JButton btnLogin;
	private JPanel panelTop;
	private JTextField txtTxtusername;
	private JTextField txtTxtpassword;
	private JButton btnNewButton;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	
	public void Komponen() {
		
		lbTitle = new JLabel("Login");
		lbUsername = new JLabel("Username");
		lbPassword = new JLabel("Password");
		lbRegister = new JLabel("Click Here to Register!");

	}

	public Coba() {
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Register");
		label.setFont(new Font("Sitka Subheading", Font.BOLD, 16));
		label.setBounds(181, 59, 133, 11);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("UserName");
		label_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		label_1.setBounds(94, 96, 107, 23);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(211, 96, 138, 21);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		label_2.setBounds(94, 130, 96, 23);
		frame.getContentPane().add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(211, 130, 138, 23);
		frame.getContentPane().add(passwordField);
		
		JLabel label_3 = new JLabel("Confirm Password");
		label_3.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		label_3.setBounds(94, 164, 96, 23);
		frame.getContentPane().add(label_3);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		passwordField_1.setBounds(211, 164, 138, 20);
		frame.getContentPane().add(passwordField_1);
		
		JLabel label_4 = new JLabel("Phone Number");
		label_4.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		label_4.setBounds(94, 202, 96, 17);
		frame.getContentPane().add(label_4);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(211, 199, 138, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel label_5 = new JLabel("Gender");
		label_5.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		label_5.setBounds(94, 242, 46, 14);
		frame.getContentPane().add(label_5);
		
		JRadioButton radioButton = new JRadioButton("Male");
		radioButton.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		radioButton.setBounds(207, 237, 53, 23);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Female");
		radioButton_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		radioButton_1.setBounds(270, 238, 64, 23);
		frame.getContentPane().add(radioButton_1);
		
		JCheckBox checkBox = new JCheckBox("I Agree With Terms");
		checkBox.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		checkBox.setBounds(94, 276, 127, 23);
		frame.getContentPane().add(checkBox);
		
		JButton button = new JButton("Register");
		button.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		button.setBounds(123, 306, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		button_1.setBounds(225, 306, 89, 23);
		frame.getContentPane().add(button_1);
		
	
	}
}
