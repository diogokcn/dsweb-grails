package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioFilmeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioFilme.list(params), model:[usuarioFilmeCount: UsuarioFilme.count()]
    }

    def show(UsuarioFilme usuarioFilme) {
        respond usuarioFilme
    }

    def create() {
        respond new UsuarioFilme(params)
    }

    @Transactional
    def save(UsuarioFilme usuarioFilme) {
        if (usuarioFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioFilme.errors, view:'create'
            return
        }

        usuarioFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioFilme.label', default: 'UsuarioFilme'), usuarioFilme.id])
                redirect usuarioFilme
            }
            '*' { respond usuarioFilme, [status: CREATED] }
        }
    }

    def edit(UsuarioFilme usuarioFilme) {
        respond usuarioFilme
    }

    @Transactional
    def update(UsuarioFilme usuarioFilme) {
        if (usuarioFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioFilme.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioFilme.errors, view:'edit'
            return
        }

        usuarioFilme.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioFilme.label', default: 'UsuarioFilme'), usuarioFilme.id])
                redirect usuarioFilme
            }
            '*'{ respond usuarioFilme, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioFilme usuarioFilme) {

        if (usuarioFilme == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usuarioFilme.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioFilme.label', default: 'UsuarioFilme'), usuarioFilme.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioFilme.label', default: 'UsuarioFilme'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
