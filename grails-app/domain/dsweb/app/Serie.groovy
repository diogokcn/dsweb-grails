package dsweb.app

class Serie {
    String nome
    float nota

    Date dataLancamento
    String sinopse
    String genero
    String linguaOriginal
    int classificacaoIndicativa

    int numeroTemporadas
    int numeroEpisodios
    String produtora
    String statusProducao
    int duracaoMediaEpisodio
    
    // de uso do moderador:
    Boolean aprovado

    static constraints = {
        nome blank: false, size: 1..255 
        nota blank:true, range: 0..10

        sinopse blank:true, size: 0..255
        genero blank:true, size: 0..255
        linguaOriginal blank:true, size: 0..255
        classificacaoIndicativa nullable: true, min: 0

        numeroTemporadas nullable: true, min: 0
        numeroEpisodios nullable: true, min: 0
        produtora blank: true, size: 0..255
        statusProducao blank: true, size: 0..255
        duracaoMediaEpisodio nullable: true, min: 0
    }
}
