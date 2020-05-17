package Servidor.View;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		chat.setBounds(250, 20, 650, 370);
		chat.setFont(new Font("Arial", Font.PLAIN, 20));
		chat.setEditable(false);
		contentPane.add(chat);
		
		JTextArea digitacao = new JTextArea();
		JScrollPane scroll = new JScrollPane(digitacao);
		digitacao.setBounds(250, 410, 550, 110);
		digitacao.setFont(new Font("Arial", Font.PLAIN, 20));
		digitacao.setLineWrap(true);
		contentPane.add(digitacao);
				
		JTextArea conectados = new JTextArea();
		conectados.setBounds(20, 20, 220, 500);
		conectados.setEditable(false);
		contentPane.add(conectados);
		
		JButton enviar = new JButton("ENVIAR");
		enviar.setBounds(810, 410, 90, 110);
		enviar.setFont(new Font("Arial", Font.BOLD, 15));
		contentPane.add(enviar);
		
		
		basePane.add(contentPane);
	}
	
	
}
