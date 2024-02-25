package org.kvpbldsck

import org.scalatest._
import flatspec._
import matchers._

class TreeSetTest extends AnyFlatSpec with should.Matchers {
    "A tree set" should "find values that added previously" in {
        val set = TreeSet.empty
        
        val filledSet = set
          .add(1)
          .add(2)
        
        filledSet.contains(1) should be (true)
        filledSet.contains(2) should be (true)
    }
    
    "A tree set" should "not find values that didn't add previously" in {
        val set = TreeSet.empty

        val filledSet = set
          .add(1)
          .add(2)

        filledSet.contains(3) should be (false)
        filledSet.contains(4) should be (false)
    }
    
    "A tree set" should "not contains duplicates" in {
        val set = TreeSet.empty

        val filledSet = set
          .add(1)
          .add(1)
          .add(2)
          .add(2)

        val result = filledSet.asArray
        
        result should contain theSameElementsInOrderAs Array(1, 2)
    }
}
