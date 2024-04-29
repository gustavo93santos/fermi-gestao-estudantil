# Fermi Gestão Estudantil

Software batizado em homenagem a Enrico Fermi, um fisico italiano naturalizado estadunidense vencedor do premio Nobel de fisica em 1938.

## Sobre o Software

O software visa ser uma suite completa para gestão estudantil, permitindo cadastro e acompanhamento de informações de diversos niveis.

O cadastro de curso possui 3 niveis sendo eles Curso, Materia e Turma.
Tambem possui controle de acesso para usuarios com politica de acessos diferencias baseado nas atribuições do Docente, tambem possuindo acesso para alunos de forma centralizada.

## Tech

O sistema foi desenvolvido utilizando Spring e Java para o condigo fonte, para o armazenamento de dados, para atender um dos requisitos foi utilizado o Postgres SQL um banco de dados ja bem estabelecido no mercado.

Para a gestão das atividades foi utilizada a ferramenta Trello e conjunto com a metodologia Kanban

## Como executar

- Clone o projeto em um diretorio local
```
git clone https://github.com/gustavo93santos/fermi-gestao-estudantil.git
```

- Criar container Docker para abrigar o banco de dados com o seguinte comando
```
docker run --hostname=7f3e8ef0161b --mac-address=02:42:ac:11:00:02 --env=PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/lib/postgresql/16/bin --env=GOSU_VERSION=1.17 --env=PG_VERSION=16.2-1.pgdg120+2 --env=LANG=en_US.utf8 --env=PG_MAJOR=16 --env=PGDATA=/var/lib/postgresql/data --env=POSTGRES_PASSWORD=postgres --env=POSTGRES_USER=postgres --env=POSTGRES_DB=postgres --volume=/var/lib/postgresql/data -p 1432:5432 --restart=no --runtime=runc -d postgres:latest
```

- Conectar ao banco de dados com os seguintes dados.
```
User: postgres
Password: postgres
db: postgres
port: 1432
```

- Executar os scripts contidos no diretorio resources do diretorio clonado na seguinte ordem
1. schema.sql
2. data.sql

- Inicie o projeto através do InteliJ


- As rotas estarão disponiveis para acesso nos endpoints, conforme descrito no documento de especificação abaixo sendo acessadas através da raiz http://localhost:8090/
```
https://docs.google.com/document/d/1ZfxKvxv36a6sjDmaAxYFqLbJr1KqB6wNkDOnI6S_rns/edit
```