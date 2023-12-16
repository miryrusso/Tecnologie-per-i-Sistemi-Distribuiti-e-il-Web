package edu.unict.magazzon;

import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/magazzon")
public class App extends HttpServlet{
    private Connection conn;
    private final String connString = "jdbc:mysql://localhost:3306/magazzino?user=miry&password=password";
    
    //crea una connessione al database usando le credenziali fornite
    public void init(){
        try{
            conn = DriverManager.getConnection(connString);
            System.out.println("Connessione effettuata");
        } catch (Exception e){
            e.printStackTrace();    
        }
    }

    // inizializzo la pagina e creo la pagina html
    private PrintWriter initializeWebPage(HttpServletResponse response){
        response.setContentType("text/html");
        PrintWriter page = null;
        try{
            page=response.getWriter();
        } catch (Exception e){
            e.printStackTrace();
        }

        page.write("<html>");
        page.write("<head><title>Magazzon WebPage</title></head>");
        page.write("<body>");
        return page;
    }


    private void closeWebPage(PrintWriter page){
        page.write("</body>");
        page.write("</html>");

    }


    private ResultSet getAvailableProducts(){
        ResultSet result = null;
        String sql = "SELECT * FROM prodotti WHERE giacenza>0";
        try{
            result = conn.createStatement().executeQuery(sql);
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    private void showAvailableProducs(ResultSet products, PrintWriter page){
        try {
            while(products.next()){
                page.write("<p>" + products.getString("nome_prodotto") + "</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void showAddProductForm(PrintWriter page){
        page.println("<h1>Insert a new product</h1>");
        page.println("<form action='/magazzon' method='post'>");
        page.println("<input type='submit' value='Create'/>");
        page.println("<input type='text' name='nome_prodotto' placeholder='Nome prodotto' />");
        page.println("<input type='text' name='quantita' placeholder='Quantita' />");
        page.println("<input type='text' name='prezzo' placeholder='Prezzo' />");
        page.println("</form>");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        PrintWriter page=initializeWebPage(response);
       
        page.print("<h1>Welcome</h1>");
        ResultSet products = getAvailableProducts();
        showAvailableProducs(products, page);
        showAddProductForm(page);
       
       
        closeWebPage(page);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        PrintWriter page=initializeWebPage(response);

        String nome_prodotto = request.getParameter("nome_prodotto");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        float prezzo = Float.parseFloat(request.getParameter("prezzo"));
        
        String sql = "INSERT INTO prodotti (nome_prodotto, giacenza,prezzo) VALUES (?,?,?)";
        PreparedStatement st = null;
        
        int rows = 0;
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, nome_prodotto);
            st.setFloat(2, quantita);
            st.setFloat(3, prezzo);
            rows = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        page.write("<h1>Inserimento avvenuto con successo. Aggiunti " + rows + " record");


       closeWebPage(page);

    }

    public void destroy(){
        // close db
    }
}