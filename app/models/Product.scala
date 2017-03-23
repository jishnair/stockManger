package models


/**
  * Created by jishnair on 22/03/2017.
  */


//Product type definition
case class Product(name: String, quantity: Int)

object Stock {

  //Sample inventory
  var stock = Set(Product("pen", 10),
    Product("pencil", 100),
    Product("notebook", 25),
    Product("paperclip", 1000))

  def getStock = stock.toList.sortBy(_.name)


  def takeProduct(item: String, amount: Int): Unit = {
    stock = stock.map(e => if (e.name == item && (e.quantity - amount) >= 0) Product(item, e.quantity - amount) else e)
  }

  def addProduct(item: String, amount: Int): Unit = {
    stock = stock.map(e => if (e.name == item) Product(item, e.quantity + amount) else e)
  }


}