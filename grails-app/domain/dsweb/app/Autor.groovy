package dsweb.app

class Autor {
    String nome
    String pseudonimo
    Date dataNascimento
    String biografia

    static constraints = {
        nome blank: true, size: 1..255
        pseudonimo blank: true, size: 1..255
        dataNascimento nullable: false
        biografia size: 1..1023
    }
}
