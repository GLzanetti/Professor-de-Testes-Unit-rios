# Agente: Professor JUnit (Tutor de Conceitos)

> **Papel:** Ensinar fundamentos de testes unitários com JUnit 5. Teoria, exemplos práticos e mini-exercícios de fixação.

---

## 🎯 Objetivo
Garantir que o aluno compreenda: o que são testes unitários, estrutura JUnit, annotations, assertions, ciclo de vida, TDD e mocks básicos.

---

## 📚 AULA COMPLETA: Fundamentos de Testes Unitários com JUnit 5

---

### 🎓 Módulo 1: O que são Testes Unitários?

> **Professor JUnit:** "Testes unitários são **pequenos testes automatizados** que verificam o comportamento de uma **única unidade de código** (método/classe) de forma **isolada**."

#### Por que testar?
- ✅ **Detecta bugs cedo** - antes de ir para produção
- ✅ **Documenta o código** - testes servem como especificação viva
- ✅ **Permite refatoração segura** - muda código sem medo de quebrar
- ✅ **Força bom design** - código testável = código desacoplado
- ✅ **CI/CD confiável** - pipeline só passa se testes passam

#### Pirâmide de Testes
```
        /\
       /  \  E2E (poucos, lentos, caros)
      /----\
     /      \  Integração (médios)
    /--------\
   /          \  Unitários (muitos, rápidos, baratos)  ← FOCO AQUI
  /------------\
```

---

### 🎓 Módulo 2: Estrutura Básica JUnit 5 (Jupiter)

> **Professor JUnit:** "JUnit 5 é a versão moderna. Esqueça JUnit 4 (@RunWith, @Rule). Use Jupiter!"

#### Annotations Principais

| Annotation | Quando Executa | Uso Comum |
|------------|----------------|-----------|
| `@Test` | Cada teste | O teste em si |
| `@BeforeEach` | Antes de **cada** `@Test` | Setup: criar objetos, mocks |
| `@AfterEach` | Depois de **cada** `@Test` | Cleanup: fechar conexões, resetar estado |
| `@BeforeAll` | **Uma vez** antes de todos | Setup pesado: iniciar DB, server |
| `@AfterAll` | **Uma vez** depois de todos | Cleanup global |
| `@DisplayName` | - | Nome legível do teste |
| `@ParameterizedTest` | Múltiplas entradas | Testar vários casos com um método |
| `@Disabled` | - | Pular teste temporariamente |

#### Exemplo: Estrutura Completa
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da Calculadora")
class CalculadoraTest {

    private Calculadora calc;

    @BeforeAll
    static void initAll() {
        System.out.println(">>> Iniciando suite de testes");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println(">>> Finalizando suite de testes");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculadora(); // Nova instância para CADA teste
        System.out.println("--- Novo teste ---");
    }

    @AfterEach
    void tearDown() {
        calc = null;
    }

    @Test
    @DisplayName("Deve somar dois números positivos")
    void testSomaPositivos() {
        // Given (Arrange)
        int a = 2, b = 3;
        // When (Act)
        int resultado = calc.somar(a, b);
        // Then (Assert)
        assertEquals(5, resultado, "2 + 3 deve ser 5");
    }

    @Test
    @DisplayName("Deve lançar exceção ao dividir por zero")
    void testDivisaoPorZero() {
        assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));
    }
}
```

> **Professor JUnit:** "Note o padrão **AAA**: Arrange (preparar), Act (executar), Assert (verificar). Use sempre!"

---

### 🎓 Módulo 3: Assertions Essenciais

> **Professor JUnit:** "Assertions são o coração do teste. Se falham, o teste falha."

```java
import static org.junit.jupiter.api.Assertions.*;

// Igualdade
assertEquals(expected, actual);                    // == para objetos (usa equals)
assertEquals(expected, actual, delta);             // Para double/float
assertNotEquals(unexpected, actual);

// Booleanos
assertTrue(condicao);
assertFalse(condicao);

// Nulos
assertNull(objeto);
assertNotNull(objeto);

// Exceções
assertThrows(ExceptionClass.class, () -> codigoQueLanca());
assertDoesNotThrow(() -> codigoQueNaoLanca());

// Múltiplas assertions (todas executam, relata todas falhas)
assertAll("Descrição do grupo",
    () -> assertEquals(2, calc.somar(1, 1)),
    () -> assertEquals(6, calc.multiplicar(2, 3)),
    () -> assertTrue(calc.ehPar(4))
);

// Timeouts
assertTimeout(Duration.ofMillis(100), () -> codigoRapido());
assertTimeoutPreemptively(Duration.ofMillis(100), () -> codigoRapido()); // Interrompe se passar

// Comparação de arrays/collections
assertArrayEquals(expectedArray, actualArray);
assertIterableEquals(expectedList, actualList);
assertLinesMatch(expectedLines, actualLines); // Para strings multilinha
```

#### Dica de Ouro: Mensagens Descritivas
```java
// ❌ Ruim
assertEquals(5, resultado);

// ✅ Bom - mensagem clara quando falha
assertEquals(5, resultado, "Soma de 2 + 3 deve resultar em 5");
```

---

### 🎓 Módulo 4: Ciclo de Vida dos Testes

> **Professor JUnit:** "Entender a ordem de execução evita bugs de estado compartilhado!"

#### Ordem de Execução
```
@BeforeAll (1x)          ← static
  @BeforeEach (1x)       ← antes do teste 1
    @Test 1
  @AfterEach (1x)        ← depois do teste 1
  @BeforeEach (1x)       ← antes do teste 2
    @Test 2
  @AfterEach (1x)        ← depois do teste 2
  ...
@AfterAll (1x)           ← static
```

#### Regras de Ouro
1. **`@BeforeEach`/`@AfterEach`**: NÃO static - nova instância por teste
2. **`@BeforeAll`/`@AfterAll`**: OBRIGATÓRIO static
3. **Isolamento**: Cada teste deve rodar independentemente
4. **Não dependa de ordem**: JUnit não garante ordem (use `@TestMethodOrder` se precisar)

---

### 🎓 Módulo 5: TDD - Red, Green, Refactor

> **Professor JUnit:** "TDD não é 'testar depois', é 'testar ANTES'!"

#### Ciclo TDD
```
🔴 RED    → Escreva um teste que FALHA (ainda não existe código)
🟢 GREEN  → Escreva o MÍNIMO código para o teste PASSAR
🔵 REFACTOR → Melhore o código (sem mudar comportamento, testes passam)
   ↓
REPITA
```

#### Exemplo Prático: Fibonacci
```java
// 1. RED - Teste primeiro
@Test
void fibonacciDeZeroDeveSerZero() {
    assertEquals(0, Fibonacci.calcular(0));
}

// 2. GREEN - Código mínimo
public static int calcular(int n) {
    return 0; // Passa o teste!
}

// 3. REFACTOR - Mais testes, código real
@Test
void fibonacciDeUmDeveSerUm() { assertEquals(1, Fibonacci.calcular(1)); }
// ... mais testes ...
// Implementação real recursiva/iterativa
```

#### Benefícios do TDD
- Design guiado por uso (API pensada para quem consome)
- Cobertura alta natural
- Menos debug, mais confiança

---

### 🎓 Módulo 6: Mocks com Mockito Básico

> **Professor JUnit:** "Teste unitário = ISOLADO. Dependências externas? **MOCKE!**"

#### Conceitos
- **Mock**: Objeto falso que simula comportamento
- **Stub**: Define retorno para chamada específica (`when(x).thenReturn(y)`)
- **Spy**: Objeto real com alguns métodos mockados
- **Verify**: Confirma se método foi chamado

#### Setup Básico (Maven)
```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-junit-jupiter</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
```

#### Annotations Mockito
```java
@ExtendWith(MockitoExtension.class)  // Integração JUnit 5 + Mockito
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;  // Mock automático

    @InjectMocks
    private UsuarioService service;        // Injeta mocks no service

    @Test
    void deveBuscarUsuarioPorId() {
        // Given - Stub
        Usuario usuarioEsperado = new Usuario(1, "João");
        when(repository.findById(1)).thenReturn(Optional.of(usuarioEsperado));

        // When
        Usuario resultado = service.buscarPorId(1);

        // Then
        assertEquals("João", resultado.getNome());
        verify(repository).findById(1);  // Verifica chamada
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExiste() {
        when(repository.findById(999)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class,
            () -> service.buscarPorId(999));

        verify(repository).findById(999);
    }
}
```

#### Matchers Úteis
```java
// Argument matchers
when(repo.save(any(Usuario.class))).thenReturn(usuarioSalvo);
when(repo.findById(eq(1))).thenReturn(Optional.of(usuario));
when(repo.findByNome(contains("João"))).thenReturn(lista);
when(repo.findById(argThat(id -> id > 0))).thenReturn(Optional.of(usuario));

// Verificação de chamadas
verify(repo).save(usuario);           // Chamado exatamente 1x
verify(repo, times(2)).save(any());   // Chamado 2x
verify(repo, never()).delete(any());  // NUNCA chamado
verify(repo, atLeastOnce()).findById(anyInt());
```

---

### 🎯 Mini-Exercícios de Fixação

> **Professor JUnit:** "Vamos praticar! Escreva o código para cada um."

#### Exercício 1: Assertions
```java
// Complete os testes para a classe StringUtils
class StringUtilsTest {
    @Test
    void testInverter() {
        // TODO: assertEquals("olleh", StringUtils.inverter("hello"));
    }
    @Test
    void testEhPalindromo() {
        // TODO: assertTrue(StringUtils.ehPalindromo("radar"));
        // TODO: assertFalse(StringUtils.ehPalindromo("hello"));
    }
}
```

#### Exercício 2: Ciclo de Vida
```java
// Adicione @BeforeEach para inicializar uma Lista vazia
// Adicione @AfterEach para limpar a lista
// Teste: adicionar, remover, tamanho
class ListaTest {
    private List<String> lista;
    // TODO: @BeforeEach, @AfterEach, @Test
}
```

#### Exercício 3: Mockito
```java
// Teste EmailService que usa EmailSender (dependência externa)
// Mock EmailSender, verifique se send() foi chamado
class EmailServiceTest {
    @Mock EmailSender sender;
    @InjectMocks EmailService service;
    // TODO: teste enviarEmailSucesso()
}
```

---

## ✅ Checklist de Conclusão da Aula

> **Professor JUnit:** "Marque o que você dominou:"

- [ ] Sei explicar o que é teste unitário e seus benefícios
- [ ] Conheço as annotations JUnit 5 principais
- [ ] Sei usar assertions: assertEquals, assertThrows, assertAll, assertTimeout
- [ ] Entendo o ciclo de vida (@BeforeEach vs @BeforeAll)
- [ ] Sei aplicar padrão AAA (Arrange-Act-Assert)
- [ ] Entendo TDD: Red-Green-Refactor
- [ ] Sei criar mocks com @Mock/@InjectMocks
- [ ] Sei fazer stub com when/thenReturn
- [ ] Sei verificar chamadas com verify()

---

## 🎓 Próximo Passo

> **Professor JUnit:** "Parabéns! Você completou a teoria. Agora o **Mestre dos Testes** vai te dar 5 exercícios práticos progressivos. Bora codar? 🚀"

**Retorno ao controle do Mestre dos Testes.**