package TelaAdmin1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CadastrarUsuario extends JFrame implements ActionListener {

	// Variável para manter o estado da tela TelaUsuarios
    private boolean telaUsuariosAberta = false;
    private static final Statement Conexao = null;
	// Componentes da tela
    private JLabel labelMatricula, labelNome, labelTipo, labelSquad, labelSenha;
    private JTextField txtMatricula, txtNome, txtSquad, txtSenha;
    private JComboBox<String> txtTipo;
    private JButton btnCadastrar, btnUsuariosCadastrados;
    
    public CadastrarUsuario() {
        // Configurações básicas da tela
        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2));
        setResizable(false); // impedir maximização e redimensionamento
        

        // Criação dos componentes
        labelMatricula = new JLabel("Matrícula:");
        txtMatricula = new JTextField();
        txtMatricula.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // impede a entrada de caracteres não numéricos
                }
            }
        });
        labelNome = new JLabel("Nome:");
        txtNome = new JTextField();
        labelTipo = new JLabel("Tipo:");
        txtTipo = new JComboBox<>(new String[]{"", "Administrador", "Colaborador", "Gestor"});
        labelSquad = new JLabel("Squad:");
        txtSquad = new JTextField();
        labelSenha = new JLabel("Senha:");
        txtSenha = new JPasswordField();
        btnCadastrar = new JButton("Cadastrar");
        btnUsuariosCadastrados = new JButton("Usuários cadastrados");

        // Adição dos componentes na tela
        add(labelMatricula);
        add(txtMatricula);
        add(labelNome);
        add(txtNome);
        add(labelTipo);
        add(txtTipo);
        add(labelSquad);
        add(txtSquad);
        add(labelSenha);
        add(txtSenha);
        add(btnUsuariosCadastrados);
        add(btnCadastrar);

        // Configura o botão cadastrar para responder ao evento de clique
        btnCadastrar.addActionListener(this);
        
        // Configura o botão usuários cadastrados para chamar a tela TelaUsuarios.java
        btnUsuariosCadastrados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaUsuarios telaUsuarios = new TelaUsuarios();
                telaUsuarios.setVisible(true);
            }
        });
    }

	// Método que é chamado quando o botão cadastrar é clicado
    public void actionPerformed(ActionEvent e) {

        // Verifica se algum campo de texto está vazio
        if (txtMatricula.getText().isEmpty() || 
            txtNome.getText().isEmpty() || 
            txtTipo.getSelectedItem() == null || 
            txtSquad.getText().isEmpty() || 
            txtSenha.getText().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Preencha todos os campos antes de cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Obter os valores dos campos de texto
        String matricula = txtMatricula.getText();
        String nome = txtNome.getText();
        String tipo = (String) txtTipo.getSelectedItem();
        String squad = txtSquad.getText();
        String senha = new String(((JPasswordField) txtSenha).getPassword()); // Como a senha é uma JPasswordField, precisamos usar getPassword() para obter seu valor

     
       

        // Limpar os campos de texto
        txtMatricula.setText("");
        txtNome.setText("");
        txtTipo.setToolTipText("");
        txtSquad.setText("");
        txtSenha.setText("");
        
        
        
     // Inserir as informações do usuário na tabela "usuarios" do PostgreSQL
        try (Connection conn = DriverManager.getConnection ("jdbc:postgresql://localhost:5432/2RP" , "postgres" , "02466420")) {
            String sql = "INSERT INTO usuarios (matricula, nome, tipo, squad, senha) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, matricula);
            stmt.setString(2, nome);
            stmt.setString(3, tipo);
            stmt.setString(4, squad);
            stmt.setString(5, senha);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
        
        // Exibir mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Cadastrado com sucesso!");
        
    }
        
    


    public static void main(String[] args) {
        CadastrarUsuario tela = new CadastrarUsuario();
        tela.setVisible(true);
        
        
    }
}