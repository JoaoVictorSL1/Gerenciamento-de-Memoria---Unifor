import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Interface extends JFrame {
    private static final Color BG_COLOR = new Color(240, 244, 248);
    private static final Color SIDEBAR_COLOR = new Color(30, 41, 59);
    private static final Color ACCENT_COLOR = new Color(59, 130, 246);
    private static final Color CARD_BG = Color.WHITE;
    private static final Color TEXT_PRIMARY = new Color(15, 23, 42);
    private static final Color TEXT_SECONDARY = new Color(100, 116, 139);

    private JTextField pagesInputField;
    private JSpinner frameSizeSpinner;
    private JTextArea outputArea;
    private JTable resultsTable;
    private Grafico chartPanel;
    private JLabel statusLabel;

    public Interface() {
        setTitle("Simulador de Memória v2.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);

        JPanel mainLayout = new JPanel(new BorderLayout(0, 0));
        mainLayout.add(createSidebar(), BorderLayout.WEST);
        mainLayout.add(createMainContent(), BorderLayout.CENTER);

        add(mainLayout);
        setVisible(true);
        
        SwingUtilities.invokeLater(this::runSimulation);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(300, 0));
        sidebar.setBackground(SIDEBAR_COLOR);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));

        JLabel title = new JLabel("SIMULADOR");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel subtitle = new JLabel("Gerenciamento de Memória");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(new Color(148, 163, 184));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebar.add(title);
        sidebar.add(subtitle);
        sidebar.add(Box.createVerticalStrut(40));

        sidebar.add(createSidebarLabel("SEQUÊNCIA DE PÁGINAS"));
        pagesInputField = createSidebarTextField("1 2 3 4 1 2 5 1 2 3 4 5");
        sidebar.add(pagesInputField);
        sidebar.add(Box.createVerticalStrut(20));

        sidebar.add(createSidebarLabel("TAMANHO DA MEMÓRIA"));
        frameSizeSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 20, 1));
        frameSizeSpinner.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        sidebar.add(frameSizeSpinner);
        sidebar.add(Box.createVerticalStrut(40));

        JButton btn = new JButton("EXECUTAR ANÁLISE");
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        btn.setBackground(ACCENT_COLOR);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> runSimulation());
        sidebar.add(btn);

        sidebar.add(Box.createVerticalGlue());
        
        statusLabel = new JLabel("● Sistema Pronto");
        statusLabel.setForeground(new Color(34, 197, 94));
        sidebar.add(statusLabel);

        return sidebar;
    }

    private JPanel createMainContent() {
        JPanel content = new JPanel(new BorderLayout(20, 20));
        content.setBackground(BG_COLOR);
        content.setBorder(new EmptyBorder(30, 30, 30, 30));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel headTitle = new JLabel("Painel de Desempenho");
        headTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headTitle.setForeground(TEXT_PRIMARY);
        header.add(headTitle, BorderLayout.WEST);
        content.add(header, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBackground(CARD_BG);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));

        chartPanel = new Grafico();
        tabs.addTab("  Visualização Gráfica  ", chartPanel);

        resultsTable = new JTable();
        resultsTable.setRowHeight(40);
        resultsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabs.addTab("  Tabela Comparativa  ", new JScrollPane(resultsTable));

        outputArea = new JTextArea();
        outputArea.setBackground(new Color(15, 23, 42));
        outputArea.setForeground(new Color(148, 163, 184));
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setMargin(new Insets(10, 10, 10, 10));
        tabs.addTab("  Logs do Sistema  ", new JScrollPane(outputArea));

        content.add(tabs, BorderLayout.CENTER);

        return content;
    }

    private JLabel createSidebarLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 10));
        l.setForeground(new Color(148, 163, 184));
        l.setBorder(new EmptyBorder(0, 0, 8, 0));
        return l;
    }

    private JTextField createSidebarTextField(String text) {
        JTextField f = new JTextField(text);
        f.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        f.setBackground(new Color(51, 65, 85));
        f.setForeground(Color.WHITE);
        f.setCaretColor(Color.WHITE);
        f.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(71, 85, 105)),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return f;
    }

    private void runSimulation() {
        try {
            statusLabel.setText("○ Processando...");
            statusLabel.setForeground(new Color(234, 179, 8));

            String input = pagesInputField.getText().trim();
            input = input.replaceAll("[,;]", " ");
            String[] pageStrings = input.split("\\s+");
            int[] pages = new int[pageStrings.length];

            for (int i = 0; i < pageStrings.length; i++) pages[i] = Integer.parseInt(pageStrings[i]);
            int frameSize = (Integer) frameSizeSpinner.getValue();

            List<Simulador.SimulationResult> results = 
                Simulador.runAllAlgorithms(pages, frameSize);

            updateResults(results);
            chartPanel.setResults(results);
            chartPanel.repaint();
            updateTable(results);

            statusLabel.setText("● Simulação Concluída");
            statusLabel.setForeground(new Color(34, 197, 94));
        } catch (Exception ex) {
            statusLabel.setText("● Erro na Entrada");
            statusLabel.setForeground(new Color(239, 68, 68));
        }
    }

    private void updateResults(List<Simulador.SimulationResult> results) {
        outputArea.setText(" > Simulador Iniciado...\n");
        outputArea.append(" > Processando " + results.size() + " algoritmos...\n\n");
        
        int minFaults = results.stream().mapToInt(r -> r.pageFaults).min().orElse(0);
        
        for (Simulador.SimulationResult result : results) {
            String marker = result.pageFaults == minFaults ? " [MELHOR]" : "";
            outputArea.append(String.format(" [%s] %s: %d faltas %s\n", 
                result.pageFaults == minFaults ? "★" : "✓",
                result.algorithmName, result.pageFaults, marker));
        }
        
        outputArea.append("\n > Detalhes de Substituição:\n");
        for (Simulador.SimulationResult result : results) {
            outputArea.append("\n --- " + result.algorithmName + " ---\n");
            for (String s : result.trace) {
                outputArea.append("  • " + s + "\n");
            }
        }
    }

    private void updateTable(List<Simulador.SimulationResult> results) {
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Algoritmo", "Faltas de Página", "Eficiência"}, 0);
        int maxFaults = results.stream().mapToInt(r -> r.pageFaults).max().orElse(1);
        
        for (Simulador.SimulationResult r : results) {
            double efficiency = (1.0 - (double)r.pageFaults / maxFaults) * 100;
            model.addRow(new Object[]{r.algorithmName, r.pageFaults, String.format("%.1f%%", efficiency)});
        }
        resultsTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interface());
    }
}
