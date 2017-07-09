# DSWeb - Grails  
## Informações gerais
A documentação que será gerada em PDF é o arquivo README.md que está na pasta [doc](https://github.com/LeandroNovak/dsweb-grails/tree/master/doc)!  

Este arquivo serve apenas de referência para sabermos quais tarefas precisam ser feitas.  
Para gerar o PDF da documentação pode-se usar [este site](http://www.markdowntopdf.com/).  

Como fiquei sem paciência para os travamentos do intellij, agora estou usando o visual studio code e o terminal. Para compilar uso o terminal com o comando:  

        grails run-app  

Durante o desenvolvimento gero as models(domains) com:  

        grails create-domain-class nomeClasse  

E por último gero as views e controllers automaticmante com:  

        grails generate-all nomePackage.nomeClasse  

Por hora esses comandos estão sendo suficientes e como o projeto está com muitos models, eu estou gerando tudo com o script chamado xgenerate-all.sh.


## MySQL

Como o H2 não permite persistência de dados e eu não estava conseguindo configurar o PostgreSQL, resolvi instalar o MySQL, para usá-lo basta seguir os seguintes passos:  

        sudo apt-get install mysql-server  
        sudo mysql -p // isso executará o mysql com seu usuário e será necessário informar a senha logo em seguida  
        create database db;  
        create user user;  
        grant all on db.* to 'user'@'localhost' identified by 'password';  
        grant select, insert, delete, update on db.* to 'user'@'localhost' identified by 'password';  


## Andamento

Classes que foram minimamente implementadas:  
        
        - Ator  
        - Autor  
        - Diretor  
        - Filme  
        - HQ  
        - Livro  
        - Serie  
        - Usuário  
        - e as relações entre essas classes (ainda deixando a desejar)  


## TO DO  
> Marque a [ ] com um x quando for feita a tarefa!  
> Adicione mais itens se necessário.  

- [x] Postar no moodle o nosso grupo com o link para este repositório  
- [x] Definir as entidades  
- [x] Fazer diagrama de classes  
- [x] Configurar projeto para o heroku (não está funcionando corretamente o postgresql)  
- [x] Criar as classes a partir das entidades
- [ ] Criar as funções  
- [ ] Criar o front-end  
- [ ] Fazer testes  
- [ ] Completar a documentação  
- [ ] Definir como será a apresentação  


## Autores  

Aluno | RA  
------------ | ------------  
Felipe Francisco Berreta | 422649  
Leandro Prates Novak  | 586927  
Lucas Hochleitner da Silva | 586706  

