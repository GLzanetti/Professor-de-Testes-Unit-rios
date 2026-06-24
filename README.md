# 🤖 IA Tutora de Testes Unitários em Java

[![Licença](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Status](https://img.shields.io/badge/status-active-green.svg)]()
[![JUnit 5](https://img.shields.io/badge/JUnit-5-red.svg?style=flat&logo=junit5)](https://junit.org/junit5/)
[![Mockito](https://img.shields.io/badge/Mockito-5-green.svg)](https://site.mockito.org/)

Um sistema educacional interativo baseado em uma orquestra multi-agente de Inteligência Artificial modelado totalmente em arquivos Markdown. Projetado para guiar desenvolvedores em uma jornada de aprendizado teórica e prática sobre Testes Unitários em Java utilizando o **JUnit 5 (Jupiter)** e o **Mockito 5+**.

---

## 📌 Índice

- [Funcionalidades](#-funcionalidades)
- [Arquitetura e Estrutura do Projeto](#-arquitetura-e-estrutura-do-projeto)
- [Pré-requisitos e Configuração](#-pré-requisitos-e-configuração)
- [Como Executar o Projeto](#-como-executar-o-projeto)
- [Rotas / API Endpoints](#-rotas--api-endpoints)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Licença](#-licença)

---

## 🚀 Funcionalidades

Este ecossistema disponibiliza uma jornada de aprendizado completa através de quatro personas especialistas de IA:

- **🧭 Orquestração e Controle de Estado (Mestre dos Testes):** Gerencia o fluxo de aprendizado do aluno através de uma máquina de estados interativa. Realiza o questionário de diagnóstico inicial (5 perguntas), decide as rotas de aprendizado e mantém o estado da sessão do aluno salvo em memória no formato YAML.
- **🎓 Ensino Didático de Conceitos (Professor JUnit):** Entra em ação caso o aluno precise de nivelamento. Fornece explicações minuciosas sobre anotações, ciclo de vida de testes, asserções e simulação de dependências com exemplos claros e práticos.
- **🏗️ Geração de Exercícios (Arquiteto de Desafios):** Apresenta 5 desafios práticos progressivos que vão desde lógica simples de cálculo até testes parametrizados complexos e mocking de repositórios.
- **🔍 Avaliação de Código e Feedback (Inspetor de Código):** Inspeciona de forma cirúrgica e técnica as propostas de teste JUnit enviadas pelo aluno. Atribui uma nota rigorosa de `0.0` a `5.0` baseada em boas práticas de design (como padrão AAA e Clean Code), aponta os erros e acertos, e fornece a solução ideal comentada.

---

## 🏗️ Arquitetura e Estrutura do Projeto

O projeto é construído sobre o padrão arquitetural de **Sistema Multi-Agente Baseado em Personas e Orquestração por Máquina de Estados (State Machine)**, onde cada agente é definido em arquivos Markdown dedicados com diretrizes comportamentais e prompts bem estabelecidos.

### Estrutura de Pastas

Abaixo está a representação da árvore de diretórios do sistema tutor:

```text
TestesUnitarios/
├── .gitignore
├── AGENTS.md                  # Ponto de Entrada do Sistema Multi-Agente (System Prompt)
├── plan.md                    # Plano de implementação, requisitos e status atual do projeto
├── README.md                  # Esta documentação principal
├── agents/                    # Arquivos de prompt de sistema das personas
│   ├── analisador_testes.md   # Persona: "Inspetor de Código" (Avaliação de soluções)
│   ├── gerador_exercicios.md  # Persona: "Arquiteto de Desafios" (Apresentação de desafios)
│   ├── tutor_conceitos.md     # Persona: "Professor JUnit" (Aulas e nivelamento)
│   └── tutor_principal.md     # Persona: "Mestre dos Testes" (Orquestração geral)
├── templates/                 # Modelos reutilizáveis para geração de dados
│   ├── analise_template.md    # Template estruturado para relatórios de feedback técnico
│   └── exercicio_template.md  # Template estruturado para enunciados de exercícios
└── workspace/                 # Área interativa de estudos do aluno
    ├── analises/              # Pasta destinada ao salvamento dos relatórios de feedback (.gitkeep)
    └── exercicios/            # Enunciados dos desafios práticos de 1 a 5 e respostas
```

---

## ⚙️ Pré-requisitos e Configuração

### Requisitos Mínimos
- **Java JDK 17** ou superior (para compilar e rodar os códigos de teste localmente).
- **Maven 3.8+** ou **Gradle 7+** (gerenciadores de build recomendados para importar as dependências de JUnit e Mockito).
- Um interpretador de IA / LLM (como o Zed coding agent, Claude ou GPT) para atuar como motor de execução das personas definidas nos agentes.

---

## 💻 Como Executar o Projeto

### 1. Clonar o Repositório
```bash
git clone https://github.com/GLzanetti/Professor-de-Testes-Unitarios.git
cd TestesUnitarios
```

### 2. Instalar as Dependências (Ambiente Local Java)
Configure as dependências do JUnit 5 e Mockito no seu arquivo `pom.xml` (Maven) ou `build.gradle` (Gradle). Em seguida, execute a resolução das dependências:

**Usando Maven:**
```bash
mvn dependency:resolve
```

**Usando Gradle:**
```bash
./gradlew testClasses
```

### 3. Inicializar o Treinamento com a IA
Para iniciar a sua mentoria interativa, passe o arquivo `AGENTS.md` como contexto para a sua IA (ou use o assistente que já roda integrado neste repositório) e envie qualquer uma das mensagens abaixo no chat:

```text
Iniciar Treinamento
```
*ou*
```text
Quero aprender Testes Unitários
```

### 🧪 Executando os Testes Desenvolvidos Localmente
Sempre que finalizar um exercício, você pode rodá-lo localmente na sua máquina para verificar se o JUnit executa todos os cenários sem falhas:

**Com Maven:**
```bash
mvn test
```

**Com Gradle:**
```bash
./gradlew test
```

---

## 📜 Rotas / API Endpoints (Se aplicável)

> 💡 **Nota de Varredura:** Não se aplica a este tipo de projeto. Por se tratar de um sistema conversacional e pedagógico baseado em arquivos Markdown e orquestração de prompts inteligentes de IA, o projeto não expõe endpoints HTTP ou rotas Web clássicas. A comunicação ocorre estritamente por meio de interações síncronas de chat baseadas no roteiro definido no arquivo `AGENTS.md`.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+ (LTS)** — Linguagem alvo onde o estudante desenvolve as soluções de código e testes.
- **JUnit 5 (Jupiter)** — Framework moderno de testes automatizados ensinado e validado em todos os desafios.
- **Mockito 5+** — Framework de mocking ensinado nos módulos avançados para isolamento e stubbing de dependências.
- **Markdown & Multi-Agent Prompting** — Arquitetura para especificação de regras, fluxos de máquina de estado e personas dos agentes de IA tutores.

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo de licença para mais detalhes.

---

Feito com 💻 e 🤖 para impulsionar sua jornada de desenvolvimento de software!
