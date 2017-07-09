package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class AutorHQController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond AutorHQ.list(params), model:[autorHQCount: AutorHQ.count()]
    }

    def show(AutorHQ autorHQ) {
        respond autorHQ
    }

    def create() {
        respond new AutorHQ(params)
    }

    @Transactional
    def save(AutorHQ autorHQ) {
        if (autorHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (autorHQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond autorHQ.errors, view:'create'
            return
        }

        autorHQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'autorHQ.label', default: 'AutorHQ'), autorHQ.id])
                redirect autorHQ
            }
            '*' { respond autorHQ, [status: CREATED] }
        }
    }

    def edit(AutorHQ autorHQ) {
        respond autorHQ
    }

    @Transactional
    def update(AutorHQ autorHQ) {
        if (autorHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (autorHQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond autorHQ.errors, view:'edit'
            return
        }

        autorHQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'autorHQ.label', default: 'AutorHQ'), autorHQ.id])
                redirect autorHQ
            }
            '*'{ respond autorHQ, [status: OK] }
        }
    }

    @Transactional
    def delete(AutorHQ autorHQ) {

        if (autorHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        autorHQ.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'autorHQ.label', default: 'AutorHQ'), autorHQ.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'autorHQ.label', default: 'AutorHQ'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
