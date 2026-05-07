import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Painel de Gráfico Moderno com Design Profissional
 */
class ModernChartPanel extends JPanel {
    private static final Color[] CHART_COLORS = {
        new Color(41, 128, 185),   // Azul
        new Color(231, 76, 60),    // Vermelho
        new Color(46, 204, 113),   // Verde
        new Color(241, 196, 15)    // Amarelo
    };

    private static final Color GRID_COLOR = new Color(220, 230, 241);
    private static final Color TEXT_COLOR = new Color(52, 73, 94);
    private static final Color AXIS_COLOR = new Color(149, 165, 166);

    private List<PageReplacementSimulator.SimulationResult> results;

    public void setResults(List<PageReplacementSimulator.SimulationResult> results) {
        this.results = results;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        if (results == null || results.isEmpty()) {
            drawEmptyState(g2d);
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int padding = 60;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;

        // Desenhar fundo
        drawBackground(g2d, width, height);

        // Desenhar grade
        drawGrid(g2d, padding, chartHeight, height);

        // Encontrar máximo
        int maxFaults = results.stream().mapToInt(r -> r.pageFaults).max().orElse(1);
        maxFaults = Math.max(maxFaults, 10);

        // Desenhar barras
        drawBars(g2d, padding, height, chartHeight, maxFaults, chartWidth);

        // Desenhar eixos
        drawAxes(g2d, padding, height, chartHeight, maxFaults);

        // Desenhar título
        drawTitle(g2d, width);

        // Desenhar legenda
        drawLegend(g2d, width, height);
    }

    private void drawEmptyState(Graphics2D g2d) {
        int width = getWidth();
        int height = getHeight();

        g2d.setColor(new Color(236, 240, 241));
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(new Color(149, 165, 166));
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        String message = "Execute uma simulação para ver o gráfico";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(message)) / 2;
        int y = height / 2;
        g2d.drawString(message, x, y);
    }

    private void drawBackground(Graphics2D g2d, int width, int height) {
        GradientPaint gradient = new GradientPaint(0, 0, new Color(245, 248, 250),
                                                   width, height, new Color(236, 240, 241));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }

    private void drawGrid(Graphics2D g2d, int padding, int chartHeight, int height) {
        g2d.setColor(GRID_COLOR);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        // Linhas horizontais
        int gridLines = 5;
        for (int i = 0; i <= gridLines; i++) {
            int y = (int) (height - padding - (i * (double) chartHeight / gridLines));
            g2d.drawLine(padding, y, getWidth() - padding, y);

            // Labels do eixo Y
            int value = (int) ((i * (double) results.stream()
                    .mapToInt(r -> r.pageFaults).max().orElse(10) / gridLines));
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            g2d.drawString(String.valueOf(value), padding - 35, y + 4);
            g2d.setColor(GRID_COLOR);
        }
    }

    private void drawBars(Graphics2D g2d, int padding, int height, int chartHeight,
                         int maxFaults, int chartWidth) {
        int barWidth = chartWidth / (results.size() * 2);
        int spacing = chartWidth / results.size();

        for (int i = 0; i < results.size(); i++) {
            PageReplacementSimulator.SimulationResult result = results.get(i);

            int x = padding + (i * spacing) + (spacing - barWidth) / 2;
            int barHeight = (int) ((double) result.pageFaults / maxFaults * chartHeight);
            int y = height - padding - barHeight;

            // Desenhar barra com gradiente
            Color baseColor = CHART_COLORS[i % CHART_COLORS.length];
            GradientPaint barGradient = new GradientPaint(x, y, baseColor.brighter(),
                                                          x, y + barHeight, baseColor);
            g2d.setPaint(barGradient);
            g2d.fillRoundRect(x, y, barWidth, barHeight, 8, 8);

            // Borda da barra
            g2d.setColor(baseColor.darker());
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRoundRect(x, y, barWidth, barHeight, 8, 8);

            // Valor no topo
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 13));
            String valueStr = String.valueOf(result.pageFaults);
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(valueStr, x + (barWidth - fm.stringWidth(valueStr)) / 2, y - 8);

            // Label no eixo X
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2d.setColor(TEXT_COLOR);
            String label = result.algorithmName;
            FontMetrics fmLabel = g2d.getFontMetrics();
            g2d.drawString(label, x + (barWidth - fmLabel.stringWidth(label)) / 2,
                          height - padding + 25);
        }
    }

    private void drawAxes(Graphics2D g2d, int padding, int height, int chartHeight, int maxFaults) {
        g2d.setColor(AXIS_COLOR);
        g2d.setStroke(new BasicStroke(2.5f));

        // Eixo X
        g2d.drawLine(padding, height - padding, getWidth() - padding, height - padding);

        // Eixo Y
        g2d.drawLine(padding, padding, padding, height - padding);

        // Setas nos eixos
        int arrowSize = 8;
        // Seta X
        g2d.fillPolygon(
            new int[]{getWidth() - padding, getWidth() - padding - arrowSize, getWidth() - padding - arrowSize},
            new int[]{height - padding, height - padding - arrowSize / 2, height - padding + arrowSize / 2},
            3
        );
        // Seta Y
        g2d.fillPolygon(
            new int[]{padding, padding - arrowSize / 2, padding + arrowSize / 2},
            new int[]{padding, padding + arrowSize, padding + arrowSize},
            3
        );

        // Labels dos eixos
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2d.drawString("Faltas de Página", 15, 25);
        g2d.drawString("Algoritmos", getWidth() - 100, height - 20);
    }

    private void drawTitle(Graphics2D g2d, int width) {
        g2d.setColor(new Color(41, 128, 185));
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 18));
        String title = "Comparativo de Faltas de Página";
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(title, (width - fm.stringWidth(title)) / 2, 35);
    }

    private void drawLegend(Graphics2D g2d, int width, int height) {
        int legendX = width - 200;
        int legendY = 60;
        int boxSize = 12;
        int lineHeight = 25;

        g2d.setFont(new Font("Segoe UI", Font.BOLD, 11));
        g2d.setColor(TEXT_COLOR);
        g2d.drawString("Legenda:", legendX, legendY);

        for (int i = 0; i < results.size(); i++) {
            int y = legendY + 20 + (i * lineHeight);

            // Caixa de cor
            Color color = CHART_COLORS[i % CHART_COLORS.length];
            g2d.setColor(color);
            g2d.fillRoundRect(legendX, y - boxSize, boxSize, boxSize, 3, 3);

            // Texto
            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            g2d.drawString(results.get(i).algorithmName, legendX + 20, y + 2);
        }
    }
}
