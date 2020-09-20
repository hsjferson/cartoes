CREATE TABLE usuario (
    id serial PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf char(11) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE regra (
    id serial PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL
);

CREATE TABLE usuario_regra (
    usuario_id INT NOT NULL REFERENCES usuario (id),
    regra_id INT NOT NULL REFERENCES regra (id),
    PRIMARY KEY (usuario_id, regra_id)
);