package org.kvpbldsck

import scala.annotation.tailrec

private case class TreeItem(
  value: Int, 
  left: Option[TreeItem] = Option.empty, 
  right: Option[TreeItem] = Option.empty)

class TreeSet private(val root: Option[TreeItem] = None) {
  
  def add(item: Int): TreeSet = 
    new TreeSet(add(root, item))
  
  def contains(item: Int): Boolean = 
    contains(root, item)
  
  
  def asArray: Array[Int] =
    traverse(root)
  
  @tailrec
  private def contains(subtree: Option[TreeItem], item: Int): Boolean =
    subtree match
      case Some(TreeItem(value, _, _)) if value == item => true
      case Some(TreeItem(value, left, _)) if value > item => contains(left, item)
      case Some(TreeItem(value, _, right)) if value < item => contains(right, item)
      case _ => false
      
  private def add(subtree: Option[TreeItem], item: Int): Option[TreeItem] =
    subtree match
      case None => Some(TreeItem(item))
      case Some(TreeItem(value, _, _)) if value == item => subtree
      case Some(TreeItem(value, left, right)) if value > item => Some(TreeItem(value, add(left, item), right))
      case Some(TreeItem(value, left, right)) if value < item => Some(TreeItem(value, left, add(right, item)))
      case _ => throw new Error("Cannot add new item in broken tree")

  private def traverse(subtree: Option[TreeItem]): Array[Int] =
    subtree match
      case None => Array.empty
      case Some(item: TreeItem) => traverse(item.left) ++ (item.value +: traverse(item.right))
}

object TreeSet {
  def empty: TreeSet = new TreeSet()
}
