# 🧮 Calculadora de Impostos Profissional

Este projeto é uma ferramenta robusta para o cálculo de tributação sobre salário bruto, desenvolvida com foco em **precisão financeira** e **tratamento de exceções**.

## 🛠️ Tecnologias e Decisões Técnicas

### 1. Precisão com BigDecimal
Diferente de tipos primitivos como `float` ou `double`, utilizei a classe **`BigDecimal`**. 
* **Por quê?** Em cálculos financeiros, o arredondamento binário de tipos de ponto flutuante pode gerar erros de centavos. O `BigDecimal` garante o controle total sobre a escala e o arredondamento.

### 2. Resiliência com Try-Catch
O sistema implementa um bloco de tratamento de erros para lidar com entradas inválidas do usuário.
* **Fluxo:** Se o usuário digitar um texto onde se espera um número, o sistema captura a `Exception`, exibe uma mensagem amigável e utiliza o comando `continue` para não interromper a execução do loop.

### 3. Persistência de Dados (CSV)
Os cálculos realizados são exportados para um arquivo `.csv`, permitindo auditoria posterior dos dados processados.

---
## 🚀 Como Executar

1. Certifique-se de ter o **JDK 17+** instalado.
2. Compile o arquivo:
   `javac CalculadorImposto.java`
3. Execute a aplicação:
   `java CalculadorImposto`
