package org.kvpbldsck

def merge[T](first: Vector[T], second: Vector[T])(implicit ord: Ordering[T]) : Vector[T] = {
  (first, second) match
    case (Vector(), _) => second
    case (_, Vector()) => first
    case (xf +: x, yf +: y) =>
      if ord.lt(xf, yf) then
        xf +: merge(x, second)
      else 
        yf +: merge(first, y)
}

def mergeSort[T](vector: Vector[T])(implicit ord: Ordering[T]) : Vector[T] = {
  if (vector.size <= 1) 
    vector
  else
    val (left, right) = vector.splitAt (vector.length / 2)
    merge (mergeSort(left), mergeSort(right))
}

implicit val intOrdering: Ordering[Int] = Ordering.Int

@main def startSort(): Unit =
  val arr = Vector[Int](1, 6, 3, 9, 4, 2, 1, 0)
  println(mergeSort(arr))
