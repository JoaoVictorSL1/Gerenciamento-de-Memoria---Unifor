import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;

/**
 * Interface Gráfica Moderna - Simulador de Substituição de Páginas
 * Design limpo, cores vibrantes e layout profissional
 */
public class ModernSimulatorGUI extends JFrame {
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(52, 152, 219);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color WARNING_COLOR = new Color(241, 196, 15);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color DARK_BG = new Color(44, 62, 80);
    private static final Color LIGHT_BG = new Color(236, 240, 241);
    private static final Color TEXT_COLOR = new Color(52, 73, 94);
    private static final Color WHITE = new Color(255, 255, 255);

    private JTextField pagesInputField;
    private JSpinner frameSizeSpinner;
    private JTextArea outputArea;
    private JTable resultsTable;
    private ModernChartPanel chartPanel;
    private JLabel statusLabel;

    public ModernSimulatorGUI() {
        setTitle("Simulador de Substituição de Páginas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setResizable(true);
        setUndecorated(false);

        // Ícone e aparência
        UIManager.put("TabbedPane.contentAreaColor", LIGHT_BG);
        UIManager.put("TabbedPane.background", WHITE);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(LIGHT_BG);

        // Header
        mainPanel.add(createHeaderPanel(), BorderLayout.NORTH);

        // Painel central
        mainPanel.add(createCentralPanel(), BorderLayout.CENTER);

        // Footer
        mainPanel.add(createFooterPanel(), BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_COLOR);
        panel.setPreferredSize(new Dimension(0, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("📊 Simulador de Substituição de Páginas");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(WHITE);

        JLabel subtitleLabel = new JLabel("Analise o desempenho de diferentes algoritmos de gerenciamento de memória");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(236, 240, 241));

        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setOpaque(false);
        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);

        panel.add(textPanel, BorderLayout.WEST);
        return panel;
    }

    private JPanel createCentralPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(LIGHT_BG);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel de entrada
        mainPanel.add(createInputPanel(), BorderLayout.NORTH);

        // Painel com abas
        JTabbedPane tabbedPane = createTabbedPane();
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(WHITE);
        panel.setBorder(createRoundedBorder(12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.anchor = GridBagConstraints.WEST;

        // Título da seção
        JLabel sectionTitle = new JLabel("⚙️ Configuração da Simulação");
        sectionTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sectionTitle.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(sectionTitle, gbc);

        // Sequência de páginas
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel pagesLabel = createModernLabel("Sequência de Páginas:");
        panel.add(pagesLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        pagesInputField = createModernTextField("1 2 3 4 1 2 5 1 2 3 4 5", 30);
        panel.add(pagesInputField, gbc);

        // Tamanho da memória
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        JLabel frameSizeLabel = createModernLabel("Memória (frames):");
        panel.add(frameSizeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        frameSizeSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));
        frameSizeSpinner.setPreferredSize(new Dimension(100, 35));
        frameSizeSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panel.add(frameSizeSpinner, gbc);

        // Botão de simulação
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton simulateButton = createModernButton("▶ Executar Simulação", SUCCESS_COLOR);
        simulateButton.addActionListener(e -> runSimulation());
        panel.add(simulateButton, gbc);

        return panel;
    }

    private JTabbedPane createTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(WHITE);
        tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Aba de Gráfico
        chartPanel = new ModernChartPanel();
        tabbedPane.addTab("📊 Gráfico Comparativo", chartPanel);

        // Aba de Tabela
        resultsTable = createModernTable();
        JScrollPane tableScrollPane = new JScrollPane(resultsTable);
        tableScrollPane.setBackground(WHITE);
        tabbedPane.addTab("📋 Tabela de Resultados", tableScrollPane);

        // Aba de Log
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 11));
        outputArea.setBackground(DARK_BG);
        outputArea.setForeground(new Color(52, 211, 153));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        tabbedPane.addTab("📝 Log de Execução", scrollPane);

        return tabbedPane;
    }

    private JTable createModernTable() {
        JTable table = new JTable() {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);
                if (row % 2 == 0) {
                    comp.setBackground(new Color(245, 248, 250));
                } else {
                    comp.setBackground(WHITE);
                }
                if (isCellSelected(row, column)) {
                    comp.setBackground(SECONDARY_COLOR);
                    comp.setForeground(WHITE);
                }
                return comp;
            }
        };

        table.setRowHeight(35);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(PRIMARY_COLOR);
        table.getTableHeader().setForeground(WHITE);
        table.setGridColor(new Color(220, 230, 241));
        table.setShowGrid(true);

        return table;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(DARK_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        statusLabel = new JLabel("✓ Pronto");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusLabel.setForeground(SUCCESS_COLOR);

        panel.add(statusLabel, BorderLayout.WEST);
        return panel;
    }

    private void runSimulation() {
        try {
            statusLabel.setText("⏳ Processando...");
            statusLabel.setForeground(WARNING_COLOR);

            String input = pagesInputField.getText().trim();
            input = input.replaceAll("[,;]", " ");
            String[] pageStrings = input.split("\\s+");
            int[] pages = new int[pageStrings.length];

            for (int i = 0; i < pageStrings.length; i++) {
                pages[i] = Integer.parseInt(pageStrings[i]);
            }

            int frameSize = (Integer) frameSizeSpinner.getValue();

            if (frameSize <= 0) {
                JOptionPane.showMessageDialog(this, "Tamanho da memória deve ser maior que 0",
                    "⚠️ Erro", JOptionPane.ERROR_MESSAGE);
                statusLabel.setText("✗ Erro na entrada");
                statusLabel.setForeground(DANGER_COLOR);
                return;
            }

            List<PageReplacementSimulator.SimulationResult> results =
                PageReplacementSimulator.runAllAlgorithms(pages, frameSize);

            updateResults(results);
            updateChart(results);
            updateTable(results);

            statusLabel.setText("✓ Simulação concluída com sucesso");
            statusLabel.setForeground(SUCCESS_COLOR);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "❌ Entrada inválida! Use números separados por espaço, vírgula ou ponto-e-vírgula.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("✗ Erro na entrada");
            statusLabel.setForeground(DANGER_COLOR);
        }
    }

    private void updateResults(List<PageReplacementSimulator.SimulationResult> results) {
        outputArea.setText("");
        outputArea.append("╔════════════════════════════════════════════════════════════╗\n");
        outputArea.append("║               RESULTADOS DA SIMULAÇÃO                      ║\n");
        outputArea.append("╚════════════════════════════════════════════════════════════╝\n\n");

        int minFaults = results.stream().mapToInt(r -> r.pageFaults).min().orElse(0);

        for (PageReplacementSimulator.SimulationResult result : results) {
            String marker = result.pageFaults == minFaults ? " ⭐ MELHOR" : "";
            outputArea.append(String.format("%-15s: %3d faltas de página%s\n",
                result.algorithmName, result.pageFaults, marker));
        }

        outputArea.append("\n╔════════════════════════════════════════════════════════════╗\n");
        outputArea.append("║                 TRACE DE EXECUÇÃO                          ║\n");
        outputArea.append("╚════════════════════════════════════════════════════════════╝\n");

        for (PageReplacementSimulator.SimulationResult result : results) {
            outputArea.append("\n┌─ " + result.algorithmName + " ─────────────────────────────┐\n");
            if (result.trace.isEmpty()) {
                outputArea.append("  (Sem eventos de substituição)\n");
            } else {
                for (String s : result.trace) {
                    outputArea.append("  • " + s + "\n");
                }
            }
            outputArea.append("└────────────────────────────────────────────────────────┘\n");
        }
    }

    private void updateChart(List<PageReplacementSimulator.SimulationResult> results) {
        chartPanel.setResults(results);
        chartPanel.repaint();
    }

    private void updateTable(List<PageReplacementSimulator.SimulationResult> results) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("🔹 Algoritmo");
        model.addColumn("📊 Faltas de Página");

        int minFaults = results.stream().mapToInt(r -> r.pageFaults).min().orElse(0);

        for (PageReplacementSimulator.SimulationResult result : results) {
            String marker = result.pageFaults == minFaults ? " ⭐" : "";
            model.addRow(new Object[]{result.algorithmName, result.pageFaults + marker});
        }

        resultsTable.setModel(model);
    }

    // Métodos auxiliares para componentes modernos
    private JLabel createModernLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JTextField createModernTextField(String placeholder, int columns) {
        JTextField field = new JTextField(placeholder, columns);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setForeground(TEXT_COLOR);
        field.setBackground(LIGHT_BG);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        return field;
    }

    private JButton createModernButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getModel().isArmed() ? color.darker() : color);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setPreferredSize(new Dimension(0, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private Border createRoundedBorder(int radius) {
        return new javax.swing.border.AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(220, 230, 241));
                g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            }

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(10, 10, 10, 10);
            }
        };
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernSimulatorGUI());
    }
}
