package Exercicio3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static Exercicio3.ValidadorEntrada.ValidacaoException;

public class ValidadorEntradaTest {

    ValidadorEntrada validadorEntrada;

    @BeforeEach
    public void setUp(){
        validadorEntrada = new ValidadorEntrada();
    }

    @ParameterizedTest
    @ValueSource(strings = { "joao@gmail.com", "maria.silva@empresa.com.br", "user+test@dominio.co", "a_b-c@sub.dominio.org", "teste123@meusite.io"} )
    public void ehUmFormatoDeEmailValido(String email) {

        assertTrue(validadorEntrada.ehEmailValido(email));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"email-sem-arroba.com", "@dominio.com"," nome@", "nome@dominio", "nome..sobrenome@dominio.com"})
    public void ehUmFormatoDeEmailInvalido(String email) {

        assertFalse(validadorEntrada.ehEmailValido(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Senha123", "MinhaSenha2024", "Abc12345", "Teste@2024Forte", "JavaJunit5x"})
    public void ehUmaSenhaValida(String senha) {
        assertDoesNotThrow(() -> validadorEntrada.validarSenha(senha));
    }

    @ParameterizedTest
    @CsvSource({
            "1234567, A senha deve ter pelo menos 8 caracteres!",
            "Abcdefgh, A senha deve conter pelo menos um número!",
            "abcdefg1, A senha deve conter letras maiúsculas e minúsculas!",
            "ABCDEFG1, A senha deve conter letras maiúsculas e minúsculas!",
            "Ab1, A senha deve ter pelo menos 8 caracteres!"
    })
    public void ehUmaSenhaInvalida(String senha, String esperado) {
        ValidacaoException mensagem = assertThrows(ValidadorEntrada.ValidacaoException.class, () -> validadorEntrada.validarSenha(senha));

        assertEquals(esperado, mensagem.getMessage(), "As mensagens de erro devem ser iguais");
    }

    @Test
    public void  verificarSeADuracaoDaCriptografiaEstaLenta() {
        String senha = "MinhaSenha2024";

        assertTimeoutPreemptively(Duration.ofMillis(150), () -> validadorEntrada.validarSenha(senha), "O tempo foi excedido, tente novamente.");
    }

}

//- [ X ] **Validação de Email (Sucesso):** Testar múltiplos formatos de e-mails válidos em um único método parametrizado usando `@ValueSource`.
//- [ X ] **Validação de Email (Invalidez):** Testar múltiplos formatos de e-mails inválidos (como sem `@`, sem domínio ou nulo) usando `@ValueSource`.
//- [ X ] **Validação de Senha (Sucesso):** Testar senhas válidas e garantir que não lançam exceção.
//- [ X ] **Validação de Senha (Exceptions Customizadas):** Testar cenários de senha fraca (menos de 8 caracteres, sem números, sem maiúsculas) usando `@CsvSource` para passar a senha inválida e a mensagem esperada da exceção, validando se a mensagem lançada confere.
//- [ X ] **Timeout de Validação:** Garantir que o método `validarSenha` executa em menos de 150 milissegundos usando `assertTimeoutPreemptively`.
