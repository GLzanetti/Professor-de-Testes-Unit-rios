# Plano de Implementação - IA Tutora de Testes Unitários em Java com JUnit

## Visão Geral
Sistema de agentes em arquivos Markdown para ensinar Testes Unitários em Java com JUnit através de um fluxo interativo de avaliação, ensino, prática e análise.

---

## Estrutura de Diretórios

```
TestesUnitarios/
├── AGENTS.md                         # Ponto de Entrada / System Prompt de Orquestração
├── plan.md                           # Este arquivo
├── agents/
│   ├── tutor_principal.md            # Agente principal (orquestrador)
│   ├── tutor_conceitos.md            # Agente de ensino de conceitos
│   ├── gerador_exercicios.md         # Agente gerador de exercícios
│   └── analisador_testes.md          # Agente analisador de testes
...
├── workspace/
│   ├── exercicios/                   # Exercícios gerados
│   │   ├── exercicio_1.md
│   │   ├── exercicio_2.md
│   │   ├── exercicio_3.md
│   │   ├── exercicio_4.md
│   │   └── exercicio_5.md
│   └── analises/                     # Análises dos testes do usuário
│       ├── analise_1.md
│       ├── analise_2.md
│       ├── analise_3.md
│       ├── analise_4.md
│       └── analise_5.md
└── templates/
    ├── exercicio_template.md         # Template para exercícios
    └── analise_template.md           # Template para análises
```

---

## Agentes e Responsabilidades

### 1. Tutor Principal (`tutor_principal.md`)
**Nome sugerido:** `Mestre dos Testes`

**Responsabilidades:**
- Ponto de entrada e orquestração do fluxo
- Comunicação direta com o usuário
- Avaliação inicial de conhecimento (questionário)
- Decisão de fluxo: pular para exercícios OU acionar tutor de conceitos
- Controle do ciclo: exercícios → análise → continuar/parar
- Manutenção de estado da sessão (qual exercício, pontuação, histórico)

**Perguntas de Avaliação Inicial:**
1. Você sabe o que é um teste unitário?
2. Conhece a estrutura básica de um teste JUnit (@Test, @BeforeEach, @AfterEach)?
3. Sabe o que são assertions (assertEquals, assertTrue, assertThrows, etc.)?
4. Conhece o conceito de mock/stub?
5. Já escreveu testes para código com dependências externas?

**Critério de Pulo:** Se acertar ≥ 4 de 5 → vai direto para exercícios.

---

### 2. Tutor de Conceitos (`tutor_conceitos.md`)
**Nome sugerido:** `Professor JUnit`

**Responsabilidades:**
- Explicar o que são testes unitários e por que são importantes
- Ensinar estrutura de um teste JUnit 5 (Jupiter)
- Explicar annotations principais: @Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll, @DisplayName, @ParameterizedTest
- Ensinar assertions: assertEquals, assertNotEquals, assertTrue, assertFalse, assertNull, assertNotNull, assertThrows, assertAll, assertTimeout
- Explicar ciclo de vida dos testes
- Demonstrar com exemplos práticos (classe Calculadora, Service com dependências)
- Introduzir conceitos de TDD (Red-Green-Refactor)
- Explicar mocks com Mockito básico (@Mock, @InjectMocks, when/thenReturn)

**Formato:** Explicação teórica + exemplos de código comentados + mini-exercícios de fixação.

---

### 3. Gerador de Exercícios (`gerador_exercicios.md`)
**Nome sugerido:** `Arquiteto de Desafios`

**Responsabilidades:**
- Gerar sequência de 5 exercícios progressivos
- Cada exercício deve ter: enunciado, requisitos, classe alvo (se aplicável), critérios de aceitação
- Progressão de dificuldade:
  1. **Básico:** Testes simples em classe sem dependências (ex: Calculadora)
  2. **Intermediário 1:** Testes com @BeforeEach/@AfterEach, múltiplos cenários
  3. **Intermediário 2:** Testes com exceções (assertThrows), timeouts
  4. **Avançado 1:** Testes com Mockito - service com repository mockado
  5. **Avançado 2:** Testes parametrizados (@ParameterizedTest) + edge cases
- Salvar cada exercício em `workspace/exercicios/exercicio_N.md`
- Fornecer template de resposta para o usuário

---

### 4. Analisador de Testes (`analisador_testes.md`)
**Nome sugerido:** `Inspetor de Código`

**Responsabilidades:**
- Ler o teste escrito pelo usuário (fornecido como input)
- Comparar com critérios de aceitação do exercício correspondente
- Atribuir nota 0-5 baseada em rubrica:
  - **5:** Perfeito - todos critérios atendidos, boas práticas, nomes claros
  - **4:** Bom - funcional, pequenos problemas de estilo/nomenclatura
  - **3:** Aceitável - funciona mas faltam casos de borda ou boas práticas
  - **2:** Parcial - erros de lógica ou assertions incorretas
  - **1:** Insuficiente - estrutura errada, não compila
  - **0:** Não entregue / totalmente incorreto
- Gerar análise detalhada:
  - ✅ O que está certo
  - ⚠️ O que pode melhorar (sugestões)
  - ❌ O que está errado (correções necessárias)
  - 💡 Dicas para próximo nível
- Salvar em `workspace/analises/analise_N.md`

---

## Fluxo de Trabalho Detalhado

```
INÍCIO
  │
  ▼
┌─────────────────────────────────────┐
│  TUTOR PRINCIPAL: Avaliação Inicial │
│  (5 perguntas diagnóstico)          │
└─────────────────────────────────────┘
  │
  ├── Sabe ≥ 4? ──SIM──▶ Pular para EXERCÍCIOS
  │
  NÃO
  ▼
┌─────────────────────────────────────┐
│  TUTOR CONCEITOS: Aula Completa     │
│  (teoria + exemplos + mini-fixação) │
└─────────────────────────────────────┘
  │
  ▼
┌─────────────────────────────────────┐
│  GERADOR EXERCÍCIOS: 5 Exercícios   │
│  (salvos em workspace/exercicios/)  │
└─────────────────────────────────────┘
  │
  ▼
┌─────────────────────────────────────┐
│  LOOP PARA CADA EXERCÍCIO (1-5)     │
│  ┌───────────────────────────────┐  │
│  │ Usuário escreve teste         │  │
│  │ (fornece código como input)   │  │
│  └───────────────────────────────┘  │
  │                │
  ▼                ▼
┌─────────────────────────────────────┐
│  ANALISADOR: Avalia + Nota 0-5      │
│  (salva em workspace/analises/)     │
└─────────────────────────────────────┘
  │
  ▼
┌─────────────────────────────────────┐
│  TUTOR PRINCIPAL: Mostra análise    │
│  Pergunta: "Continuar ou Parar?"    │
└─────────────────────────────────────┘
  │
  ├── Continuar ──▶ Próximo exercício (se houver) ou FIM
  │
  PARAR
  ▼
FIM
```

---

## Templates

### Template de Exercício (`templates/exercicio_template.md`)
```markdown
# Exercício N: [Título]

## Objetivo
[O que o aluno deve aprender/praticar]

## Classe Alvo (se aplicável)
```java
// Código da classe a ser testada
```

## Requisitos
- [ ] Requisito 1
- [ ] Requisito 2
- [ ] ...

## Critérios de Aceitação
1. Critério 1
2. Critério 2
3. ...

## Template de Resposta
```java
// Seu teste aqui
```

## Dicas
- Dica 1
- Dica 2
```

### Template de Análise (`templates/analise_template.md`)
```markdown
# Análise do Exercício N

## Nota: X/5

## ✅ O que está certo
- Ponto 1
- Ponto 2

## ⚠️ O que pode melhorar
- Sugestão 1
- Sugestão 2

## ❌ O que está errado
- Erro 1
- Correção necessária

## 💡 Dicas para próximo nível
- Dica 1
- Dica 2
```

---

## Critérios de Qualidade para os Agentes

1. **Linguagem:** Português brasileiro, tom didático e encorajador
2. **Formato:** Markdown com blocos de código Java bem formatados
3. **Interatividade:** Cada agente deve fazer perguntas claras e aguardar resposta
4. **Rastreabilidade:** Referenciar exercício/análise anterior quando relevante
5. **Progressão:** Dificuldade crescente, conceitos se acumulam

---

## Próximos Passos de Implementação

1. ✅ Criar `plan.md` (este arquivo)
2. ✅ Criar estrutura de diretórios
3. ✅ Implementar `agents/tutor_principal.md`
4. ✅ Implementar `agents/tutor_conceitos.md`
5. ✅ Implementar `agents/gerador_exercicios.md`
6. ✅ Implementar `agents/analisador_testes.md`
7. ✅ Criar templates em `templates/`
8. ✅ Gerar 5 exercícios iniciais em `workspace/exercicios/`
9. 🧪 Testar fluxo completo simulado

---

## Status Atual da Implementação

| Componente | Status | Arquivo |
|------------|--------|---------|
| Orquestrador Principal / System Prompt | ✅ Concluído | `AGENTS.md` |
| Plano principal | ✅ Concluído | `plan.md` |
| Estrutura de diretórios | ✅ Concluída | `agents/`, `templates/`, `workspace/exercicios/`, `workspace/analises/` |
| Tutor Principal (Mestre dos Testes) | ✅ Concluído | `agents/tutor_principal.md` |
| Tutor de Conceitos (Professor JUnit) | ✅ Concluído | `agents/tutor_conceitos.md` |
| Gerador de Exercícios (Arquiteto de Desafios) | ✅ Concluído | `agents/gerador_exercicios.md` |
| Analisador de Testes (Inspetor de Código) | ✅ Concluído | `agents/analisador_testes.md` |
| Template de Exercício | ✅ Concluído | `templates/exercicio_template.md` |
| Template de Análise | ✅ Concluído | `templates/analise_template.md` |
| Exercício 1: Calculadora Básica | ✅ Concluído | `workspace/exercicios/exercicio_1.md` |
| Exercício 2: Ciclo de Vida + Múltiplos Cenários | ✅ Concluído | `workspace/exercicios/exercicio_2.md` |
| Exercício 3: Exceções + Timeouts | ✅ Concluído | `workspace/exercicios/exercicio_3.md` |
| Exercício 4: Mockito - Service com Repository | ✅ Concluído | `workspace/exercicios/exercicio_4.md` |
| Exercício 5: Testes Parametrizados + Edge Cases | ✅ Concluído | `workspace/exercicios/exercicio_5.md` |

---

## Notas Técnicas

- **JUnit Version:** JUnit 5 (Jupiter) - padrão atual
- **Mock Framework:** Mockito 5+ (inline mock maker)
- **Java Version:** 17+ (LTS atual)
- **Build Tool:** Maven ou Gradle (exemplos em ambos)
- **Execução:** `mvn test` ou `./gradlew test`

---

*Documento vivo - atualizar conforme implementação avança.*