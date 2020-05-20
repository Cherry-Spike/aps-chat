package Servidor.View;

import java.awt.Font;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class TelaChat {
	private JPanel basePane;
	private static JPanel contentPane;
	private static JTextArea chat;
	private JList<String> liUsuarios;
	
	public TelaChat(JPanel basePane, JPanel contentPane) throws IOException {
		setPane(basePane);
		setContentPane();
	}
	
	public void setPane(JPanel basePane) {
		this.basePane = basePane;
	}
	
	public void setContentPane() throws IOException{
		
		//Defini鈬o de Componentes
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 940, 580);
		
		chat = new JTextArea();
		getChat().setBounds(250, 20, 650, 370);
		getChat().setFont(new Font("Arial", Font.PLAIN, 20));
		getChat().setEditable(false);
		getChat().setLineWrap(true);
		JScrollPane scrollChat = new JScrollPane(getChat());
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollChat.setBounds(250, 20, 650, 370);
		contentPane.add(scrollChat);
		
		JTextArea digitacao = new JTextArea();
		digitacao.setBounds(250, 410, 550, 110);
		digitacao.setFont(new Font("Arial", Font.PLAIN, 20));
		digitacao.setLineWrap(true);
		JScrollPane scrollDig = new JScrollPane(digitacao);
		scrollDig.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollDig.setBounds(250, 410, 550, 110);
	    contentPane.add(scrollDig);
				
		JTextArea conectados = new JTextArea();
		conectados.setBounds(20, 20, 220, 500);
		conectados.setEditable(false);
		
		String[] test = {"teste", "teste2", "teste3"};
		liUsuarios = new JList<String>(test);
		liUsuarios.setBounds(25, 60, 150, 150);
		liUsuarios.setFont(new Font("Arial", Font.BOLD, 15));
		conectados.add(liUsuarios);
		
		JLabel lbConx = new JLabel("Conectados:");
		lbConx.setBounds(60, 13, 90, 40);
		lbConx.setFont(new Font("Arial", Font.BOLD, 15));
		conectados.add(lbConx);
		//contentPane.add(conectados);
		
		JScrollPane scrollConx = new JScrollPane(liUsuarios);
		scrollConx.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConx.setBounds(30, 60, 160, 420);
		conectados.add(scrollConx);
		contentPane.add(conectados);		
				
		JButton enviar = new JButton("ENVIAR");
		enviar.setBounds(810, 410, 90, 110);
		enviar.setFont(new Font("Arial", Font.BOLD, 15));
		contentPane.add(enviar);
		
		getChat().append("Porta " + 12345 + " aberta!\n");
		
		enviar.addActionListener(e -> {
			
			String msg = digitacao.getText();
			
				digitacao.setText("");
				getChat().append(msg + "\n");
				
			});
		    		
		basePane.add(contentPane);
		
	}

	/*GET & SET*/
	public static JTextArea getChat() {
		return chat;
	}
}
