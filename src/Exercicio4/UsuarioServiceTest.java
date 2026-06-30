package Exercicio4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    UsuarioRepository repository;

    @InjectMocks
    UsuarioService service;

    private Usuario criarUsuarioTeste(){
        return new Usuario(1L, "Joao", "joao123@gmail.com");
    }

    @Test
    public void testasCadastroDeUsuarioComSucesso(){
        //Arrange
        Usuario usuarioParaCadastro = criarUsuarioTeste();
        when(repository.buscarPorEmail("joao123@gmail.com")).thenReturn(Optional.empty());
        when(repository.salvar(any(Usuario.class))).thenReturn(usuarioParaCadastro);

        //Act
        Usuario usuarioRetornado = service.cadastrarUsuario(usuarioParaCadastro);

        //Assert
        assertAll("Retorno das propriedades do Usuario",
                () -> assertEquals(1L, usuarioRetornado.getId()),
                () -> assertEquals("Joao", usuarioRetornado.getNome()),
                () -> assertEquals("joao123@gmail.com", usuarioRetornado.getEmail())
        );
        verify(repository, times(1)).buscarPorEmail("joao123@gmail.com");
        verify(repository, times(1)).salvar(usuarioParaCadastro);
    }

    @Test
    public void testarErroAoCadastrarEmailJaEmUso(){
        //Arrange
        Usuario usuarioDeTeste = criarUsuarioTeste();
        when(repository.buscarPorEmail("joao123@gmail.com")).thenReturn(Optional.of(usuarioDeTeste));

        //Act
        Usuario usuarioRetornado = criarUsuarioTeste();

        //Assert
        assertThrows(IllegalStateException.class, () -> service.cadastrarUsuario(usuarioRetornado));
        verify(repository, never()).salvar(any(Usuario.class));
    }

    @Test
    public void testarObterUmusuarioPorID(){
        //Arange
        Usuario usuarioDeTeste = criarUsuarioTeste();
        when(repository.buscarPorId(usuarioDeTeste.getId())).thenReturn(Optional.of(usuarioDeTeste));

        //Act
        Usuario usuarioRetornado = service.obterPorId(usuarioDeTeste.getId());


        //Assert
        assertAll("O retorno da busca por ID deve retornar o usuario",
                () -> assertEquals(1L, usuarioRetornado.getId()),
                () -> assertEquals("Joao", usuarioRetornado.getNome()),
                () -> assertEquals("joao123@gmail.com", usuarioRetornado.getEmail())
                );

        verify(repository, times(1)).buscarPorId(1L);
    }

    @Test
    public void testarErroAoBuscarUsuarioPorUmId(){
        //Arrange
        Usuario usuarioDeTeste = criarUsuarioTeste();
        when(repository.buscarPorId(usuarioDeTeste.getId())).thenReturn(Optional.empty());

        //Act
        Usuario usuarioRetornado = criarUsuarioTeste();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.obterPorId(usuarioRetornado.getId()));

        //Assert
        assertAll("Garantir que a o metodo chame um exception e tambem que a mensagem estaja certa",
                () -> assertThrows(IllegalArgumentException.class, () -> service.obterPorId(usuarioRetornado.getId())),
                () -> assertEquals("Usuário não encontrado com o ID: " + usuarioRetornado.getId(), exception.getMessage())
                );
    }

    @Test
    public void testeDeExcluirUmUsuarioExistente(){
        //Arrange
        Usuario usuarioDeTeste = criarUsuarioTeste();
        when(repository.buscarPorId(usuarioDeTeste.getId())).thenReturn(Optional.of(usuarioDeTeste));

        //Act
        Usuario usuarioRetornado = criarUsuarioTeste();
        service.excluirUsuario(usuarioRetornado.getId());

        //Assert
        verify(repository, times(1)).deletarPorId(usuarioRetornado.getId());
    }

    @Test
    public void testeDeExcluirUmUsuarioQueNaoExisteRetornandoErro(){
        //Arrange
        Usuario usuarioDeTeste = criarUsuarioTeste();
        when(repository.buscarPorId(usuarioDeTeste.getId())).thenReturn(Optional.empty());

        //Act
        Usuario usuarioRetornado = criarUsuarioTeste();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.excluirUsuario(usuarioRetornado.getId()));

        //Assert
        assertAll("Garantir que a o metodo chame uma exception e tambem que a mensagem estaja certa",
                () -> assertThrows(IllegalArgumentException.class, () -> service.obterPorId(usuarioRetornado.getId())),
                () -> assertEquals("Não é possível deletar. Usuário inexistente!", exception.getMessage())
        );
        verify(repository, never()).deletarPorId(usuarioRetornado.getId());
    }
}


//- [ X ] **Cadastrar com Sucesso:** Mockar a busca por e-mail para retornar vazio e o salvamento para retornar o usuário cadastrado. Chamar o serviço e validar as propriedades retornadas. Verificar se `buscarPorEmail` e `salvar` foram invocados.
//- [ X ] **Cadastrar Email Duplicado (Erro):** Mockar `buscarPorEmail` para retornar um usuário existente. Validar se uma `IllegalStateException` é lançada. Garantir que o método `salvar` do repositório **nunca** seja chamado neste fluxo.
//- [ X ] **Obter por ID Sucesso:** Mockar `buscarPorId` para retornar um usuário válido e verificar se o retorno do serviço está correto.
//- [ X ] **Obter por ID Falha:** Mockar `buscarPorId` para retornar `Optional.empty()`. Garantir que lança `IllegalArgumentException` com a mensagem correta.
//- [ X ] **Excluir Sucesso:** Mockar `buscarPorId` para retornar um usuário existente. Chamar `excluirUsuario` e verificar se `deletarPorId` foi de fato invocado.
//- [ X ] **Excluir Falha:** Mockar `buscarPorId` para retornar `Optional.empty()`. Chamar `excluirUsuario` e verificar se lança `IllegalArgumentException`. Garantir que o repositório **nunca** chama `deletarPorId`.