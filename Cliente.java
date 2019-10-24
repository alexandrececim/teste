
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;

public class Cliente {

   public static void main(String[] args) {

    String dialogo = callServidor("gtk-c4");
    System.out.println("Dialogo: " + dialogo);
   
   }
   private static String callServidor(String msn){
     String respostaServidor = null;
    try {
      Socket cliente = new Socket("localhost",12345);
      
      PrintStream saida = new PrintStream(cliente.getOutputStream());
      //Scanner teclado = new Scanner(System.in);
      //System.out.println("Digita ai:");
      

     // while (teclado.hasNextLine()) {
          saida.println(msn);
          //saida.close();
          Scanner entrada = new Scanner(cliente.getInputStream());
          respostaServidor = entrada.nextLine();
          //System.out.println("Msn-Servidor: " + entrada.nextLine());
          saida.close();
          cliente.close();
     // }
     
      //cliente.close();
      System.out.println("Conexão encerrada");
      
    }
    catch(Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
    return respostaServidor;
   } 
}