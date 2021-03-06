package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task

object Application extends Controller {
  
  val taskForm = Form(
  	"name" -> nonEmptyText
  )

  /**
  * I read this as:  I have a function defined named "index" that returns an Action.  The
  * function will redirect the request to the Application route named "tasks" defined in the
  * routes configuration.
  */
  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
  	Ok(views.html.index(Task.twoTasks(), taskForm))
  }
  
  def newTask = Action { implicit request =>
  	taskForm.bindFromRequest.fold(
  		errors => BadRequest(views.html.index(Task.twoTasks(), errors)),
  		name => {
  			Task.create(name)
  			Redirect(routes.Application.tasks)
  		}
  	)
  }

  def deleteTask (id: Long) = TODO
}