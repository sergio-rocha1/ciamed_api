CREATE TABLE pacientes (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL UNIQUE,
                         telefone VARCHAR(20) NOT NULL,
                         logradouro VARCHAR(100) NOT NULL,
                         bairro VARCHAR(100) NOT NULL,
                         cep VARCHAR(9) NOT NULL,
                         cidade VARCHAR(100) NOT NULL,
                         uf VARCHAR(2) NOT NULL,
                         complemento VARCHAR(100) NOT NULL,
                         numero VARCHAR(10) NOT NULL,

                         PRIMARY KEY (id)
);