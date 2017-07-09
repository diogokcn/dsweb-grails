package dsweb.app

class Livro {
    String nome
    float nota

    int numeroPaginas
    String editora
    String categoria

    Date anoPublicacao
    
    // de uso do moderador:
    Boolean aprovado

    static constraints = {
        nome blank: false, size: 1..255 
        nota min: 0.0f, max: 10.0f

        numeroPaginas min: 0
        editora blank: true, nullable: true, size: 1..255
        categoria blank: true, nullable: true, size: 1..255
        
        anoPublicacao nullable: true
    }
    static hasMany = [autorLivro: AutorLivro, usuarioLivro: UsuarioLivro]
}
