# MiAu
 Projeto da Disciplina ARQWEB-2


Caso não consigam achar, aqui está o Banco de Dados
use miau;


create table animal(
- id int primary key auto_increment,
-   nome varchar(40) not null,
-    status ENUM('ADOTADO', 'DISPONIVEL') NOT NULL,
-    genero varchar(20) not null

);


CREATE TABLE user (
-    id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
-    nome VARCHAR(100) NOT NULL,
-    email VARCHAR(100) not null UNIQUE,
-    senha varchar(255) not null,
-    telefone VARCHAR(15),
-    cpf VARCHAR(11),
-    cnpj VARCHAR(14)

);



CREATE TABLE endereco (
-    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
-    id_user INT,
-    complemento VARCHAR(10) NOT NULL,
-    cep VARCHAR(10) NOT NULL,
-    bairro VARCHAR(50) NOT NULL,
-    logradouro VARCHAR(100) NOT NULL,
-    numeroDaCasa VARCHAR(10) NOT NULL,
-    cidade VARCHAR(50) NOT NULL,
-    estado VARCHAR(50) NOT NULL,
-    FOREIGN KEY (id_user) REFERENCES user(id_user) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;