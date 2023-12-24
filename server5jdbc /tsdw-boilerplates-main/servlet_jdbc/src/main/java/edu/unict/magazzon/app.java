package edu.unict.magazzon;

//esercizio sui prodotti 
import java.io.*; 
import java.sql.*; 
import jakarta.servlet.annotation.WebServlet; 
import jakarta.servlet.http.*;

@WebServlet("/lista")
public class app extends HttpServlet{
    Connection conn; 
    String connString = "jdbc:mysql://localhost:3306/magazzino?user=miry&password=password"; 

    public void init(){
        try{
            conn = DriverManager.getConnection(connString); 
            System.out.println("Connessione stabilita");
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }

    private PrintWriter initWebPage(HttpServletResponse response){
        PrintWriter page = null; 
        System.out.println("Inizio initWebPage");
        response.setContentType("text/html");
        try{
            page = response.getWriter();  
        }catch(Exception e){}

        page.write("<html>"); 
        page.write("<body>");
        System.out.println("Fine initWebPage");
        return page;
    }

    private void closeWebPage(PrintWriter page){
        page.write("</html>");
        page.write("</body>"); 
    }

    private ResultSet getProduct(){
        System.out.println("Ei tu sono in ResultSet");
        ResultSet result = null; 
        String sql = "select * from prodotti"; 
        try{
            Statement st = conn.createStatement(); 
            result = st.executeQuery(sql); 

        }catch(Exception e){}


        System.out.println("Ei tu sono alla fine di ResultSet");
        return result; 
    }

    public void showPage(ResultSet result, PrintWriter page){
        page.write("<h1>Lista prodotti</h1>");
        try{
            while(result.next()){
                int id = result.getInt("id"); 
                String nome_prodotto = result.getString("nome_prodotto"); 
                int giacenza = result.getInt("giacenza"); 
                int prezzo = result.getInt("prezzo"); 
                page.write("<p>");
                page.write(" id:"+id+" nome_prodotto:"+nome_prodotto+" giacenza:"+giacenza+" prezzo: "+prezzo+"<a href='/lista?action=delete&id="+id+"'>DELETE</a> <br>"); 
                page.write("<a href='/lista?action=update&id="+id+"'>AGGIORNA RIGA </a>");
                page.write("</p>");
            }
        }catch(Exception e){}
    }

    public void showFormInsert(PrintWriter page){
        page.write("<h1>Inserisci un prodotto </h1>");
        page.write("<form method='post'>"); 
        page.write("<input type = 'text' name = 'nome_prodotto' placeholder= 'Inserisci il nome prodotto'>");
        page.write("<input type = 'text' name = 'giacenza' placeholder= 'Inserisci la giacenza'>");
        page.write("<input type = 'text' name = 'prezzo' placeholder= 'Inserisci il prezzo'>");
       
        page.write("<input type= 'hidden' name = 'action' value = 'insert'>"); 
        page.write("<input type = 'submit' value='Inserisci il prodotto'> "); 
        page.write("</form>"); 
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response){
        System.out.println("Sono in doGet");
        PrintWriter page = initWebPage(response); 
        String action = request.getParameter("action"); 
        if("delete".equals(action)){
            //fai delete dopo 
            int id = Integer.parseInt(request.getParameter("id")); 
            String sql = "DELETE FROM prodotti WHERE id=?"; 
            try{
                PreparedStatement st = conn.prepareStatement(sql); 
                st.setInt(1, id); 
                st.executeUpdate(); 
            }catch(Exception e){}
        }else if("update".equals(action)){
            int id = Integer.parseInt(request.getParameter("id")); 
            updateBookForm(response, id); 
            return; 
        }
        
        ResultSet result = getProduct(); 
        showPage(result, page); 
        showFormInsert(page); 
        closeWebPage(page);
    }


    public void doPost( HttpServletRequest request, HttpServletResponse response){
        PrintWriter page = initWebPage(response); 
        String action = request.getParameter("action"); 

        if("insert".equals(action)){
            //insert time 
           
            String nome_prodotto = request.getParameter("nome_prodotto"); 
            int giacenza = Integer.parseInt(request.getParameter("giacenza")); 
            int prezzo = Integer.parseInt(request.getParameter("prezzo")); 

            String sql = "INSERT INTO prodotti(nome_prodotto, giacenza, prezzo) VALUES ( ?,?,?)"; 
            try{
                PreparedStatement st = conn.prepareStatement(sql); 
                st.setString(1, nome_prodotto);
                st.setInt(2, giacenza);
                st.setInt(3, prezzo); 
                st.executeUpdate();
            }catch(Exception e){}

        }else if("update".equals(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            String nome_prodotto = request.getParameter("nome_prodotto"); 
            int giacenza = Integer.parseInt(request.getParameter("giacenza")); 
            int prezzo = Integer.parseInt(request.getParameter("prezzo")); 
            try{
                PreparedStatement stmt = conn.prepareStatement("UPDATE prodotti SET nome_prodotto = ?, giacenza = ?, prezzo = ? WHERE id = ?");
                stmt.setString(1, nome_prodotto);
                stmt.setInt(2, giacenza);
                stmt.setInt(3, prezzo);
                stmt.setInt(4, id);


                stmt.executeUpdate();
            }catch(Exception e){}


        }

        try{
            response.sendRedirect("/lista");
        }catch(Exception e){}

        closeWebPage(page);
    }

     private void showUpdateBookForm(PrintWriter page, ResultSet book) {
        try {
            page.write("<br> <h1> Aggiorna libro </h1>");
            page.write("<form method='POST'>");
            page.write("<input type='hidden' name='action' value='update'/>");
            page.write("<input type='hidden' name='id' value='" + book.getInt("id") + "'/>");
            page.write("<input type='text' name='nome_prodotto' placeholder='nome_prodotto' value='" + book.getString("nome_prodotto") + "'/>");
            page.write("<input type='text' name='giacenza' placeholder='giacenza' value='" + book.getString("giacenza") + "'/>");
            page.write("<input type='text' name='prezzo' placeholder='prezzo' value='" + book.getString("prezzo") + "'/>");
            
            page.write("<input type='submit' value='Aggiorna'/>");
            page.write("</form>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBookForm(HttpServletResponse response, int bookId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM prodotti WHERE id = ?");
            stmt.setInt(1, bookId);
            ResultSet book = stmt.executeQuery();

            if (book.next()) {
                PrintWriter page = initWebPage(response);
                showUpdateBookForm(page, book);
                closeWebPage(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}