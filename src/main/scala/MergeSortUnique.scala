package org.kvpbldsck

def mergeUnique[T](first: Vector[T], second: Vector[T])(implicit ord: Ordering[T]) : Vector[T] = {
  (first, second) match
    case (Vector(), _) => second
    case (_, Vector()) => first
    case (xf +: x, yf +: y) =>
      if ord.lt(xf, yf) then
        val rest = mergeUnique(x, second)
        if ord.equiv(rest.head, xf) then rest
        else xf +: rest
      else
        val rest = mergeUnique(first, y)
        if ord.equiv(rest.head, yf) then rest
        else yf +: rest
}

def mergeSortUnique[T](vector: Vector[T])(implicit ord: Ordering[T]) : Vector[T] = {
  if (vector.size <= 1) 
    vector
  else
    val (left, right) = vector.splitAt (vector.length / 2)
    mergeUnique(mergeSortUnique(left), mergeSortUnique(right))
}

@main def testMergeSortUnique(): Unit =
  val arr = Vector[Int](1, 6, 3, 9, 4, 2, 1, 0)
  println(mergeSortUnique(arr))
