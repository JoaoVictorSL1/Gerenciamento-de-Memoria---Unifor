#  DOCUMENTAÇÃO COMPLETA DO PROJETO

Este documento unifica todas as informações necessárias para entender, executar e entregar o Simulador de Substituição de Páginas.

---

## 1.  GUIA RÁPIDO: COMO RODAR

A forma mais simples de executar o projeto no Windows é através dos scripts automatizados:

1.  Entre na pasta `scripts`.
2.  Execute o arquivo **`run-modern.bat`**.
3.  O sistema irá compilar o código e abrir o Dashboard automaticamente.

**Via Terminal (Manual):**
```bash
javac -d build src/*.java
java -cp build Main
```

---

## 2. DETALHES TÉCNICOS (ALGORITMOS)

O simulador foca nos 5 principais algoritmos de gerenciamento de memória virtual:

### 2.1 FIFO (First-In, First-Out)
*   **Conceito**: A página que entrou primeiro na memória é a primeira a ser removida.
*   **Prós**: Extremamente simples de implementar.
*   **Contras**: Pode sofrer da Anomalia de Belady (onde aumentar a RAM aumenta as faltas de página).

### 2.2 LRU (Least Recently Used)
*   **Conceito**: Remove a página que não foi acessada por mais tempo.
*   **Prós**: Muito eficiente, aproxima-se do desempenho ótimo em uso real.
*   **Contras**: Exige mais processamento para manter o histórico de uso.

### 2.3 Clock (Segunda Chance)
*   **Conceito**: Uma otimização do FIFO que usa um bit de referência. Se a página foi usada recentemente, ela ganha uma "segunda chance" e não é removida.
*   **Prós**: Muito mais eficiente que o FIFO puro com baixo custo computacional.

### 2.4 Ótimo (Optimal)
*   **Conceito**: Remove a página que demorará mais tempo para ser usada no futuro.
*   **Observação**: É um algoritmo teórico (impossível em sistemas reais pois exige prever o futuro), servindo como benchmark de perfeição.

### 2.5 LFU (Least Frequently Used)
*   **Conceito**: Substitui a página que teve o menor número de acessos totais durante sua permanência na memória.

---

## 3. MANUAL DA INTERFACE (DASHBOARD)

A interface foi projetada como um Painel de Controle moderno:

*   **Painel Lateral (Azul)**: Onde você insere a sequência de páginas (ex: `1 2 3 4...`) e define quantos frames de RAM o simulador possui.
*   **Gráfico Comparativo**: Mostra visualmente qual algoritmo teve menos "Page Faults".
*   **Tabela de Dados**: Exibe os números brutos e calcula a eficiência percentual.
*   **Logs do Sistema**: Um histórico passo a passo de cada página que entrou ou saiu da memória.

---

## 4. INSTRUÇÕES PARA ENTREGA NO AVA

Para realizar a entrega corretamente:

1.  **Gere o PDF**: Use o arquivo `RELATORIO_PARA_PDF.md` como base.
2.  **Preencha seus dados**: Nome e Matrícula.
3.  **Insira o Print**: Capture uma tela do programa rodando e cole no relatório.
4.  **Link**: No relatório, inclua o link para o README ou para o seu repositório se for o caso.
5.  **Formato**: O arquivo final DEVE ser em PDF.

---

## 5. ESTRUTURA DE ARQUIVOS (RESUMO)

*   `Main.java`: Ponto de partida.
*   `Simulador.java`: Lógica matemática.
*   `Interface.java`: Janela e botões.
*   `Grafico.java`: Renderização das barras.

---
**Desenvolvido para a disciplina de Sistemas Operacionais - 2026**
