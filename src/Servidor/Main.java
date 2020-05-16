package Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
	public static void main(String[] args) throws IOException {
    	
    	ServerSocket servidor = null;
    	Socket cliente;
    	int porta = 12345; 
    	
    	try {
    		
    		servidor = new ServerSocket(porta);
    		System.out.println("Porta " + porta + " aberta!");

            while (true) {
            	cliente = servidor.accept();
                String ipCliente = cliente.getInetAddress().getHostAddress();
                System.out.println("Nova conexção com o cliente " + ipCliente);
                new GerenciadorDeCliente(cliente);
            }

            
    	}catch (IOException e) {
    		
    		System.err.println("O servidor foi fechado");
    		servidor.close();
    		
    	}
    }
}
