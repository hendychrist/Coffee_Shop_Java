import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BuyDrinkForm extends JInternalFrame {
	
	private JTable tableUp;
	private JTextField tfMenuId = new JTextField(); 
	private JTextField tfPrice = new JTextField();
	private JTextField tfTotalPrice = new JTextField();
	private JTable tableDown;
	private JButton btnAddCart = new JButton("Add to Cart");
	private SpinnerModel sm ; 
	private JSpinner tfQuantity = new JSpinner();
	private 	JButton btnRemoveAll = new JButton("Remove All");
	private 	JLabel lbBuyDrink = new JLabel("Buy Drink");
	private 	JScrollPane scrollPane = new JScrollPane();
	private 	JLabel lbMenuId = new JLabel("Menu ID");
	private JLabel lbPrice = new JLabel("   Price");
	private JLabel lblQuantity = new JLabel("Quantity");
	private JLabel lblTotalPrice = new JLabel("Total Price");
	private JScrollPane scrollPane_1 = new JScrollPane();
	private JButton btnCheckOut = new JButton("Check Out");
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate localDate = LocalDate.now();
	
	 JInternalFrame frame3 = new JInternalFrame();
	Connect con = new Connect();
	
	Random rand = new Random();
	
	int txtID = 0;
	int angka;

	public BuyDrinkForm() {
		
		setTitle("Buy Drink");
		setIconifiable(true);
		setClosable(true);

		// Change
		setLocation(100,100);
		setSize(788,385);
		
		lbBuyDrink.setLocation(316,0);
		lbBuyDrink.setSize(90,22);
		
		scrollPane.setLocation(20,21);
		scrollPane.setSize(732,144);
		
		lbMenuId.setLocation(30,176);
		lbMenuId.setSize(60,18);
		
		lbPrice.setLocation(20,213);
		lbPrice.setSize(46,14);
		
		lblQuantity.setLocation(30,246);
		lblQuantity.setSize(70,14);
		
		lblTotalPrice.setLocation(30,279);
		lblTotalPrice.setSize(60,14);
		
		tfMenuId.setLocation(162,176);
		tfMenuId.setSize(145,22);
		
		tfPrice.setLocation(162,209);
		tfPrice.setSize(145,22);
		
		tfQuantity.setLocation(162,242);
		tfQuantity.setSize(145,22);
		
		tfTotalPrice.setLocation(162,275);
		tfTotalPrice.setSize(145,22);
		
		btnAddCart.setLocation(20,308);
		btnAddCart.setSize(89,23);
		
		btnRemoveAll.setLocation(119,308);
		btnRemoveAll.setSize(89,23);
		
		btnCheckOut.setLocation(218,308);
		btnCheckOut.setSize(89,23);
		
		lbBuyDrink.setFont(new Font("Calibri", Font.BOLD, 15));
//		getContentPane().setLayout(null);
		getContentPane().add(lbBuyDrink);
		getContentPane().add(scrollPane);
	
		tableUp = new JTable();
	//	tableUp.setCellSelectionEnabled(true);
		tableUp.getSelectedColumns();
		scrollPane.setViewportView(tableUp);
		
		//TableUp
				try {
						String query = " SELECT MenuID,Name,Quantity,Price FROM menus";
						

						Class.forName("com.mysql.jdbc.Driver");
						
						ResultSet rs = con.executeQuery(query);
						tableUp.setModel(DbUtils.resultSetToTableModel(rs) );
						
					} catch (Exception e) {
						e.printStackTrace();
					}
		
		tfMenuId.setEnabled(false);
		tfPrice.setEnabled(false);
		tfTotalPrice.setEnabled(false);

		scrollPane_1.setLocation(316,176);
		scrollPane_1.setSize(436,155);

		getContentPane().add(scrollPane_1);
		
		tableDown = new JTable();
		scrollPane_1.setViewportView(tableDown);
			
		lbMenuId.setFont(new Font("Calibri", Font.PLAIN, 14));
		getContentPane().add(lbMenuId);
		
	
		lbPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
		getContentPane().add(lbPrice);
		
		lblQuantity.setFont(new Font("Calibri", Font.PLAIN, 14));
		getContentPane().add(lblQuantity);
		
		lblTotalPrice.setFont(new Font("Calibri", Font.PLAIN, 14));
	
		getContentPane().add(lblTotalPrice);
		
		getContentPane().add(tfMenuId);
		tfMenuId.setColumns(10);
		
	
		getContentPane().add(tfPrice);
		tfPrice.setColumns(10);
		
		getContentPane().add(tfQuantity);
		getContentPane().add(tfTotalPrice);
		tfTotalPrice.setColumns(10);
		
		btnAddCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(event.getSource() == btnAddCart) {
		
					//Hanya bisa di klik di bagian MenuID pada table
				int row = tableUp.getSelectedRow();
				int col = tableUp.getSelectedColumn();
				String value = (String) tableUp.getValueAt(row, col).toString(); 
					//    String values  = (String) (tableUp.getModel().getValueAt(row, col) );
				
				String query = "SELECT * FROM menus WHERE MenuID = '  "+ value + " ' ";
				ResultSet rs = con.executeQuery(query);

			try {
				while(rs.next()) {	
					
					int harga = rs.getInt("price");
					int qtyy = rs.getInt("Quantity");
					int qty = (Integer) tfQuantity.getValue();
					int tprice = harga * qty;
					
							if(tableUp.getSelectedColumn() != 0) {
								JOptionPane.showMessageDialog(frame3,"Please choose the menu first ! ", null, JOptionPane.ERROR_MESSAGE);	
							}else {					
					//			JOptionPane.showMessageDialog(frame3,value);	
									if(tfQuantity.getValue().toString().isEmpty()  || qty < 1 || qty > qtyy   ){		
										
				//						JOptionPane.showMessageDialog(frame3,"Card must not be empty", null, JOptionPane.ERROR_MESSAGE);
										JOptionPane.showMessageDialog(frame3,"Fill the quantity first", null, JOptionPane.ERROR_MESSAGE);
										
									}else {
										
										tfMenuId.setText(rs.getString("MenuID") );
										tfPrice.setText(rs.getString("Price") );			
											
										tfTotalPrice.setText(String.valueOf(tprice) ) ;
										
										String kueri = "INSERT INTO cart VALUE('  " +rs.getString("MenuID") + "  ',' "+ rs.getString("Name") + " ',' "+ qty +" ',' " + harga  + "',' " + tprice +" ',' "+ dtf.format(localDate) +" ') ";
										con.executeUpdate(kueri);
										
									}//close elese2
							}	//close else1
					}//close while
						rs.close();

				} catch (HeadlessException | SQLException e) {
						e.printStackTrace();
				}
					refreshTD();
			}
			}
		});
		
		btnAddCart.setFont(new Font("Tahoma", Font.PLAIN, 9));
	
		getContentPane().add(btnAddCart);
		
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnRemoveAll) {
					
				String[] data = check();
					
				if(data == null) {
					JOptionPane.showConfirmDialog(frame3, "There is nothing to remove " , null , JOptionPane.ERROR_MESSAGE);
				}else {
					
					selectRows(tableDown, 0,  tableDown.getRowCount());
					
			//		JOptionPane.showConfirmDialog(frame3, "LANJUTTTT " , null , JOptionPane.ERROR_MESSAGE);
				
					        ArrayList<Integer> values = new ArrayList<>();
					        int[] vals = tableDown.getSelectedRows();
			/*		        for (int i = 0; i < vals.length; i++) {
					            for (int x = 0; x < tableDown.getColumnCount(); x++) {
					            	
					                System.out.println(tableDown.getValueAt(i, x));
					                values.add(Integer.parseInt((String) tableDown.getValueAt(i, x)));
					                
					            }
					        } */
					        
				String kueri1	= "SELECT * FROM cart";
				ResultSet rs = con.executeQuery(kueri1);
		
						try {
							while(rs.next()) {
								
								for(int i = 0 ; i < vals.length ; i++) {
							String kueri2 = "	UPDATE menu SET Quantity = ' "+ rs.getInt("Quantity")  +" '  WHERE MenuID = ' " +rs.getString("MenuID")+" '  ";
							con.executeUpdate(kueri2);	
								}

							//Quantity nyo berubah tapi cuman 1 line cak mano caro brubah ny banyak line di database.
							
		//					String kueri3 = "DELETE FROM cart WHERE cart.Name = '  menu." + rs.getString("Name")+" '  ";
			//				con.executeUpdate(kueri3);
									
							JOptionPane.showConfirmDialog(frame3, "Friday bosqu");
							
							}rs.close();
							
						} catch (SQLException a) {
							a.printStackTrace();
						}

				}
				
				}
			}
		});
		btnRemoveAll.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		getContentPane().add(btnRemoveAll);
		
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if(event.getSource() == btnCheckOut) {
					
					int row = tableDown.getSelectedRow();
					int col = tableDown.getSelectedColumn();
					String value = (String) tableDown.getValueAt(row, col).toString(); 
					
			       
			    //    int[] valz = tableDown.getSelectedRows();

					String kueri3 = "SELECT MenuID,Name,Quantity,TotalPrice FROM cart  WHERE MenuID = "+ value +"  " ;
					ResultSet rs =  con.executeQuery(kueri3);
				
					try {
				
						while(rs.next()) {
							int n = rand.nextInt(6);
							n += 1;
					//	    for(int i = 0 ; i < valz.length ; i++) {
								String kueri4 = "INSERT INTO detailtransaction VALUES ( ' "+ n +" ' , ' "+rs.getString("Name") +" ' , ' "+ rs.getInt("Quantity") +" ' , ' "+rs.getInt("TotalPrice")+" ') ";
								con.executeUpdate(kueri4);
				//		    }
						    
						}
						
						rs.close();
						
					} catch (Exception e) {
							e.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Success");
			}
			}
		});

		btnCheckOut.setFont(new Font("Tahoma", Font.PLAIN, 9));
		getContentPane().add(btnCheckOut);

	} //close construcktor
	
	public String[] check() {
		
		String query = "SELECT * FROM cart";
		ResultSet rs = con.executeQuery(query);
	
		try {
			
			if(rs.next()) {
				
				String[] data =  { rs.getString("Name") };
					return data;

			}rs.close();
			
		} catch (Exception event) {
			event.printStackTrace();
		}
		return null;
	}
	
	public void refreshTD() {
		
		//TableDown
		try {
			
			String kueri = "SELECT MenuID,Name,Price,Quantity,TotalPrice FROM cart";
			ResultSet rs = con.executeQuery(kueri);
			
			tableDown.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	   private void selectRows(JTable tableDown, int start, int end) {
	        // Use this mode to demonstrate the following examples
		   tableDown.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        // Needs to be set or rows cannot be selected
		   tableDown.setRowSelectionAllowed(true);
	        // Select rows from start to end if start is 0 we change to 1 or leave it (used to preserve coloums headers)
		   tableDown.setRowSelectionInterval(start, end - 1);
	    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyDrinkForm frame = new BuyDrinkForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
