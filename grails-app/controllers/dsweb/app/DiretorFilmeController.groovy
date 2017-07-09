package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class DiretorFilmeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DiretorFilme.list(params), model:[diretorFilmeCount: DiretorFilme.count()]
    }

    def show(DiretorFilme diretorFilme) {
        respond diretorFilme
    }

    def create() {
        respond new DiretorFilme(params)
    }

    @Transactional
    def save(DiretorFilme diretorFilme) {
        if (diretorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diretorFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diretorFilme.errors, view:'create'
            return
        }

        diretorFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'diretorFilme.label', default: 'DiretorFilme'), diretorFilme.id])
                redirect diretorFilme
            }
            '*' { respond diretorFilme, [status: CREATED] }
        }
    }

    def edit(DiretorFilme diretorFilme) {
        respond diretorFilme
    }

    @Transactional
    def update(DiretorFilme diretorFilme) {
        if (diretorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (diretorFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond diretorFilme.errors, view:'edit'
            return
        }

        diretorFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'diretorFilme.label', default: 'DiretorFilme'), diretorFilme.id])
                redirect diretorFilme
            }
            '*'{ respond diretorFilme, [status: OK] }
        }
    }

    @Transactional
    def delete(DiretorFilme diretorFilme) {

        if (diretorFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        diretorFilme.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'diretorFilme.label', default: 'DiretorFilme'), diretorFilme.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'diretorFilme.label', default: 'DiretorFilme'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
