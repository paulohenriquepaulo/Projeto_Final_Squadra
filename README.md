![img.png](projetoFinalSquadra/src/main/java/br/com/squadra/app/imagens/img.png)

# 💻 DESAFIO FINAL - New Thinkes Program 2022:

Ao longo dessa jornada construiremos uma aplicação back-end de cadastro de endereços. Trabalharemos com cadastro
de UF, MUNICÍPIO, BAIRRO, ENDEREÇO e PESSOA. Iremos permitir o cadastro de vários endereços para uma pessoa.

CONSIDERAÇÕES GERAIS:

Construiremos: UF, MUNICIPIO, BAIRRO, PESSOA

- GET - obrigatório

- POST - obrigatório

- PUT - obrigatório

- DELETE - opcional

- Pode fazer apenas alterando um status de 1 para 2
- Pode fazer deletando um registro, mas neste caso precisa ser com deleção em cascata.

Todas as respostas devem vir com status 200 se deu certo e um status diferente de 200 se algo deu errado.
As mensagens de erro devem vir sempre em português

## 💻 Tecnologias utilizadas:

- JAVA 18
- Oracle Database
- Maven
- Spring
- JPA
- Postman

**Inicie o sistema a partir da clase** 

    @SpringBootApplication
    public class SquadraApplication {

    public static void main(String[] args) {
        SpringApplication.run(SquadraApplication.class, args);
         }
    }

**▶️Utilização**

1 - Cadastrar UF - **POST** ✅️

![img_7.png](projetoFinalSquadra/src/main/java/br/com/squadra/app/imagens/img_7.png)

2 - Atualizar UF - **PUT** ☑️

![img_8.png](projetoFinalSquadra/src/main/java/br/com/squadra/app/imagens/img_8.png)

3 - Buscar UF por criterio - **GET** 🔍

![img_9.png](projetoFinalSquadra/src/main/java/br/com/squadra/app/imagens/img_9.png)

4 - Deletar UF por sigla - **DELETE** ❌

![img_10.png](projetoFinalSquadra/src/main/java/br/com/squadra/app/imagens/img_10.png)