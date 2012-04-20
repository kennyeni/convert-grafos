package mx.adk.grafos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Enumeration;

class MyDefaultTableModel extends DefaultTableModel {
	public MyDefaultTableModel(){
		super();
	}
    public Vector getColumnIdentifiers() {
        return columnIdentifiers;
    }
}

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtEstados;
	private JTextField txtLenguaje;
	private JTextField txtFinal;
	private JTable tblTrans;
	private JList lstEstados;
	private JList lstLenguaje;
	DefaultTableModel modelo;

	DefaultListModel listaEstados;
	TreeSet<String> setEstados = new TreeSet<String>();
	
	DefaultListModel listaLenguaje;
	TreeSet<Character> setLenguaje = new TreeSet<Character>();
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equalsIgnoreCase("btnAEstados")){
			String tmp = txtEstados.getText();
			if(setEstados.add(tmp)){
				listaEstados.addElement(tmp);
				String[] data = new String[1];
				data[0] = tmp;
				modelo.addRow(data);
			}
			return;
		}
		if(comando.equalsIgnoreCase("btneestados")){
			String tmp = (String) lstEstados.getSelectedValue();
			setEstados.remove(tmp);
			listaEstados.removeElement(tmp);
		}
		if(comando.equalsIgnoreCase("btnALenguaje")){
			char tmp = txtLenguaje.getText().charAt(0);
			if(setLenguaje.add(tmp)){
				listaLenguaje.addElement(tmp);
				modelo.addColumn(tmp);
			}
			return;
		}
		if(comando.equalsIgnoreCase("btnelenguaje")){
			char tmp = (char) lstLenguaje.getSelectedValue().toString().charAt(0);
			setLenguaje.remove(tmp);
			listaLenguaje.removeElement(tmp);
			TableColumn col = new TableColumn();
			col.setHeaderValue(tmp);
			int i = getColumnID(tmp);
			removeColumnAndData(tblTrans, i);
		}
	}
	
	public void removeColumnAndData(JTable table, int vColIndex) {
	    MyDefaultTableModel model = (MyDefaultTableModel)table.getModel();
	    TableColumn col = table.getColumnModel().getColumn(vColIndex);
	    int columnModelIndex = col.getModelIndex();
	    Vector data = model.getDataVector();
	    Vector colIds = model.getColumnIdentifiers();

	    // Remove the column from the table
	    table.removeColumn(col);

	    // Remove the column header from the table model
	    colIds.removeElementAt(columnModelIndex);

	    // Remove the column data
	    for (int r=0; r<data.size(); r++) {
	        Vector row = (Vector)data.get(r);
	        row.removeElementAt(columnModelIndex);
	    }
	    model.setDataVector(data, colIds);

	    // Correct the model indices in the TableColumn objects
	    // by decrementing those indices that follow the deleted column
	    Enumeration enu = table.getColumnModel().getColumns();
	    for (; enu.hasMoreElements(); ) {
	        TableColumn c = (TableColumn)enu.nextElement();
	        if (c.getModelIndex() >= columnModelIndex) {
	            c.setModelIndex(c.getModelIndex()-1);
	        }
	    }
	    model.fireTableStructureChanged();
	}
	
	private int getColumnID(char tmp) {
		int i;
		for(i=0;i<modelo.getColumnCount(); i++){
			if(modelo.getColumnName(i).charAt(0)==tmp)
				break;
		}
		return i;
	}

	public GUI() {
		setTitle("Automata finito no deterministico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 368);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEstados = new JTextField();
		txtEstados.setBounds(26, 82, 72, 28);
		contentPane.add(txtEstados);
		txtEstados.setColumns(10);
		
		JButton btnAEstados = new JButton(">>");
		btnAEstados.setBounds(103, 69, 51, 29);
		contentPane.add(btnAEstados);
		btnAEstados.addActionListener(this);
		btnAEstados.setActionCommand("btnAEstados");
		
		listaEstados = new DefaultListModel();
		lstEstados = new JList(listaEstados);
		lstEstados.setBounds(154, 46, 105, 109);
		contentPane.add(lstEstados);
		lstEstados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblEstados = new JLabel("Estados");
		lblEstados.setBounds(83, 19, 59, 16);
		contentPane.add(lblEstados);
		
		JButton btnEEstados = new JButton("<<");
		btnEEstados.setBounds(103, 96, 51, 29);
		contentPane.add(btnEEstados);
		btnEEstados.addActionListener(this);
		btnEEstados.setActionCommand("btnEEstados");
		
		JButton btnELenguaje = new JButton("<<");
		btnELenguaje.setBounds(103, 231, 51, 29);
		contentPane.add(btnELenguaje);
		btnELenguaje.addActionListener(this);
		btnELenguaje.setActionCommand("btnELenguaje");
		
		JButton btnALenguaje = new JButton(">>");
		btnALenguaje.setBounds(103, 204, 51, 29);
		contentPane.add(btnALenguaje);
		btnALenguaje.addActionListener(this);
		btnALenguaje.setActionCommand("btnALenguaje");
		
		txtLenguaje = new JTextField();
		txtLenguaje.setColumns(10);
		txtLenguaje.setBounds(26, 217, 72, 28);
		contentPane.add(txtLenguaje);
		
		listaLenguaje = new DefaultListModel();
		lstLenguaje = new JList(listaLenguaje);
		lstLenguaje.setBounds(154, 181, 105, 109);
		contentPane.add(lstLenguaje);
		lstLenguaje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblLenguaje = new JLabel("Lenguaje");
		lblLenguaje.setBounds(95, 161, 59, 16);
		contentPane.add(lblLenguaje);
		
		txtFinal = new JTextField();
		txtFinal.setText("QFinal");
		txtFinal.setColumns(10);
		txtFinal.setBounds(26, 298, 72, 28);
		contentPane.add(txtFinal);
		
		modelo = new DefaultTableModel();
	    tblTrans = new JTable(modelo);
	    JScrollPane sPane = new JScrollPane(tblTrans);
	    sPane.setBounds(316, 47, 248, 243);
	    modelo.addColumn("Edos/Sim");
	    contentPane.add(sPane);
		
		JLabel lblTransiciones = new JLabel("Transiciones");
		lblTransiciones.setBounds(394, 19, 89, 16);
		contentPane.add(lblTransiciones);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(476, 311, 117, 29);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("btnAceptar");
	}
}
