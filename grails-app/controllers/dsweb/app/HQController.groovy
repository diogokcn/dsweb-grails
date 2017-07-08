package dsweb.app

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HQController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond HQ.list(params), model:[HQCount: HQ.count()]
    }

    def show(HQ HQ) {
        respond HQ
    }

    def create() {
        respond new HQ(params)
    }

    @Transactional
    def save(HQ HQ) {
        if (HQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (HQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond HQ.errors, view:'create'
            return
        }

        HQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'HQ.label', default: 'HQ'), HQ.id])
                redirect HQ
            }
            '*' { respond HQ, [status: CREATED] }
        }
    }

    def edit(HQ HQ) {
        respond HQ
    }

    @Transactional
    def update(HQ HQ) {
        if (HQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (HQ.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond HQ.errors, view:'edit'
            return
        }

        HQ.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'HQ.label', default: 'HQ'), HQ.id])
                redirect HQ
            }
            '*'{ respond HQ, [status: OK] }
        }
    }

    @Transactional
    def delete(HQ HQ) {

        if (HQ == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        HQ.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'HQ.label', default: 'HQ'), HQ.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'HQ.label', default: 'HQ'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
