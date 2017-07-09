package dsweb.app

class Ator {
    String nomeReal
    String nomeArtistico
    Date dataNascimento
    String localNascimento
    String biografia

    static constraints = {
        nomeReal blank: true, nullable: true, size: 1..255
        nomeArtistico blank: true, nullable: true, size: 1..255
        dataNascimento nullable: true
        localNascimento blank: true, nullable: true, size: 1..255
        biografia blank: true, nullable: true, size: 1..1023
    }
}
