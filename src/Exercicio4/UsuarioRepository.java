package Exercicio4;

import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorEmail(String email);
    Usuario salvar(Usuario usuario);
    void deletarPorId(Long id);
}
