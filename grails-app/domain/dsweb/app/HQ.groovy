package dsweb.app

class HQ {
    String nome
    float nota

    int numeroPaginas
    String editora
    String categoria

    int numeroVolumes
    String statusProducao

    static constraints = {
        nome blank: false, size: 1..255 
        nota blank: true, range: 0..10

        numeroPaginas min: 0, nullable: true
        editora blank: true, size: 0..255
        categoria blank: true, size: 0..255

        numeroVolumes nullable: true, min: 0
        statusProducao nullable: true, size: 0..255
    }
}