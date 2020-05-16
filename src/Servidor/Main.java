package Servidor;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import View.BasePane;

public class Main {
    public static void main(String[] args) throws IOException {
        
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");
        Socket cliente = servidor.accept();

        String ipCliente = cliente.getInetAddress().getHostAddress();
        System.out.println("Nova conexção com o cliente " + ipCliente);

        Scanner scanner = new Scanner(cliente.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        servidor.close();
        cliente.close();
        
    }
}
