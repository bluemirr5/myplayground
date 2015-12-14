package kr.rang2ne.playground.scala

import javax.persistence.{Entity, GeneratedValue, Id}

import lombok.{AllArgsConstructor, NoArgsConstructor}

import scala.beans.BeanProperty

/**
  * Created by gswon on 15. 12. 15.
  */
@Entity
@BeanProperty
@AllArgsConstructor
class ScalaEntity {
  @Id @GeneratedValue var id:Long = _
  var name:String = _
  def this(id: Long, name: String) = this();this.id = id; this.name = name
}
