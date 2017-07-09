package dsweb.app

import grails.test.mixin.*
import spock.lang.*

@TestFor(UsuarioSerieController)
@Mock(UsuarioSerie)
class UsuarioSerieControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.usuarioSerieList
            model.usuarioSerieCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.usuarioSerie!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def usuarioSerie = new UsuarioSerie()
            usuarioSerie.validate()
            controller.save(usuarioSerie)

        then:"The create view is rendered again with the correct model"
            model.usuarioSerie!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            usuarioSerie = new UsuarioSerie(params)

            controller.save(usuarioSerie)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/usuarioSerie/show/1'
            controller.flash.message != null
            UsuarioSerie.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def usuarioSerie = new UsuarioSerie(params)
            controller.show(usuarioSerie)

        then:"A model is populated containing the domain instance"
            model.usuarioSerie == usuarioSerie
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def usuarioSerie = new UsuarioSerie(params)
            controller.edit(usuarioSerie)

        then:"A model is populated containing the domain instance"
            model.usuarioSerie == usuarioSerie
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/usuarioSerie/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def usuarioSerie = new UsuarioSerie()
            usuarioSerie.validate()
            controller.update(usuarioSerie)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.usuarioSerie == usuarioSerie

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            usuarioSerie = new UsuarioSerie(params).save(flush: true)
            controller.update(usuarioSerie)

        then:"A redirect is issued to the show action"
            usuarioSerie != null
            response.redirectedUrl == "/usuarioSerie/show/$usuarioSerie.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/usuarioSerie/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def usuarioSerie = new UsuarioSerie(params).save(flush: true)

        then:"It exists"
            UsuarioSerie.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(usuarioSerie)

        then:"The instance is deleted"
            UsuarioSerie.count() == 0
            response.redirectedUrl == '/usuarioSerie/index'
            flash.message != null
    }
}
