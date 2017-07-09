package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioSerieController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond UsuarioSerie.list(params), model:[usuarioSerieCount: UsuarioSerie.count()]
    }

    def show(UsuarioSerie usuarioSerie) {
        respond usuarioSerie
    }

    def create() {
        respond new UsuarioSerie(params)
    }

    @Transactional
    def save(UsuarioSerie usuarioSerie) {
        if (usuarioSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioSerie.errors, view:'create'
            return
        }

        usuarioSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuarioSerie.label', default: 'UsuarioSerie'), usuarioSerie.id])
                redirect usuarioSerie
            }
            '*' { respond usuarioSerie, [status: CREATED] }
        }
    }

    def edit(UsuarioSerie usuarioSerie) {
        respond usuarioSerie
    }

    @Transactional
    def update(UsuarioSerie usuarioSerie) {
        if (usuarioSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (usuarioSerie.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond usuarioSerie.errors, view:'edit'
            return
        }

        usuarioSerie.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuarioSerie.label', default: 'UsuarioSerie'), usuarioSerie.id])
                redirect usuarioSerie
            }
            '*'{ respond usuarioSerie, [status: OK] }
        }
    }

    @Transactional
    def delete(UsuarioSerie usuarioSerie) {

        if (usuarioSerie == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        usuarioSerie.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuarioSerie.label', default: 'UsuarioSerie'), usuarioSerie.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuarioSerie.label', default: 'UsuarioSerie'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
