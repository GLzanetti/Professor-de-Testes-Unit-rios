package Exercicio3;

public class ValidadorEntrada {

    // Exceção customizada interna
    public static class ValidacaoException extends RuntimeException {
        public ValidacaoException(String mensagem) {
            super(mensagem);
        }
    }

    public void validarSenha(String senha) {
        // Simulação de delay para teste de timeout
        try {
            Thread.sleep(10); // Simula processamento leve de criptografia/hashing
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (senha == null || senha.length() < 8) {
            throw new ValidacaoException("A senha deve ter pelo menos 8 caracteres!");
        }
        if (!senha.matches(".*[0-9].*")) {
            throw new ValidacaoException("A senha deve conter pelo menos um número!");
        }
        if (!senha.matches(".*[a-z].*") || !senha.matches(".*[A-Z].*")) {
            throw new ValidacaoException("A senha deve conter letras maiúsculas e minúsculas!");
        }
    }

    public boolean ehEmailValido(String email) {
        if (email == null) return false;
        // Validação simples de email por regex
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

}
