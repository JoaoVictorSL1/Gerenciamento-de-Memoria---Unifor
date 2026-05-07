# RELATÓRIO TÉCNICO - SIMULADOR DE SUBSTITUIÇÃO DE PÁGINAS

##  Informações do Projeto

**Disciplina**: Sistemas Operacionais  
**Tema**: Gerenciamento de Memória Virtual - Algoritmos de Substituição de Páginas  
**Desenvolvedor**: [João Victor Lira]  
**Status**: Finalizado e Testado  

---

## 1. Introdução

O gerenciamento eficiente da memória virtual é um dos pilares de desempenho dos sistemas operacionais modernos. Quando a memória física (RAM) atinge sua capacidade máxima, o sistema deve decidir qual página remover para dar lugar a novos dados. Este processo é controlado por **Algoritmos de Substituição de Páginas**.

Este projeto apresenta um simulador educacional desenvolvido em Java que implementa e compara o desempenho dos principais algoritmos utilizados academicamente e em sistemas reais.

---

## 2. Objetivos

*   Compreender o funcionamento prático da substituição de páginas.
*   Implementar e testar algoritmos clássicos e heurísticos.
*   Comparar a eficiência (faltas de página) entre diferentes métodos.
*   Fornecer uma interface visual moderna para facilitar a análise de dados.

---

## 3. Algoritmos Implementados

O simulador conta com 5 métodos de substituição:

1.  **FIFO (First In, First Out)**: A página mais antiga na memória é a primeira a ser removida.
2.  **LRU (Least Recently Used)**: Substitui a página que não foi utilizada por mais tempo.
3.  **Clock (Segunda Chance)**: Uma otimização do FIFO que usa um bit de referência para dar uma "segunda chance" a páginas acessadas recentemente.
4.  **Ótimo (Optimal)**: O benchmark teórico que remove a página que será usada mais longe no futuro (requer conhecimento prévio da sequência).
5.  **LFU (Least Frequently Used)**: Remove a página com a menor frequência de acesso acumulada.

---

## 4. Guia de Execução

Para garantir a facilidade de uso, o projeto foi organizado com nomes diretos e scripts automatizados.

###  Forma Rápida (Windows)
Navegue até a pasta `scripts` e execute:
*   **`run-modern.bat`**: Compila e abre o Dashboard moderno automaticamente.

###  Via Terminal
```bash
# Compilar todos os arquivos
javac -d build src/*.java

# Executar o ponto de entrada principal
java -cp build Main
```

---

## 5. Estrutura do Código

*   **`Main.java`**: Gerencia a inicialização, exibindo resultados no console e abrindo a interface gráfica.
*   **`Simulador.java`**: O "núcleo" do projeto, onde estão implementadas as lógicas de todos os algoritmos.
*   **`Interface.java`**: Define o Dashboard moderno com o painel lateral de controle.
*   **`Grafico.java`**: Responsável pela renderização visual dos gráficos de desempenho.

---

## 6. Análise de Resultados

Ao executar o simulador, os resultados são apresentados em três formatos:
1.  **Console**: Resumo rápido das faltas de página.
2.  **Dashboard (Gráfico)**: Comparação visual imediata da eficiência.
3.  **Tabela de Dados**: Exibição detalhada incluindo a porcentagem de eficiência de cada método.

---

## 7. Referências

*   TANENBAUM, A. S. **Sistemas Operacionais Modernos**. 4ª ed. Pearson, 2016.
*   SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G. **Operating System Concepts**. 9ª ed. Wiley, 2012.

---
**Maio de 2026**
