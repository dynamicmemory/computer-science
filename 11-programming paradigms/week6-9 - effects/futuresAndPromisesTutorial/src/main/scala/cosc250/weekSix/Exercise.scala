//> using scala "2.13.13"
//> using dep "com.typesafe.akka::akka-actor:2.8.8"
//> using dep "com.typesafe.akka::akka-stream:2.8.8"
//> using dep "com.typesafe.play::play-json:2.10.6"
//> using dep "com.typesafe.play::play-ws-standalone:2.1.3"
//> using dep "com.typesafe.play::play-ahc-ws-standalone:2.1.3"

package cosc250.weekSix

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.json
import play.api.libs.json.{JsValue, Json}
import play.api.libs.ws.ahc.StandaloneAhcWSClient

import scala.concurrent.{Promise, Future}
import scala.concurrent.ExecutionContext.Implicits.global

object Exercise {



  /*
   * First, let's just do some basic things with Promise and Future
   */


  /**
    * Just complete this promise with a number. Have a look at what the test is doing
    */
  def completeMyPromise(p:Promise[Int]):Unit = ???

  /**
    * I'm going to give you a Future[Int]. Double it and return it.
    * Have a look at what the test is doing
    */
  def doubleMyFuture(p:Future[Int]):Future[Int] = ???


  /**
    * Let's chain a few things together.
    * I'm going to give you two Future[String]s. You're going to convert them both to uppercase, and count how many
    * letters are identical in each
    *
    * Hint: use for { a <- fut } notation
    *
    * Don't use isComplete.
    */
  def compareMyFutureStrings(fs1:Future[String], fs2:Future[String]):Future[Int] = ???

  /**
    * Here's an example of parsing a JSON string
    */
  def nameFromJason() = {
    val json: JsValue = Json.parse("""
      {
        "name" : "Watership Down",
        "location" : {
          "lat" : 51.235685,
          "long" : -1.309197
        },
        "residents" : [ {
          "name" : "Fiver",
          "age" : 4,
          "role" : null
        }, {
          "name" : "Bigwig",
          "age" : 6,
          "role" : "Owsla"
        } ]
      }
     """)

    val name = (json \ "name").as[String]

    name
  }


  /*
   * This stuff sets up our web client
   */
  implicit val system:ActorSystem = ActorSystem("Sys")
  val wsClient = StandaloneAhcWSClient()


  /**
    * Here's an example of using the Web Client.
    */
  def webExample() = {
    wsClient
      .url("http://turing.une.edu.au/~cosc250/lectures/cosc250/test.txt")
      .get()
      .map(_.body)
  }


  /**
    * Your first challenge...
    *
    * Get the file http://turing.une.edu.au/~cosc250/lectures/cosc250/second.json and extract the name from the JSON
    */
  def secondName():Future[String] = ???

  /**
    * Your second challenge...
    *
    * Get the file from url1
    * Get the file from url2
    * Parse them each as JSON
    * and case insensitively see how many characters are in common in the two names...
    */
  def nameCharactersInCommon(url1:String, url2:String):Future[Int] = ???


}
