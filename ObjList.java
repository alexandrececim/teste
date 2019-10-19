import java.util.Scanner;
import java.util.ArrayList;

public class ObjList{


 public static void main(String[] args){
  ArrayList<Ticket> fila = new ArrayList<Ticket>();
  Scanner digitado = new Scanner(System.in);
  String foi = "";
  
  do {
   Ticket ticket = new Ticket();
   System.out.println("Adicinar na fila: ");
   
   foi = digitado.nextLine();
   //ticket.setTicketNormal(foi);
   if(foi.equals("-")){break;} 

//Mostra a fila
   if(foi.equals("*")){
    System.out.println("####### Fila #######");
    int xt = fila.size();
    for(int i = 0; xt > i; i++){
         System.out.println("["+i+"] "+	fila.get(i).getTicketNormal() + " .... At.: " +	fila.get(i).getStatusAtendimento());
    }
    continue;
   }
//Muda o status do ticket(atendimento)
  if(foi.equals("#")){
    System.out.println("##### Altera Status #####");
    ticket.atendeFila(fila);

/*    int xt = fila.size();
    for(int i = 0; xt > i; i++){
	if(fila.get(i).getStatusAtendimento() == 0){
	 System.out.println("#"+
		fila.get(i).getTicketNormal()+
		" Atender ? s | n");
	 String vai = digitado.nextLine();
	 if(vai.equals("s")){
	  //fila.get(i).setStatusAtendimento(1);
      	 // ticket.atendeFilaNormal(fila);

	  break;

	 }else{continue;}
	}
         System.out.println("");
    } */
    continue;
   } 

   //System.out.println("Status: ");
   //int st = Integer.parseInt(digitado.nextLine());
   //ticket.setStatusAtendimento(st);
   //fila.add(ticket);

  if(foi.equals("1")){
   ticket.addFilaNormal(fila);
  }
  if(foi.equals("2")){
   ticket.addFilaPreferencial(fila);
  }
   System.out.println("fila: "+fila.size());
  }while (foi.trim().equals("-") == false);

   int xp = fila.size() - 1;
  // System.out.println("******************");
  // System.out.println("Tcket:  "+fila.get(0).getTicketNormal());
  // System.out.println("Status: "+fila.get(0).getStatusAtendimento());
   System.out.println("******************");
   System.out.println("*Fim do Sistema*");

 }  
}
