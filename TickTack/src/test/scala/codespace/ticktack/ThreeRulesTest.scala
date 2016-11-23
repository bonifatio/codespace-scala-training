package codespace.ticktack

import codespace.ticktack.strategies.{ConsolePlayer, MachinePlayer}
import org.scalatest._

class ThreeRulesTest extends FunSuite
                       with BeforeAndAfterAll
{

   override def beforeAll() = {
     Console.println("Hi")
   }

   test("set field actually set field") {
     val rules = new ThreeRules
     val ef = rules.emptyField
     val f1 = ef.put(0,0,CrossLabel)
     assert(f1.get(0,0)===Some(CrossLabel))
   }

   test("impossible to get field at 10,10") {
     val rules = new ThreeRules
     val f = rules.emptyField
     assertThrows[IllegalArgumentException] {
       f.get(10,10)
     }
   }

   test("check that win is win") {
     val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
     val data = IndexedSeq(
       IndexedSeq(c,n,t),
       IndexedSeq(n,c,t),
       IndexedSeq(t,n,c)
     )
     val r = new ThreeRules
     val f = new r.ThreeField(data)
     assert(r.isWin(f) == c)
   }

  /*
  test("check that draw is draw") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(t,n,c),
      IndexedSeq(c,c,t),
      IndexedSeq(t,n,c)
    )
    val r = new ThreeRules
    val f = new r.ThreeField(data)
    assert(r.isDraw(f) === true)
  }

  */

  test("column win is win") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(c,n,t),
      IndexedSeq(n,c,t),
      IndexedSeq(t,n,t)
    )
    val r = new ThreeRules
    val f = new r.ThreeField(data)
    assert(r.isWin(f) == t)
  }

  test("row win is win") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(c,n,t),
      IndexedSeq(n,c,t),
      IndexedSeq(t,t,t)
    )
    val r = new ThreeRules
    val f = new r.ThreeField(data)
    assert(r.isWin(f) == t)
  }

  test("first player selects center as a first step") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(n,n,n),
      IndexedSeq(n,n,n),
      IndexedSeq(n,n,n)
    )

    val rules = new ThreeRules
    var field = new rules.ThreeField(data)

    val player = new MachinePlayer(CrossLabel, rules)

    val nextMove = player.nextStep(field)

    assert(nextMove._1 === (1, 1))
  }

  test("should prevent 3 in diag") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(n,n,t),
      IndexedSeq(n,t,n),
      IndexedSeq(n,n,n)
    )

    val rules = new ThreeRules
    var field = new rules.ThreeField(data)

    val player = new MachinePlayer(CrossLabel, rules)

    val nextMove = player.nextStep(field)

    assert(nextMove._1 == (2, 0))
  }

  test("should prevent 3 in row") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(n,n,n),
      IndexedSeq(t,t,n),
      IndexedSeq(n,n,n)
    )

    val rules = new ThreeRules
    var field = new rules.ThreeField(data)

    val player = new MachinePlayer(CrossLabel, rules)

    val nextMove = player.nextStep(field)

    assert(nextMove._1 === (1, 2))
  }

  test("should prevent 3 in column") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(c,n,n),
      IndexedSeq(n,c,n),
      IndexedSeq(t,n,t)
    )

    val rules = new ThreeRules
    var field = new rules.ThreeField(data)

    val player = new MachinePlayer(CrossLabel, rules)

    val nextMove = player.nextStep(field)

    assert(nextMove._1 === (2, 1))
  }

  test("tends to win") {
    val (c,t,n) = (Some(CrossLabel),Some(ToeLabel),None)
    val data = IndexedSeq(
      IndexedSeq(c,n,n),
      IndexedSeq(n,c,c),
      IndexedSeq(t,n,t)
    )

    val rules = new ThreeRules
    var field = new rules.ThreeField(data)

    val player = new MachinePlayer(CrossLabel, rules)

    val nextMove = player.nextStep(field)

    assert(nextMove._1 === (1, 0))
  }


}
