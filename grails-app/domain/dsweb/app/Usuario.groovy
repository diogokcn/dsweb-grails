package dsweb.app

class Usuario {
    String nome
    Date dataNascimento
    String login
    String password
    String email
    Boolean moderador

    static constraints = {
        nome blank: false, size: 1..255
        dataNascimento nullable: false
        login blank: false, size: 1..255
        password blank: false, size: 4..255, password: true
        email blank: false, size: 1..255, email: true
    }

    static hasMany = [usuarioFilme: UsuarioFilme, usuarioHQ: UsuarioHQ, usuarioLivro: UsuarioLivro, usuarioSerie: UsuarioSerie]
}