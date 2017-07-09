package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioHQController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioHQ.list(params), model:[usuarioHQCount: UsuarioHQ.count()]
    }

    def show(UsuarioHQ usuarioHQ) {
        respond usuarioHQ
    }

    def create() {
        respond new UsuarioHQ(params)
    }

    @Transactional
    def save(UsuarioHQ usuarioHQ) {
        if (usuarioHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioHQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioHQ.errors, view:'create'
            return
        }

        usuarioHQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioHQ.label', default: 'UsuarioHQ'), usuarioHQ.id])
                redirect usuarioHQ
            }
            '*' { respond usuarioHQ, [status: CREATED] }
        }
    }

    def edit(UsuarioHQ usuarioHQ) {
        respond usuarioHQ
    }

    @Transactional
    def update(UsuarioHQ usuarioHQ) {
        if (usuarioHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioHQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioHQ.errors, view:'edit'
            return
        }

        usuarioHQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioHQ.label', default: 'UsuarioHQ'), usuarioHQ.id])
                redirect usuarioHQ
            }
            '*'{ respond usuarioHQ, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioHQ usuarioHQ) {

        if (usuarioHQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usuarioHQ.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioHQ.label', default: 'UsuarioHQ'), usuarioHQ.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioHQ.label', default: 'UsuarioHQ'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
