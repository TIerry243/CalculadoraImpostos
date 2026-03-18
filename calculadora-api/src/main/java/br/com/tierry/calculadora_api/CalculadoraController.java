package br.com.tierry.calculadora_api;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculadoraController {

    @GetMapping("/calcular")
    public String calcularImposto(@RequestParam BigDecimal salario) {
        BigDecimal imposto;
        String faixa;

        // Lógica de Negócio (Regras do IRPF)
        if (salario.compareTo(new BigDecimal("2259.20")) <= 0) {
            imposto = BigDecimal.ZERO;
            faixa = "Isento";
        } else if (salario.compareTo(new BigDecimal("2828.65")) <= 0) {
            imposto = salario.multiply(new BigDecimal("0.075")).subtract(new BigDecimal("169.44"));
            faixa = "7,5%";
        } else {
            imposto = salario.multiply(new BigDecimal("0.275")).subtract(new BigDecimal("896.00"));
            faixa = "27,5% (Teto)";
        }

        BigDecimal impostoFinal = imposto.setScale(2, RoundingMode.HALF_UP);
        BigDecimal salarioLiquido = salario.subtract(impostoFinal);

        return String.format(
                "<h2>Resultado do Cálculo</h2>" +
                        "<b>Salário Bruto:</b> R$ %s<br>" +
                        "<b>Faixa de Imposto:</b> %s<br>" +
                        "<b>Imposto a Pagar:</b> R$ %s<br>" +
                        "<b>Salário Líquido:</b> R$ %s",
                salario, faixa, impostoFinal, salarioLiquido);
    }
}