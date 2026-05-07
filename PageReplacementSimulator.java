import java.util.*;

/**
 * Classe principal que implementa os 4 algoritmos de substituição de páginas:
 * FIFO, LRU, Clock e Ótimo
 */
public class PageReplacementSimulator {

    // ==================== FIFO ====================
    public static SimulationResult fifo(int[] pages, int frameSize) {
        Queue<Integer> frames = new LinkedList<>();
        Set<Integer> framesSet = new HashSet<>();
        int pageFaults = 0;
        List<String> trace = new ArrayList<>();

        for (int page : pages) {
            if (framesSet.contains(page)) {
                trace.add("Página " + page + " já está na memória");
            } else {
                pageFaults++;
                if (framesSet.size() < frameSize) {
                    frames.add(page);
                    framesSet.add(page);
                } else {
                    int removed = frames.poll();
                    framesSet.remove(removed);
                    frames.add(page);
                    framesSet.add(page);
                    trace.add("Página " + removed + " removida (FIFO). Página " + page + " inserida.");
                }
            }
        }

        return new SimulationResult("FIFO", pageFaults, trace);
    }

    // ==================== LRU ====================
    public static SimulationResult lru(int[] pages, int frameSize) {
        Map<Integer, Integer> lastUsed = new HashMap<>();
        Set<Integer> framesSet = new HashSet<>();
        int pageFaults = 0;
        int timestamp = 0;
        List<String> trace = new ArrayList<>();

        for (int page : pages) {
            timestamp++;
            if (framesSet.contains(page)) {
                lastUsed.put(page, timestamp);
            } else {
                pageFaults++;
                if (framesSet.size() < frameSize) {
                    framesSet.add(page);
                    lastUsed.put(page, timestamp);
                } else {
                    // Remove a página menos recentemente usada
                    int lruPage = framesSet.stream()
                            .min(Comparator.comparingInt(p -> lastUsed.getOrDefault(p, 0)))
                            .orElse(-1);
                    framesSet.remove(lruPage);
                    lastUsed.remove(lruPage);
                    framesSet.add(page);
                    lastUsed.put(page, timestamp);
                    trace.add("Página " + lruPage + " removida (LRU). Página " + page + " inserida.");
                }
            }
        }

        return new SimulationResult("LRU", pageFaults, trace);
    }

    // ==================== CLOCK (Segunda Chance) ====================
    public static SimulationResult clock(int[] pages, int frameSize) {
        List<Integer> frames = new ArrayList<>();
        Map<Integer, Boolean> referenceBit = new HashMap<>();
        int pageFaults = 0;
        int pointer = 0;
        List<String> trace = new ArrayList<>();

        for (int page : pages) {
            if (frames.contains(page)) {
                referenceBit.put(page, true);
            } else {
                pageFaults++;
                if (frames.size() < frameSize) {
                    frames.add(page);
                    referenceBit.put(page, false);
                } else {
                    // Procura uma página com bit de referência 0
                    while (referenceBit.getOrDefault(frames.get(pointer), false)) {
                        referenceBit.put(frames.get(pointer), false);
                        pointer = (pointer + 1) % frameSize;
                    }
                    int removed = frames.get(pointer);
                    frames.set(pointer, page);
                    referenceBit.remove(removed);
                    referenceBit.put(page, false);
                    trace.add("Página " + removed + " removida (Clock). Página " + page + " inserida.");
                    pointer = (pointer + 1) % frameSize;
                }
            }
        }

        return new SimulationResult("Clock", pageFaults, trace);
    }

    // ==================== ÓTIMO ====================
    public static SimulationResult optimal(int[] pages, int frameSize) {
        Set<Integer> frames = new HashSet<>();
        int pageFaults = 0;
        List<String> trace = new ArrayList<>();

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (frames.contains(page)) {
                // Página já está em memória
                continue;
            }

            pageFaults++;

            if (frames.size() < frameSize) {
                frames.add(page);
            } else {
                // Encontra a página que será usada mais longe no futuro
                int pageToRemove = findFurthestPage(pages, i + 1, frames);
                frames.remove(pageToRemove);
                frames.add(page);
                trace.add("Página " + pageToRemove + " removida (Ótimo). Página " + page + " inserida.");
            }
        }

        return new SimulationResult("Ótimo", pageFaults, trace);
    }

    // Função auxiliar para o algoritmo Ótimo
    private static int findFurthestPage(int[] pages, int startIndex, Set<Integer> frames) {
        int furthestPage = -1;
        int furthestDistance = -1;

        for (int page : frames) {
            int distance = Integer.MAX_VALUE;
            for (int i = startIndex; i < pages.length; i++) {
                if (pages[i] == page) {
                    distance = i - startIndex;
                    break;
                }
            }
            if (distance > furthestDistance) {
                furthestDistance = distance;
                furthestPage = page;
            }
        }

        return furthestPage == -1 ? frames.iterator().next() : furthestPage;
    }

    /**
     * Executa todos os 4 algoritmos e retorna os resultados
     */
    public static List<SimulationResult> runAllAlgorithms(int[] pages, int frameSize) {
        List<SimulationResult> results = new ArrayList<>();
        results.add(fifo(pages, frameSize));
        results.add(lru(pages, frameSize));
        results.add(clock(pages, frameSize));
        results.add(optimal(pages, frameSize));
        return results;
    }

    // Classe interna para armazenar resultados
    public static class SimulationResult {
        public String algorithmName;
        public int pageFaults;
        public List<String> trace;

        public SimulationResult(String name, int faults, List<String> trace) {
            this.algorithmName = name;
            this.pageFaults = faults;
            this.trace = trace;
        }

        @Override
        public String toString() {
            return algorithmName + " - " + pageFaults + " faltas de página";
        }
    }
}
