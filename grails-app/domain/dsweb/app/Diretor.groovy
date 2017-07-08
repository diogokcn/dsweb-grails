package dsweb.app

class Diretor {
    String nome
    Date dataNascimento
    String biografia

    static constraints = {
        nome blank: false, size: 1..255
        dataNascimento nullable: true
        biografia blank: true, size: 1..1023
    }
}
