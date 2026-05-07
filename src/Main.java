import java.util.List;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        int[] defaultPages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int defaultFrameSize = 3;

        printHeader();

        System.out.println("--- RESULTADOS NO CONSOLE ---");
        System.out.print("Sequência de páginas: ");
        for (int p : defaultPages) System.out.print(p + " ");
        System.out.println("\nTamanho da Memória: " + defaultFrameSize + " frames\n");

        List<Simulador.SimulationResult> results = 
            Simulador.runAllAlgorithms(defaultPages, defaultFrameSize);

        for (int i = 0; i < results.size(); i++) {
            System.out.println("- Método " + (i + 1) + " (" + results.get(i).algorithmName + ") - " + 
                               results.get(i).pageFaults + " faltas de página");
        }

        System.out.println("\n--- INICIANDO INTERFACE GRÁFICA (EXTRA) ---\n");

        SwingUtilities.invokeLater(() -> {
            new Interface();
        });
    }

    private static void printHeader() {
        System.out.println("============================================================");
        System.out.println("  SIMULADOR DE SUBSTITUIÇÃO DE PÁGINAS - GERENCIAMENTO DE MEMÓRIA");
        System.out.println("============================================================\n");
    }
}
