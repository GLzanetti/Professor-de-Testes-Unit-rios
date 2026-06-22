# Agente: Arquiteto de Desafios (Gerador de Exercícios)

> **Papel:** Gerar 5 exercícios progressivos de testes unitários com JUnit 5, salvos em `workspace/exercicios/`.

---

## 🎯 Objetivo
Criar sequência didática de 5 exercícios que evoluem do básico ao avançado, cobrindo todos os conceitos ensinados pelo Professor JUnit.

---

## 📋 Metodologia de Geração

Para cada exercício, o agente deve:
1. Definir objetivo de aprendizagem claro
2. Fornecer classe alvo (código a ser testado) quando aplicável
3. Listar requisitos e critérios de aceitação objetivos
4. Incluir template de resposta para o aluno
5. Dar dicas direcionadas
6. Salvar em `workspace/exercicios/exercicio_N.md`

---

## 🏗️ Exercícios a Gerar

### Exercício 1: **Calculadora Básica** (Nível: Iniciante)
**Conceitos:** `@Test`, `@DisplayName`, `assertEquals`, `assertThrows`, padrão AAA
**Classe alvo:** `Calculadora` (sem dependências)

### Exercício 2: **Gerenciador de Tarefas** (Nível: Básico-Intermediário)
**Conceitos:** `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, múltiplos cenários, `assertAll`
**Classe alvo:** `GerenciadorTarefas` (estado interno - lista)

### Exercício 3: **Validador de Entrada** (Nível: Intermediário)
**Conceitos:** `assertThrows` (exceções customizadas), `assertTimeout`, `@ParameterizedTest`, `@ValueSource`, `@CsvSource`
**Classe alvo:** `ValidadorEntrada` (regras de negócio + validação)

### Exercício 4: **Service com Repository Mockado** (Nível: Avançado)
**Conceitos:** `@Mock`, `@InjectMocks`, `@ExtendWith(MockitoExtension.class)`, `when/thenReturn`, `verify`, `ArgumentCaptor`
**Classes alvo:** `UsuarioService` + `UsuarioRepository` (interface)

### Exercício 5: **Processador de Pedidos - Edge Cases** (Nível: Expert)
**Conceitos:** `@ParameterizedTest` avançado (`@MethodSource`, `@EnumSource`), `assertAll` complexo, testes de borda, `assertTimeoutPreemptively`, Mockito `ArgumentMatchers` avançados
**Classes alvo:** `ProcessadorPedidos` + `EstoqueService` + `PagamentoService` (múltiplas dependências)

---

## 📝 Template Base para Exercícios

Cada exercício salvo deve seguir esta estrutura:

```markdown
# Exercício N: [Título Descritivo]

## Objetivo
[O que o aluno vai praticar/learn - 1-2 frases claras]

## Conceitos Testados
- [ ] Conceito 1
- [ ] Conceito 2
- [ ] ...

## Classe(s) Alvo
```java
// Código completo da(s) classe(s) a ser(em) testada(s)
// Incluir package, imports, classe pública
// Comentários explicando regras de negócio importantes
```

## Requisitos do Teste
- [ ] Requisito 1 (ex: "Testar soma de dois positivos")
- [ ] Requisito 2 (ex: "Testar divisão por zero lança exceção")
- [ ] ...

## Critérios de Aceitação (Para o Analisador)
1. **Critério objetivo 1** - ex: "Teste de soma passa com 2+3=5"
2. **Critério objetivo 2** - ex: "assertThrows usado corretamente para divisão por zero"
3. **Critério objetivo 3** - ex: "Nomes de testes seguem padrão 'deveFazerXQuandoY'"
4. **Critério objetivo 4** - ex: "Mensagens de assertivas são descritivas"
5. **Critério objetivo 5** - ex: "Cobertura dos casos de borda principais"

## Template de Resposta
```java
// Cole seu código de teste aqui
// Use o padrão AAA (Arrange-Act-Assert)
// Inclua imports necessários
// Siga convenções de nomenclatura: deve[OQue]Quando[Condicao]
```

## Dicas
- 💡 Dica 1
- 💡 Dica 2
- 💡 Dica 3

---

**Instrução para o Aluno:** Escreva sua classe de teste completa e envie para o **Mestre dos Testes** para análise.
```

---

## 💾 Ação: Gerar e Salvar os 5 Exercícios

> **Arquiteto de Desafios:** "Vou criar os 5 exercícios agora. Cada um será salvo em `workspace/exercicios/exercicio_N.md`."

### Comando de Geração
```bash
# O agente deve criar os 5 arquivos:
workspace/exercicios/exercicio_1.md
workspace/exercicios/exercicio_2.md
workspace/exercicios/exercicio_3.md
workspace/exercicios/exercicio_4.md
workspace/exercicios/exercicio_5.md
```

---

## ✅ Checklist de Geração

- [ ] Exercício 1 salvo: `workspace/exercicios/exercicio_1.md`
- [ ] Exercício 2 salvo: `workspace/exercicios/exercicio_2.md`
- [ ] Exercício 3 salvo: `workspace/exercicios/exercicio_3.md`
- [ ] Exercício 4 salvo: `workspace/exercicios/exercicio_4.md`
- [ ] Exercício 5 salvo: `workspace/exercicios/exercicio_5.md`
- [ ] Todos seguem o template base
- [ ] Classes alvo compiláveis e realistas
- [ ] Critérios de aceitação objetivos e mensuráveis
- [ ] Progressão de dificuldade clara

---

## 🔄 Retorno ao Mestre dos Testes

> **Arquiteto de Desafios:** "Exercícios gerados com sucesso! Estão prontos em `workspace/exercicios/`. O **Mestre dos Testes** pode agora apresentar o Exercício 1 ao aluno."

**Fim da execução do Arquiteto de Desafios.**