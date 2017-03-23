import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends PlaySpec with OneAppPerTest {

  "Routes" should {
    "send 404 on a bad request" in  {
      route(app, FakeRequest(GET, "/boum")).map(status(_)) mustBe Some(NOT_FOUND)
    }

  }

  "Application" should {

    "render the index page" in {
      val home = route(app, FakeRequest(GET, "/")).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include ("StockManager")
    }

  }

  "Application" should {

    "api tests" in {
      contentAsString(route(app, FakeRequest(GET, "/stock")).get) must include("Products")
      contentAsString(route(app, FakeRequest(POST, "/addnew?name=orange&quantity=8")).get) must include("{\"name\":\"orange\",\"quantity\":8}")
      contentAsString(route(app, FakeRequest(POST, "/delete?name=orange")).get) must not include ("orange")
      contentAsString(route(app, FakeRequest(POST, "/addstock?name=pencil&quantity=3")).get) must include("{\"name\":\"pencil\",\"quantity\":103}]}")
      contentAsString(route(app, FakeRequest(POST, "/takestock?name=pencil&quantity=3")).get) must include("{\"name\":\"pencil\",\"quantity\":100}]}")
    }

  }

}
