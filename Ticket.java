import java.util.ArrayList;

public class Ticket{

 private String ticketNormal;
 private String ticketPreferencial;
 private int statusAtendimento;

 
 public String getTicketNormal(){
  return ticketNormal;
 }
 private void setTicketNormal(String newTicket){
  ticketNormal = newTicket;
 }

public String getTicketPreferencial(){
  return ticketNormal;
 }
 private void setTicketPreferencial(String newTicket){
  ticketNormal = newTicket;
 }

 public int getStatusAtendimento(){
  return statusAtendimento;
 }
 private void setStatusAtendimento(int x){
  statusAtendimento = x;
 }
//Metodo que adiciona o ticket na fila Normal
 public void addFilaNormal(ArrayList<Ticket> fila){
  Ticket ticket = new Ticket();
  int totalFila = fila.size() + 1;//Adicionado evitando o 'N0000'
  String str = String.format ("%04d", totalFila);
  ticket.setTicketNormal("N"+str);
  ticket.setStatusAtendimento(0);
  fila.add(ticket);
 }

//Metodo que adiciona o ticket na fila Preferencial
public void addFilaPreferencial(ArrayList<Ticket> fila){
  Ticket ticket = new Ticket();
  int totalFila = fila.size() + 1;//Adicionado evitando $
  String str = String.format ("%04d", totalFila);
  ticket.setTicketPreferencial("P"+str);
  ticket.setStatusAtendimento(0);
  fila.add(ticket);
 } 

 public void atendeFila(ArrayList<Ticket> fila){
  int tamanhoFila = fila.size();
  int contaFila = 0;
 // for(int i = 0; xt > i; i++){
 do{
    int status = fila.get(contaFila).getStatusAtendimento();
    String tipoTicket = fila.get(contaFila).getTicketPreferencial().substring(0,1);
    if(status == 0 && tipoTicket.equals("P")){
      fila.get(contaFila).setStatusAtendimento(1);
      break;
    }
    contaFila++;
   }while (tamanhoFila > contaFila);

    for(int i = 0; tamanhoFila > i; i++ ){
     int status = fila.get(i).getStatusAtendimento();
     String tipoTicket = fila.get(i).getTicketNormal().substring(0,1);
     if(status == 0 && tipoTicket.equals("N")){
        fila.get(i).setStatusAtendimento(1);
        break;
     }
    }
  
 }
 /*
 public void atendeFilaPreferencial(ArrayList<Ticket> fila){
  int xt = fila.size();
  for(int i = 0; xt > i; i++){
    if(fila.get(i).getStatusAtendimento() == 0){
      fila.get(i).setStatusAtendimento(1);
      break;
   }
  } 
 }
  */
}
