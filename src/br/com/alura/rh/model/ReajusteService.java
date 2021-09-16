package br.com.alura.rh.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Funcionario;

public class ReajusteService {
	public void reajustarSalarioDoFuncionario(Funcionario funcionario, BigDecimal aumento) {
			BigDecimal salarioAtual = funcionario.getSalario(); 
			BigDecimal percentualReajuste = aumento.divide(salarioAtual, RoundingMode.HALF_UP);
			if (percentualReajuste.compareTo(new BigDecimal("0.4")) > 0) {
				throw new ValidacaoException("Reajuste nao pode ser superior a 40% do salario!");
			}
			
			LocalDate dataUltimoReajuste = funcionario.getDataUltimoReajuste();
			LocalDate dataAtual = LocalDate.now();
			//Devolverá o número de meses entre a data de reajuste e a data atual.
			long mesesDesseUltimoReajuste = ChronoUnit.MONTHS.between(dataUltimoReajuste, dataAtual);
			if (mesesDesseUltimoReajuste < 6) {
				throw new ValidacaoException("Intervalo entre reajustes devem ser de no mínimo 6 meses");
			}
			
			BigDecimal salarioReajustado = salarioAtual.add(aumento); 
			funcionario.atualizarSalario(salarioReajustado); 
			//passamos salarioReajustado como parâmetro para o método
			
	}
}
