package codespace.ticktack

import codespace.ticktack.strategies.{ConsolePlayer, MachinePlayer}

import scala.io.StdIn.readLine

object GameSession {

  def main(args: Array[String]): Unit = {

    def getPlayers(rules:Rules):(Player, Player) = {

      print("Want to make a first step? (Y\\N)")
      val humanStarts:Boolean = (readLine().toLowerCase == "y")

      if(humanStarts)
        (new ConsolePlayer(CrossLabel, rules), new MachinePlayer(ToeLabel, rules))
      else
        (new MachinePlayer(CrossLabel, rules), new ConsolePlayer(ToeLabel, rules))
    }

    val rules = new ThreeRules
    val field = rules.emptyField

    val players = getPlayers(rules)
    val game = ManMachineGame(rules)

    val (f, isWin, lastLabel) = game.play(players._1, players._2)

    if(isWin)
      println(s"Game over - WIN of ${lastLabel}")
    else
      println("Game over - DRAW")
  }

}
