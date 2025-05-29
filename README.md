# Products API

Uma aplicação RESTful Java desenvolvida com **Spring MVC** que gerencia produtos armazenados em um banco de dados MySQL. A aplicação segue princípios de **Clean Architecture**, incluindo conversão de tipos personalizados, persistência com JPA/Hibernate, e configuração manual baseada em Java.

## Tecnologias Utilizadas

- Java 17
- Spring Framework 6.x
  - Spring MVC
  - Spring Data JPA
  - Spring ORM
- Hibernate 6.x
- Jakarta Persistence
- MySQL 8.x
- Jackson (para serialização JSON)
- Maven (gestão de dependências e build)
- Tomcat 10.x (Servlet container)
- Postman (testes de API)

## Estrutura do Projeto

```text
src/
├── config/                           # Configuração Spring e inicialização da aplicação
│   ├── AppConfig.java
│   ├── WebAppInitializer.java
│   └── StringToCategoryConverter.java
├── controller/                       # Controladores REST
│   └── ProductController.java
├── model/                            # Entidades JPA e enums
│   ├── Product.java
│   └── Category.java
├── repository/                       # Repositórios Spring Data JPA
│   ├── ProductJpaRepository.java
│   └── interfaces/                   # Camada de persistência (adaptador e porta)
│       ├── ProductJpaAdapter.java
│       └── ProductGateway.java
└── service/                          # Lógica de negócio
    └── ProductService.java
```

## Endpoints da API

### `GET /products`
Lista todos os produtos.

### `GET /products/{id}`
Retorna um produto por ID.

### `POST /products`
Cria um novo produto.
- Exemplo de corpo JSON:
```json
{
  "name": "Notebook",
  "category": "ELECTRONICS",
  "price": 4500.00
}
```

### `PUT /products/{id}`
Atualiza os dados de um produto existente.

### `DELETE /products/{id}`
Remove um produto do banco de dados.

### `GET /products?category={category}`
Filtra produtos por categoria. As categorias possíveis estão definidas no enum `Category.java`.

### `GET /products?min={value}&max={value}`
Filtra produtos dentro de um intervalo de preço.

## Configuração do Banco de Dados

Configure as credenciais e propriedades do banco em `resources/application.properties`:

```properties
# db.properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/ProductsApi?useSSL=false
db.username=root
db.password=root

# hibernate.properties
hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
hibernate.show_sql=true
hibernate.hbm2ddl.auto=update
```

## Instruções de Execução

1. Clone este repositório
2. Configure o banco de dados MySQL conforme as propriedades acima
3. Compile o projeto com Maven
4. Faça o deploy no Tomcat
5. Use o Postman ou outro cliente HTTP para testar os endpoints

## Autor

Desenvolvido como parte de um projeto de estudo com foco em arquitetura limpa e APIs REST com Spring MVC.
