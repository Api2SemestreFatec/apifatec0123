import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Tabela extends JFrame{
        private JTable table;

        public Tabela() {
            // Configurações básicas da janela
            setTitle("Apontamentos");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            // Conecta ao banco de dados
            try {
                Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP","postgres", "02466420");

                // Executa a consulta
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM apontamento");

                // Cria a tabela
                table = new JTable(buildTableModel(resultSet));

                // Adiciona a tabela em um JScrollPane e adiciona ao JFrame
                JScrollPane scrollPane = new JScrollPane(table);
                add(scrollPane);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Configura o tamanho e mostra a janela
            setSize(500, 500);
            setVisible(true);
        }

        // Converte o ResultSet em um DefaultTableModel para exibir na JTable
        public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
            ResultSetMetaData metaData = resultSet.getMetaData();

            // Nomes das colunas
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            // Dados da tabela
            Object[][] data = new Object[100][columnCount];
            int rowCount = 0;
            while (resultSet.next() && rowCount < 100) {
                for (int i = 1; i <= columnCount; i++) {
                    data[rowCount][i - 1] = resultSet.getObject(i);
                }
                rowCount++;
            }

            // Cria o modelo da tabela
            return new DefaultTableModel(data, columnNames);
        }

        public static void main(String[] args) {
            new Tabela();
        }
}


