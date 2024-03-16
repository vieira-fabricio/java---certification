# Certifika - Sistema de Certificação com Java e Spring Boot

Projeto de um sistema de certificações criado em `JAVA` utilizando o Framework `Spring Boot`. O Banco de dados utilizado foi o `PostgreSql`.
onde o usuário se cadastra informando seu nome, e-mail e tecnologia que pretende se certificar.

| Linguagens de Programação | Ferramentas e Tecnologias |
| :-----------------: | :-----------------------: |
| <img height="40" src="https://github.com/rhayssakramer/rhayssakramer/blob/main/assets/icon/Java-Dark.svg"> 
  <img height="40" src=" https://github.com/rhayssakramer/rhayssakramer/blob/main/assets/icon/postgree.svg "> 
| <img height="40" src="https://logowik.com/content/uploads/images/intellij-idea286.logowik.com.webp">
  <img height="40" src=" https://www.vectorlogo.zone/util/preview.html?image=/logos/springio/springio-icon.svg ">


## Instalação

Se você deseja baixar e executar este projeto em sua máquina local, siga estas etapas:

- Clone o repositório:

Abra seu terminal e execute o seguinte comando para clonar o repositório:
```git clone https://github.com/vieira-fabricio/java-certification.git```

- Acesse o diretório:
Navegue até o diretório do projeto usando o seguinte comando:
```cd seurepositorio```

- Configuração do Banco de Dados:
Certifique-se de ter um servidor `PostgreSQL` em execução. Você pode configurar as credenciais do banco de dados no arquivo `application.properties` ou `application.yml`.

- Build e Execução:
Use o `Maven` para construir o projeto e executá-lo localmente:
```mvn spring-boot:run```
Ou execute o código direto da sua `IDE` de preferência.

- Testando a API com Postman:
Você pode usar o `Postman` ou outro programa de sua preferência para testar e consumir os endpoints da API. Siga estas etapas:

- Configuração do ambiente:
Antes de enviar solicitações, defina o ambiente do Postman para o seu projeto. Você pode criar um novo ambiente com variáveis como base_url, que aponta para http://localhost:8080, ou ajustar conforme necessário para o ambiente em que a API está hospedada.

- Enviar solicitações:
Agora você pode enviar solicitações para os endpoints da API. Por exemplo:
- Para verificar se você já fez a certificação, envie um POST para localhost:8080/students/verifyifhascertification com o corpo JSON contendo seu email e a tecnologia escolhida.
- Para verificar as questões da certificação a ser feita, envie um GET para localhost:8080/questions/technology/{nome da tecnologia}
- Para criar uma nova certificação, envie um POST para localhost:8080/students/certification/answer com o corpo JSON contendo as respostas das questões.
- Para obter todos os certificados em ordem da melhor nota para a pior, envie um GET para localhost:/ranking//top10.

## Observação´
- Certifique-se de ter o Java e o Maven instalados em seu sistema.
- Antes de iniciar o servidor Spring Boot, verifique se as configurações do banco de dados estão corretas no arquivo `application.properties` ou `application.yml`.

## Autor
<table>
  <tr>
    <td align="left">
      <a href="https://github.com/vieira-fabricio">
        <span><b>Fabricio Vieira</b></span>
      </a>
      <br>
      <span>Desenvolvedor Backend Java</span>
    </td>
  </tr>
</table>

## Histórico de Mudanças

- 1.0.0 (09/03/2024): Primeira versão lançada.
