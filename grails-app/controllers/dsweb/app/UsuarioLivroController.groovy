package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioLivroController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioLivro.list(params), model:[usuarioLivroCount: UsuarioLivro.count()]
    }

    def show(UsuarioLivro usuarioLivro) {
        respond usuarioLivro
    }

    def create() {
        respond new UsuarioLivro(params)
    }

    @Transactional
    def save(UsuarioLivro usuarioLivro) {
        if (usuarioLivro == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioLivro.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioLivro.errors, view:'create'
            return
        }

        usuarioLivro.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioLivro.label', default: 'UsuarioLivro'), usuarioLivro.id])
                redirect usuarioLivro
            }
            '*' { respond usuarioLivro, [status: CREATED] }
        }
    }

    def edit(UsuarioLivro usuarioLivro) {
        respond usuarioLivro
    }

    @Transactional
    def update(UsuarioLivro usuarioLivro) {
        if (usuarioLivro == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioLivro.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioLivro.errors, view:'edit'
            return
        }

        usuarioLivro.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioLivro.label', default: 'UsuarioLivro'), usuarioLivro.id])
                redirect usuarioLivro
            }
            '*'{ respond usuarioLivro, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioLivro usuarioLivro) {

        if (usuarioLivro == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usuarioLivro.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioLivro.label', default: 'UsuarioLivro'), usuarioLivro.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioLivro.label', default: 'UsuarioLivro'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
