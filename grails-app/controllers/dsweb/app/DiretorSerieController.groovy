package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class DiretorSerieController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DiretorSerie.list(params), model:[diretorSerieCount: DiretorSerie.count()]
    }

    def show(DiretorSerie diretorSerie) {
        respond diretorSerie
    }

    def create() {
        respond new DiretorSerie(params)
    }

    @Transactional
    def save(DiretorSerie diretorSerie) {
        if (diretorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diretorSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diretorSerie.errors, view:'create'
            return
        }

        diretorSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'diretorSerie.label', default: 'DiretorSerie'), diretorSerie.id])
                redirect diretorSerie
            }
            '*' { respond diretorSerie, [status: CREATED] }
        }
    }

    def edit(DiretorSerie diretorSerie) {
        respond diretorSerie
    }

    @Transactional
    def update(DiretorSerie diretorSerie) {
        if (diretorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diretorSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diretorSerie.errors, view:'edit'
            return
        }

        diretorSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'diretorSerie.label', default: 'DiretorSerie'), diretorSerie.id])
                redirect diretorSerie
            }
            '*'{ respond diretorSerie, [status: OK] }
        }
    }

    @Transactional
    def delete(DiretorSerie diretorSerie) {

        if (diretorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        diretorSerie.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'diretorSerie.label', default: 'DiretorSerie'), diretorSerie.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'diretorSerie.label', default: 'DiretorSerie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
