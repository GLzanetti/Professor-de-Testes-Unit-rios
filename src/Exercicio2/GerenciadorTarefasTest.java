package Exercicio2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadorTarefasTest {

    GerenciadorTarefas gerenciadorTarefas;

    @BeforeEach
    public void setUp() {
        gerenciadorTarefas = new GerenciadorTarefas();
    }

    @Test
    public void verificarAdicaoEContagemTarefa() {
        gerenciadorTarefas.adicionarTarefa("Fazer exercícios 2 do Professor Junit");
        int expected = 1;
        Integer numeroDeTarefas = gerenciadorTarefas.obterQuantidade();

        assertEquals(expected, numeroDeTarefas);
    }

    @Test
    public void verificarErroAoStringVaziaNull() {
        assertAll("Validação de Strings Null ou Vazias",
                () -> assertThrows(IllegalArgumentException.class, () -> gerenciadorTarefas.adicionarTarefa(" ")),
                () -> assertThrows(IllegalArgumentException.class, () -> gerenciadorTarefas.adicionarTarefa(null))
                );
    }

    @Test
    public void verificarRemocaoDeTarefas() {
        gerenciadorTarefas.adicionarTarefa("Fazer exercício 3 do Professor Junit");

        assertTrue(gerenciadorTarefas.removerTarefa("Fazer exercício 3 do Professor Junit"));
    }

    @Test
    public void verificarRemocaoDeTarefaInexistente() {
        gerenciadorTarefas.adicionarTarefa("Fazer exercício 3 do Professor Junit");

        assertFalse(gerenciadorTarefas.removerTarefa("Fazer café"));
    }

    @Test
    public void verificarBuscaDeTarefas() {
        String tarefa = "Estudar Junit resolvendo os exercícios do Professor Junit";
        gerenciadorTarefas.adicionarTarefa(tarefa);
        String termo = "Estudar";

        assertAll("Teste de busca de tarefas com 1 termo",
                () -> assertTrue(gerenciadorTarefas.buscarPorNome(termo).isPresent()),
                () -> {
                assertEquals(tarefa, gerenciadorTarefas.buscarPorNome(termo).get(),
                        "Pega a tarefa com base no termo utilizado");
                }
                );
    }
}

//- [ X ] **Isolamento de Estado:** Garantir que cada teste comece com um `GerenciadorTarefas` limpo.
//- [ X ] **Adicionar Sucesso:** Testar adicionar uma tarefa válida e verificar se a contagem aumenta e a tarefa está lá.
//- [ X ] **Adicionar Erro:** Testar se adicionar string vazia ou nula lança `IllegalArgumentException`.
//- [ X ] **Remover Sucesso:** Adicionar uma tarefa, removê-la e verificar se retornou `true` e se a lista ficou vazia.
//- [ X ] **Remover Inexistente:** Tentar remover tarefa inexistente e verificar se retornou `false`.
//- [ X ] **Busca:** Adicionar tarefas e testar a busca parcial por nome (exemplo: buscar "Estudar" deve achar "Estudar JUnit"). Usar `assertAll` para verificar que o resultado está presente e é exatamente o valor correto.