package dsweb.app

class Ator {
    String nomeReal
    String nomeArtistico
    Date dataNascimento
    String localNascimento
    String biografia

    static constraints = {
        nomeReal blank: true, size: 1..255
        nomeArtistico blank: false, size: 1..255
        dataNascimento nullable: false
        localNascimento size: 1..255
        biografia size: 1..1023
    }
}
