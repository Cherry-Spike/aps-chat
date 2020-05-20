package Servidor;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import Servidor.View.BasePane;
import Servidor.View.TelaChat;

public class ServidorSkt extends JFrame{
	
public static void main(String[] args) throws IOException {
	
		EventQueue.invokeLater(() ->{
	        try {
	        	BasePane frame = new BasePane();
	            frame.setVisible(true);
	        } catch (Exception e) {
	           
	        }
		});
				
    	ServerSocket servidor = null;
    	Socket cliente;
    	  	
    	try {
    		
    		servidor = new ServerSocket(12345);
    		System.out.println("Porta " + 12345 + " aberta!");

            while (true) {
            	cliente = servidor.accept();
                String ipCliente = cliente.getInetAddress().getHostAddress();
                TelaChat.getChat().append("Nova conexao com o cliente " + ipCliente + "\n");
                new GerenciadorDeCliente(cliente);
            }

            
    	}catch (IOException e) {
    		
    		System.err.println("O servidor foi fechado");
    		servidor.close();
    		
    	}
    }
}
