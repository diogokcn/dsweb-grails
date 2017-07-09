package dsweb.app

class Autor {
    String nome
    String pseudonimo
    Date dataNascimento
    String biografia

    static constraints = {
        nome blank: true, nullable: true, size: 1..255
        pseudonimo blank: true, nullable: true, size: 1..255
        dataNascimento nullable: true
        biografia blank: true, nullable: true, size: 1..1023
    }

    static hasMany = [autorLivro: AutorLivro]
    static hasMany = [autorHQ: AutorHQ]
}
