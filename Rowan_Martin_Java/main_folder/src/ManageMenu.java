import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class ManageMenu extends JInternalFrame implements ActionListener{
	
	JInternalFrame  iframe = new JInternalFrame("ManageMenu");
	JPanel mainPan,insertPan,formPan;

	JLabel idLbl,nameLbl,QLbl,priceLbl;
	JTextField tfID,tfName,tfQuantity,tfPrice;
	
	Vector<Object> tableHeader,tableData;
	JTable table;
	DefaultTableModel dtm;
	
	private JPanel pnlUtama;
	private JPanel pnlSecond;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JTable tableUp;
	private JScrollPane scrollPane;
	
	Connect con = new Connect();
	
  public ManageMenu() {
		
		iframe.setTitle("ManageMenu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
	  
		initFrame();
		initComponent();
		setVisible(true);
		
		createButtonListener();
		
	}

	public void initFrame(){
		setSize(570,288);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Manage Menu");
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
	}
	
	public void initComponent(){
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Menu ID"));
		tableHeader.add(new String("Menu Name"));
		tableHeader.add(new String("Menu quantitys"));
		tableHeader.add(new String("Menu Price"));
		
		dtm = new DefaultTableModel(tableHeader,0);
		table = new JTable(dtm){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
		
				int row = table.getSelectedRow();
				String id = (String) table.getValueAt(row, 0);
				String name = (String) table.getValueAt(row, 1);
				String quantity = (String) table.getValueAt(row, 2);
				int price = (Integer) table.getValueAt(row, 4);
				
				tfID.setText(id);
				tfName.setText(name);
				tfQuantity.setText(quantity);
				tfPrice.setText(price + "");

			}
		});
		
		mainPan = new JPanel(new FlowLayout());
		getContentPane().add(mainPan);
		
		scrollPane = new JScrollPane();
		scrollPane.setSize(436,155);
		mainPan.add(scrollPane);
		
		tableUp = new JTable();
		scrollPane.setViewportView(tableUp);
		
		//TableUp
		try {
				String query = " SELECT MenuID,Name,Quantity,Price FROM menus";
				
				ResultSet rs = con.executeQuery(query);
				tableUp.setModel(DbUtils.resultSetToTableModel(rs) );
				
			} catch (Exception e) {
				e.printStackTrace();
			}
				
				pnlUtama = new JPanel();
				getContentPane().add(pnlUtama, BorderLayout.NORTH);
				pnlUtama.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				idLbl = new JLabel("Menu Id");
				nameLbl = new JLabel("Menu Name");
				QLbl = new JLabel("Menu Quantity");
				priceLbl = new JLabel("Menu Price");
				
				tfID = new JTextField();
				tfID.setPreferredSize(new Dimension(150, 25));
				
						tfName = new JTextField();
						tfName.setPreferredSize(new Dimension(150, 25));
						
						tfQuantity = new JTextField();
						tfQuantity.setPreferredSize(new Dimension(150, 25));
						
						tfPrice = new JTextField();
						tfPrice.setPreferredSize(new Dimension(150, 25));
						
						insertPan = new JPanel(new GridLayout(4,4));
						
						insertPan.add(idLbl);
						insertPan.add(tfID);
						insertPan.add(nameLbl);
						insertPan.add(tfName);
						insertPan.add(QLbl);
						insertPan.add(tfQuantity);
						insertPan.add(priceLbl);
						insertPan.add(tfPrice);
						
						formPan = new JPanel(new BorderLayout());
						pnlUtama.add(formPan);
						formPan.add(insertPan,BorderLayout.NORTH);
						
						pnlSecond = new JPanel();
						pnlUtama.add(pnlSecond);
						pnlSecond.setLayout(new GridLayout(0, 1));
						
						btnInsert = new JButton("insert");
						pnlSecond.add(btnInsert);
						
						btnUpdate = new JButton("Update");
						pnlSecond.add(btnUpdate);
						
						btnDelete = new JButton("Delete");
						pnlSecond.add(btnDelete);
						
						btnSubmit = new JButton("Submit");
						btnSubmit.setEnabled(false);
						pnlSecond.add(btnSubmit);
						
						btnCancel = new JButton("Cancel");
						btnCancel.setEnabled(false);
						pnlSecond.add(btnCancel);
	}

	public void delete() {
		
		try {
			PreparedStatement ps = con.prepareStatement("Delete VALUES(?,?,?,?)");
			String Sql =" DELETE FROM 'detailtransaction' WHERE 1";
			ps = con.prepareStatement(Sql);
			ps.setString(1, tfID.getText());
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Delete successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getAll() {
		ResultSet rs = con.executeQuery("SELECT * FROM detailtransaction");
		dtm.setRowCount(0);
		try {
			while(rs.next()) {
				tableData = new Vector<>();
				tableData.add(rs.getString("MenuId"));
				tableData.add(rs.getString("Nama menu"));
				tableData.add(rs.getString("Quantity"));
				tableData.add(rs.getInt("Menu price"));
				dtm.addRow(tableData);
			}
			table.setModel(dtm);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void reset() {
		tfID.setText("");
		tfName.setText("");
		tfQuantity.setText("");
		tfPrice.setText("");
	}
	
	public void createButtonListener() {
		
		btnInsert.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
		
	}//close createButtonListener

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert ) {
			
			btnInsert.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			btnSubmit.setEnabled(true);
			btnCancel.setEnabled(true);
			
		}else if(e.getSource() == btnUpdate ) {
			
			int row = tableUp.getSelectedRow();
			int col = tableUp.getSelectedColumn();
			String value = (String) tableUp.getValueAt(row, col).toString(); 
			
			//-	The Insert, the Update, and the Delete button are disabled.
			btnInsert.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			
			//-	The Submit and the Cancel button are enable.
			btnSubmit.setEnabled(true);
			btnCancel.setEnabled(true);
			
		}else if(e.getSource() == btnDelete ) {
			
			int row = tableUp.getSelectedRow();
			int col = tableUp.getSelectedColumn();
			String value = (String) tableUp.getValueAt(row, col).toString(); 
			
			
			String query = "SELECT * FROM menus WHERE MenuID = '  "+ value + " ' ";
			ResultSet rs = con.executeQuery(query);

		try {
			while(rs.next()) {	
			
			if(tableUp.getSelectedColumn() != 0) 
				JOptionPane.showMessageDialog(null,"Please choose the menu first ! ", null, JOptionPane.ERROR_MESSAGE);	
			
			}
		} catch (HeadlessException | SQLException ee) {
			ee.printStackTrace();
		}
			
		}else if(e.getSource() == btnSubmit ) {
			
		} else if(e.getSource() == btnCancel ) {
			
			btnInsert.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
			btnSubmit.setEnabled(false);
			btnCancel.setEnabled(false);	
			
		}
		
	}
	
	public static void main(String[] args) {
		new ManageMenu();
	}
	
}