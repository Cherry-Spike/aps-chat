package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteSkt {

	public static void main(String[] args){
		
		String IP = "127.0.0.1";
		int porta = 12345;
		
		try {
			final Socket cliente = new Socket(IP, porta);
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
