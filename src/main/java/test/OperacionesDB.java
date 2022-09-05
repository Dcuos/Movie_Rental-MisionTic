package test;

import beans.Pelicula;
import com.mysql.jdbc.ResultSetRow;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;

public class OperacionesDB {
    public static void main(String[] args) {
        //listarPelicula();
        actualizaPelicula(1, "ficción");
    }
    public static void actualizaPelicula(int id, String genero){
        DBConnection con = new DBConnection();
        String sql = "UPDATE peliculas SET genero = '"+ genero + "' WHERE id = " + id;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    }
    public static void listarPelicula(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM peliculas ";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                String autor = rs.getString("autor");
                int copias = rs.getInt("copias");
                boolean novedad = rs.getBoolean("novedad");
                
                Pelicula pelicula = new Pelicula(id, titulo, genero, autor, copias, novedad);
                System.out.println(pelicula.toString());
            }
            st.executeQuery(sql);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            con.desconectar();
        }
    } 
}
//Esto es un comentario