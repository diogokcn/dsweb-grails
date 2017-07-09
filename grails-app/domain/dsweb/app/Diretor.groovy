package dsweb.app

class Diretor {
    String nome
    Date dataNascimento
    String biografia

    static constraints = {
        nome blank: true, nullable: true, size: 1..255
        dataNascimento nullable: true
        biografia blank: true, nullable: true, size: 1..1023
    }
    
    static hasMany = [diretorFilme: DiretorFilme]
    static hasMany = [diretorSerie: DiretorSerie]
}
