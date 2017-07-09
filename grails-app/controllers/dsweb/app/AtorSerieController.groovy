package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class AtorSerieController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AtorSerie.list(params), model:[atorSerieCount: AtorSerie.count()]
    }

    def show(AtorSerie atorSerie) {
        respond atorSerie
    }

    def create() {
        respond new AtorSerie(params)
    }

    @Transactional
    def save(AtorSerie atorSerie) {
        if (atorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atorSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atorSerie.errors, view:'create'
            return
        }

        atorSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'atorSerie.label', default: 'AtorSerie'), atorSerie.id])
                redirect atorSerie
            }
            '*' { respond atorSerie, [status: CREATED] }
        }
    }

    def edit(AtorSerie atorSerie) {
        respond atorSerie
    }

    @Transactional
    def update(AtorSerie atorSerie) {
        if (atorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atorSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atorSerie.errors, view:'edit'
            return
        }

        atorSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'atorSerie.label', default: 'AtorSerie'), atorSerie.id])
                redirect atorSerie
            }
            '*'{ respond atorSerie, [status: OK] }
        }
    }

    @Transactional
    def delete(AtorSerie atorSerie) {

        if (atorSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        atorSerie.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'atorSerie.label', default: 'AtorSerie'), atorSerie.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'atorSerie.label', default: 'AtorSerie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
