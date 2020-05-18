package Servidor.View;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
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
		chat.setBounds(250, 20, 650, 370);
		chat.setFont(new Font("Arial", Font.PLAIN, 20));
		chat.setEditable(false);
		chat.setLineWrap(true);
		JScrollPane scrollChat = new JScrollPane(chat);
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
		
		JLabel lbConx = new JLabel("Conectados:");
		lbConx.setBounds(25, 25, 90, 40);
		lbConx.setFont(new Font("Arial", Font.BOLD, 15));
		lbConx.setHorizontalAlignment(lbConx.CENTER);;
		conectados.add(lbConx);
		
		JScrollPane scrollConx = new JScrollPane(conectados);
		scrollConx.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConx.setBounds(20, 20, 220, 500);
		contentPane.add(scrollConx);
		
				
		JButton enviar = new JButton("ENVIAR");
		enviar.setBounds(810, 410, 90, 110);
		enviar.setFont(new Font("Arial", Font.BOLD, 15));
		contentPane.add(enviar);
		
		
		enviar.addActionListener(e -> {
			
			String msg = digitacao.getText();
			
				digitacao.setText("");
				chat.append(msg + "\n");
				
			});
		
		
		basePane.add(contentPane);
	}
	
		
	
}
