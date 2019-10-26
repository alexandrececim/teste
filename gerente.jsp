<html>
    <head>
        <title>Gerente</title>
    </head>
    <body>
        <h1>Controle de chamadas</h1>

        <%@ page import = "classes.*" %>
        
        <% 
            VisualizarFila vf = new VisualizarFila(); //VisualizarFila
            String chamadaDaFila = vf.chamada();
        %>

        <h2>A mensagem foi: <% out.print(chamadaDaFila); %> </h2>
        
    </body>
    
</html>