package mx.adk.grafos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.TreeSet;

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtEstados;
	private JTextField txtLenguaje;
	private JTextField txtFinal;
	private JTable tblTrans;
	private JList lstEstados;
	private JList lstLenguaje;

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
			}
			return;
		}
		if(comando.equalsIgnoreCase("btnelenguaje")){
			char tmp = (char) lstLenguaje.getSelectedValue().toString().charAt(0);
			setLenguaje.remove(tmp);
			listaLenguaje.removeElement(tmp);
		}
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
		
		tblTrans = new JTable();
		tblTrans.setBounds(316, 47, 248, 243);
		contentPane.add(tblTrans);
		
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
