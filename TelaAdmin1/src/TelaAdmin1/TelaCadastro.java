package TelaAdmin1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TelaCadastro extends JFrame implements ActionListener {

    JButton btnCliente, btnUsuario, btnCR, btnProjeto;

    public TelaCadastro() {

        // Configurações da janela
        setTitle("Cadastros");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // desabilita a maximização da janela
        setResizable(false);

        // Criação dos botões
        btnCliente = new JButton("Cadastrar Cliente");
        btnUsuario = new JButton("Cadastro de Usuários");
        btnCR = new JButton("Cadastro de CRs");
        btnProjeto = new JButton("Cadastro de Projetos");

        // Estilizando botões
        estilizarBotao(btnCliente);
        estilizarBotao(btnUsuario);
        estilizarBotao(btnCR);
        estilizarBotao(btnProjeto);

        // Adicionando botões ao painel
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] dist = { 0.0f, 0.5f, 1.0f };
                Color[] colors = { Color.BLACK, Color.GRAY, Color.WHITE };
                g2d.setPaint(new GradientPaint(start, colors[0], end, colors[2]));
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS)); // Define um layout de caixa com orientação vertical
        painel.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço acima dos botões
        painel.add(btnCliente);
        painel.add(Box.createRigidArea(new Dimension(0, 50))); // Espaço entre os botões
        painel.add(btnUsuario);
        painel.add(Box.createRigidArea(new Dimension(0, 50))); // Espaço entre os botões
        painel.add(btnCR);
        painel.add(Box.createRigidArea(new Dimension(0, 50))); // Espaço entre os botões
        painel.add(btnProjeto);

        // Adicionando painel à janela
        getContentPane().add(painel, BorderLayout.CENTER);

        // Configuração do ActionListener
        btnCliente.addActionListener(this);
        btnUsuario.addActionListener(this);
        btnCR.addActionListener(this);
        btnProjeto.addActionListener(this);
    }

    private void estilizarBotao(JButton btn) {
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFocusPainted(false);
        btn.setBackground(Color.GRAY);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.PLAIN, 16));
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }
    private void setBotaoEsquerda(JButton btnCliente) {
        // TODO Auto-generated method stub

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCliente) {
            // Cria uma nova instância da classe CadastrarClientes
            CadastrarCliente cliente = new CadastrarCliente();
            cliente.setVisible(true);

        } else if (e.getSource() == btnUsuario) {
            CadastrarUsuario usuario = new CadastrarUsuario();
            usuario.setVisible(true);
            
        } else if (e.getSource() == btnCR) {
            // Chama tela de cadastro de CRs
            // ...
        } else if (e.getSource() == btnProjeto) {
            // Chama tela de cadastro de projetos
            // ...
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true);
        });
    }
}
