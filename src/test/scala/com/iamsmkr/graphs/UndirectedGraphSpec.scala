package com.iamsmkr.graphs

import org.scalatest._
import flatspec._
import matchers._

class UndirectedGraphSpec extends AnyFlatSpec with should.Matchers {

  "isCyclic method" should "detect the presence of a cycle in a given undirected cyclic graph" in {
    val edges = List("1~2", "1~3", "1~4", "2~5", "2~6", "4~7", "4~8", "5~9", "6~10", "7~11", "8~12", "11~12")
    UndirectedGraph(edges).isCyclic should be
    true
  }

  it should "detect the absence of a cycle in a given undirected cyclic graph" in {
    val edges = List("1~2", "1~3", "1~4", "2~5", "2~6", "4~7", "4~8", "5~9", "6~10", "7~11", "8~12")
    UndirectedGraph(edges).isCyclic should be
    false
  }
}
