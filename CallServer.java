import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.lang.*;
class CallServer{

    public static String callServidor(String msn){
        String respostaServidor = null;
       try {
         Socket cliente = new Socket("localhost",12345);
         
         PrintStream saida = new PrintStream(cliente.getOutputStream());
        
             saida.println(msn);
             
             Scanner entrada = new Scanner(cliente.getInputStream());
             respostaServidor = entrada.nextLine();
             saida.close();
             cliente.close();
            //System.out.println("Conexão encerrada");
         
       }
       catch(Exception e) {
         System.out.println("Erro: " + e.getMessage());
       }
       return respostaServidor;
      }    

}