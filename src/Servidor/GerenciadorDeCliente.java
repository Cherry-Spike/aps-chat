package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import Servidor.View.TelaChat;

public class GerenciadorDeCliente extends Thread {
	
	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private BuscaCliente buscaCliente = new BuscaCliente();
	private static final Map<String, GerenciadorDeCliente> usuarios = new HashMap<String, GerenciadorDeCliente>();

	public GerenciadorDeCliente(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		
		/*INICIALIZACAO DO MODELO DE ESCRITA E LEITURA DE MENSAGENS ENTRE CLIENTES*/
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true); /*PRESTAR ATENCAO NO AUTOFLUSH*/
			escritor.println("Qual e o seu nome?");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			escritor.println("Bem vindo (" + this.nomeCliente + ")" + " escreva alguma coisa!");
			usuarios.put(this.nomeCliente, this);
			buscaCliente.setListaCliente(TelaChat.getLiUsuarios());
			buscaCliente.adicionarNomeCliente(nomeCliente);
			
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
					GerenciadorDeCliente destinatario = usuarios.get(nomeDestinatario);
					
					if (destinatario == null) {
						escritor.println("Usuario inexistente");
					}else {
						destinatario.getEscritor().println("(" + this.nomeCliente + ") disse: " + mensagemPrivada[1]);
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
