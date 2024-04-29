CREATE TABLE public.ge_t_papel (
                                   id bigserial NOT NULL,
                                   nome varchar(255) NOT NULL,
                                   CONSTRAINT ge_t_papel_pkey PRIMARY KEY (id)
);


CREATE TABLE public.ge_t_usuario (
                                     id bigserial NOT NULL,
                                     nome_usuario varchar(255) NOT NULL,
                                     senha varchar(255) NOT NULL,
                                     papel_id int8 NULL,
                                     CONSTRAINT ge_t_usuario_pkey PRIMARY KEY (id)
);
ALTER TABLE public.ge_t_usuario ADD CONSTRAINT fkt4p579ubl0bidfpsvo55479qb FOREIGN KEY (papel_id) REFERENCES public.ge_t_papel(id);

CREATE TABLE public.ge_t_docente (
                                     id bigserial NOT NULL,
                                     data_entrada timestamp(6) NULL,
                                     nome varchar(255) NOT NULL,
                                     usuario_id int8 NOT NULL,
                                     CONSTRAINT ge_t_docente_pkey PRIMARY KEY (id),
                                     CONSTRAINT uk_mawto1retq778l65nfr6xxu8q UNIQUE (usuario_id)
);
ALTER TABLE public.ge_t_docente ADD CONSTRAINT fkkp9s3gt34ig8exhymnqi0p2lj FOREIGN KEY (usuario_id) REFERENCES public.ge_t_usuario(id);

CREATE TABLE public.ge_t_curso (
                                   id bigserial NOT NULL,
                                   nome varchar(255) NOT NULL,
                                   CONSTRAINT ge_t_curso_pkey PRIMARY KEY (id)
);

CREATE TABLE public.ge_t_materia (
                                     id bigserial NOT NULL,
                                     nome varchar(255) NOT NULL,
                                     curso_id int8 NOT NULL,
                                     CONSTRAINT ge_t_materia_pkey PRIMARY KEY (id)
);
ALTER TABLE public.ge_t_materia ADD CONSTRAINT fkn253finjbawyt4suqf8dghfca FOREIGN KEY (curso_id) REFERENCES public.ge_t_curso(id);

CREATE TABLE public.ge_t_turma (
                                   id bigserial NOT NULL,
                                   nome varchar(255) NOT NULL,
                                   curso_id int8 NOT NULL,
                                   professor_id int8 NOT NULL,
                                   CONSTRAINT ge_t_turma_pkey PRIMARY KEY (id)
);

ALTER TABLE public.ge_t_turma ADD CONSTRAINT fkdx83fitji25t285h7f7j2e3fh FOREIGN KEY (curso_id) REFERENCES public.ge_t_curso(id);
ALTER TABLE public.ge_t_turma ADD CONSTRAINT fkro4p5vl6vbe95hlhl0yb48ifb FOREIGN KEY (professor_id) REFERENCES public.ge_t_docente(id);

CREATE TABLE public.ge_t_aluno (
                                   id bigserial NOT NULL,
                                   data_nascimento timestamp(6) NOT NULL,
                                   nome varchar(255) NOT NULL,
                                   turma_id int8 NOT NULL,
                                   usuario_id int8 NOT NULL,
                                   CONSTRAINT ge_t_aluno_pkey PRIMARY KEY (id),
                                   CONSTRAINT uk_565ttdu38xjpi67dn7v61w8hf UNIQUE (usuario_id)
);
ALTER TABLE public.ge_t_aluno ADD CONSTRAINT fk3ri0woswc671ei2usguk9ilff FOREIGN KEY (usuario_id) REFERENCES public.ge_t_usuario(id);
ALTER TABLE public.ge_t_aluno ADD CONSTRAINT fkagqhfjw3yt4chbbb436oy67fm FOREIGN KEY (turma_id) REFERENCES public.ge_t_turma(id);

CREATE TABLE public.ge_t_nota (
                                  id bigserial NOT NULL,
                                  "data" timestamp(6) NOT NULL,
                                  valor int8 NOT NULL,
                                  aluno_id int8 NOT NULL,
                                  materia_id int8 NOT NULL,
                                  professor_id int8 NOT NULL,
                                  CONSTRAINT ge_t_nota_pkey PRIMARY KEY (id)
);
ALTER TABLE public.ge_t_nota ADD CONSTRAINT fkc6iogl3wx4bdppclti8d13dit FOREIGN KEY (professor_id) REFERENCES public.ge_t_docente(id);
ALTER TABLE public.ge_t_nota ADD CONSTRAINT fkiv3del10t5eea8p8qa4gt9e38 FOREIGN KEY (aluno_id) REFERENCES public.ge_t_aluno(id);
ALTER TABLE public.ge_t_nota ADD CONSTRAINT fkp5v7x6tekit59x3yxopui8n0h FOREIGN KEY (materia_id) REFERENCES public.ge_t_materia(id);