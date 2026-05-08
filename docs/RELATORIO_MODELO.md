# RELATÓRIO - SIMULADOR DE SUBSTITUIÇÃO DE PÁGINAS
---

## 1. Introdução

O gerenciamento eficiente da memória virtual é crucial para o desempenho dos sistemas operacionais. Quando a memória física não é suficiente para manter todas as páginas em execução, o sistema operacional utiliza algoritmos de substituição de páginas para decidir qual página deve ser removida da memória quando uma falta de página ocorre.

Este trabalho apresenta a implementação de um simulador educacional que permite comparar o desempenho de quatro algoritmos de substituição de páginas: FIFO, LRU, Clock e Ótimo.

---

## 2. Objetivos

- Compreender o funcionamento dos principais algoritmos de substituição de páginas
- Implementar os algoritmos em linguagem Java
- Comparar o desempenho entre os algoritmos
- Analisar o impacto do tamanho da memória nas faltas de página
- Visualizar graficamente os resultados comparativos

---

## 3. Algoritmos Implementados

### 3.1 FIFO (First In, First Out)

**Descrição**: A página mais antiga na memória é a primeira a ser substituída.

**Características**:
- Simples de implementar e entender
- Baixo overhead computacional
- Pode sofrer da anomalia de Belady
- Apresenta desempenho geralmente inferior aos outros algoritmos

**Complexidade**: O(1)

### 3.2 LRU (Least Recently Used)

**Descrição**: A página menos recentemente usada é substituída.

**Características**:
- Aproximação prática do algoritmo ótimo
- Requer manutenção de histórico de uso
- Maior complexidade que FIFO
- Desempenho geralmente superior

**Complexidade**: O(n)

### 3.3 Clock (Segunda Chance)

**Descrição**: Simula um relógio circular com bits de referência.

**Características**:
- Compromisso entre FIFO e LRU
- Utiliza bit de referência para rastrear uso
- Melhor overhead que LRU
- Desempenho intermediário

**Complexidade**: O(1) amortizado

### 3.4 Ótimo (Optimal)

**Descrição**: Remove a página que será usada mais longe no futuro.

**Características**:
- Referência teórica (requer conhecimento do futuro)
- Impossível de implementar em sistemas reais
- Serve como benchmark para outros algoritmos
- Sempre produz o menor número de faltas

**Complexidade**: O(n²)

---

## 4. Resultados dos Testes

### Teste 1: Sequência Simples (3 frames)

**Sequência**: 1 2 3 4 1 2 5 1 2 3 4 5

| Algoritmo | Faltas de Página |
|-----------|-----------------|
| FIFO      | 9               |
| LRU       | 10              |
| Clock     | 10              |
| Ótimo     | 7               |

**Análise**: O algoritmo ótimo apresenta o melhor desempenho, enquanto FIFO, LRU e Clock apresentam desempenho semelhante.

---

### Teste 2: Padrão Repetitivo (2 frames)

**Sequência**: 0 1 2 3 0 1 2 3 0 1 2 3

| Algoritmo | Faltas de Página |
|-----------|-----------------|
| FIFO      | 12              |
| LRU       | 12              |
| Clock     | 12              |
| Ótimo     | 9               |

**Análise**: Todos os algoritmos heurísticos produzem o mesmo resultado neste caso, indicando uma sequência particularmente desafiadora. O ótimo demonstra significativa superioridade.

---

### Teste 3: Anomalia de Belady (3 frames)

**Sequência**: 1 2 3 4 5 1 2 3 4 5

| Algoritmo | Faltas de Página |
|-----------|-----------------|
| FIFO      | 10              |
| LRU       | 10              |
| Clock     | 10              |
| Ótimo     | 7               |

**Análise**: Demonstra como FIFO pode não ser adequado, embora neste caso todos os heurísticos tenham produzido resultado idêntico.

---

### Teste 4: Sequência Longa (4 frames)

**Sequência**: 1 2 3 4 5 1 2 3 4 5 6 7 1 2 3 4 5

| Algoritmo | Faltas de Página |
|-----------|-----------------|
| FIFO      | 17              |
| LRU       | 17              |
| Clock     | 17              |
| Ótimo     | 10              |

**Análise**: Com sequências mais longas, a vantagem do algoritmo ótimo aumenta significativamente.

---

## 5. Gráfico Comparativo

[Insira aqui uma captura de tela do gráfico gerado pela GUI]

```
Comparativo de Faltas de Página (Teste 1)

│
│     ■ FIFO   ■ LRU    ■ Clock   ■ Ótimo
│
10 │ ┌─┐
   │ │9│ ┌──┐ ┌──┐
 9 │ └─┘ │10│ │10│
   │     └──┘ └──┘ ┌─┐
 8 │               │7│
   │               └─┘
 7 │
   └────────────────────────
    FIFO  LRU  Clock Ótimo
```

---

## 6. Análise e Discussão

### 6.1 Comparação Entre Algoritmos

1. **FIFO**: Mais simples, mas frequentemente não é a melhor opção. Sofre da anomalia de Belady.

2. **LRU**: Oferece bom desempenho em muitos cenários, próximo ao ótimo em casos práticos.

3. **Clock**: Oferece um bom compromisso entre simplicidade e desempenho.

4. **Ótimo**: Serve como referência teórica, sempre superior aos outros, mas impraticável.

### 6.2 Observações

- O algoritmo ótimo consistentemente produz os melhores resultados
- LRU e Clock oferecem bom desempenho prático com overhead aceitável
- FIFO, apesar de simples, não é recomendado para aplicações de produção
- O tamanho da memória influencia significativamente o desempenho geral

---

## 7. Conclusão

Este trabalho permitiu compreender e implementar os principais algoritmos de substituição de páginas. Os resultados experimentais confirmam a teoria, demonstrando que:

1. Algoritmos mais sofisticados geralmente têm melhor desempenho
2. O trade-off entre complexidade e desempenho é importante
3. Diferentes cargas de trabalho podem favorecer diferentes algoritmos
4. O algoritmo ótimo serve como excelente benchmark

A implementação de uma interface gráfica permitiu visualizar claramente as diferenças entre os algoritmos.

---

## 8. Referências

- TANENBAUM, A. S. **Sistemas Operacionais Modernos**. 4ª ed. Pearson, 2016.
- SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G. **Operating System Concepts**. 9ª ed. Wiley, 2012.
- Simulador de referência: https://sdpm-simulator.netlify.app

---

## 9. Apêndice: Instruções de Uso

### Como Compilar

```bash
cd "Substituição de Memoria"
javac *.java
```

### Como Executar

```bash
java SimulatorGUI
```

### Como Usar a Interface

1. Digite uma sequência de páginas (ex: 1 2 3 4 1 2 5)
2. Defina o tamanho da memória
3. Clique em "Executar Simulação"
4. Visualize os resultados nas abas

--- 
Link Readme: https://github.com/JoaoVictorSL1/Gerenciamento-de-Memoria---Unifor/
