
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

   public static void main(String[] args) {

    try {
        Socket cliente = new Socket("localhost",12345);
        
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digita ai:");
        

        while (teclado.hasNextLine()) {
            saida.println(teclado.nextLine());
            //saida.close();
            Scanner entrada = new Scanner(cliente.getInputStream());
            System.out.println("Msn-Servidor: " + entrada.nextLine());
            cliente.close();
        }
       
        //cliente.close();
        System.out.println("Conexão encerrada");
      }
      catch(Exception e) {
        System.out.println("Erro: " + e.getMessage());
      }
   } 
}