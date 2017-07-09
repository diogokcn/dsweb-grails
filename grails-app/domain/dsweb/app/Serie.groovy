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
        nota min: 0.0f, max: 10.0f

        sinopse blank:true, nullable: true, size: 1..255
        genero blank:true, nullable: true, size: 1..255
        linguaOriginal blank:true, nullable: true, size: 1..255
        classificacaoIndicativa  min: 0

        numeroTemporadas min: 0
        numeroEpisodios min: 0
        produtora blank: true, nullable: true, size: 1..255
        statusProducao blank: true, nullable: true, size: 1..255
        duracaoMediaEpisodio min: 0
    }
}
