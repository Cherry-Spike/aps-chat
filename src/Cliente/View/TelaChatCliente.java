package Cliente.View;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class TelaChatCliente {
	
	private JPanel basePane;
	private static JPanel contentPane;
	private static JTextArea chat;
	private static JList<String> liUsuarios;
	
	public TelaChatCliente(JPanel basePane, JPanel contentPane){
		setPane(basePane);
		setContentPane();
	}
	
	public void setPane(JPanel basePane) {
		this.basePane = basePane;
	}
	
	public void setContentPane(){
		
		//Defini鈬o de Componentes
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBounds(0, 0, 940, 580);
		
		chat = new JTextArea();
		getChat().setBounds(250, 20, 650, 370);
		getChat().setFont(new Font("Arial", Font.PLAIN, 20));
		getChat().setBorder(new LineBorder(Color.LIGHT_GRAY));
		getChat().setEditable(false);
		getChat().setLineWrap(true);
		JScrollPane scrollChat = new JScrollPane(getChat());
		scrollChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollChat.setBounds(250, 20, 650, 370);
		contentPane.add(scrollChat);
		
		JTextArea digitacao = new JTextArea();
		digitacao.setBounds(250, 410, 550, 110);
		digitacao.setFont(new Font("Arial", Font.PLAIN, 20));
		digitacao.setBorder(new LineBorder(Color.LIGHT_GRAY));
		digitacao.setLineWrap(true);
		JScrollPane scrollDig = new JScrollPane(digitacao);
		scrollDig.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollDig.setBounds(250, 410, 550, 110);
	    contentPane.add(scrollDig);
				
		JTextArea conectados = new JTextArea();
		conectados.setBounds(20, 20, 220, 500);
		conectados.setBorder(new LineBorder(Color.LIGHT_GRAY));
		conectados.setEditable(false);
		
		liUsuarios = new JList<String>(new DefaultListModel<String>());
		liUsuarios.setBounds(25, 60, 150, 150);
		liUsuarios.setFont(new Font("Arial", Font.BOLD, 15));
		liUsuarios.setBorder(new LineBorder(Color.BLACK));
		conectados.add(liUsuarios);
		
		JLabel lbConx = new JLabel("Conectados:");
		lbConx.setBounds(60, 13, 90, 40);
		lbConx.setFont(new Font("Arial", Font.BOLD, 15));
		
		conectados.add(lbConx);
		//contentPane.add(conectados);
		
		JScrollPane scrollConx = new JScrollPane(liUsuarios);
		scrollConx.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollConx.setBorder(new LineBorder(Color.BLACK));
		scrollConx.setBounds(0, 60, 220, 440);
		conectados.add(scrollConx);
		contentPane.add(conectados);		
				
		JButton enviar = new JButton("ENVIAR");
		enviar.setBounds(810, 410, 90, 110);
		enviar.setFont(new Font("Arial", Font.BOLD, 15));
		contentPane.add(enviar);
		
		enviar.addActionListener(e -> {
			
			String msg = digitacao.getText();
			
			if(msg.isEmpty()) {
				digitacao.setText("");
			}else {
				digitacao.setText("");
				getChat().append(msg + "\n");
			}
				
		});
		    		
		basePane.add(contentPane);
		
	}

	/*GET & SET*/
	public static JTextArea getChat() {
		return chat;
	}

	public static JList<String> getLiUsuarios() {
		return liUsuarios;
	}
}
