package controllers

import javax.inject._

import play.api._
import play.api.mvc._
import play.twirl.api.Html
import models.Stock
import play.api.libs.json._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class Application @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  def index = Action {  implicit request =>
    Ok(views.html.list(Stock.getStock))

      }

  def stock= Action {implicit request =>

    val result:JsObject= Json.obj("Products" -> Stock.getStock)
    Ok(result)

  }




}
