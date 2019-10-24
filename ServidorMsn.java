import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.ArrayList;
import Ticket;

public class ServidorMsn{
 

  public static void main(String[] args) {
   //System.out.println("Alouu");
    ArrayList<Ticket> fila = new ArrayList<Ticket>();
    

    //operando(12345, fila, ticket);
    operando(12345, fila);
  
 }

 private static void operando(int porta, ArrayList<Ticket> fila){
  try {
    // Instancia o ServerSocket ouvindo a porta 12345
    ServerSocket servidor = new ServerSocket(porta);
    System.out.println("Servidor ouvindo a porta 12345");
    while(true) {
      
      Socket cliente = servidor.accept();
      System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
      Scanner entrada = new Scanner(cliente.getInputStream());
      String msnCliente = entrada.nextLine();
      System.out.println("Msn: " + msnCliente);

        protocoloMsn(msnCliente, fila);

        PrintStream saida = new PrintStream(cliente.getOutputStream());
        saida.println("Servidor respondendo !");

        cliente.close();
        entrada.close();
      }
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

  /*
   * Metodo que le a mensagem e compara a permisão e o tipo gtk - cabeçalho de
   * Permição de conexão tipo - Comando para:
   *  1) c1 - Zera a lista de  Atendimento(renicia a contagem dos ticket's). 
   *  2) c2 - Cria um ticket normal.
   *  3) c3 - Cria um ticket preferencial. 
   *  4) c4 - Altera status de atendimento (chamada para proximo ticket). 
   *  5) c5 - Retorna o ticket em atendimento.
   */
  private static void protocoloMsn(String msn, ArrayList<Ticket> fila) {
    System.out.println("Chegou na leitura do protocolo.");
    Ticket ticket = new Ticket();
    String[] protocoloMsn = msn.split("-");
    
    String msnHead = protocoloMsn[0];
    String msnParm = protocoloMsn[1];

    System.out.println("Protocolo[0] = " + msnHead);
    System.out.println("Protocolo[1] = " + msnParm);
    
    if(msnHead.equals("gtk")){

      if(msnParm.equals("c1")){ 
        fila.clear();
        
      }

      if(msnParm.equals("c2")){
        
        ticket.addFilaNormal(fila);
        String teste = emAtendimento(fila);
        System.out.println("Elemento: " + teste);
      }

      if(msnParm.equals("c3")){
        
        ticket.addFilaPreferencial(fila);
      }

      if(msnParm.equals("c4")){
        ticket.atendeFila(fila); 
      }
      
      if(msnParm.equals("c5")){
        //aqui o servidor lança uma mensagem de retorno
        
      }
      
    }else{
     System.out.println("Conexão não autorizada");
    }
    
 }

// Metodo que mostra o ticket chamado da lista de atendimento
  private static String emAtendimento(ArrayList<Ticket> fila){
    int xt = fila.size();
    int ticketEmAtendimento;
    String chamadaFila = "";
  // for inverso para pegar a ultima alteração da lista
    for(int i = xt - 1; i >= 0; i++){
    ticketEmAtendimento = fila.get(i).getTicketChamado();
    System.out.println("A boolean: " + fila.get(i).getTicketChamado());
    if(ticketEmAtendimento == 1){
      String pref = fila.get(i).getTicketPreferencial();
     // if(isEmpty(fila.get(i).getTicketPreferencial())){
      if(pref.isEmpty()){
        chamadaFila = fila.get(i).getTicketPreferencial();
        
      }else{
        chamadaFila = fila.get(i).getTicketNormal();
        
      }
    }
  }
    return chamadaFila;

  }

}
