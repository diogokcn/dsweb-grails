# Documentação  


## Introdução  

O sistema a ser desenvolvido pode ser definido como um sistema de gerenciamento de Filmes, Séries, Livros e HQs onde os usuários pode adicionar itens ao sistema e "marcar" esses itens como Lidos ou Assistidos, além de atribuir uma nota aos itens associados.


## Entidades  

* __Ator:__ Entidade que representa um ator que pode ter participado de filmes e/ou séries. Tem como atributos nomes, data de nascimento, local de nascimento e biografia.  

* __Autor:__ Entidade que representa o escritor de algum livro ou HQ. Pode possuir um nome e um pseudonimo, além da data de nascimento e a biografia.  

* __Diretor:__ Diretor de algum filme ou serie. Possui nome, data de nascimento e biografia.  

* __Filme:__ Uma produção cinematográfica composta por nome, a média das notas dadas pelo usuário, data de lançamento, sinopse, genero, lingua original, classificacao indicativa, duração em minutos e orçamento bilheteria em dólares. Além disso possui um atributo que indica se a inserção foi aprovada por um moderador.

* __HQ:__ Representa Histórias em Quadrinhos, que podem possuir nome, nota média, número médio de páginas por volume, editora, categoria, número de volumes publicados e o status de produção (se já começou a ser publicado, se está publicando ou se já foi encerrado), além do atributo de aprovação por moderador.

* __Livro:__ Entidade que representa um livro, contém atributos como nome, nota média, número de páginas, editora, categoria, ano da publicação e o atributo de aprovação.

* __Serie:__ Representa uma série de TV. Possui como atributos o nome, a nota média, a data da primeira exibição, sinopse, gênero, língua original, classificação indicativa, número de temporadas produzidas, número de episódios produzidos, produtora, status de produção, tempo de duração médio por episódio e o status de aprovação.

* __Usuario:__ Representa um usuário do sistema, pode ser ou não moderador (condição atribuida por um moderador ou pelo administrador do sistema), nome, data de nascimento, login, password e email.


## Funcionalidades  

Além das funcionalidades do tipo CRUD, o sistema possui as seguintes funcionalidades:

__De inserção:__  

* Inserção de novos Atores, Autores, Diretores, Livros, HQs, Filmes ou Séries no sistema (Usuário deve estar autenticado).

* Associação de Atores e Diretores com Séries e Filmes.

* Associação de Autores com Livros e HQs.

* Associação de Livros, HQs, Filmes ou Séries com o Usuário (deve estar autenticado), contendo na associação atributos como nota, status e comentários.

__De listagem:__  

* Listagem dos Filmes, Séries, Livros e HQs associados a um determinado usuário.  

* Listagem dos Filmes, Séries, Livros e HQs ordenados pela nota ou pelos que possuem maior número de usuários associados (crescente ou decrescente).  

* Listagem dos Usuários do sistema ordenados pelo número de itens associados (listagem por categoria).  


## Autores  

Aluno | RA  
------------ | ------------  
Felipe Francisco Berreta | 422649  
Leandro Prates Novak  | 586927
Lucas Hochleitner da Silva | 586706


## Referências  
Material disponibizado em aula. Disponível em: <http://www2.dc.ufscar.br/~delano/Grails/>.  
Livro texto do professor Disponívem em: <https://github.com/delanobeder/FG/blob/master/Texto/Version2/Livro.pdf>.  
 WEISSMANN, Henrique Lobo. Falando de Grails. Casa do Código.  