package codespace.ticktack

trait Rules {

  def isCorrect(ij:(Int,Int),f:Field,l:Label):Boolean

  def isWin(f:Field): Option[Label]

  def isDraw(f:Field): Boolean

  def emptyField: Field

}
