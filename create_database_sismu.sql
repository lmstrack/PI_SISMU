CREATE DATABASE SISMU;
USE SISMU;

CREATE TABLE CATEGORIA(
	IDCATEGORIA INT NOT NULL,
    DESCRICAO VARCHAR(255) NOT NULL,
    PRIMARY KEY ( IDCATEGORIA )
);

CREATE TABLE DOADOR(
	IDDOADOR INT NOT NULL,
	NOME VARCHAR(255),
	CIDADE VARCHAR(255),
	CPF_CNPJ VARCHAR(255),
	ENDERECO VARCHAR(255),
	IDENTIDADE VARCHAR(255)
);

CREATE TABLE UNIDADE(
	IDUNIDADE INT NOT NULL,
    DESCRICAO VARCHAR(128) NOT NULL,
    TAMANHO VARCHAR(64) NOT NULL,
    HISTORIA TEXT,
    HISTORIADOADOR VARCHAR(255),
    FABRICANTE VARCHAR(64),
    ORIGEM VARCHAR(64) NOT NULL,
    DATADOACAO DATE,
    NPATRIMONIO INT,
    PACOTE VARCHAR(255),
    VALORNF DECIMAL(22,2),
    CODCATEGORIA INT,
    CODDOADOR INT,
    CODREPLEGAL VARCHAR(255),
    PRIMARY KEY ( IDUNIDADE ), 
    FOREIGN KEY ( CODCATEGORIA ) REFERENCES CATEGORIA ( IDCATEGORIA )
    FOREIGN KEY (CODDOADOR) REFERENCES DOADOR ( IDDOADOR )
);

CREATE TABLE FOTO(
	IDFOTO INT NOT NULL,
    LOCAL VARCHAR(255),
    NOMEEXTERNO VARCHAR(255),
    EXPOSTA BOOLEAN NOT NULL,
    CODUNIDADE INT,
    PRIMARY KEY ( IDFOTO ),
    FOREIGN KEY ( CODUNIDADE ) REFERENCES UNIDADE ( IDUNIDADE )
);

CREATE TABLE EXPOSITOR(
	IDEXPOSITOR INT(11) NOT NULL,
    DESCRICAO VARCHAR(255) NOT NULL,
    PRIMARY KEY ( IDEXPOSITOR )
);

CREATE TABLE EXPOSICAO(
	IDEXPOSICAO INT(11) NOT NULL,
    DATAINICIO DATE NOT NULL,
    DATAFIM DATE NOT NULL,
    CODEXPOSITOR INT(11),
    CODUNIDADE INT,
    PRIMARY KEY ( IDEXPOSICAO, DATAINICIO, DATAFIM ),
    FOREIGN KEY ( CODEXPOSITOR ) REFERENCES EXPOSITOR ( IDEXPOSITOR ),
    FOREIGN KEY ( CODUNIDADE ) REFERENCES UNIDADE ( IDUNIDADE )
);