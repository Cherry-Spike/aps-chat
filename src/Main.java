import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        ServerSocketcket servidor = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta!");
        Socketocket cliente = servidor.accept();

        String ipCliente = cliente.getInetAddress().getHostAddress();
        System.out.println("Nova conexão com o cliente " + ipCliente);

        Scanner scanner = new Scanner(cliente.getInputStream());
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
        servidor.close();
        cliente.close();
    }
}
