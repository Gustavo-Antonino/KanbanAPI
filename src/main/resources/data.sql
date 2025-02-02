-- Criação da tabela Board
CREATE TABLE Board (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Criação da tabela Task
CREATE TABLE Task (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50) NOT NULL,
    board_id BIGINT,
    CONSTRAINT fk_board FOREIGN KEY (board_id) REFERENCES Board(id) ON DELETE CASCADE
);

