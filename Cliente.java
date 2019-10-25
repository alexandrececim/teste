
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;

public class Cliente {

   public static void main(String[] args) {

    //String dialogo = callServidor("gtk-c4");
    //System.out.println("Dialogo: " + dialogo);
    teste();
   
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

  public static void teste(){
    Scanner digitado = new Scanner(System.in);
  String foi = "";
  
   System.out.println("################ Comandos ################");
   System.out.println("add Ticket Normal:       Digite .... 1  ");
   System.out.println("add Ticket Preferencial: Digite .... 2  ");
   System.out.println("Visualizar Chamada:      Digite .... *  ");
   System.out.println("Atender Fila:            Digite .... #  ");
   System.out.println("Sair do programa:        Digite ... -");
   System.out.println("##########################################");

do {

   //Ticket ticket = new Ticket();
   System.out.println("Adicinar na fila: ");
   
   foi = digitado.nextLine();
   //ticket.setTicketNormal(foi);
   if(foi.equals("-")){break;} 

  //Mostra a fila
    if(foi.equals("*")){
      System.out.println("####### Letreiro #######");

      String dialogo = callServidor("gtk-c5");
      System.out.println("Call Ticket: " + dialogo);

      continue;
    }
  //Muda o status do ticket(atendimento)
    if(foi.equals("#")){
      System.out.println("##### Altera Status #####");
    
      String dialogo = callServidor("gtk-c4");
      System.out.println("Call Ticket: " + dialogo);

      continue;
    } 


    if(foi.equals("1")){
    
      String dialogo = callServidor("gtk-c2");
      System.out.println("Call Ticket: " + dialogo);

    }
    if(foi.equals("2")){
    
      String dialogo = callServidor("gtk-c3");
      System.out.println("Call Ticket: " + dialogo);

    }

  // System.out.println("fila: "+fila.size());

  }while (foi.trim().equals("-") == false);

  // int xp = fila.size() - 1;
   System.out.println("******************");
   System.out.println("*Fim do Sistema*");
  }
   
}