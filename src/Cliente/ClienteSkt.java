package Cliente;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JFrame;
import Cliente.View.BasePaneCliente;

public class ClienteSkt extends JFrame{

	private static final long serialVersionUID = 3105562055276069363L;
	private static Socket cliente;

	public static void main(String[] args){
		
		EventQueue.invokeLater(() ->{
	        try {
	        	BasePaneCliente frame = new BasePaneCliente();
	            frame.setVisible(true);
	        } catch (Exception e) {
	           
	        }
		});
		
		String IP = "127.0.0.1";
		int porta = 12345;				
		
		try {
			cliente = new Socket(IP, porta);
			System.out.println("Voce se conectou ao servidor!");
			
			//lendo mensagems do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println(mensagem);
						}
						
					} catch (IOException e) {
						System.out.println("Nao e possivel receber a mensagem do servidor");
						e.printStackTrace();
					} 
				}
			}.start();
			
			//escrevendo mensagems para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true); /*PRESTAR ATENCAO NO AUTOFLUSH*/
			BufferedReader leitorDoTerminal = new BufferedReader(new InputStreamReader(System.in));
			String msgTerminal = "";
			
			while((msgTerminal = leitorDoTerminal.readLine()) != null) {
				escritor.println(msgTerminal);
				if (msgTerminal.equalsIgnoreCase("/sair")) {
					System.exit(0);
				}
			}
			
		}catch (UnknownHostException e){			
			System.out.println("O endereco e invalido");
			e.printStackTrace();			
		}catch (IOException e){		
			System.out.println("O Servidor encontra-se indisponivel");
			e.printStackTrace();
		}
	}
}
