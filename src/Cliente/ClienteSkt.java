package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.DefaultListModel;
import com.google.gson.Gson;
import Cliente.View.TelaChatCliente;
import Cliente.View.TelaInicialCliente;

public class ClienteSkt {

	private static Socket cliente;
	private static Gson gson;
	private static ClienteJson json; 

	public static void main(String[] args){
		
		String IP = "127.0.0.1";
		int porta = TelaInicialCliente.getRecebePorta();
		
		try {
			cliente = new Socket(IP, porta);
			TelaChatCliente.getChat().append("Voce se conectou ao servidor!\n");
			
			/*lendo mensagems do servidor*/
			new Thread() {
				@Override
				public void run() {
					try {
						
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						gson = new Gson();
											
						while(true) {
							String mensagem = leitor.readLine();
							json = gson.fromJson(mensagem, ClienteJson.class);
							
							if(json.getTipo() == 1) {
								for (String msg : json.getMensagem()) {
									TelaChatCliente.getChat().append(msg);							
								}
							}else if(json.getTipo() == 2) {
								((DefaultListModel<String>) TelaChatCliente.getClienteLi().getModel()).removeAllElements();
								for (String msg : json.getMensagem()) {									
									((DefaultListModel<String>) TelaChatCliente.getClienteLi().getModel()).addElement(msg);
								}
							}else if(json.getTipo() == 3) {
								PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
								escritor.println("teste");
							}
							
							System.out.println(mensagem);
						}
						
					} catch (IOException e) {
						TelaChatCliente.getChat().append("Nao e possivel receber a mensagem do servidor");
						System.out.println("Nao e possivel receber a mensagem do servidor");
						e.printStackTrace();
					} 
				}
			}.start();
			
			/*escrevendo mensagems para o servidor*/
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
