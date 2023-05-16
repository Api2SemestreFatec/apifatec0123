package TelaAdmin1;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastrarCliente extends JFrame implements ActionListener {

    // Componentes da tela
    private JLabel labelCodigo, labelCliente, labelSetor, labelProjeto, labelVerba;
    private JTextField txtCodigo, txtCliente, txtSetor, txtProjeto, txtVerba;
    private JButton btnCadastrar, btnClientesCadastrados;

    public CadastrarCliente() {
        // Configurações básicas da tela
        setTitle("Cadastro de Clientes");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // impede a janela de ser maximizada
        setLayout(new GridLayout(6, 2));
        setResizable(false); // impedir maximização e redimensionamento

        // Criação dos componentes
        labelCodigo = new JLabel("Código:");
        txtCodigo = new JTextField();
        txtCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // impede a entrada de caracteres não numéricos
                }
            }
        });
        labelCliente = new JLabel("Cliente:");
        txtCliente = new JTextField();
        labelSetor = new JLabel("Setor:");
        txtSetor = new JTextField();
        labelProjeto = new JLabel("Projeto:");
        txtProjeto = new JTextField();
        labelVerba = new JLabel("Verba:");
        txtVerba = new JTextField();
        btnCadastrar = new JButton("Cadastrar Cliente");
        btnClientesCadastrados = new JButton("Clientes Cadastrados"); 
        
        

        // Adição dos componentes na tela
        add(labelCodigo);
        add(txtCodigo);
        add(labelCliente);
        add(txtCliente);
        add(labelSetor);
        add(txtSetor);
        add(labelProjeto);
        add(txtProjeto);
        add(labelVerba);
        add(txtVerba);
        add(btnClientesCadastrados);
        add(btnCadastrar);
        
        

        // Configura o botão cadastrar para responder ao evento de clique
        btnCadastrar.addActionListener(this);
    
        // Configura o botão usuários cadastrados para chamar a tela TelaUsuarios.java
        btnClientesCadastrados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes();
                telaClientes.setVisible(true);
            }
        });
    }
    
    

    // Método que é chamado quando o botão cadastrar é clicado
    public void actionPerformed(ActionEvent e) {

        // Verifica se algum campo de texto está vazio
        if (txtCodigo.getText().isEmpty() ||
            txtCliente.getText().isEmpty() ||
            txtSetor.getText().isEmpty() ||
            txtProjeto.getText().isEmpty() ||
            txtVerba.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

     // Obter os valores dos campos de texto
        String codigo = txtCodigo.getText();
        String cliente = txtCliente.getText();
        String setor = txtSetor.getText();
        String projeto = txtProjeto.getText();
        String verba = txtVerba.getText();

        // Limpar os campos de texto
        txtCodigo.setText("");
        txtCliente.setText("");
        txtSetor.setText("");
        txtProjeto.setText("");
        txtVerba.setText("");

        // Conectar ao banco de dados e inserir os dados
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/2RP" , "postgres" , "02466420");
            String sql = "INSERT INTO clientes (codigo, cliente, setor, projeto, verba) VALUES (?, ?, ?, ?, ?)";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(codigo));
            stmt.setString(2, cliente);
            stmt.setString(3, setor);
            stmt.setString(4, projeto);
            stmt.setDouble(5, Double.parseDouble(verba));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fechar a conexão e o statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
       
    }

    public static void main(String[] args) {
        CadastrarCliente tela = new CadastrarCliente();
        tela.setVisible(true);
    }



	public static void exibir() {
		// TODO Auto-generated method stub
		
	}
}
