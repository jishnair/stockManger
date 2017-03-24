package models

/**
  * Created by jishnair on 23/03/2017.
  */

// Use H2Profile to connect to an H2 database
import slick.jdbc.H2Profile.api._

import scala.concurrent.ExecutionContext.Implicits.global

object DB {

  // Definition of the SUPPLIERS table
  class Products(tag: Tag) extends Table[(Int, String, Int)](tag, "PRODUCTS") {
    def id = column[Int]("PROD_ID", O.PrimaryKey, O.AutoInc)
    // This is the primary key column
    def name = column[String]("PROD_NAME")
    def qty = column[Int]("QUANTITY")
    // Every table needs a * projection with the same type as the table's type parameter
    def * = (id, name, qty)
  }

  val stock = TableQuery[Products]

  val db = Database.forConfig("h2mem1")

  def getStock:List[Products] =db.run

  def addProduct(name: String, qty: Int)={

    stock += (-99,name, qty)
  }



}
