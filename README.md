# Barbearia

Aplicação web para uma barbearia, desenvolvida com Java, Spring Boot, Thymeleaf e Bootstrap. Inclui páginas para agendamento e administração, além de uma API REST para cadastro de barbeiros.

## Requisitos

- Java 21 ou superior
- Não é necessário instalar o Maven: o projeto inclui Maven Wrapper

## Como executar no Windows

Abra o PowerShell nesta pasta (`MyFirtsSpringBoot-main`) e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Quando a aplicação iniciar, acesse <http://localhost:8080>. Para encerrar, pressione `Ctrl+C` no terminal.

Para executar os testes:

```powershell
.\mvnw.cmd test
```

## Páginas

| Endereço | Página |
| --- | --- |
| `/` ou `/agendar` | Agendamento |
| `/admin/login` | Login da administração |
| `/admin/painel-agenda` ou `/admin/painel` | Painel da agenda |
| `/cliente/status` | Consulta de status do agendamento |
| `/cliente/cancelar` | Cancelamento de agendamento |
| `/cliente/remarcar` | Remarcação de agendamento |

## API de barbeiros

A API base é `/api/barbeiros` e oferece operações para listar, consultar, criar, atualizar e excluir barbeiros.

| Método | Endereço | Ação |
| --- | --- | --- |
| `GET` | `/api/barbeiros` | Lista barbeiros |
| `GET` | `/api/barbeiros/{id}` | Consulta um barbeiro |
| `POST` | `/api/barbeiros` | Cria um barbeiro |
| `PUT` | `/api/barbeiros/{id}` | Atualiza um barbeiro |
| `DELETE` | `/api/barbeiros/{id}` | Exclui um barbeiro |

Exemplo de corpo para `POST` e `PUT`:

```json
{
  "nome": "João",
  "foto": "/images/joao.jpg",
  "senhaHash": "<hash-da-senha>",
  "tentativasLoginFalhas": 0,
  "bloqueadoAte": null
}
```

`bloqueadoAte` aceita uma data e hora no formato ISO 8601, por exemplo `"2026-10-20T21:30:00"`, ou `null`. A API não retorna o campo `senhaHash` nas respostas. Envie nesse campo somente um hash de senha, nunca uma senha em texto puro.

## Banco de dados

A aplicação usa H2 em modo arquivo, armazenado em `data/barbearia`, na pasta de execução do projeto. Assim, os registros permanecem salvos quando a aplicação é reiniciada. As tabelas são atualizadas automaticamente pelo JPA.


## Desenvolvedores

- Thiago Melo — front-end e back-end; responsável pela empresa
- Sergio Henry — back-end; busca e números de agendamento
- Patricia Oliveira — back-end e front-end; responsável por vagas
- Kaue Apolinario — back-end; pessoa e login