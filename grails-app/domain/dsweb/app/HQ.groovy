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
        nota min: 0.0f, max: 10.0f

        numeroPaginas min: 0
        editora blank: true, nullable: true, size: 1..255
        categoria blank: true, nullable: true, size: 1..255

        numeroVolumes nullable: true, min: 0
        statusProducao blank: true, nullable: true, size: 1..255
    }
}