-- Durante a aula criamos o seguinte banco de dados:


-- Banco De Dado: sisacademico_2manha
-- Usuário: root
-- Senha: root

-- Scripts do banco (DDL)--:

-- TABELA DE CURSOS:
CREATE TABLE tb_Curso (
  id_Curso INT NOT NULL PRIMARY KEY
    GENERATED ALWAYS AS IDENTITY
    (START WITH 1, INCREMENT BY 1),
  nome_curso VARCHAR(90) NOT NULL,
  tipo_curso VARCHAR(50)
);

-- TABELA DE ALUNOS:
CREATE TABLE tb_aluno (
    id_aluno INT NOT NULL PRIMARY KEY
        GENERATED ALWAYS AS IDENTITY
        (START WITH 1, INCREMENT BY 1),
    ra INT NOT NULL,
    nome VARCHAR(100),
    id_curso INT NOT NULL,
        CONSTRAINT fb_aluno_curso FOREIGN KEY(id_curso)
        REFERENCES TB_CURSO
);


-- POPULAÇÃO DO BANCO (DML);
-- INSERT DE ALGUNS CURSOS:
INSERT INTO TB_CURSO (NOME_CURSO, TIPO_CURSO) 
	VALUES ('Sistemas de Informação', 'Bacharelado')
INSERT INTO TB_CURSO (NOME_CURSO, TIPO_CURSO) 
	VALUES ('Flutter', 'Livre')
INSERT INTO TB_CURSO (NOME_CURSO, TIPO_CURSO) 
	VALUES ('Biomedicina', 'Doutorado')

--INSERT DE ALGUNS ALUNOS:
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (123, 'Giovane Ferreira', 1)
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (321, 'Vinicius Afonso', 1)
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (1111, 'Thiago Traue', 2)
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (222, 'James Costa', 1)
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (3333, 'Rahaf Alhaswa', 2)
INSERT INTO ROOT.TB_ALUNO (RA, NOME, ID_CURSO) 
	VALUES (5555, 'Hebe Camargo', 1)

------- FIM DO SCRIPT -------
