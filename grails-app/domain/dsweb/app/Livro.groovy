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
        nota blank: true, range: 0..10

        numeroPaginas min: 0, nullable: true
        editora blank: true, size: 0..255
        categoria blank: true, size: 0..255
        
        anoPublicacao nullable: true
    }
}
