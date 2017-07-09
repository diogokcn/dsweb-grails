package dsweb.app

class AtorFilme {
//    Ator ator
//    Filme filme

    static constraints = {
//        ator nullable: false
//        filme nullable: false
    }

    static hasOne = [filme: Filme, ator: Ator]
}
