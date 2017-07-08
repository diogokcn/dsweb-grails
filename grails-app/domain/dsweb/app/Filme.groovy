package dsweb.app

class Filme {
    String nome
    float nota

    Date dataLancamento
    String sinopse
    String genero
    String linguaOriginal
    int classificacaoIndicativa
    int duracao

    // em milhoes:
    int orcamento
    int bilheteria
    
    // de uso do moderador:
    Boolean aprovado

    static constraints = {
        nome blank: false, size: 1..255 
        nota blank:true, range: 0..10

        sinopse blank:true, size: 0..255
        genero blank:true, size: 0..255
        linguaOriginal blank:true, size: 0..255
        classificacaoIndicativa nullable: true, min: 0

        duracao nullable: true, min: 0
        orcamento nullable: true, min: 0
        bilheteria nullable: true, min: 0
    }
}
