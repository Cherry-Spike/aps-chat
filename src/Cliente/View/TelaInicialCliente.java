package Cliente.View;

import java.awt.Font;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import Cliente.ClienteSkt;

public class TelaInicialCliente{

	private JPanel basePane;
	ValidadorCliente validador = new ValidadorCliente();
	private static JFormattedTextField recebePorta;
    private static JFormattedTextField recebeIp;
	
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
		
		//Action Listenes
		botao.addActionListener(e -> {			
			
			contentPane.setVisible(false);
			new TelaChatCliente(basePane, contentPane);
			
			new Thread() {
				@Override
				public void run() {
					new ClienteSkt();
					ClienteSkt.main(null); 
				}
			}.start();
				
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
		
		basePane.add(contentPane);
	}

	/*GET & SET*/
	
	public static int getRecebePorta() {
		return Integer.parseInt(recebePorta.getText());
	}

	public static String getRecebeIp() {
		return recebeIp.getText();
	}
	
}
