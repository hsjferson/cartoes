CREATE TABLE transacao (
    id serial PRIMARY KEY,
    data_transacao DATE NOT NULL,
    cnpj char(14) NOT NULL UNIQUE,
    valor numeric NOT NULL,
    qtd_parcelas INT NOT NULL,
    juros numeric NOT NULL,
    cartao_id INT NOT NULL REFERENCES cartao (id)
);