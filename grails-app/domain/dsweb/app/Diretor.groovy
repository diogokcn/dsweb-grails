package dsweb.app

class Diretor {
    String nome
    Date dataNascimento
    String biografia

    static constraints = {
        nome blank: true, size: 1..255
        dataNascimento nullable: false
        biografia size: 1..1023
    }
}
