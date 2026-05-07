import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Interface Gráfica Swing para o Simulador de Substituição de Páginas
 */
public class SimulatorGUI extends JFrame {
    private JTextField pagesInputField;
    private JSpinner frameSizeSpinner;
    private JTextArea outputArea;
    private JTable resultsTable;
    private ChartPanel chartPanel;

    public SimulatorGUI() {
        setTitle("Simulador de Substituição de Páginas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);

        // Painel principal com layout em grid
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Painel superior para entrada de dados
        JPanel inputPanel = createInputPanel();
        mainPanel.add(inputPanel, BorderLayout.NORTH);

        // Painel central com abas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Aba de Gráfico
        chartPanel = new ChartPanel();
        tabbedPane.addTab("Gráfico Comparativo", chartPanel);

        // Aba de Tabela de Resultados
        resultsTable = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(resultsTable);
        tabbedPane.addTab("Tabela de Resultados", tableScrollPane);

        // Aba de Log de Execução
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(outputArea);
        tabbedPane.addTab("Log de Execução", scrollPane);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Configuração da Simulação"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Label e input para sequência de páginas
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Sequência de Páginas (separadas por espaço ou vírgula):"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        pagesInputField = new JTextField("1 2 3 4 1 2 5 1 2 3 4 5");
        panel.add(pagesInputField, gbc);

        // Label e input para tamanho do frame
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Tamanho da Memória (frames):"), gbc);

        gbc.gridx = 1;
        frameSizeSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));
        frameSizeSpinner.setPreferredSize(new Dimension(100, 30));
        panel.add(frameSizeSpinner, gbc);

        // Botão de simulação
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JButton simulateButton = new JButton("Executar Simulação");
        simulateButton.setPreferredSize(new Dimension(200, 40));
        simulateButton.addActionListener(e -> runSimulation());
        panel.add(simulateButton, gbc);

        return panel;
    }

    private void runSimulation() {
        try {
            // Parse da entrada
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
                    "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Executar simulação
            List<PageReplacementSimulator.SimulationResult> results =
                PageReplacementSimulator.runAllAlgorithms(pages, frameSize);

            // Atualizar interface
            updateResults(results);
            updateChart(results);
            updateTable(results);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Entrada inválida! Use números separados por espaço, vírgula ou ponto-e-vírgula.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateResults(List<PageReplacementSimulator.SimulationResult> results) {
        outputArea.setText("");
        outputArea.append("=== RESULTADOS DA SIMULAÇÃO ===\n\n");
        for (PageReplacementSimulator.SimulationResult result : results) {
            outputArea.append(result.toString() + "\n");
        }
        outputArea.append("\n=== TRACE DE EXECUÇÃO ===\n");
        for (PageReplacementSimulator.SimulationResult result : results) {
            outputArea.append("\n--- " + result.algorithmName + " ---\n");
            for (String s : result.trace) {
                outputArea.append(s + "\n");
            }
        }
    }

    private void updateChart(List<PageReplacementSimulator.SimulationResult> results) {
        chartPanel.setResults(results);
        chartPanel.repaint();
    }

    private void updateTable(List<PageReplacementSimulator.SimulationResult> results) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Algoritmo");
        model.addColumn("Faltas de Página");

        for (PageReplacementSimulator.SimulationResult result : results) {
            model.addRow(new Object[]{result.algorithmName, result.pageFaults});
        }

        resultsTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimulatorGUI());
    }
}

/**
 * Painel personalizado para desenhar gráfico de barras
 */
class ChartPanel extends JPanel {
    private List<PageReplacementSimulator.SimulationResult> results;

    public void setResults(List<PageReplacementSimulator.SimulationResult> results) {
        this.results = results;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (results == null || results.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Execute uma simulação para ver o gráfico", 50, 50);
            return;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int padding = 60;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;

        // Desenhar eixos
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(padding, height - padding, width - padding, height - padding); // Eixo X
        g2d.drawLine(padding, padding, padding, height - padding); // Eixo Y

        // Encontrar o máximo de faltas para escala
        int maxFaults = results.stream().mapToInt(r -> r.pageFaults).max().orElse(1);
        maxFaults = Math.max(maxFaults, 10); // Mínimo de 10 para melhor visualização

        // Desenhar barras
        int barWidth = chartWidth / (results.size() * 2);
        Color[] colors = {new Color(0, 102, 204), new Color(204, 0, 0),
                         new Color(0, 153, 76), new Color(255, 153, 0)};

        for (int i = 0; i < results.size(); i++) {
            PageReplacementSimulator.SimulationResult result = results.get(i);
            int x = padding + (i * 2 + 1) * barWidth;
            int barHeight = (int) ((double) result.pageFaults / maxFaults * chartHeight);
            int y = height - padding - barHeight;

            // Desenhar barra
            g2d.setColor(colors[i % colors.length]);
            g2d.fillRect(x, y, barWidth, barHeight);

            // Desenhar borda
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, y, barWidth, barHeight);

            // Desenhar valor no topo da barra
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            g2d.drawString(String.valueOf(result.pageFaults), x + barWidth / 2 - 10, y - 5);

            // Desenhar label no eixo X
            g2d.setFont(new Font("Arial", Font.PLAIN, 11));
            g2d.drawString(result.algorithmName, x + barWidth / 2 - 30, height - padding + 20);
        }

        // Título
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Comparativo de Faltas de Página", width / 2 - 100, 30);

        // Label do eixo Y
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.drawString("Faltas de Página", 10, height / 2);
    }
}
