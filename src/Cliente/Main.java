package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args){
		
		try {
			final Socket cliente = new Socket("127.0.0.1", 12345);
			System.out.println("O cliente se conectou ao servidor!");
			
			//lendo mensagems do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						
						while(true) {
							String mensagem = leitor.readLine();
							System.out.println("O Servidor disse: " + mensagem);
						}
						
					} catch (IOException e) {
						System.out.println("Nao e possivel receber a mensagem do servidor");
						e.printStackTrace();
					} 
				}
			}.start();
			
			//escrevendo mensagems para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream());
			BufferedReader leitorDoTerminal = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				String msgTerminal = leitorDoTerminal.readLine();
				escritor.println(msgTerminal);
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
