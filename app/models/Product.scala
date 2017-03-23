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

  //returns stock as a list
  def getStock = stock.toList.sortBy(_.name)


  //reduce stock by specified amount matching name
  def takeStock(item: String, amount: Int): Unit = {
    stock = stock.map(e => if (e.name == item && (e.quantity - amount) >= 0) Product(item, e.quantity - amount) else e)
  }

  //increase stock by specified amount matching name
  def addStock(item: String, amount: Int): Unit = {
    stock = stock.map(e => if (e.name == item) Product(item, e.quantity + amount) else e)
  }

  //return a Product matching name
  def getProductByName(name: String)={
    stock.filter(_.name ==name)
  }

  //Add new product in to stock
  def addNewProduct(item: String, amount: Int ): Unit ={
    stock += Product(item, amount)
  }

  //check if a Product matching name exist
  def isProductExist(item: String):Boolean={
    stock.exists(p => p.name==item)
  }

  //delete a product matching name
  def deleteProduct(item: String)={
    stock=stock.filterNot(p => p.name ==item)
  }


}