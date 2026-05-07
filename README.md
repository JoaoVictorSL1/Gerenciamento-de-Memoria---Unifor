# Simulador de Substituição de Páginas em Java

## 📋 Descrição

Simulador educacional que implementa 4 algoritmos de substituição de páginas para gerenciamento de memória virtual:

1. **FIFO (First In, First Out)** - Remove a página mais antiga
2. **LRU (Least Recently Used)** - Remove a página menos recentemente usada
3. **Clock (Segunda Chance)** - Algoritmo circular com bit de referência
4. **Ótimo** - Remove a página usada mais longe no futuro (referência teórica)

## 🚀 Como Usar

### Requisitos
- Java 8 ou superior
- IDE Java (Eclipse, IntelliJ, VS Code + Extension Pack for Java) OU terminal

### Compilação e Execução

#### Opção 1: Usando o terminal (Windows)
```bash
# Navegue até o diretório do projeto
cd "c:/Users/joaov/Desktop/Substituição de Memoria"

# Compile os arquivos
javac *.java

# Execute a aplicação
java SimulatorGUI
```

#### Opção 2: Usando IDE
1. Crie um novo projeto Java
2. Copie os arquivos `PageReplacementSimulator.java` e `SimulatorGUI.java` para o projeto
3. Execute a classe `SimulatorGUI`

### Usando a Aplicação

1. **Entrada de Sequência de Páginas**: Digite as páginas separadas por espaço, vírgula ou ponto-e-vírgula
   - Exemplo: `1 2 3 4 1 2 5 1 2 3 4 5`
   - Exemplo: `1,2,3,4,1,2,5,1,2,3,4,5`

2. **Tamanho da Memória**: Especifique quantos frames (slots) a memória possui
   - Use o spinner para ajustar (1-20)

3. **Executar Simulação**: Clique no botão "Executar Simulação"

### Abas de Resultados

- **Gráfico Comparativo**: Visualização em barras comparando faltas de página
- **Tabela de Resultados**: Exibição em tabela dos resultados
- **Log de Execução**: Trace detalhado de cada algoritmo

## 📊 Exemplos de Entrada

### Exemplo 1 - Sequência simples
```
Sequência: 1 2 3 4 1 2 5 1 2 3 4 5
Tamanho: 3 frames
```

### Exemplo 2 - Sequência com padrão
```
Sequência: 0 1 2 3 0 1 2 3 0 1 2 3
Tamanho: 3 frames
```

## 🎯 Objetivos Educacionais

Este simulador permite:
- Compreender o funcionamento de diferentes algoritmos de substituição
- Comparar a eficiência entre algoritmos
- Visualizar o impacto do tamanho da memória nas faltas de página
- Analisar o comportamento de cada algoritmo em diferentes padrões de acesso

## 📈 Comparativo de Algoritmos

| Algoritmo | Complexidade | Implementação | Eficiência |
|-----------|-------------|---------------|-----------|
| FIFO | O(1) | Simples | Baixa (pode causar anomalia de Belady) |
| LRU | O(n) | Média | Alta (próxima ao ótimo) |
| Clock | O(1) amortizado | Média | Média-Alta (bom compromisso) |
| Ótimo | O(n²) | Complexa | Máxima (referência teórica) |

## 🔧 Estrutura do Código

```
PageReplacementSimulator.java
├── fifo()           - Implementação do algoritmo FIFO
├── lru()            - Implementação do algoritmo LRU
├── clock()          - Implementação do algoritmo Clock
├── optimal()        - Implementação do algoritmo Ótimo
└── SimulationResult - Classe para armazenar resultados

SimulatorGUI.java
├── SimulatorGUI     - Frame principal da aplicação
├── ChartPanel       - Painel para desenhar gráfico
└── Métodos de interface
```

## 💡 Notas Importantes

- O algoritmo **Ótimo** é de referência teórica: ele requer conhecimento futuro, que não é possível em sistemas reais
- O algoritmo **LRU** é geralmente considerado o melhor em relação custo-benefício
- O algoritmo **Clock** oferece uma aproximação eficiente do LRU com menos overhead
- **FIFO** pode sofrer da anomalia de Belady: adicionar mais frames pode aumentar faltas

## 📝 Autor
Desenvolvido como projeto acadêmico sobre gerenciamento de memória virtual.

## 📚 Referências
- Tanenbaum, A. S. - Sistemas Operacionais Modernos
- https://sdpm-simulator.netlify.app
