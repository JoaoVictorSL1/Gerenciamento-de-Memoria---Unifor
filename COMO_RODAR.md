# 🚀 COMO RODAR - GUIA RÁPIDO

## ⚡ Forma Mais Fácil (Recomendado)

### Opção 1: Duplo Clique no Batch
```
📁 Abra a pasta: c:/Users/joaov/Desktop/Substituição de Memoria

👉 Duplo-clique em: run-modern.bat

✓ A aplicação abrirá automaticamente em 2-3 segundos!
```

---

## 💻 Se Preferir Usar o Terminal

### Passo 1: Abrir o Terminal
```
Windows: Tecla [Win] + [R]
         Digite: cmd
         Pressione: Enter
```

### Passo 2: Navegar até a Pasta
```bash
cd "c:/Users/joaov/Desktop/Substituição de Memoria"
```

### Passo 3: Compilar (Primeira Vez Apenas)
```bash
javac *.java
```
Você verá mensagens como `javac is compiling...` (normal)

### Passo 4: Executar
```bash
java ModernSimulatorGUI
```

✓ A janela do simulador abrirá!

---

## 📋 Passo a Passo Detalhado (Com Imagens)

### 1️⃣ Abra a Pasta
```
C:\Users\joaov\Desktop\Substituição de Memoria

Dentro você verá:
├── run-modern.bat        ← CLIQUE AQUI
├── ModernSimulatorGUI.java
├── PageReplacementSimulator.java
└── (outros arquivos)
```

### 2️⃣ Clique Duplo em `run-modern.bat`
```
Um terminal aparecerá mostrando:
========================================
Simulador de Substitui​cão de Paginas
Versão Moderna com Design Profissional
========================================

Compilando arquivos Java...
Compilação concluída com sucesso!

Iniciando aplicação...
```

### 3️⃣ Aguarde a Janela Abrir
Demore ~2-3 segundos e você verá:
```
╔════════════════════════════════════════════════╗
║  📊 Simulador de Substituição de Páginas       ║
║  Analise o desempenho de diferentes algoritmos║
╚════════════════════════════════════════════════╝
```

---

## 🎯 Usando o Simulador

### Passo 1: Insira uma Sequência
```
Campo: "Sequência de Páginas"

Digite exemplo: 1 2 3 4 1 2 5 1 2 3 4 5

Ou use: 1,2,3,4,1,2,5  (com vírgula também funciona)
```

### Passo 2: Defina Tamanho da Memória
```
Campo: "Memória (frames)"

Altere para: 3 (ou outro valor de 1-20)
```

### Passo 3: Clique no Botão
```
Botão: "▶ Executar Simulação"

Clique e aguarde ~1 segundo
```

### Passo 4: Veja os Resultados
```
Três abas aparecem:

📊 Gráfico       ← Mostra barras coloridas
📋 Tabela        ← Mostra números
📝 Log           ← Mostra detalhes
```

---

## ⚠️ Se Não Funcionar

### Erro 1: "Java não reconhecido"
```
Solução: Java não está instalado

👉 Baixe em: https://www.java.com/download
   (Versão mais recente)
   
Depois reinicie o terminal
```

### Erro 2: "Arquivo não encontrado"
```
Verifique:
✓ Você está na pasta correta?
✓ Os arquivos .java estão presentes?
✓ Digitou o caminho certo no cd?

Tente:
cd c:\Users\joaov\Desktop\Substituição\ de\ Memoria
(escape os espaços com \)
```

### Erro 3: Compilação falha
```
✓ Verifique se tem Java instalado
✓ Tente: javac -version
✓ Se funcionar, delete arquivos .class antigos

Depois tente compilar novamente
```

---

## 🎮 Exemplo Completo de Uso

### Terminal Passo-a-Passo

```
C:\Users\joaov\Desktop> cd "Substituição de Memoria"

C:\Users\joaov\Desktop\Substituição de Memoria> javac *.java
(... compilando ...)

C:\Users\joaov\Desktop\Substituição de Memoria> java ModernSimulatorGUI
(janela abre aqui)

[SIMULADOR ABERTO]

Digite na sequência: 1 2 3 4 1 2 5
Memória: 3
Clique: Executar Simulação

[RESULTADOS]
FIFO: 9 faltas
LRU: 10 faltas
Clock: 10 faltas
Ótimo: 7 faltas ⭐

✓ PRONTO!
```

---

## 📊 Testando com Exemplos

### Exemplo 1 - Simples
```
Sequência: 1 2 3 4 1 2 5 1 2 3 4 5
Memória: 3
Resultado esperado: Gráfico mostrando 4 barras diferentes
```

### Exemplo 2 - Padrão
```
Sequência: 0 1 2 3 0 1 2 3 0 1 2 3
Memória: 2
Resultado esperado: Todos algoritmos com muitas faltas
```

### Exemplo 3 - Seu Próprio
```
Sequência: 5 7 2 8 3 5 2 9 1 5
Memória: 4
Resultado esperado: Resultados variados
```

---

## 🎯 Versões Disponíveis

### Versão Moderna (Recomendada) ⭐
```bash
java ModernSimulatorGUI

✓ Cores bonitas
✓ Interface elegante
✓ Gráficos com gradiente
```

### Versão Clássica
```bash
java SimulatorGUI

✓ Interface simples
✓ Mesma funcionalidade
✓ Sem cores elaboradas
```

### Testes Automáticos
```bash
java SimulatorTest

✓ Executa 4 testes
✓ Mostra resultados
✓ Sem GUI (apenas console)
```

---

## 🔧 Troubleshooting Rápido

| Problema | Solução |
|----------|---------|
| Nada acontece | Verifique se Java está instalado: `java -version` |
| "Cannot find symbol" | Delete .class antigos e compile novamente |
| Janela não abre | Aguarde mais 5 segundos |
| Botão não responde | Insira valores válidos (números) |
| Erro "NumberFormat" | Use apenas números, separe por espaço/vírgula |

---

## 💾 Salvar Resultados

Para capturar o gráfico para seu relatório:

### Opção 1: Print Screen
```
1. Execute o simulador
2. Execute uma simulação
3. Clique em "📊 Gráfico"
4. Pressione: Ctrl + PrintScreen
5. Abra Paint
6. Ctrl + V (colar)
7. Salve como imagem
```

### Opção 2: Ferramenta de Captura
```
1. Pressione: Windows + Shift + S
2. Selecione a área
3. Salva automaticamente
```

---

## ✅ Checklist para Começar

- [ ] Java está instalado (java -version)
- [ ] Pasta existe: c:/Users/joaov/Desktop/Substituição de Memoria
- [ ] Arquivos .java estão dentro
- [ ] Duplo-clique em run-modern.bat (OU execute via terminal)
- [ ] Aguarde 2-3 segundos
- [ ] Janela aparece com interface azul
- [ ] Digite uma sequência
- [ ] Clique "Executar Simulação"
- [ ] Veja gráfico colorido
- [ ] ✓ SUCESSO!

---

## 🎓 Próximos Passos

1. **Teste com vários exemplos**
   ```
   Capture os gráficos para seu relatório
   ```

2. **Analise os resultados**
   ```
   Qual algoritmo foi melhor?
   Por quê?
   ```

3. **Crie seu relatório PDF**
   ```
   Use as imagens capturadas
   Use RELATORIO_MODELO.md como referência
   ```

4. **Entregue no AVA**
   ```
   PDF + Link do repositório (opcional)
   ```

---

**Dúvida?** Leia `README.md` ou `COMECE_AQUI.md` na pasta do projeto!

**Pronto para começar? Execute agora:**
```
run-modern.bat
```
🚀✨
