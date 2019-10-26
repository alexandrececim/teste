

public class VisualizarFila{

    public VisualizarFila(){
        chamada();
    }


public String chamada(){
    CallServer cs = new CallServer();
    return cs.callServidor("gtk-c5");
}

    
}
