import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CalculadorImposto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resposta = "S";

        int qtdCalculada = 0;
        int qtdIsentos = 0;
        BigDecimal arrecadacaoTotal = BigDecimal.ZERO;
        ArrayList<String> historico = new ArrayList<>();

        while (resposta.equalsIgnoreCase("S")) {

            try {

                // SOLICITE O SALÁRIO BRUTO DO USUÁRIO
                System.out.println("\nDigite o nome do funcionário:");
                String nome = sc.nextLine();
                System.out.print("Digite o salário bruto: ");
                BigDecimal salarioBruto = new BigDecimal(sc.nextLine());

                BigDecimal imposto;

                // LÓGICA DO IF / ELSE IF / ELSE AQUI
                if (salarioBruto.compareTo(new BigDecimal("-0.01")) < 0) {
                    System.out.println("SALÁRIO INVÁLIDO! O salário bruto deve ser um valor positivo.");
                    continue;
                } else if (salarioBruto.compareTo(new BigDecimal("2000.00")) <= 0) {
                    imposto = salarioBruto.multiply(new BigDecimal("0.00"));
                    qtdIsentos++;
                } else if (salarioBruto.compareTo(new BigDecimal("5000.00")) <= 0) {
                    imposto = salarioBruto.multiply(new BigDecimal("0.10"));
                } else {
                    imposto = salarioBruto.multiply(new BigDecimal("0.20"));
                }

                // CALULO DO SALÁRIO LÍQUIDO E ARRECADAÇÃO TOTAL
                qtdCalculada++;
                arrecadacaoTotal = arrecadacaoTotal.add(imposto);
                BigDecimal salarioLiquido = salarioBruto.subtract(imposto);
                historico.add(nome + ";" + salarioBruto + ";" + imposto + ";" + salarioLiquido);

                System.out.println("Cálculo realizado para: " + nome);

            } catch (Exception e) {
                System.out.println("VALOR INVÁLIDO! Por favor, use apenas números e pontos/vírgulas.");
                continue;// VOLTA PARA O INÍCIO DO LOOP PARA NOVA TENTATIVA
            }

            System.out.println("Deseja calcular para outro salário? (S/N): ");
            resposta = sc.nextLine();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("relatorio_impostos.csv"))) {

            writer.println("Nome;Salário Bruto;Imposto;Salário Líquido");

            for (String funcionario : historico) {
                writer.println(funcionario);
            }
            System.out.println("\n[SUCESSO] Planilha 'relatorio_impostos.csv' gerada!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o histórico: " + e.getMessage());
        }

        // RELATÓRIO FINAL
        System.out.println("\nRESUMO DO DIA");
        System.out.println("\n--- LISTA DE FUNCIONÁRIOS CALCULADOS ---");

        // IMPRIME O HISTÓRICO DE FUNCIONÁRIOS CALCULADOS
        for (String funcionario : historico) {
            String[] dados = funcionario.split(";");

            BigDecimal bruto = new BigDecimal(dados[1]).setScale(2, java.math.RoundingMode.HALF_UP);
            BigDecimal imp = new BigDecimal(dados[2]).setScale(2, java.math.RoundingMode.HALF_UP);
            BigDecimal liq = new BigDecimal(dados[3]).setScale(2, java.math.RoundingMode.HALF_UP);

            System.out.println(
                    "FUNCIONÁRIO: " + dados[0] + " | SALÁRIO BRUTO: R$ " + bruto + " | IMPOSTO: R$ " + imp
                            + " | SALÁRIO LÍQUIDO: R$ " + liq);
        }
        System.out.println("Quantidade de salários calculados: " + qtdCalculada);
        System.out.println("Quantidade de salários isentos: " + qtdIsentos);
        System.out.println(
                "Total de impostos arrecadados: R$ " + arrecadacaoTotal.setScale(2, java.math.RoundingMode.HALF_UP));
        System.out.println("------------------------------");

        System.out.println("Programa encerrado.");
        sc.close();
    }
}