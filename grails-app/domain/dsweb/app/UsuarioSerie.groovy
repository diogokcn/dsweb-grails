package dsweb.app

class UsuarioSerie {
    String status 
    float nota
    String comentarios

    static constraints = {
        status blank: true, nullable: true, size: 1..255 
        nota min: 0.0f, max: 10.0f
        comentarios blank: true, nullable: true
    }
}
