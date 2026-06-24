package Exercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorTarefas {

    private final List<String> tarefas = new ArrayList<>();

    public void adicionarTarefa(String tarefa) {
        if (tarefa == null || tarefa.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da tarefa não pode ser vazia!");
        }
        tarefas.add(tarefa);
    }

    public boolean removerTarefa(String tarefa) {
        return tarefas.remove(tarefa);
    }

    public int obterQuantidade() {
        return tarefas.size();
    }

    public Optional<String> buscarPorNome(String termo) {
        return tarefas.stream()
                .filter(t -> t.toLowerCase().contains(termo.toLowerCase()))
                .findFirst();
    }

    public void limparTodas() {
        tarefas.clear();
    }

}
