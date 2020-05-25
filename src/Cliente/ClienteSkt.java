package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Cliente.View.TelaChatCliente;
import Cliente.View.TelaInicialCliente;
import Servidor.BuscaUsuario;

public class ClienteSkt {

	private static Socket cliente;

	public static void main(String[] args){
		
		String IP = "127.0.0.1";
		int porta = TelaInicialCliente.getRecebePorta();
		
		try {
			cliente = new Socket(IP, porta);
			TelaChatCliente.getChat().append("Voce se conectou ao servidor!\n");
			
			//lendo mensagems do servidor
			new Thread() {
				@Override
				public void run() {
					try {
						
						BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						ObjectInputStream objEntrada = new ObjectInputStream(cliente.getInputStream());
						BuscaUsuario buscaUsuario = new BuscaUsuario();
											
						while(true) {
							String mensagem = leitor.readLine();
							TelaChatCliente.getChat().append(mensagem + "\n");
							System.out.println(mensagem);
							buscaUsuario = (BuscaUsuario) objEntrada.readObject();
							TelaChatCliente.setClienteLi(buscaUsuario.getListaUsuario());
						}
						
					} catch (IOException | ClassNotFoundException e) {
						TelaChatCliente.getChat().append("Nao e possivel receber a mensagem do servidor");
						System.out.println("Nao e possivel receber a mensagem do servidor");
						e.printStackTrace();
					} 
				}
			}.start();
			
			//escrevendo mensagems para o servidor
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true); /*PRESTAR ATENCAO NO AUTOFLUSH*/
			BufferedReader leitorDoTerminal = new BufferedReader(new InputStreamReader(System.in));
			String msgTerminal = "";
			
			//buscaUsuario.setListaUsuario(TelaChatCliente.getClienteLi());
			//buscaUsuario.adicionarNomeUsuario(msgTerminal);
			
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
