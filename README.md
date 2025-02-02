# KanbanAPI

Esta é uma API simples e poderosa para gerenciar **boards** e **tasks** no estilo Kanban. Com ela, você pode criar, visualizar, atualizar e excluir boards e tasks, além de organizar as tasks em colunas como "A Fazer", "Em Progresso" e "Concluído".

---

Com o KanbanAPI, você pode:

1. **Criar e gerenciar boards**:
    - Crie um novo board para organizar suas tasks.
    - Atualize o nome de um board existente.
    - Exclua um board quando não for mais necessário.

2. **Criar e gerenciar tasks**:
    - Adicione tasks a um board, definindo seu status inicial (A Fazer, Em Progresso ou Concluído).
    - Atualize o nome e a descrição de uma task.
    - Mova uma task entre as colunas (status).
    - Exclua uma task quando ela não for mais necessária.

3. **Visualizar tudo de forma organizada**:
    - Veja todos os boards criados.
    - Visualize todas as tasks de um board, agrupadas por status (colunas).

---

## Tecnologias e Dependências 🛠️

O projeto foi construído usando as seguintes tecnologias e dependências:

- **Spring Boot**: Framework para criar aplicações Java de forma rápida e eficiente.
- **Spring Data JPA**: Facilita a interação com o banco de dados usando ORM (Mapeamento Objeto-Relacional).
- **H2 Database**: Banco de dados em memória, perfeito para desenvolvimento e testes.
- **Lombok**: Biblioteca que reduz a verbosidade do código, gerando automaticamente getters, setters, construtores e muito mais.
- **Maven**: Gerenciador de dependências e build do projeto.

---

## Como rodar o projeto? 🚀

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/KanbanAPI.git
   ```

2. **Navegue até o diretório do projeto**:
   ```bash
   cd KanbanAPI
   ```

3. **Execute o projeto**:
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a API**:
   A API estará disponível em `http://localhost:5000`.

5. **Acesse o console do H2** (banco de dados em memória):
    - URL: `http://localhost:5000/kanban-h2`
    - Usuário: `sa`
    - Senha: `password22`

---

## Endpoints da API 🌐

Aqui estão todos os endpoints disponíveis e o que você pode fazer com cada um deles:

### Boards 🎯

- **Listar todos os boards**  
  `GET /boards`  
  Retorna uma lista com todos os boards criados.

- **Buscar um board por ID**  
  `GET /boards/{id}`  
  Retorna os detalhes de um board específico.

- **Criar um novo board**  
  `POST /boards`  
  Cria um novo board. Envie um JSON com o campo `name`.

- **Atualizar o nome de um board**  
  `PUT /boards/{id}`  
  Atualiza o nome de um board existente. Passe o novo nome como parâmetro `name`.

- **Excluir um board**  
  `DELETE /boards/{id}`  
  Remove um board e todas as tasks associadas a ele.

---

### Tasks 📝

- **Criar uma nova task**  
  `POST /boards/{boardId}/tasks`  
  Cria uma nova task em um board específico. Defina o status inicial da task (TO_DO, IN_PROGRESS ou DONE).

- **Listar todas as tasks de um board**  
  `GET /boards/{boardId}/tasks`  
  Retorna todas as tasks de um board, organizadas por status (colunas).

- **Atualizar uma task**  
  `PUT /boards/{boardId}/tasks/{taskId}`  
  Atualiza o nome e a descrição de uma task.

- **Atualizar o status de uma task**  
  `PUT /boards/{boardId}/tasks/{taskId}/status`  
  Move uma task para outra coluna (status).

- **Excluir uma task**  
  `DELETE /boards/{boardId}/tasks/{taskId}`  
  Remove uma task específica.

---

## Relacionamentos do Banco de Dados 🗃️

O banco de dados possui duas entidades principais:

1. **Board**:
    - Um board pode ter várias tasks.
    - Quando um board é excluído, todas as tasks associadas a ele também são removidas (cascata).

2. **Task**:
    - Cada task pertence a um único board.
    - O status de uma task é definido por um enum (`TO_DO`, `IN_PROGRESS`, `DONE`).
    - A task está sempre vinculada a um board, e essa relação é mantida pelo campo `board_id`.

---

## Exemplos Práticos no Postman 🛠️

Aqui estão alguns exemplos de como usar a API no Postman:

### 1. Criar um Board
- **Método**: POST
- **URL**: `http://localhost:5000/boards`
- **Body (JSON)**:
  ```json
  {
    "name": "Projeto de Desenvolvimento"
  }
  ```
- **Resposta**:
  ```json
  {
    "id": 1,
    "name": "Projeto de Desenvolvimento"
  }
  ```

### 2. Adicionar uma Task
- **Método**: POST
- **URL**: `http://localhost:5000/boards/1/tasks?status=TO_DO`
- **Body (JSON)**:
  ```json
  {
    "name": "Criar API",
    "description": "Desenvolver a API do Kanban"
  }
  ```
- **Resposta**:
  ```json
  {
    "id": 1,
    "name": "Criar API",
    "description": "Desenvolver a API do Kanban",
    "status": "TO_DO"
  }
  ```

### 3. Mover uma Task para "Em Progresso"
- **Método**: PUT
- **URL**: `http://localhost:5000/boards/1/tasks/1/status?status=IN_PROGRESS`
- **Resposta**:
  ```json
  {
    "id": 1,
    "name": "Criar API",
    "description": "Desenvolver a API do Kanban",
    "status": "IN_PROGRESS"
  }
  ```

### 4. Listar todas as Tasks de um Board
- **Método**: GET
- **URL**: `http://localhost:5000/boards/1/tasks`
- **Resposta**:
  ```json
  {
    "TO_DO": [],
    "IN_PROGRESS": [
      {
        "id": 1,
        "name": "Criar API",
        "description": "Desenvolver a API do Kanban",
        "status": "IN_PROGRESS"
      }
    ],
    "DONE": []
  }
  ```

### 5. Excluir uma Task
- **Método**: DELETE
- **URL**: `http://localhost:5000/boards/1/tasks/1`
- **Resposta**: Status `204 No Content`.

---

Se tiver alguma dúvida ou sugestão, é só entrar em contato.