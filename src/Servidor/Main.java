package Servidor;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("null")
	public static void main(String[] args) throws IOException {
    	
    	ServerSocket servidor = null;
    	
    	try {
    		
    		servidor = new ServerSocket(12345);
    		System.out.println("Porta 12345 aberta!");
            Socket cliente = servidor.accept();
            String ipCliente = cliente.getInetAddress().getHostAddress();
            System.out.println("Nova conexção com o cliente " + ipCliente);

            Scanner scanner = new Scanner(cliente.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
                new GerenciadorDeCliente(cliente);
            }
            scanner.close();
            servidor.close();
            cliente.close();
            
    	}catch (IOException e) {
    		
    		System.err.println("O servidor foi fechado");
    		servidor.close();
    		
    	}
    }
}
