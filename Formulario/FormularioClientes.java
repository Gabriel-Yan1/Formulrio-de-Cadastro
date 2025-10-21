import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioClientes extends JFrame {

    // Construtor da Janela
    public FormularioClientes() {
        // 1. Configurações da Janela (JFrame)
        setTitle("Cadastrar Clientes"); // Título da Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha o app ao fechar a janela
        setSize(550, 450); // Tamanho inicial da janela (largura, altura)
        setLayout(new BorderLayout()); // Define o layout principal como BorderLayout

        // 2. Cria e adiciona a Barra de Ferramentas (Topo)
        JToolBar toolBar = criarToolBar();
        add(toolBar, BorderLayout.NORTH);

        // 3. Cria e adiciona o Painel de Conteúdo (Centro)
        JPanel painelFormulario = criarPainelFormulario();
        add(painelFormulario, BorderLayout.CENTER);

        // 4. Cria e adiciona o Botão Voltar (Rodapé)
        JPanel painelRodape = criarPainelRodape();
        add(painelRodape, BorderLayout.SOUTH);

        // Torna a janela visível
        setVisible(true);
        // Centraliza a janela na tela
        setLocationRelativeTo(null);
    }

    public void setTitle(java.lang.String cadastrarClientes) {
    }

    // Método para criar a Barra de Ferramentas (Topo)
    private JToolBar criarToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // Impede que a barra de ferramentas seja arrastada

        // Botão Gravar Dados
        JButton btnGravar = new JButton("Gravar Dados");
        // OBS: A lógica de validação será implementada neste botão
        btnGravar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCampos(); // Chamada ao método de validação
            }
        });

        // Botão Cancelar Cadastro
        JButton btnCancelar = new JButton("Cancelar Cadastro");
        // Adiciona uma ação de exemplo (pode ser ajustada depois)
        btnCancelar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ação Cancelar Acionada."));

        // Adiciona os botões à barra de ferramentas
        toolBar.add(btnGravar);
        toolBar.addSeparator(); // Adiciona um pequeno espaço
        toolBar.add(btnCancelar);

        return toolBar;
    }

    // Método para criar o Rodapé (Botão Voltar)
    private JPanel criarPainelRodape() {
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Alinha o conteúdo à esquerda
        JButton btnVoltar = new JButton("Voltar");
        // Adiciona uma ação de exemplo (pode ser ajustada depois)
        btnVoltar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Ação Voltar Acionada."));
        painelRodape.add(btnVoltar);
        return painelRodape;
    }


    // Método Principal (main) para iniciar a aplicação
    public static void main(String[] args) {
        // Garante que a GUI seja criada na Thread de Despacho de Eventos
        SwingUtilities.invokeLater(() -> new FormularioClientes());
    }

    // --- Campos do Formulário ---
    // Vamos declarar os campos fora dos métodos para poder acessá-los no método 'validarCampos'
    private JTextField txtNomeCompleto = new JTextField(20);
    private JTextField txtEndereco = new JTextField(20);
    private JTextField txtCidade = new JTextField(20);
    private JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"", "SP", "RJ", "MG", "Outro"}); // Lista de exemplo
    private JFormattedTextField txtTelefone = new JFormattedTextField(); // Usaremos um formatador depois
    private JRadioButton rbAtivo = new JRadioButton("Ativo");
    private JRadioButton rbInativo = new JRadioButton("Inativo");


    // Método para criar o Painel do Formulário (Centro)
    private JPanel criarPainelFormulario() {
        // Usamos GridBagLayout para um controle mais preciso sobre o posicionamento
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Espaçamento entre os componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinhar à esquerda

        // 1. Mensagem de instrução
        JLabel lblInstrucao = new JLabel("Preencha os dados corretamente e clique em Gravar Dados");
        lblInstrucao.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0; // Coluna 0
        gbc.gridy = 0; // Linha 0
        gbc.gridwidth = 2; // Ocupa duas colunas
        painel.add(lblInstrucao, gbc);

        // Reseta o gridwidth
        gbc.gridwidth = 1;

        // 2. Nome Completo
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Nome Completo:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Faz o campo de texto preencher o espaço
        painel.add(txtNomeCompleto, gbc);

        // 3. Endereço
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Endereço:"), gbc);
        gbc.gridx = 1;
        painel.add(txtEndereco, gbc);

        // 4. Cidade
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Cidade:"), gbc);
        gbc.gridx = 1;
        painel.add(txtCidade, gbc);

        // 5. Estado
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Estado:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE; // Desliga o preenchimento para o ComboBox
        cmbEstado.setPreferredSize(new Dimension(80, 25)); // Define um tamanho fixo
        painel.add(cmbEstado, gbc);

        // 6. Telefone
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Telefone:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Configura o JFormattedTextField para o Telefone (mascara)
        try {
            txtTelefone = new JFormattedTextField(new javax.swing.text.MaskFormatter("(##) ####-####"));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        painel.add(txtTelefone, gbc);

        // 7. Status (Radio Buttons)
        gbc.gridy++;
        gbc.gridx = 0;
        painel.add(new JLabel("Status:"), gbc);

        // Cria um painel para agrupar os Radio Buttons
        JPanel painelStatus = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        ButtonGroup grupoStatus = new ButtonGroup();
        grupoStatus.add(rbAtivo);
        grupoStatus.add(rbInativo);
        painelStatus.add(rbAtivo);
        painelStatus.add(rbInativo);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.NONE;
        painel.add(painelStatus, gbc);

        // Adiciona um separador visual
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        painel.add(new JSeparator(), gbc);

        return painel;
    }

    // Método para validar os campos
    private void validarCampos() {
        // String para armazenar a lista de campos não preenchidos
        StringBuilder camposVazios = new StringBuilder();

        // 1. Validação dos Campos de Texto (JTextField) e JFormattedTextField
        if (txtNomeCompleto.getText().trim().isEmpty()) {
            camposVazios.append("Nome Completo\n");
        }
        if (txtEndereco.getText().trim().isEmpty()) {
            camposVazios.append("Endereço\n");
        }
        if (txtCidade.getText().trim().isEmpty()) {
            camposVazios.append("Cidade\n");
        }

        // 2. Validação do ComboBox (JComboBox)
        // O valor "" foi adicionado na posição 0 para forçar a seleção
        if (cmbEstado.getSelectedIndex() <= 0) {
            camposVazios.append("Estado\n");
        }

        // A validação do Telefone é mais complexa devido à máscara, mas podemos simplificar checando se os dígitos não foram inseridos
        // Se o JFormattedTextField estiver vazio, ele geralmente retorna a string da máscara ou nulo, dependendo da implementação.
        // Vamos checar o conteúdo formatado, assumindo que "(  )    -" é o vazio:
        if (txtTelefone.getText().trim().equals("(_) __-_")) {
            camposVazios.append("Telefone\n");
        }

        // 3. Validação dos Radio Buttons (ButtonGroup)
        if (!rbAtivo.isSelected() && !rbInativo.isSelected()) {
            camposVazios.append("Status\n");
        }

        // --- Exibe o resultado da validação ---
        if (camposVazios.length() > 0) {
            // Se houver campos vazios, exibe a mensagem de erro
            String mensagem = "Os seguintes campos não estão preenchidos:\n\n" + camposVazios.toString();
            JOptionPane.showMessageDialog(this, mensagem, "Erro de Validação", JOptionPane.ERROR_MESSAGE);
        } else {
            // Se todos os campos estiverem preenchidos, a validação foi bem-sucedida
            JOptionPane.showMessageDialog(this, "Cadastro validado e gravado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}