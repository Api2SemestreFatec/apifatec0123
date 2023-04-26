import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() {
        Connection conexao = null;
        try{
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP", "postgres", "02466420");
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao acessar o banco");
        } catch (ClassNotFoundException e) {
            System.out.println("Drive de banco de dados não localizado");
        }
        return conexao;
    }

}

