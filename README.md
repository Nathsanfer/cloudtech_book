# CloudTech Books API

API REST para cadastro e gerenciamento de livros, desenvolvida com Java 21 e Spring Boot. Os dados são armazenados em memória: a aplicação inicia com dois livros de exemplo, e as alterações são perdidas quando ela é reiniciada.

## Requisitos

- JDK 21
- Maven Wrapper incluído no projeto

## Executar a aplicação

No Windows, abra o terminal na pasta `api` e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

No macOS ou Linux:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

## Rotas

| Método | Caminho | Descrição |
| --- | --- | --- |
| `GET` | `/livros` | Lista todos os livros |
| `GET` | `/livros/{id}` | Busca um livro pelo ID |
| `GET` | `/livros/` | Retorna uma mensagem de boas-vindas |
| `GET` | `/livros/documentacao` | Retorna a descrição das rotas |
| `POST` | `/livros/cadastrar` | Cadastra um livro; o ID é obrigatório |
| `PUT` | `/livros/alterar/{id}` | Atualiza os dados do livro indicado |
| `DELETE` | `/livros/{id}` | Remove o livro indicado |

Exemplo de corpo JSON para cadastrar ou atualizar:

```json
{
  "id": 3,
  "titulo": "Coração de Gelo",
  "autor": "Babi A. Sette",
  "editora": "Verus",
  "ano": "2025",
  "categoria": "Romantasia",
  "status": "DISPONIVEL"
}
```

Os valores aceitos para `status` são `DISPONIVEL`, `INDISPONIVEL` e `EMPRESTADO`. Para atualização, o ID da URL identifica o livro; não é necessário enviar o campo `id` no JSON.

## Executar os testes

No Windows:

```powershell
.\mvnw.cmd test
```

No macOS ou Linux:

```bash
./mvnw test
```
