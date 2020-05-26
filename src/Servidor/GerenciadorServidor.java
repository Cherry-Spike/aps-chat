package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import Servidor.View.TelaChat;

public class GerenciadorServidor extends Thread {
	
	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	private static Gson gson;
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
			gson = new Gson();
			
			enviarMensagem(1, "Qual e o seu nome?", escritor);
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			usuarios.put(this.nomeCliente, this);
			buscaUsuario.setListaUsuario(TelaChat.getLiUsuarios());
			buscaUsuario.adicionarNomeUsuario(nomeCliente);
			
			enviarMensagemGlobal(false, "***(" + this.nomeCliente + ")" + " entrou na sala!***");
			enviarMensagem(1, "Utilize {/ajuda} sem {} para vizualizar os comandos disponiveis.\n\n" 
							+ "Bem vindo (" + this.nomeCliente + "), digite uma mensagem!\n", escritor);
			
			new Thread() {
				public void run() {
					while(true) {
						ServerJson listaUsuarios = new ServerJson(2);
						
						for(int i = 0; i < buscaUsuario.getListaUsuario().getModel().getSize(); i++) {
							String item = buscaUsuario.getListaUsuario().getModel().getElementAt(i);
							listaUsuarios.setMensagem(item);
						}
						escritor.println(gson.toJson(listaUsuarios));
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}					
				}
			}.start();
				
			while(true) {
				
				msg = leitor.readLine();
			
				/* COMANDO SAIR */
				if (msg.equalsIgnoreCase("/sair")) {
					this.cliente.close();
				}
				/* COMANDO DE AJUDA */
				else if(msg.equalsIgnoreCase("/ajuda")) {
					enviarMensagem(1, "\nComandos disponiveis:", escritor);
					enviarMensagem(1, "{/<nome>} mensagem privada para o usuario.", escritor);
					enviarMensagem(1, "{/sair} comando para se desconectar.\n", escritor);
				}
				/* COMANDO DE MENSAGEM PRIVADA */
				else if(msg.startsWith("/") && msg.contains(" ")){
					
					String[] mensagemPrivada = msg.split(" ", 2);
					String nomeDestinatario = mensagemPrivada[0].substring(mensagemPrivada[0].lastIndexOf("/") + 1);
					enviarMensagem(1, "(" + this.nomeCliente + ") disse para (" + nomeDestinatario + "): " + mensagemPrivada[1], escritor);
					GerenciadorServidor destinatario = usuarios.get(nomeDestinatario);
					
					if (destinatario == null) {
						enviarMensagem(1, "Usuario inexistente", escritor);
					}else {
						ServerJson json = new ServerJson(1);
						json.setMensagem("(" + this.nomeCliente + ") disse para voce: " + mensagemPrivada[1] + "\n");
						destinatario.getEscritor().println(gson.toJson(json));
					}
					
				}
				else if(msg.startsWith("/")){
					enviarMensagem(1, "Comando nao reconhecido", escritor);
				}
				else{															
					enviarMensagemGlobal(true, msg);				
				}
				
			}
			
			
		} catch (IOException e) {
			
			System.err.println("O cliente fechou a conexao");
			buscaUsuario.removeUsuario(TelaChat.getLiUsuarios(), nomeCliente);
			String ip = cliente.getInetAddress().getHostAddress();
			TelaChat.getChat().append("O cliente " + ip + " fechou a conexao\n");
			
		}
	}
	
	private void enviarMensagem(int tipo, String mensagem, PrintWriter escritor) {
		ServerJson json = new ServerJson(tipo);
		json.setMensagem(mensagem + "\n");
		escritor.println(gson.toJson(json));
	}
	
	public void enviarMensagemGlobal(boolean name, String mensagem) {
		GerenciadorServidor destinatario;
		ServerJson json = new ServerJson(3);
		for(int i = 0; i < buscaUsuario.getListaUsuario().getModel().getSize(); i++) {
			String nomeDestinatario = buscaUsuario.getListaUsuario().getModel().getElementAt(i);
			destinatario = usuarios.get(nomeDestinatario);
			if(name == true) {
				json.setMensagem("(" + this.nomeCliente + ") disse: " + mensagem + "\n");				
			}else {
				json.setMensagem(mensagem + "\n");
			}
			destinatario.getEscritor().println(gson.toJson(json));						
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
