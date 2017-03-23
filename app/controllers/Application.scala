package controllers

import javax.inject._
import models.Stock
import play.api.libs.json._
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class Application @Inject() extends Controller {



  implicit val userFormat = Json.format[models.Product]
  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */

  def index = Action { implicit request =>
    Ok(views.html.list(Stock.getStock))

  }

  //Returns the stock as Json
  def stock = Action { implicit request =>
    Ok(Json.obj("Products" -> Stock.getStock))
  }

 // Returns the product matching name as Json
  def productByName(name: String) = Action { implicit request =>
    Ok(Json.toJson(Stock.getProductByName(name)))
  }

  // Adds a new product to Stock and returns the updated Stock as Json
  def addNewProduct(name: Option[String], qty: Option[Int]) = Action { implicit request =>
    if (name == None || qty == None)
      BadRequest(Json.toJson("Missing parameters"))
    else if(Stock.isProductExist(name.get))
      Ok(Json.toJson("Item already exist"))
    else {
      Stock.addNewProduct(name.get, qty.get)
      Ok(Json.obj("Products" -> Stock.getStock))
    }
  }


// Delete an existing product from Stock and returns the updated Stock as Json
  def deleteProduct(name: Option[String]) = Action { implicit request =>
    if (name == None)
      BadRequest("Missing parameters")
    else if(!Stock.isProductExist(name.get))
      Ok(Json.toJson("Item doesn't exist in stock"))
    else {
      Stock.deleteProduct(name.get)
      Ok(Json.obj("Products" -> Stock.getStock))
    }
  }


//  Fills products matching name and returns the updated Stock as Json
  def addStock(name: Option[String], qty: Option[Int]) = Action { implicit request =>
    if (name == None || qty == None)
      BadRequest("Missing parameters")
     else if(!Stock.isProductExist(name.get))
      Ok(Json.toJson("Item doesn't exist in stock"))
    else {
      Stock.addStock(name.get, qty.get)
      Ok(Json.obj("Products" -> Stock.getStock))
    }
  }


  // Remove specified amount of items from stock and returns the updated Stock as Json

  def takeStock(name: Option[String], qty: Option[Int]) = Action { implicit request =>
    if (name == None || qty == None)
      BadRequest("Missing parameters")
    else if(!Stock.isProductExist(name.get))
      Ok(Json.toJson("Item doesn't exist in stock"))
    else {
      Stock.takeStock(name.get, qty.get)
      Ok(Json.obj("Products" -> Stock.getStock))
    }
  }


}
