import java.awt.EventQueue;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import net.proteanit.sql.DbUtils;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

	public class ViewTransactionUser extends JInternalFrame {
	private JTable table;
	private JLabel lblNewLabel = new JLabel("Transaction");
	private JLabel lblNewLabel_1 = new JLabel("Transaction ID");
	private JLabel lblDate = new JLabel("Date");
	private JLabel lblTotalPricew = new JLabel("Total Price");
	private JScrollPane scrollPane = new JScrollPane();
	private JComboBox comboBox = new JComboBox();
	
	Connect con = new Connect(); 
	JLabel lbTotal = new JLabel("");
	  JLabel lbDate = new JLabel("");

	public ViewTransactionUser() {
		getContentPane().setBackground(UIManager.getColor("Button.light"));
		
		setTitle("Transaction");
		setIconifiable(true);
		setClosable(true);
		
		setLocation(100,100);
		setSize(433,343);
	
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setLocation(148,11);
		lblNewLabel.setSize(118,29);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setLocation(48,51);
		lblNewLabel_1.setSize(103,17);
		getContentPane().add(lblNewLabel_1);
		
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setLocation(48,86);
		lblDate.setSize(103,17);
		getContentPane().add(lblDate);

		lblTotalPricew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalPricew.setLocation(48,123);
		lblTotalPricew.setSize(103,17);
		getContentPane().add(lblTotalPricew);
		
		comboBox.setLocation(194,51);
		comboBox.setSize(114,21);
		getContentPane().add(comboBox);
				
		scrollPane.setLocation(10,163);
		scrollPane.setSize(397,139);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setLocation(10,163);
		table.setSize(397,139);
		getContentPane().add(table);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lbTotal.setLocation(194,123);
		lbTotal.setSize(114,21);
		
		lbDate.setLocation(194,89);
		lbDate.setSize(114,21);
		
		getContentPane().add(lbTotal);
		getContentPane().add(lbDate);
		
		refreshTD();
		Fillcombo();
		TotalPrice();
		setDate();
		
		Vector<Integer> vTransactionID = new Vector<>();
		Vector<String> MenuID = new Vector<>();
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent Event ) {
				int id = 0;
				id =  (int) comboBox.getSelectedItem();
		//		System.out.println(id);
			
				if(id > 0 ) {
					
				try {
					
					String kueri = "SELECT TransactionID,MenuID,Quantity,Price  FROM detailtransaction WHERE TransactionID = " + id  +" ";
					ResultSet rs = con.executeQuery(kueri);	
					DefaultTableModel tm = (DefaultTableModel) table.getModel();
					tm.setRowCount(0);
					
					while(rs.next()) {
						
						Object o[] = {rs.getInt("TransactionID") , rs.getString("MenuID"),rs.getInt("Quantity") , rs.getInt("Price")};
						tm.addRow(o);
						
				//		vTransactionID.add(rs.getInt("TransactionID"));
			//			MenuID.add(rs.getString("MenuID"));
						
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				

			
				}
		
					
			}
		});

	}
	
	private void Fillcombo() {
		
		String sql = "SELECT * FROM detailtransaction ";
		ResultSet rs = con.executeQuery(sql);
		
		try {
			
			while(rs.next()) {
				int TransactionID = rs.getInt("TransactionID");
				
				//Buat item di combo box
				comboBox.addItem(TransactionID);
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	
	}
	
	public void setDate() {
		
		String sel = "SELECT *  FROM cart";
		ResultSet rs = con.executeQuery(sel);

		try {
			
			while(rs.next()) {

				lbDate.setText(rs.getString("TransactionDate"));
			}
			rs.close();
		} catch (Exception e) {
	
		}
		
	}
	
	public void TotalPrice() {
		
		String sal = "SELECT SUM(Price) AS harga  FROM detailtransaction";
		ResultSet rs = con.executeQuery(sal);

		try {
			
			while(rs.next()) {

				lbTotal.setText(rs.getString("harga"));
			}
			rs.close();
		} catch (Exception e) {
	
		}
	}
	
	public void refreshTD() {

		try {
			
			String kueri = "SELECT TransactionID,MenuID,Quantity,Price  FROM detailtransaction";
			ResultSet rs = con.executeQuery(kueri);		
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	   private void selectRows(JTable table, int start, int end) {
	        // Use this mode to demonstrate the following examples
		   table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
	        // Needs to be set or rows cannot be selected
		   table.setRowSelectionAllowed(true);
	        // Select rows from start to end if start is 0 we change to 1 or leave it (used to preserve coloums headers)
		   table.setRowSelectionInterval(start, end - 1);
	    }

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTransactionUser frame = new ViewTransactionUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
