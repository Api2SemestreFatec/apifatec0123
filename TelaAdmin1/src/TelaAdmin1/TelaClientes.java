package TelaAdmin1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaClientes extends JFrame {

	// Componentes da tela
	private JTable tabelaClientes;
	private JComboBox<String> comboBoxFiltro;
	private JButton btnFiltrar;

	public TelaClientes() {
	    // Configurações básicas da tela
	    setTitle("Lista de Clientes");
	    setSize(600, 400);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // fecha apenas esta janela quando for fechada
	    setLocationRelativeTo(null);
	    setLayout(new BorderLayout());

	    // Criação da tabela
	    tabelaClientes = new JTable();
	    DefaultTableModel model = new DefaultTableModel();
	    model.addColumn("Código");
	    model.addColumn("Cliente");
	    model.addColumn("Setor");
	    model.addColumn("Projeto");
	    model.addColumn("Verba");
	    tabelaClientes.setModel(model);

	    // Adiciona a tabela em um JScrollPane para permitir rolagem
	    JScrollPane scrollPane = new JScrollPane(tabelaClientes);
	    add(scrollPane, BorderLayout.CENTER);

	    // Criação do botão para cadastrar um novo cliente
	    JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
	    cadastrarClienteButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            CadastrarCliente cadastrarCliente = new CadastrarCliente();
	            cadastrarCliente.setVisible(true);
	        }
	    });
	    add(cadastrarClienteButton, BorderLayout.SOUTH);

	    // Criação do painel para filtro
	    JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    panelFiltro.setBorder(BorderFactory.createTitledBorder("Filtrar"));

	    // Criação do ComboBox para selecionar o tipo de filtro
	    comboBoxFiltro = new JComboBox<String>();
	    comboBoxFiltro.addItem("Todos");
	    comboBoxFiltro.addItem("Código");
	    comboBoxFiltro.addItem("Cliente");
	    comboBoxFiltro.addItem("Setor");
	    comboBoxFiltro.addItem("Projeto");
	    comboBoxFiltro.addItem("Verba");
	    panelFiltro.add(comboBoxFiltro);

	    // Criação do botão para aplicar o filtro
	    btnFiltrar = new JButton("Filtrar");
	    btnFiltrar.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Popula a tabela com os dados do banco de dados filtrados pelo valor selecionado no ComboBox
	            String filtro = (String) comboBoxFiltro.getSelectedItem();
	            String valorFiltro = JOptionPane.showInputDialog("Digite o valor do filtro:");
	            Connection conexao = null;
	            PreparedStatement stmt = null;
	            ResultSet rs = null;
	            try {
	                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP" , "postgres" , "02466420");
	                String query = "SELECT * FROM clientes";
	                if(!filtro.equals("Todos")) {
	                    query += " WHERE " + filtro.toLowerCase() + " LIKE ?";
	                    valorFiltro = "%" + valorFiltro + "%";
	                }
	                
	                stmt = conexao.prepareStatement(query);
	                if(!filtro.equals("Todos")) {
	                    stmt.setString(1, valorFiltro);
	                }
	                
	                
	                rs = stmt.executeQuery();
	                model.setRowCount(0);
	                while (rs.next()) {
	                    model.addRow(new Object[] {
	                            rs.getInt("codigo"),
	                            rs.getString("cliente"),
	                            rs.getString("setor"),
	                            rs.getString("projeto"),
	                            rs.getDouble("verba")
	                    });
	                    
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            } finally {
	                // Fecha a conexão com o banco de dados
	                try {
                            if(rs != null) rs.close();
                            if(stmt != null) stmt.close();
                            if(conexao != null) conexao.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            panelFiltro.add(btnFiltrar);

            add(panelFiltro, BorderLayout.NORTH);

            // Preenche a tabela com todos os clientes cadastrados no banco de dados
            Connection conexao = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP" , "postgres" , "02466420");
                String query = "SELECT * FROM clientes";
                stmt = conexao.prepareStatement(query);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    model.addRow(new Object[] {
                            rs.getInt("codigo"),
                            rs.getString("cliente"),
                            rs.getString("setor"),
                            rs.getString("projeto"),
                            rs.getDouble("verba")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                // Fecha a conexão com o banco de dados
                try {
                    if(rs != null) rs.close();
                    if(stmt != null) stmt.close();
                    if(conexao != null) conexao.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    
public static void main(String[] args) {
    TelaClientes tela = new TelaClientes();
    tela.setVisible(true);
}
}