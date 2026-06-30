package Exercicio4;

public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio!");
        }

        // Regra de Negócio: Não permite emails duplicados
        if (repository.buscarPorEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalStateException("Email já cadastrado no sistema!");
        }

        return repository.salvar(usuario);
    }

    public Usuario obterPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com o ID: " + id));
    }

    public void excluirUsuario(Long id) {
        // Regra de Negócio: Garante que o usuário existe antes de tentar deletar
        if (!repository.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("Não é possível deletar. Usuário inexistente!");
        }
        repository.deletarPorId(id);
    }
}
