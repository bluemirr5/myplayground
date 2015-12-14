package kr.rang2ne.playground.scala

import org.springframework.beans.factory.annotation.Autowired

import collection.JavaConversions._
import org.springframework.http.{HttpStatus, ResponseEntity}
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import scala.collection.immutable.Map

@Controller
class ScalaConroller {
  @Autowired
  var scalaService: ScalaService = _

  @RequestMapping(
    value=Array("/scalaTest")
  )
  def index() = new ResponseEntity(mapAsJavaMap(Map("a" -> "ab", "b" -> "ba")), HttpStatus.OK)

  @RequestMapping(
    value=Array("/scalaSave")
  )
  def save = {
    scalaService.save(new ScalaEntity(0, "hi"))
    new ResponseEntity(HttpStatus.OK)
  }

  @RequestMapping(
    value=Array("/scalaGet")
  )
  def select = {
    scalaService.select(2)
  }
}