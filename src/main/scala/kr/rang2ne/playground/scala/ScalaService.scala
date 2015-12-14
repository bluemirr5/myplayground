package kr.rang2ne.playground.scala

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by gswon on 15. 12. 15.
  */
@Service
class ScalaService {
  @Autowired
  var scalaRepository: ScalaRepository = _

  def save(entity: ScalaEntity) = {
    scalaRepository.save(entity)
  }

  def select(id: Long) = {
    scalaRepository.findOne(id)
  }
}
