import javax.swing.*;
import java.awt.*;
import java.util.List;

class Grafico extends JPanel {
    private static final Color[] CHART_COLORS = {
        new Color(59, 130, 246),
        new Color(239, 68, 68),
        new Color(34, 197, 94),
        new Color(234, 179, 8),
        new Color(168, 85, 247)
    };

    private static final Color GRID_COLOR = new Color(226, 232, 240);
    private static final Color TEXT_COLOR = new Color(71, 85, 105);
    private static final Color AXIS_COLOR = new Color(148, 163, 184);

    private List<Simulador.SimulationResult> results;

    public void setResults(List<Simulador.SimulationResult> results) {
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
        int padding = 80;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;

        drawBackground(g2d, width, height);
        drawGrid(g2d, padding, chartHeight, height);

        int maxFaults = results.stream().mapToInt(r -> r.pageFaults).max().orElse(1);
        maxFaults = (int)(maxFaults * 1.2); 

        drawBars(g2d, padding, height, chartHeight, maxFaults, chartWidth);
        drawAxes(g2d, padding, height, chartHeight, maxFaults);
        drawLegend(g2d, width);
    }

    private void drawEmptyState(Graphics2D g2d) {
        g2d.setColor(new Color(248, 250, 252));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(TEXT_COLOR);
        g2d.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        String msg = "Aguardando dados da simulação...";
        FontMetrics fm = g2d.getFontMetrics();
        g2d.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2);
    }

    private void drawBackground(Graphics2D g2d, int width, int height) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
    }

    private void drawGrid(Graphics2D g2d, int padding, int chartHeight, int height) {
        g2d.setColor(GRID_COLOR);
        g2d.setStroke(new BasicStroke(1));

        int gridLines = 5;
        for (int i = 0; i <= gridLines; i++) {
            int y = (int) (height - padding - (i * (double) chartHeight / gridLines));
            g2d.drawLine(padding, y, getWidth() - padding, y);

            if (results != null && !results.isEmpty()) {
                int maxVal = results.stream().mapToInt(r -> r.pageFaults).max().orElse(10);
                int val = (int)(i * (maxVal * 1.2) / gridLines);
                g2d.setColor(TEXT_COLOR);
                g2d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
                g2d.drawString(String.valueOf(val), padding - 40, y + 5);
                g2d.setColor(GRID_COLOR);
            }
        }
    }

    private void drawBars(Graphics2D g2d, int padding, int height, int chartHeight,
                         int maxFaults, int chartWidth) {
        int barWidth = Math.min(60, chartWidth / (results.size() * 2));
        int spacing = chartWidth / results.size();

        for (int i = 0; i < results.size(); i++) {
            Simulador.SimulationResult result = results.get(i);

            int x = padding + (i * spacing) + (spacing - barWidth) / 2;
            int barHeight = (int) ((double) result.pageFaults / maxFaults * chartHeight);
            int y = height - padding - barHeight;

            Color baseColor = CHART_COLORS[i % CHART_COLORS.length];
            g2d.setColor(baseColor);
            g2d.fillRoundRect(x, y, barWidth, barHeight, 10, 10);

            g2d.setColor(TEXT_COLOR);
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 14));
            String val = String.valueOf(result.pageFaults);
            g2d.drawString(val, x + (barWidth - g2d.getFontMetrics().stringWidth(val)) / 2, y - 10);

            g2d.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            String name = result.algorithmName;
            g2d.drawString(name, x + (barWidth - g2d.getFontMetrics().stringWidth(name)) / 2, height - padding + 25);
        }
    }

    private void drawAxes(Graphics2D g2d, int padding, int height, int chartHeight, int maxFaults) {
        g2d.setColor(AXIS_COLOR);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(padding, height - padding, getWidth() - padding, height - padding);
        g2d.drawLine(padding, padding, padding, height - padding);
    }

    private void drawLegend(Graphics2D g2d, int width) {
        g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
        g2d.setColor(new Color(15, 23, 42));
        String title = "Faltas de Página por Algoritmo";
        g2d.drawString(title, 80, 40);
    }
}
