INSERT INTO public.ge_t_papel (nome) VALUES
                                         ('ADM'),
                                         ('PEDAGOGICO'),
                                         ('RECRUITER'),
                                         ('PROFESSOR'),
                                         ('ALUNO');

INSERT INTO public.ge_t_usuario
(id, nome_usuario, senha, papel_id)
VALUES(4, 'ADM', '$2a$10$51udskr.nI99bqAaHj3gXeWb5FP5DX/VnPTx8h0wxDtBN3KPGhpRK', 1);