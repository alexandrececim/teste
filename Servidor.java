
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

    public static void main(String[] args) {

        try {
            // Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");
            while(true) {
              // o método accept() bloqueia a execução até que
              // o servidor receba um pedido de conexão
              Socket cliente = servidor.accept();
              System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
              Scanner entrada = new Scanner(cliente.getInputStream());
              System.out.println("Msn: " + entrada.nextLine());

              PrintStream saida = new PrintStream(cliente.getOutputStream());
              saida.println("Servidor respondendo !");
             
              cliente.close();
            }  
          }   
          catch(Exception e) {
             System.out.println("Erro: " + e.getMessage());
          }
    }
}