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

    // em dolares:
    int orcamento
    int bilheteria
    
    // de uso do moderador:
    Boolean aprovado

    static constraints = {
        nome blank: false, size: 1..255, unique: true
        nota min: 0.0f, max: 10.0f

        dataLancamento nullable: true
        sinopse blank: true, nullable: true
        genero blank: true, nullable: true, size: 0..255
        linguaOriginal blank: true, nullable: true, size: 0..255
        classificacaoIndicativa nullable: true, min: 0

        duracao min: 0
        orcamento min: 0
        bilheteria min: 0
    }

    static hasMany = [usuarioFilme: UsuarioFilme, atorFilme: AtorFilme, diretorFilme: DiretorFilme]
}
