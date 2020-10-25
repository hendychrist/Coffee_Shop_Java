import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Image;

public class MainForm   extends VPInternalFrame   implements ActionListener {

	JFrame frame;

	private JMenu MenuUser = new JMenu("User");
	private JMenuItem btnView = new JMenuItem("View Profile");
	private JMenuItem ChangePass = new JMenuItem("Change Password");
	private JMenuItem Logout = new JMenuItem("Logout");
	
	private JMenu MenuTrans = new JMenu("Transaction");
	private JMenuItem BuyDrink = new JMenuItem("Buy Drink");
	private JMenuItem ViewTrans = new JMenuItem("View Transaction");
	
	private JMenuBar Menubar = new JMenuBar();
	private JDesktopPane desktopPane1 = new JDesktopPane();
	
	private BuyDrinkForm frame3 = new BuyDrinkForm();
	private ViewTransactionUser framee = new ViewTransactionUser();
	
	public MainForm() {
		createView();
		createButtonListener();
	}

	public  void createView() {
		
		frame = new JFrame("Rowan Martini (User)");
		frame.setSize(775, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/Final3.jpg"));
		final Image img = icon.getImage();
		desktopPane1 = new JDesktopPane() {
			public void paintComponent(Graphics g) {
				g.drawImage(img,0,-91, 779, 491, this);
			}
		};
			
		desktopPane1.setLocation(0,0);
		desktopPane1.setSize(759,361);
		frame.getContentPane().add(desktopPane1);
		frame.setLocationRelativeTo(null);
		
		frame3.setLocation(0, 0);
		
		desktopPane1.add(iframe);
		desktopPane1.add(frame2);
		desktopPane1.add(frame3);
		desktopPane1.add(framee);

		//panel add
		MenuUser.add(btnView);
		MenuUser.add(ChangePass);
		MenuUser.add(Logout);
	
		MenuTrans.add(BuyDrink);
		MenuTrans.add(ViewTrans);
		
		Menubar.add(MenuUser);
		Menubar.add(MenuTrans);
		
		frame.setJMenuBar(Menubar);
		
	}
	
	public void createButtonListener() {
		Logout.addActionListener(this);
		btnView.addActionListener(this);
		ChangePass.addActionListener(this);
		BuyDrink.addActionListener(this);
		ViewTrans.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == Logout) {
			
				frame.dispose();
						Login log = new Login();
			  			log.dialog.setVisible(true);
			  			
		}else if(event.getSource() == btnView ) {

			iframe.setVisible(true); //  InternalFrame

		}else if(event.getSource() == ChangePass ) {
			
			frame2.setVisible(true);
			
		}else if(event.getSource() == BuyDrink) {
			
			frame3.setVisible(true);
			
		}else if(event.getSource() == ViewTrans) {
			
			framee.setVisible(true);
			
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
