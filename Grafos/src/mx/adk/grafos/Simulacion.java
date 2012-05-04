package mx.adk.grafos;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class Simulacion implements ActionListener{
	
	Automata grafo;
	private PickedState<Estado> seleccionados;
	JFrame frame;
	JButton boton = new JButton("Siguiente");
	String caso;
	String pasado = "";
	JTextField txtCaso;
	DefaultListModel descripcionesModel;
	JLabel succed;
	
	public Simulacion(Automata auto){
		grafo = auto;
		
	}
	
	
	public void simula(){
		JFrame panel = new JFrame();
		panel.setTitle("Simulaci—n");
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBounds(780, 700, 422, 270);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setContentPane(contentPane);
		contentPane.setLayout(null);
		descripcionesModel = new DefaultListModel();
		JList descripciones = new JList();
		contentPane.add(descripciones);
		descripciones.setModel(descripcionesModel);
		descripciones.setBounds(17, 102, 388, 91);
		succed = new JLabel();
		contentPane.add(succed);
		succed.setBounds(154, 36, 105, 10);
		txtCaso = new JTextField();
		contentPane.add(txtCaso);
		txtCaso.setText("Caso de prueba");
		txtCaso.setBounds(17, 69, 388, 22);
		panel.setVisible(true);
		contentPane.add(boton);
		boton.addActionListener(this);
		boton.setActionCommand("next");
		boton.setBounds(300, 210, 120, 30);
		
		
		seleccionados.clear();
		seleccionados.pick((Estado)grafo.getEstadoInicial(), true);
		
		
		
		System.out.println(pasado+grafo.getCurrNode()+caso);
	}
	
	
	
	
	public void print(Automata<Estado, Union> grafo) {
		Layout<Estado, Union> layout = new CircleLayout<Estado, Union>(grafo.getGraph());
		layout.setSize(new Dimension(300,300));
		VisualizationViewer<Estado, Union> vv =
				new VisualizationViewer<Estado, Union>(layout);
		vv.setPreferredSize(new Dimension(350,350));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Estado>());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Union>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.S);
		seleccionados = vv.getRenderContext().getPickedVertexState();
		
		
		DefaultModalGraphMouse<Estado, Union> gm = new DefaultModalGraphMouse<Estado, Union>();
		gm.setMode(Mode.PICKING);
		vv.setGraphMouse(gm);
		frame = new JFrame("Automata");
		frame.setBounds(780, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equalsIgnoreCase("next")){
			char sig = getNextLetter();
			if(sig=='$'){
				if(grafo.terminar()){
					succed.setText("Aceptado :D");
					succed.setForeground(Color.GREEN);
				}
				else{
					succed.setText("Rechazado :(");
					succed.setForeground(Color.RED);
				}
				boton.setEnabled(false);
				return;
			}
			State sigu = grafo.next(sig);
			seleccionados.clear();
			seleccionados.pick((Estado)sigu, true);
			System.out.println(pasado+grafo.getCurrNode()+caso);
			descripcionesModel.add(0, pasado+grafo.getCurrNode()+caso);
		}
	}


	private char getNextLetter() {
		if(caso==null){
			caso=txtCaso.getText();
			if(caso.charAt(caso.length()-1)!='$')
				caso+='$';
			txtCaso.setEnabled(false);
		}
		char siguienteLetra = caso.charAt(0);
		pasado+=siguienteLetra;
		caso = caso.substring(1, caso.length());
		return siguienteLetra;
	}

}
