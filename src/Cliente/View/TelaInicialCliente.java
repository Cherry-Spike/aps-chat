package Cliente.View;

import java.awt.Font;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class TelaInicialCliente{
	private JPanel basePane;
	ValidadorCliente validador = new ValidadorCliente();
	JFormattedTextField recebePorta;
    JFormattedTextField recebeIp;
	
	public TelaInicialCliente(JPanel basePane){
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
		
		JButton botao = new JButton("Confirmar");
		botao.setBounds(380, 410, 180, 60);
		botao.setFont(new Font("Arial", Font.BOLD, 25));
		contentPane.add(botao);
		
		JLabel porta = new JLabel("PORTA:");
		porta.setBounds(230, 140, 120, 100);
		porta.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(porta);
		
		JLabel ip = new JLabel("IP:");
		ip.setBounds(310, 180, 60, 100);
		ip.setFont(new Font("Arial", Font.BOLD, 30));
		contentPane.add(ip);
		
		//Action Listenes
		botao.addActionListener(e -> {			
			
			
			contentPane.setVisible(false);
			new TelaChatCliente(basePane, contentPane);
				
		});
		
		
		//Validador de caracteres		
		try {
			recebePorta = new JFormattedTextField(validador.MaskPorta());
			recebePorta.setBounds(370, 180, 200, 30);
	        recebePorta.setHorizontalAlignment(SwingConstants.CENTER);
	        contentPane.add(recebePorta);
		} catch (ParseException e) {
			System.out.println("Dados Invalidos");
			e.printStackTrace();
		}
                   
		try {
			recebeIp = new JFormattedTextField(validador.MaskIp());
			  recebeIp.setBounds(370, 220, 200, 30);
		      recebeIp .setHorizontalAlignment(SwingConstants.CENTER);
		      contentPane.add(recebeIp);
		        
		} catch (ParseException e) {
			System.out.println("Dados Invalidos");
			e.printStackTrace();
		}
      
		
		basePane.add(contentPane);
	}
	
	/*GET & SET*/
	
	
	
}
