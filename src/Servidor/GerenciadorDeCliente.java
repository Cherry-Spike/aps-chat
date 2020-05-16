package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GerenciadorDeCliente extends Thread {
	
	private Socket cliente;
	private String nomeCliente;

	public GerenciadorDeCliente(Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		try {
			
			BufferedReader leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			escritor.println("Qual e o seu nome?");
			String msg = leitor.readLine();
			this.nomeCliente = msg;
			
			while(true) {
				msg = leitor.readLine();
				escritor.println("Voce disse:" + msg);
			}
			
		} catch (IOException e) {
			
			System.err.println("O cliente fechou a conexao");
			e.printStackTrace();
			
		}
		super.run();
	}

}
