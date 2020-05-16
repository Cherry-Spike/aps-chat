package Servidor.View;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class TelaChat {
	private JPanel basePane;
	
	public TelaChat(JPanel basePane, JPanel contentPane){
		setPane(basePane);
		setContentPane();
	}
	
	public void setPane(JPanel basePane) {
		this.basePane = basePane;
	}
	
	public void setContentPane() {
		
		//Definição de Componentes
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 940, 580);
		
		JTextArea chat = new JTextArea();
		chat.setBounds(50, 940, 200, 200);
		
		JTextArea digitacao = new JTextArea();
		
		
		JTextArea contectados = new JTextArea();
		
	}
	
	
}
