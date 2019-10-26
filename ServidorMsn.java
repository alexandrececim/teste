import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServidorMsn{
 
  //variavel que pega a chamada da fila
  private static String consultaChamada = "";

  public static void main(String[] args) {
   //System.out.println("Alouu");
    ArrayList<Ticket> fila = new ArrayList<Ticket>();
    ArrayList<String> foiChamado = new ArrayList<String>();

    //operando(12345, fila, ticket);
    operando(12345, fila, foiChamado);
  
 }

 private static void operando(int porta, ArrayList<Ticket> fila, ArrayList<String> foiChamado){
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

        protocoloMsn(msnCliente, fila, foiChamado);
        
      //resposta
      if(consultaChamada.isEmpty()){
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        saida.println("Servidor respondendo !");
      }else{
        PrintStream saida = new PrintStream(cliente.getOutputStream());

        saida.println(consultaChamada);
      }
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
  private static void protocoloMsn(String msn, ArrayList<Ticket> fila, ArrayList<String> foiChamado) {
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
       
      }

      if(msnParm.equals("c3")){
        
        ticket.addFilaPreferencial(fila);
      }

      if(msnParm.equals("c4")){
        ticket.atendeFila(fila, foiChamado); 
       
      }
      
      if(msnParm.equals("c5")){
        String callTicket = emAtendimento(foiChamado);
        //System.out.println("Elemento: " + callTicket);
        consultaChamada = callTicket;
        
      }
      System.out.println("####### Fila #######");

    //visualizar lista para teste do sistema  
    /* 
      int xt = fila.size();
      for(int i = 0; xt > i; i++){
           System.out.println("["+i+"] "+	fila.get(i).getTicketNormal() + " .... At.: " +	fila.get(i).getStatusAtendimento() + " .... Ch.: " +	fila.get(i).getTicketChamado());
      }
      System.out.println("******************");
      */
    //fim da visualização

    }else{
     System.out.println("Conexão não autorizada");
    }
    
 }

// Metodo que mostra o ticket chamado da lista de atendimento
private static String emAtendimento(ArrayList<String> foiChamado){
  int xt = foiChamado.size() - 1;
  String chamandoDaFila;
  if(xt > -1){
    chamandoDaFila = foiChamado.get(xt);
  }else{
    chamandoDaFila = "Atendimento não inicializado";
  }
  return chamandoDaFila;

 }

}
