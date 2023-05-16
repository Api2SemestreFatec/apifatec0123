package TelaAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TelaUsuarios extends JFrame {

    private JComboBox<String> comboBoxFiltro;
    private JTable tabelaUsuarios;
    
    public TelaUsuarios() {
        // Configurações básicas da tela
        setTitle("Usuários cadastrados");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        

        // Criação do JComboBox
        String[] opcoesFiltro = {"Todos", "Nome","Tipo", "Squad"};
        comboBoxFiltro = new JComboBox<>(opcoesFiltro);
        comboBoxFiltro.addActionListener(e -> atualizarTabela());

        // Adição do JComboBox na tela
        JPanel panelFiltro = new JPanel(new FlowLayout());
        panelFiltro.add(new JLabel("Filtro:"));
        panelFiltro.add(comboBoxFiltro);
        add(panelFiltro, BorderLayout.NORTH);

        // Criação da tabela
        String[] colunas = {"Matrícula", "Nome", "Tipo", "Squad"};
        String[][] dados = carregarDados();
        tabelaUsuarios = new JTable(dados, colunas);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);

        // Adição da tabela na tela
        add(scrollPane, BorderLayout.CENTER);

        // Criação do botão Novo usuário
        JButton botaoNovoUsuario = new JButton("Novo usuário");
        botaoNovoUsuario.addActionListener(e -> cadastrarUsuario());

        // Adição do botão Novo usuário na tela
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(botaoNovoUsuario);
        add(panelBotoes, BorderLayout.SOUTH);
    }
    

    // Método que carrega os dados dos usuários a partir do banco de dados
    private String[][] carregarDados() {
        String[][] dados = null;
        String filtro = (String) comboBoxFiltro.getSelectedItem();

        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP", "postgres", "02466420")) {
            String sql = "SELECT * FROM usuarios";
            if (!filtro.equals("Todos")) {
                sql += " WHERE " + filtro.toLowerCase() + " = ?";
            }
            PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if (!filtro.equals("Todos")) {
                stmt.setString(1, JOptionPane.showInputDialog("Digite o valor do filtro:"));
            }
            ResultSet rs = stmt.executeQuery();

            // Verifica se a consulta retornou algum resultado
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado!");
                return new String[][]{};
            }

            // Conta o número de linhas do ResultSet
            rs.last();
            int numRows = rs.getRow();
            rs.beforeFirst();

            // Cria a matriz de dados com o número de linhas do ResultSet
            dados = new String[numRows][4];

            // Preenche a matriz de dados com os resultados da consulta
            int i = 0;
            while (rs.next()) {
                dados[i][0] = rs.getString("matricula");
                dados[i][1] = rs.getString("nome");
                dados[i][2] = rs.getString("tipo");
                dados[i][3] = rs.getString("squad");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return dados;
    }

    // Método que atualiza a tabela de usuários de acordo com o filtro selecionado
    private void atualizarTabela() {
        String[][] dados = carregarDados();
        DefaultTableModel model = new DefaultTableModel(dados, new String[]{"Matrícula", "Nome", "Tipo", "Squad"});
        tabelaUsuarios.setModel(model);
    }

    // Método que abre a tela de cadastro de usuários
    private void cadastrarUsuario() {
        CadastrarUsuario CadastroUsuario = new CadastrarUsuario();
        CadastroUsuario.setVisible(true);
    }

    public static void main(String[] args) {
        TelaUsuarios telaUsuarios = new TelaUsuarios();
        telaUsuarios.setVisible(true);
    }
}