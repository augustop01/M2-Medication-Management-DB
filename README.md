# Banco de Dados - Medication Management

## Descrição do Projeto

Projeto avaliativo do Módulo II do curso DEVinHouse-[Clamed]-v2, que utiliza o framework Spring Boot para criar uma aplicação de um banco de dados de uma rede de farmácias, gerenciando seus medacimentos e estoques.

## Tecnologias Utilizadas

- **Spring Boot:** Framework Java para criar aplicativos independentes, facilitando a configuração e o desenvolvimento.
- **Banco de Dados:** PostgresSQL.
- **Maven:** Ferramenta de gerenciamento de dependências para o Java.

## Pré-requisitos

Certifique-se de ter instalado em sua máquina:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

## Como Executar a Aplicação

1. Baixe o repositório pelo GitHub ou clone-o para a sua máquina:

   ```bash
   git clone https://github.com/augustop01/M2-Medication-Management-DB.git
   ```

2. Navegue até o diretório em que o projeto foi extraído/clonado.

3. Modifique o arquivo [application.properties] conforme as especificações de sua máquina e execute a aplicação usando utilizando uma IDE.

4. Acesse a aplicação no navegador ou através de uma plataforma de execução de APIs como Postman, Insomnia, entre outros:

   ```
   http://localhost:8080/
   ```

## Utilizando a Aplicação

### Inicialização do banco de dados

1. Após a execução da aplicação, três tabelas serão criadas no seu banco de dados: farmacias, medicamentos e estoques. Elas estarão vazias;
2. Para popular as tabelas, faça uma requisição POST, sem corpo de request, para: `[http](http://localhost:8080/inicializacao)`.
3. As tabelas serão populadas.

### Medicamentos
#### Consulta de medicamentos

1. Faça uma requisição GET para: `[http](http://localhost:8080/medicamentos)`.
   
#### Cadastro de novo medicamento

1. Faça uma requisição POST para: `[http](http://localhost:8080/medicamentos)`.
2. O request body deve ser seguir o formato a seguir:
    ```
    {
        "nroRegistro": 9999,
        "nome": "NomeMedicamento",
        "laboratorio": "LabCX",
        "dosagem": "0x ao dia",
        "descricao": "Descrição cabível pra um medicamento da categoria pertinente.",
        "preco": 10.0,
        "tipo": ("COMUM" OU "CONTROLADO")
    }
    ```
    
### Farmacias
####Consulta de farmácias

1. Para a consulta geral de farmácias, faça uma requisição GET para: `[http](http://localhost:8080/farmacias)`.
2. Para a consulta específica de farmácia, digite o CNPJ da farmácia em uma requisição GET para `[http](http://localhost:8080/farmacias/[CNPJ da farmácia])`.

####Cadastro de nova farmácia

1. Faça uma requisição POST para: `[http](http://localhost:8080/farmacias)`.
2. 2. O request body deve ser seguir o formato a seguir:
    ```
    {
    "cnpj": [CNPJ da nova farmácia (somente números)],
    "razaoSocial": "MedMaxi Ltda",
    "nomeFantasia": "MedMaxi",
    "email": "medmaxi@email.com",
    "telefone": "(99)9999-9999",
    "celular": "(99)99999-9999",
    "endereco": {
        "cep": 9090990,
        "logradouro": "Nome Rua",
        "numero": 123,
        "bairro": "Nome Bairro",
        "cidade": "Nome Cidade",
        "estado": "SC",
        "complemento": "apto 101",
        "latitude": 11.0000,
        "longitude": 8.0000
    }
}
    ```

### Estoques
#### Consulta de estoques

1. Para a consulta geral de estoques, faça uma requisição GET para: `[http](http://localhost:8080/estoque)`.
2. Para a consulta específica de estoque, digite o CNPJ da farmácia cujo estoque deseja consultar em uma requisição GET para `[http](http://localhost:8080/estoque/[CNPJ da farmácia])`.

#### Cadastro de novo estoque

1. Faça uma requisição POST para: `[http](http://localhost:8080/estoque)`.
2. 2. O request body deve ser seguir o formato a seguir:
    ```
    {
        "cnpj": [CNPJ da respectiva farmácia (somente números)],
        "nroRegistro": 9999,
        "quantidade": 9
    }
    ```

#### Venda de medicamento (deleção de estoque)

1. Faça uma requisição DELETE para: `[http](http://localhost:8080/estoque)`.
2. 2. O request body deve ser seguir o formato a seguir:
    ```
    {
        "cnpj": [CNPJ da respectiva farmácia (somente números)],
        "nroRegistro": 9999,
        "quantidade": 9
    }
    ```
    
OBS: Caso a quantidade em estoque do medicamento seja maior que a quantidade informada no request body, a primeira será apenas subtraída pela segunda, não sendo deletada do estoque.

#### Troca de medicamento entre estoques

1. Faça uma requisição PUT para: `[http](http://localhost:8080/estoque)`.
2. O request body deve ser seguir o formato a seguir:
    ```
    {
        "nroRegistro": 9999,
        "cnpjOrigem": [CNPJ da farmácia de origem (somente números)],
        "cnpjDestino": [CNPJ da farmácia de destino (somente números)],
        "quantidade": 5
    }
    ```

OBS: Caso todos os medicamentos da farmácia de origem sejam transferidores, o registro do respectivo medicamento será deletado de seu estoque.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE). Veja o arquivo [LICENSE](LICENSE) para obter mais detalhes.

---
