public class CallTicket{

       private String callServidor(String msn){
        String respostaServidor = null;
       try {
         Socket cliente = new Socket("localhost",12345);
         
         PrintStream saida = new PrintStream(cliente.getOutputStream());
         saida.println(msn);
            
         Scanner entrada = new Scanner(cliente.getInputStream());
             respostaServidor = entrada.nextLine();
         saida.close();
         cliente.close();
        
       }
       catch(Exception e) {
         System.out.println("Erro: " + e.getMessage());
       }
       return respostaServidor;
      } 


}
