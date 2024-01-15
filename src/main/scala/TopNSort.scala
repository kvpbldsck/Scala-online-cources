package org.kvpbldsck

def insert[T](accumulator: Vector[T], item: T, popLast: Boolean = false)(implicit ordering: Ordering[T]) = {
  val insertAtIndex = accumulator.indexWhere { accItem => ordering.gt(accItem, item) }
  if insertAtIndex == -1 & !popLast then accumulator.appended(item)
  else if insertAtIndex == -1 then accumulator
  else accumulator
    .slice(0, insertAtIndex)
    .appended(item)
    .appendedAll(accumulator.slice(insertAtIndex, if popLast then accumulator.length - 1 else accumulator.length))  
}

def topNSort[T](vector: Vector[T], n: Int)(implicit ordering: Ordering[T]) = {
  vector.foldLeft(Vector[T]()) { (acc, item) =>
    if acc.length < n then insert(acc, item)
    else if ordering.lt(item, acc.last) then insert(acc, item, true)
    else acc  
  }
}

implicit val intOrdering: Ordering[Int] = Ordering.Int

@main def testTopNSort(): Unit =
  val arr = Vector[Int](1, 6, 3, 9, 4, 2, 1, 0)
  println(topNSort(arr, 1))
  println(topNSort(arr, 2))
  println(topNSort(arr, 3))
  println(topNSort(arr, 4))
  println(topNSort(arr, 5))
  println(topNSort(arr, 6))
  println(topNSort(arr, 7))
  println(topNSort(arr, 8))
 
