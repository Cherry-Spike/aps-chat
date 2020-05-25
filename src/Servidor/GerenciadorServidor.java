package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import Servidor.View.TelaChat;

public class GerenciadorServidor extends Thread {
	
	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private ObjectOutputStream objSaida;
	private BuscaUsuario buscaUsuario = new BuscaUsuario();
	private static final Map<String, GerenciadorServidor> usuarios = new HashMap<String, GerenciadorServidor>();

	public GerenciadorServidor(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		
		/*INICIALIZACAO DO MODELO DE ESCRITA E LEITURA DE MENSAGENS ENTRE CLIENTES*/
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true); /*PRESTAR ATENCAO NO AUTOFLUSH*/
			objSaida = new ObjectOutputStream(cliente.getOutputStream());
			escritor.println("Qual e o seu nome?");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			escritor.println("Bem vindo (" + this.nomeCliente + ")" + " escreva alguma coisa!");
			usuarios.put(this.nomeCliente, this);
			buscaUsuario.setListaUsuario(TelaChat.getLiUsuarios());
			buscaUsuario.adicionarNomeUsuario(nomeCliente);
			objSaida.writeObject(buscaUsuario);
			
			while(true) {
				
				msg = leitor.readLine();
				
				/* COMANDO SAIR */
				if (msg.equalsIgnoreCase("/sair")) {
					this.cliente.close();
				}
				/* COMANDO DE MENSAGEM PRIVADA */
				else if(msg.startsWith("/") && msg.contains(" ")){
					
					String[] mensagemPrivada = msg.split(" ", 2);
					String nomeDestinatario = mensagemPrivada[0].substring(mensagemPrivada[0].lastIndexOf("/") + 1);
					escritor.println("(" + this.nomeCliente + ") disse para (" + nomeDestinatario + "): " + mensagemPrivada[1]);
					GerenciadorServidor destinatario = usuarios.get(nomeDestinatario);
					
					if (destinatario == null) {
						escritor.println("Usuario inexistente");
					}else {
						destinatario.getEscritor().println("(" + this.nomeCliente + ") disse: " + mensagemPrivada[1] + "\n");
					}
					
				}
				else if(msg.startsWith("/")){
					escritor.println("Comando nao reconhecido");
				}else{
					escritor.println(nomeCliente + " disse: " + msg);
				}
				
			}
			
		} catch (IOException e) {
			
			System.err.println("O cliente fechou a conexao");
			buscaUsuario.removeUsuario(TelaChat.getLiUsuarios(), nomeCliente);
			//buscaUsuario.removeUsuario(TelaChatCliente.getLiUsuarios(), nomeCliente);
			String ip = cliente.getInetAddress().getHostAddress();
			TelaChat.getChat().append("O cliente " + ip + " fechou a conexao\n");
			
		}
	}
	
	/* GET & SET*/
	public PrintWriter getEscritor() {
		return escritor;
	}
	
	public String getNomCliente() {
		return nomeCliente;
	}
	
}
