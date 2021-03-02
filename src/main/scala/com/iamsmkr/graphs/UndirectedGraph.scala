package com.iamsmkr.graphs

import com.iamsmkr.graphs.UndirectedGraph.AdjacencyList

import scala.annotation.tailrec

private final case class Vertex(id: String) extends AnyVal

class UndirectedGraph private(adj: AdjacencyList) {
  def isCyclic: Boolean = {
    def traverse(visited: List[Vertex], next: Vertex): Boolean = {
      if (visited.contains(next)) visited.tail.head != next
      else adj(next).exists(n => traverse(next :: visited, n))
    }

    val first = adj.head._1
    adj(first).exists(n => traverse(List(first), n))
  }
}

object UndirectedGraph {
  private type AdjacencyList = Map[Vertex, List[Vertex]]

  def apply(edges: List[String]): UndirectedGraph = {
    @tailrec
    def buildAdjacencyList(edges: List[String], adj: AdjacencyList = Map()): AdjacencyList = {
      def build(x: String): AdjacencyList = {
        def addToAdjacencyList(edge: List[Vertex], x: AdjacencyList): AdjacencyList = {
          val v1 = edge.head
          val v2 = edge.last

          if (x.isDefinedAt(v1)) x ++ Map(v1 -> (v2 :: x(v1)))
          else x ++ Map(v1 -> List(v2))
        }

        val edge = x.split("~").map(_.trim).map(Vertex).toList

        addToAdjacencyList(edge.reverse, addToAdjacencyList(edge, adj))
      }

      edges match {
        case Nil => adj
        case x :: Nil => buildAdjacencyList(Nil, build(x))
        case x :: y => buildAdjacencyList(y, build(x))
      }
    }

    new UndirectedGraph(buildAdjacencyList(edges))
  }

  def main(args: Array[String]): Unit = {
    val edges = args(0).split(",").toList

    val res = UndirectedGraph(edges).isCyclic
    println(res)
  }
}
