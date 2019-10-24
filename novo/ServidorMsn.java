import java.net.ServerSocket;

public class ServidorMsn{

 private ArrayList<Ticket> fila = new ArrayList<Ticket>(); 
 Ticket ticket = new Ticket();

 public static void main(String[] args){
  
  try{
   ServerSocket servidor = new ServerSocket(3415);
   System.out.println("Servidor rodando..");
   Socket cliente = servidor.accept();
   Scanner inMsn = new Scanner(cliente.getInputStream());
   while(inMsn.hasNextLine()){
    //Atividade dos metodos
     String msn = inMsn.hasNextLine();
     protocoloMsn(msn);
   }
   inMsn.close();
  }catch(){
   System.out.println("Erro ao criar o Servidor.");
  }
 }
/*
* Metodo que le a mensagem e compara a permisão e o tipo
* gtk 	- cabeçalho de Permição de conexão
* tipo 	- Comando para:
*	 1) c1 - Zera a lista de Atendimento(renicia a contagem dos ticket's).
*	 2) c2 - Cria um ticket normal.
*	 3) c3 - Cria um ticket preferencial.
*	 4) c4 - Altera status de atendimento (chamada para proximo ticket).
*	 5) c5 - Retorna o ticket em atendimento.
*/
 private static void protocoloMsn(String msn){
  
  String protocolo = new String[2]; 
  protocolo = msn.split("-");
  if(protocolo[0].equals("gtk")){

   if(protocolo[1].equals("c1")){ 
    fila.clear();
   }

   if(protocolo[1].equals("c2")){
    ticket.addFilaNormal(fila);
   }

   if(protocolo[1].equals("c3")){
    ticket.addFilaPreferencial(fila);
   }

   if(protocolo[1].equals("c4")){
    ticket.atendeFila(fila); 
   }
   if(protocolo[1].equals("c5")){
    //aqui o servidor lança uma mensagem de retorno
    
   }

  }else{
   System.out.println("Conexão não autorizada.");
  }

 }

// Metodo que mostra o ticket chamado da lista de atendimento
 private String emAtendimento(ArrayList<Ticket> fila){
  int xt = fila.size();
// for inverso para pegar a ultima alteração da lista
  for(int i = xt - 1; i >= 0; i++){
   boolean ticketEmAtendimento = fila.get(i).getTicketChamada();
   if(ticketEmAtendimento == True){
    
     if(isEmpty(fila.get(i).getTicketPreferencial())){
      String str = fila.get(i).getTicketPreferencial();
      return str;
     }else{
      String str = fila.get(i).getTicketNormal();
      return str;
     }
   }
 }

}
