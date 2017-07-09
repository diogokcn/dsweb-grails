package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class AtorFilmeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AtorFilme.list(params), model:[atorFilmeCount: AtorFilme.count()]
    }

    def show(AtorFilme atorFilme) {
        respond atorFilme
    }

    def create() {
        respond new AtorFilme(params)
    }

    @Transactional
    def save(AtorFilme atorFilme) {
        if (atorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atorFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atorFilme.errors, view:'create'
            return
        }

        atorFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'atorFilme.label', default: 'AtorFilme'), atorFilme.id])
                redirect atorFilme
            }
            '*' { respond atorFilme, [status: CREATED] }
        }
    }

    def edit(AtorFilme atorFilme) {
        respond atorFilme
    }

    @Transactional
    def update(AtorFilme atorFilme) {
        if (atorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atorFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atorFilme.errors, view:'edit'
            return
        }

        atorFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'atorFilme.label', default: 'AtorFilme'), atorFilme.id])
                redirect atorFilme
            }
            '*'{ respond atorFilme, [status: OK] }
        }
    }

    @Transactional
    def delete(AtorFilme atorFilme) {

        if (atorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        atorFilme.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'atorFilme.label', default: 'AtorFilme'), atorFilme.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'atorFilme.label', default: 'AtorFilme'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
