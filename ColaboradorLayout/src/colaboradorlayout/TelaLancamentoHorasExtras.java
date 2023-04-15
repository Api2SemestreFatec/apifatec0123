package colaboradorlayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;


public class TelaLancamentoHorasExtras {
    // Variáveis para armazenar as informações inseridas
    private static String dataHorasExtras;
    private static String horaInicio;
    private static String horaFim;
    private static String nomeCliente;
    private static String nomeProjeto;
    private static String motivoHorasExtras;

    public static void main(String[] args) {
        try {
            // Define o estilo Nimbus
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Caso não seja possível aplicar o estilo Nimbus, utiliza o estilo padrão
        }

        JFrame frame = new JFrame("Lançamento de Horas Extras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 370);
        
        
        
        
        
        
        JPanel panel = new JPanel();
        Color corFundo = new Color(112, 128, 144); // Cor azul escuro
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona margem para o painel
        panel.setBackground(corFundo); // Defina a cor de fundo do painel
        
        
        // Cria uma fonte com estilo negrito
        Font fonteNegrito = new Font(Font.SANS_SERIF, Font.BOLD, 12); // Pode ajustar o tamanho da fonte aqui
        
        

        JLabel labelData = new JLabel("Informe a data em que foram realizadas as horas extras (Ex: 01.01.2023): ");
        labelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelData.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextField campoData = new JTextField(10);
        int limiteCaracteresData = 10; // Define o limite de caracteres desejado
        campoData.setDocument(new LimitarDocument(limiteCaracteresData));
        panel.add(labelData);
        panel.add(campoData);

        JLabel labelHoraInicio = new JLabel("Informe o horário inicial (Ex: 12:00): ");
        labelHoraInicio.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelHoraInicio.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextField campoHoraInicio = new JTextField(10);
        int limiteCaracteresHoraInicio = 5;
        campoHoraInicio.setDocument(new LimitarDocument(limiteCaracteresHoraInicio));        
        panel.add(labelHoraInicio);
        panel.add(campoHoraInicio);

        JLabel labelHoraFim = new JLabel("Informe o horário final (Ex: 13:00): ");
        labelHoraFim.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelHoraFim.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextField campoHoraFim = new JTextField(10);
        int limiteCaracteresHoraFim = 5; // Define o limite de caracteres desejado
        campoHoraFim.setDocument(new LimitarDocument(limiteCaracteresHoraFim));
        panel.add(labelHoraFim);
        panel.add(campoHoraFim);

        JLabel labelNomeCliente = new JLabel("Informe o nome do cliente: ");
        labelNomeCliente.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelNomeCliente.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextField campoNomeCliente = new JTextField(10);
        int limiteCaracteresNomeCliente = 50; // Define o limite de caracteres desejado
        campoNomeCliente.setDocument(new LimitarDocument(limiteCaracteresNomeCliente));
        panel.add(labelNomeCliente);
        panel.add(campoNomeCliente);

        JLabel labelNomeProjeto = new JLabel("Informe o nome do projeto: ");
        labelNomeProjeto.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelNomeProjeto.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextField campoNomeProjeto = new JTextField(10);
        int limiteCaracteresNomeProjeto = 50; // Define o limite de caracteres desejado
        campoNomeProjeto.setDocument(new LimitarDocument(limiteCaracteresNomeProjeto));
        panel.add(labelNomeProjeto);
        panel.add(campoNomeProjeto);

        JLabel labelMotivo = new JLabel("Justificativa: ");
        labelMotivo.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelMotivo.setFont(fonteNegrito); // Aplica a fonte negrito ao JLabel
        JTextArea campoMotivo = new JTextArea(5, 20);
        int limiteCaracteresMotivo = 200; // Define o limite de caracteres desejado
        campoMotivo.setDocument(new LimitarDocument(limiteCaracteresMotivo));
        JScrollPane scrollPane = new JScrollPane(campoMotivo);
        panel.add(labelMotivo);
        panel.add(scrollPane);

        JButton botaoLancar = new JButton("Lançar Horas Extras");
        botaoLancar.setAlignmentX(JButton.CENTER_ALIGNMENT); // Centraliza o botão horizontalmente
        botaoLancar.addActionListener(e -> {
            // Recupera os textos dos campos de texto e área de texto
            dataHorasExtras = campoData.getText();
            horaInicio = campoHoraInicio.getText();
            horaFim = campoHoraFim.getText();
            nomeCliente = campoNomeCliente.getText();
            nomeProjeto = campoNomeProjeto.getText();
            motivoHorasExtras = campoMotivo.getText();
            
            // Verifica se algum campo está em branco
            if (dataHorasExtras.isEmpty() || horaInicio.isEmpty() || horaFim.isEmpty() || nomeCliente.isEmpty() || nomeProjeto.isEmpty() || motivoHorasExtras.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos antes de lançar as horas extras.");
                return;
            }

            // Código para processar os dados inseridos, por exemplo, salvando-os em uma estrutura de dados ou enviando-os para um banco de dados

            // Reseta os campos de texto e área de texto para novo preenchimento
            campoData.setText("");
            campoHoraInicio.setText("");
            campoHoraFim.setText("");
            campoNomeCliente.setText("");
            campoNomeProjeto.setText("");
            campoMotivo.setText("");

            JOptionPane.showMessageDialog(frame, "Horas extras lançadas com sucesso!");
        });
        panel.add(botaoLancar);
        
        

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
