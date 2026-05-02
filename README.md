# TicketHub

Sistema web de controle e emissão de ingressos desenvolvido com Spring Boot, MongoDB e Thymeleaf, seguindo o padrão MVC com camada de serviços e hierarquia de classes para demonstrar polimorfismo.

## Estrutura do projeto

```
src/main/java/com/ingressos/
├── model/               Entidades de domínio
│   ├── Ingresso.java        Classe abstrata base — define calcularValor() e imprimirIngresso()
│   ├── IngressoNormal.java  Subtipo: preço base sem alteração
│   ├── IngressoVIP.java     Subtipo: preço base x 1,8 (acréscimo de 80%)
│   ├── IngressoMeia.java    Subtipo: preço base x 0,5 (desconto de 50%)
│   └── Evento.java          Entidade de evento com controle de vagas
├── repository/          Camada de acesso a dados (Spring Data MongoDB)
│   ├── IngressoRepository.java
│   └── EventoRepository.java
├── service/             Camada de regras de negócio
│   ├── IngressoService.java    Fábrica polimórfica — instancia o subtipo correto por tipo
│   └── EventoService.java
├── controller/          Camada de controle HTTP
│   ├── HomeController.java
│   ├── IngressoController.java
│   └── EventoController.java
├── config/
│   ├── DataInitializer.java        Insere 5 eventos de exemplo na primeira execução
│   ├── WebConfig.java              Configura handlers de recursos estáticos
│   └── GlobalExceptionHandler.java Captura exceções e redireciona para a view de erro
└── IngressosApplication.java

src/main/resources/
├── templates/
│   ├── index.html
│   ├── eventos/
│   │   ├── lista.html
│   │   └── form.html
│   ├── ingressos/
│   │   ├── lista.html
│   │   ├── form.html
│   │   └── detalhe.html
│   └── error.html
├── static/css/
│   └── style.css
└── application.properties
```

## Regras de negócio

O valor do ingresso é calculado automaticamente pelo subtipo selecionado. `IngressoNormal` retorna o preço base sem alteração. `IngressoVIP` multiplica por 1,8. `IngressoMeia` multiplica por 0,5, destinada a estudantes, idosos e pessoas com deficiência. A emissão só é permitida se o evento tiver vagas disponíveis. A cada emissão o contador de ingressos vendidos é incrementado, e a cada cancelamento é decrementado.

## Como rodar

### Opção 1 — MongoDB local

**1. Instale o MongoDB Community** em https://www.mongodb.com/try/download/community e certifique-se de que o serviço está rodando na porta padrão 27017.

**2. Edite o `application.properties`** trocando a URI pelo endereço local:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/ingressos
spring.data.mongodb.database=ingressos
```

**3. Clone e execute:**

```bash
git clone https://github.com/SEU_USUARIO/tickethub.git
cd tickethub
mvn spring-boot:run
```

Acesse em: http://localhost:8080

---

### Opção 2 — MongoDB Atlas (nuvem)

**1. Crie uma conta** em https://www.mongodb.com/atlas e crie um cluster gratuito (M0).

**2. Configure o acesso:**
- Em *Database Access*, crie um usuário com senha.
- Em *Network Access*, adicione seu IP (ou `0.0.0.0/0` para liberar qualquer IP).

**3. Copie a connection string** clicando em *Connect* no seu cluster e escolhendo *Drivers*. O formato será:

```
mongodb+srv://USUARIO:SENHA@CLUSTER.mongodb.net/?retryWrites=true&w=majority
```

**4. Cole no `application.properties`:**

```properties
spring.data.mongodb.uri=mongodb+srv://USUARIO:SENHA@CLUSTER.mongodb.net/?retryWrites=true&w=majority
spring.data.mongodb.database=ingressos
```

**5. Execute:**

```bash
mvn spring-boot:run
```

Acesse em: http://localhost:8080

---

### Gerar JAR executável

Para empacotar o projeto em um único arquivo e rodar sem Maven:

```bash
mvn clean package -DskipTests
java -jar target/sistema-ingressos-1.0.0.jar
```

O JAR já inclui todas as dependências e pode ser enviado para qualquer servidor que tenha o Java 17 instalado.

## Stack tecnológica

| Tecnologia          | Versão | Função                       |
|---------------------|--------|------------------------------|
| Java                | 17     | Linguagem                    |
| Spring Boot         | 3.2.0  | Framework principal          |
| Spring Data MongoDB | 3.2.0  | Acesso ao banco de dados     |
| Thymeleaf           | 3.1    | Template engine              |
| MongoDB             | 6+     | Banco de dados NoSQL         |
| Lombok              | atual  | Redução de boilerplate       |
| Maven               | 3.8+   | Gerenciamento de build       |

## Autor

Desenvolvido como projeto acadêmico por Luiza — Sistemas de Informação.
Atividade: Prática sobre Diagramas UML, Herança e Polimorfismo.
