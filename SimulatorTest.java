/**
 * Classe de exemplo para testar o simulador sem GUI
 * Útil para testes automatizados e geração de relatórios
 */
public class SimulatorTest {

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  SIMULADOR DE SUBSTITUIÇÃO DE PÁGINAS - TESTES            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // Teste 1: Sequência simples com 3 frames
        testSequence("Teste 1: Sequência Simples", new int[]{1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5}, 3);

        // Teste 2: Sequência repetitiva com 2 frames
        testSequence("Teste 2: Padrão Repetitivo", new int[]{0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3}, 2);

        // Teste 3: Sequência com anomalia de Belady
        testSequence("Teste 3: Anomalia de Belady", new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5}, 3);

        // Teste 4: Sequência longa
        testSequence("Teste 4: Sequência Longa", new int[]{1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6, 7, 1, 2, 3, 4, 5}, 4);
    }

    static void testSequence(String testName, int[] pages, int frameSize) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(testName);
        System.out.println("─────────────────────────────────────────────────────────");

        System.out.print("Sequência: ");
        for (int page : pages) {
            System.out.print(page + " ");
        }
        System.out.println("\nTamanho da Memória: " + frameSize + " frames");
        System.out.println();

        var results = PageReplacementSimulator.runAllAlgorithms(pages, frameSize);

        // Encontrar o melhor resultado
        int minFaults = results.stream().mapToInt(r -> r.pageFaults).min().orElse(0);

        for (PageReplacementSimulator.SimulationResult result : results) {
            String marker = result.pageFaults == minFaults ? " ← MELHOR" : "";
            System.out.printf("%-15s: %3d faltas de página%s%n",
                result.algorithmName, result.pageFaults, marker);
        }

        System.out.println();
    }
}
