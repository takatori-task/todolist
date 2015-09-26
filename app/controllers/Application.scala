package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

import java.util.UUID
import util.OAuth2


object Application extends Controller {

  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def signin = Action { implicit request =>
    val oauth2 = new OAuth2(Play.current) // OAuth2インスタンス作成
    val callbackUrl = util.routes.OAuth2.callback(None, None).absoluteURL() //callbackURLの取得
    val scope = "user"   // github scope - request repo access
    val state = UUID.randomUUID().toString  // random confirmation string
    val redirectUrl = oauth2.getAuthorizationUrl(callbackUrl, scope, state)
    Ok(views.html.signin("Your new application is ready.", redirectUrl)).
      withSession("oauth-state" -> state)
  }

  def signout = TODO
  

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(request.session.get("user"), label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }
}


