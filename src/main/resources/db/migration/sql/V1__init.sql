CREATE TABLE cliente (
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf char(11) NOT NULL UNIQUE,
    uf char(2) NOT NULL,
    data_atualizacao DATE
);

CREATE TABLE cartao(
    id serial PRIMARY KEY,
    numero char(16) NOT NULL UNIQUE,
    data_validade DATE NOT NULL,
    bloqueado BOOLEAN NOT NULL,
    data_atualizacao DATE,
    cliente_id INT NOT NULL REFERENCES cliente (id)
);