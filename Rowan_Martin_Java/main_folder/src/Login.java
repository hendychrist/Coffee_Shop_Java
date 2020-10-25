import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.AncestorListener;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class Login implements ActionListener{

	JFrame dialog = new JFrame();
	Connect con = new Connect();
	ResultSet rs;
	
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	JLabel lbRegister = new JLabel("Click Here to Register !");
	JButton btnSubmit = new JButton("Login");

	public Login(  ) {
	
		dialog.setSize( 418, 344);
		dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pnlTop = new JPanel();
		dialog.getContentPane().add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel LoginTitle = new JLabel("Login");
		LoginTitle.setFont(new Font("Sitka Text", Font.BOLD, 16));
		pnlTop.add(LoginTitle);
		
		JPanel pnlBot = new JPanel();
		JPanel panelKet = new JPanel();
		JPanel panelBtn = new JPanel();
		
		panelKet.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		pnlBot.setLayout(new GridLayout(0, 1));
		
		dialog.getContentPane().add(pnlBot, BorderLayout.SOUTH);
	//	pnlBot.setLayout(new FlowLayout(FlowLayout.CENTER));
		
	//	pnlBot.add(panelBtn,BorderLayout.SOUTH);
		pnlBot.add(panelBtn, "Center");
		panelBtn.setLayout(new BorderLayout(0, 0));
		panelBtn.add(btnSubmit);
		
		btnSubmit.setVerticalAlignment(SwingConstants.TOP);
		btnSubmit.setFont(new Font("Sitka Subheading", Font.BOLD, 13));
		pnlBot.add(panelKet,BorderLayout.NORTH);
		pnlBot.add(panelKet,"NORTH");
		
		JLabel lbl = new JLabel("Dont Have an Account");
		lbl.setVerticalAlignment(SwingConstants.BOTTOM);
		panelKet.add(lbl);
		lbl.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		panelKet.add(lbRegister);
		
		lbRegister.setVerticalAlignment(SwingConstants.BOTTOM);
		lbRegister.setForeground(Color.BLUE);
		lbRegister.setFont(new Font("Sitka Subheading", Font.PLAIN, 12));
		
		JPanel pnlMid = new JPanel();
		dialog.getContentPane().add(pnlMid, BorderLayout.CENTER);
		pnlMid.setLayout(new GridLayout(2, 2));
		
		JLabel lbUsername = new JLabel("Username");
		lbUsername.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		pnlMid.add(lbUsername);
		
		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		pnlMid.add(tfUsername);
		
		JLabel lbPassword = new JLabel("Password");
		lbPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 13));
		pnlMid.add(lbPassword);
		
		pfPassword = new JPasswordField();
		pnlMid.add(pfPassword);
		createButtonListener();
	}

	public void createButtonListener() {
		btnSubmit.addActionListener(this);
		lbRegister.addMouseListener(new MouseAdapter() {
			
            @Override
            public void mouseClicked(MouseEvent event) {
            	if(event.getSource() == lbRegister) {
            	dialog.dispose();
            	
            	Register regis = new Register();
            	regis.frame.setVisible(true);
            	}
            	
            }

        });

	}//close createButtonListener
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
	if(event.getSource() == btnSubmit) {
				
				String[]data = validasi();
				
				if(data != null) {
		
					JOptionPane.showMessageDialog(dialog,"Submit Successfully");
					
					dialog.dispose();
					
					AdminForm af = new AdminForm();
					af.prame.setVisible(true);
						
				}//close if  validate
			}//close if getSource
	}//close actionPerformed
	
	public  String[] validasi() {
		
		if(tfUsername.getText() == null || "".equalsIgnoreCase(tfUsername.getText())) {
			 
			 JOptionPane.showMessageDialog(null, "Username must be filled.");
			return null;
			 
			
		 }else if (new String(pfPassword.getPassword()) == null || "".equals(new String(pfPassword.getPassword()))) {
			 
				JOptionPane.showMessageDialog(null, "Password  must be filled.");
				return null;
				
			}else {   
				
				String UserName = tfUsername.getText();
				String UserPassword = new String(pfPassword.getPassword());
			
				String query = " SELECT UserName, UserPassword FROM  users  WHERE   UserName  = '" + UserName + "' AND UserPassword = ' " + UserPassword +" ' ";
		        rs = (ResultSet) con.executeQuery(query);
					
					try  {
					
						if(rs.next()) {
							
							String[] data = {rs.getString("UserName"), rs.getString("UserPassword")};
							return data;
							
						}else {
							JOptionPane.showMessageDialog(dialog, "Your account is not registered");
							return null;
							
						}//close else
						
					}  catch(SQLException e)  {
						JOptionPane.showMessageDialog(dialog, "Invalid email or password.");
						return null;
					}//close catch
						
				}//close else
		
	}//close validasi
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	public void run() {
		try {
					Login window = new Login();
					window.dialog.setVisible(true);
				} catch (Exception e) {	
					e.printStackTrace();
				}
			}
		});
	}

}
