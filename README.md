# Blog com Java e Spring

Neste repositório esta o código do fonte da API de um Blog escrita em **Java** utilizando o **Spring Framework**. 

Esse projeto é uma iniciativa minha para me aprofundar mais no universo do Java, começando com uma API simples, porém entregando a melhor qualidade possível para o projeto.

## Arquitetura

O código foi escrito sobre a arquitetura hexagonal, onde o core da aplicação ficará desacoplado das implementações externas, além de seguir as melhores práticas de POO e os principios do SOLID.

O código foi dividio em 2 pacotes principais são elas:

- `core`: Onde ficará as entidades e os casos de uso com a regras de negócio e desacoplado de qualquer implementação externa, além das portas que serão interfaces que o pacote de adaptadores irão implementar.
- `adapters`: Onde ficará as implementação das portas.

## Funcionalidades

O blog terá poucas funcionaldiades, que são um CRUD de usuário de postagens, com as seguintes regras de negócio.

## Autoria

Criado por **Arthur Reis**.