import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;

public class AdminForm    implements ActionListener{   
	
	JFrame prame;
	
	private JMenuItem miTransaction = new JMenuItem("View Transaction");
	private JMenuItem mbLogout = new JMenuItem("Logout");
	private  JMenuItem miManageMenu = new JMenuItem("Manage menu");
	private  JMenuBar menuBar = new JMenuBar();
	private JMenu mnTransaction = new JMenu("Transaction");
	private JMenu mnUser = new JMenu("User");
	private  JMenu mnManages = new JMenu("Manages");
	 JDesktopPane desktopPane = new JDesktopPane();

	Connect con = new Connect();
	ResultSet rs;
	
	String username;
	
	public AdminForm() {
		createView();
		createButtonListener();
	}
	
		public void createView() {
      
			prame= new JFrame("Rowan Martini (Admin)");
			prame.setSize(775, 421);
			prame.setLocation(300,200);
			prame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			prame.getContentPane().setLayout(null);
      
	      ImageIcon icon = new ImageIcon(this.getClass().getResource("/images/Final3.jpg"));
			final Image img = icon.getImage();
			desktopPane = new JDesktopPane() {
				public void paintComponent(Graphics g) {
					g.drawImage(img,0,-91, 779, 491, this);
				}
			};
      
	      desktopPane.setLocation(0,0);
	      desktopPane.setSize(759,361);
	      prame.getContentPane().add(desktopPane);
	      prame.setLocationRelativeTo(null);
	     
	      menuBar.add(mnUser);
	      mnUser.add(mbLogout);
	      mnTransaction.add(miTransaction);
	      menuBar.add(mnTransaction);
	      menuBar.add(mnManages);
	      mnManages.add(miManageMenu);
	      
	      prame.setJMenuBar(menuBar);
	      
			}
		
			public void createButtonListener() {
				mbLogout.addActionListener(this);
				miTransaction.addActionListener(this);
				miManageMenu.addActionListener(this);
			}
			
			@Override
			public void actionPerformed(ActionEvent event) {
				if(event.getSource() == mbLogout ) {
					
					//frame close nya belom bisa
					prame.dispose();
					
	      			Login log = new Login();
	      			log.dialog.setVisible(true);
	      			
	      		}else if(event.getSource() == miTransaction ) {
		
	      		ViewTransactionAdmin ifs = new ViewTransactionAdmin();
	      	      desktopPane.add(ifs);     
	      	      ifs.show();
	      				
	      		}else if(event.getSource() == miManageMenu) {
	      			
	      			ManageMenu mm = new ManageMenu();
	      	        desktopPane.add(mm);     
	      			mm.iframe.setVisible(true);
	      			
	      		}
			}
		
			public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AdminForm ass = new AdminForm();
							ass.prame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			
		}


