package mx.adk.grafos;

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



public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	public boolean listo = false;
	private JTextField txtEstados;
	private JTextField txtLenguaje;
	private JTextField txtinicial;
	private JTable tblTrans;
	private JList lstEstados;
	private JList lstLenguaje;
	DefaultTableModel modelo;
	JScrollPane sPane;

	DefaultListModel listaEstados;
	TreeSet<String> setEstados = new TreeSet<String>();
	
	DefaultListModel listaLenguaje;
	TreeSet<Character> setLenguaje = new TreeSet<Character>();
	private JTextField qFinal;
	
	private DefaultTableModel removeCol(int id){
		DefaultTableModel tmp = new DefaultTableModel();
		int columnas = modelo.getColumnCount();
		for(int i=0;i<columnas;i++){
			if(i!=id)
				tmp.addColumn(modelo.getColumnName(i));
		}
		int rows = modelo.getRowCount();
		String datos[] = new String[columnas-1];
		for(int row=0;row<rows;row++){
			for(int col=0,sel=0;col<columnas;col++,sel++){
				if(col!=id)
					datos[sel] = (String) modelo.getValueAt(row, col);
				else
					sel--;
			}
			tmp.addRow(datos);
		}
		return tmp;
		
	}
	
	

	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equalsIgnoreCase("btnAEstados")){
			String tmp = txtEstados.getText();
			if(tmp==""||tmp.length()==0)
				return;
			if(setEstados.add(tmp)){
				listaEstados.addElement(tmp);
				String[] data = new String[1];
				data[0] = tmp;
				modelo.addRow(data);
				txtEstados.setText("");
				txtEstados.requestFocus();
			}
			return;
		}
		if(comando.equalsIgnoreCase("btneestados")){
			String tmp = (String) lstEstados.getSelectedValue();
			setEstados.remove(tmp);
			listaEstados.removeElement(tmp);
			int i=0;
			for(i=0;i<modelo.getRowCount();i++){
				if(modelo.getValueAt(i, 0)==tmp){
					break;
				}
			}
			modelo.removeRow(i);
		}
		if(comando.equalsIgnoreCase("btnALenguaje")){
			if(txtLenguaje.getText().length()==0)
				return;
			char tmp = txtLenguaje.getText().charAt(0);
			if(tmp>32)
				if(setLenguaje.add(tmp)){
					listaLenguaje.addElement(tmp);
					modelo.addColumn(tmp);
					txtLenguaje.setText("");
					txtLenguaje.requestFocus();
				}
			return;
		}
		if(comando.equalsIgnoreCase("btnelenguaje")){
			char tmp = (char) lstLenguaje.getSelectedValue().toString().charAt(0);
			setLenguaje.remove(tmp);
			listaLenguaje.removeElement(tmp);
			int i=0;
			while(true){
				if(modelo.getColumnName(i).charAt(0)==tmp)
					break;
				i++;
			}
			DefaultTableModel mo = removeCol(i);
			tblTrans = null;
			contentPane.remove(sPane);
			sPane = null;
			tblTrans = new JTable(mo);
			sPane = new JScrollPane(tblTrans);
		    sPane.setBounds(316, 47, 248, 243);
		    contentPane.add(sPane);
		    return;
		}
		if(comando.equalsIgnoreCase("btnAceptar")){
			String bla = (qFinal.getText()).replaceAll(" ", "");
			String estados[] =  bla.split(",");

			Procesa main = new Procesa(setEstados, setLenguaje, modelo, txtinicial.getText(), estados);
		}
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
		
		txtinicial = new JTextField();
		txtinicial.setText("QInicial");
		txtinicial.setColumns(10);
		txtinicial.setBounds(26, 298, 72, 28);
		contentPane.add(txtinicial);
		
		modelo = new DefaultTableModel();
	    tblTrans = new JTable(modelo);
	    sPane = new JScrollPane(tblTrans);
	    sPane.setBounds(316, 47, 248, 243);
	    modelo.addColumn("Edos/Sim");
	    modelo.addColumn("Ep");
	    contentPane.add(sPane);
		
		JLabel lblTransiciones = new JLabel("Transiciones");
		lblTransiciones.setBounds(394, 19, 89, 16);
		contentPane.add(lblTransiciones);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(476, 311, 117, 29);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(this);
		btnAceptar.setActionCommand("btnAceptar");
		
		qFinal = new JTextField();
		qFinal.setText("qFinal1,\u2026, qFinalN");
		qFinal.setBounds(126, 298, 186, 28);
		contentPane.add(qFinal);
		qFinal.setColumns(10);
	}
}
