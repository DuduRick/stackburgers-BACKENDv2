# 🍔 Stack Burgers — Backend API

> Projeto acadêmico desenvolvido para a disciplina de **Programação Orientada a Objetos**  
> Curso: Análise e Desenvolvimento de Sistemas — 3º Semestre

---

## 📁 Estrutura do Projeto

```
stackburgers/
├── src/main/java/com/stackburgers/
│   ├── StackBurgersApplication.java       ← Ponto de entrada
│   │
│   ├── model/                             ← Entidades / POO
│   │   ├── Produto.java                   ← Classe ABSTRATA (Abstração)
│   │   ├── Hamburger.java                 ← Herda de Produto (Herança)
│   │   ├── Bebida.java                    ← Herda de Produto (Herança)
│   │   ├── Usuario.java                   ← Classe ABSTRATA
│   │   ├── Admin.java                     ← Herda de Usuario
│   │   ├── Cliente.java                   ← Herda de Usuario
│   │   ├── Pedido.java                    ← Agregação de itens
│   │   ├── ItemPedido.java                ← Item do pedido
│   │   ├── Carrinho.java                  ← Carrinho em memória
│   │   ├── StatusPedido.java              ← Enum de status
│   │   └── TipoUsuario.java               ← Enum de tipos
│   │
│   ├── repository/                        ← Acesso ao banco (JPA)
│   │   ├── ProdutoRepository.java
│   │   ├── HamburgerRepository.java
│   │   ├── BebidaRepository.java
│   │   ├── ClienteRepository.java
│   │   ├── AdminRepository.java
│   │   └── PedidoRepository.java
│   │
│   ├── service/                           ← Lógica de negócio
│   │   ├── HamburgerService.java
│   │   ├── BebidaService.java
│   │   ├── ClienteService.java
│   │   ├── AdminService.java
│   │   └── PedidoService.java
│   │
│   ├── controller/                        ← Endpoints REST
│   │   ├── HamburgerController.java
│   │   ├── BebidaController.java
│   │   ├── ClienteController.java
│   │   ├── AdminController.java
│   │   └── PedidoController.java
│   │
│   ├── dto/                               ← Objetos de transferência
│   │   ├── ProdutoDTO.java
│   │   ├── ClienteDTO.java
│   │   └── PedidoDTO.java
│   │
│   └── config/                            ← Configurações
│       ├── CorsConfig.java
│       ├── DataInitializer.java
│       └── GlobalExceptionHandler.java
│
└── src/main/resources/
    └── application.properties             ← Configurações do banco
```

---

## 🎓 Conceitos de POO Aplicados

### 1. Abstração
- `Produto` é uma classe abstrata — não pode ser instanciada diretamente
- Define o "contrato" que `Hamburger` e `Bebida` devem seguir
- `Usuario` também é abstrato — `Admin` e `Cliente` herdam dele

### 2. Herança
```
Produto (abstrata)
├── Hamburger  → adiciona: tipoCarne, ingredientes, calorias, vegetariano
└── Bebida     → adiciona: tipo, volumeMl, alcolica, gelada

Usuario (abstrata)
├── Admin      → adiciona: cargo | podeGerenciarProdutos() = true
└── Cliente    → adiciona: cpf, telefone, endereco | podeGerenciarProdutos() = false
```

### 3. Polimorfismo
- `getCategoria()` é implementado diferente em `Hamburger` e `Bebida`
- `podeGerenciarProdutos()` retorna `true` no Admin e `false` no Cliente
- `calcularValorTotal()` no Pedido funciona para qualquer tipo de Produto
- Spring usa polimorfismo internamente ao injetar dependências

### 4. Encapsulamento
- Todos os atributos das entidades são `private`
- Acesso apenas via `getters` e `setters`
- Lógica de negócio encapsulada nos Services (não vaza para Controllers)

---

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- MySQL 8+
- Maven 3.8+

### Passo 1: Banco de Dados
```sql
-- O Spring cria o banco automaticamente, mas você pode criar manualmente:
CREATE DATABASE stackburgers;
```

### Passo 2: Configurar credenciais
Edite `src/main/resources/application.properties`:
```properties
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

### Passo 3: Executar
```bash
# Na raiz do projeto (onde está o pom.xml):
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080/api`

---

## 📡 Endpoints da API

### Hambúrgueres
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/hamburgeres | Listar todos |
| GET | /api/hamburgeres/disponiveis | Listar disponíveis |
| GET | /api/hamburgeres/vegetarianos | Listar vegetarianos |
| GET | /api/hamburgeres/{id} | Buscar por ID |
| POST | /api/hamburgeres | Cadastrar novo |
| PUT | /api/hamburgeres/{id} | Atualizar |
| DELETE | /api/hamburgeres/{id} | Remover |
| PATCH | /api/hamburgeres/{id}/disponibilidade | Ativar/Desativar |

### Bebidas
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/bebidas | Listar todas |
| GET | /api/bebidas/disponiveis | Listar disponíveis |
| GET | /api/bebidas/{id} | Buscar por ID |
| POST | /api/bebidas | Cadastrar nova |
| PUT | /api/bebidas/{id} | Atualizar |
| DELETE | /api/bebidas/{id} | Remover |

### Clientes
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/clientes | Listar todos |
| GET | /api/clientes/{id} | Buscar por ID |
| POST | /api/clientes | Cadastrar |
| PUT | /api/clientes/{id} | Atualizar |
| DELETE | /api/clientes/{id} | Desativar |

### Pedidos
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /api/pedidos | Listar todos |
| GET | /api/pedidos/{id} | Buscar por ID |
| GET | /api/pedidos/cliente/{clienteId} | Pedidos do cliente |
| GET | /api/pedidos/status/{status} | Filtrar por status |
| POST | /api/pedidos | Criar pedido |
| PATCH | /api/pedidos/{id}/status | Atualizar status |
| PATCH | /api/pedidos/{id}/cancelar | Cancelar pedido |

---

## 🧪 Exemplos de Requisições

### Cadastrar Hambúrguer (POST)
```json
POST /api/hamburgeres
{
  "nome": "Stack Classic",
  "descricao": "O clássico da casa",
  "preco": 28.90,
  "tipoCarne": "Angus",
  "ingredientes": "Pão brioche, blend 180g, queijo cheddar",
  "calorias": 650,
  "vegetariano": false
}
```

### Cadastrar Cliente (POST)
```json
POST /api/clientes
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "minhasenha",
  "cpf": "123.456.789-00",
  "telefone": "(11) 98765-4321",
  "endereco": "Rua das Flores, 123"
}
```

### Criar Pedido (POST)
```json
POST /api/pedidos
{
  "clienteId": 1,
  "itens": [
    { "produtoId": 1, "quantidade": 2 },
    { "produtoId": 5, "quantidade": 1 }
  ],
  "observacao": "Sem cebola no hambúrguer"
}
```

### Atualizar Status (PATCH)
```json
PATCH /api/pedidos/1/status
{
  "status": "EM_PREPARO"
}
```

---

## 👥 Dados de Teste (Inseridos Automaticamente)

| Tipo | E-mail | Senha |
|------|--------|-------|
| Admin | admin@stackburgers.com | admin123 |
| Cliente | cliente@demo.com | cliente123 |

**Produtos inseridos:** 4 hambúrgueres + 4 bebidas

---

## 🏗️ Tecnologias

- **Java 17**
- **Spring Boot 3.2**
- **Spring Data JPA** — ORM / banco de dados
- **Spring Web** — API REST
- **Spring Validation** — validação de dados
- **MySQL 8** — banco de dados relacional
- **Maven** — gerenciamento de dependências
