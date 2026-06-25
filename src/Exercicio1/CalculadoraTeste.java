package Exercicio1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Testes para a classe Calculadora")
public class CalculadoraTeste {

    Calculadora calc = new Calculadora();

    @Test
    public void testarSomaDeDoisNumerosInteiros(){
        // Arrange
        int esperado = 4;

        // Act
        int resultado = calc.somar(2, 2);

        // Assert
        assertEquals(esperado, resultado, "O resultado esperado de 2+2 é 4");
    }

    @Test
    public void testarSubtracaoDeDoisNumerosNegativos(){
        // Arrange
        int esperado = 10;

        // Act
        int resultado = calc.subtrair(20, 10);

        // Assert
        assertEquals(esperado, resultado, "O resultado esperado de 20-10 é 10");
    }

    @Test
    public void testarMultiplicacaoComZero(){
        // Arrange
        int esperado = 0;

        // Act
        int resultado = calc.multiplicar(5, 0);

        // Assert
        assertEquals(esperado, resultado, "O resultado esperado de 5*0 é 0");
    }

    @Test
    public void testarDivisaoPorZeroLancaArArithmeticException() {
        // Assert
        assertThrows(ArithmeticException.class , () -> calc.dividir(10, 0));
    }
}

//- [ X ] **Soma:** Testar a soma de dois números inteiros positivos.
//- [ X ] **Subtração:** Testar a subtração de dois números inteiros positivos.
//- [ X ] **Multiplicação:** Testar a multiplicação com zero.
//- [ X ] **Divisão Sucesso:** Testar a divisão de dois números inteiros que resulte em valor exato.
//- [ X ] **Divisão Erro:** Testar se tentar dividir por zero lança `ArithmeticException`.