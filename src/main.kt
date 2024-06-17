var selection = "Unbekannt"
var selectionEnemy = "Unbekannt"
var regretScissors: Double = 0.0
var regretStone: Double = 0.0
var regretPaper: Double = 0.0
var regretScissorsEnemy : Double = 0.0
var regretStoneEnemy:Double = 0.0
var regretPaperEnemy:Double = 0.0
var totalScissors: Double = 0.0
var totalStone: Double = 0.0
var totalPaper: Double = 0.0
var computerChoice = "Unbekannt"
var roundRegretEval: Int = 0
var runtime: Int = 0



fun main() {
    newGame()
    newRound()
}

fun newRound() {
    while(roundRegretEval < runtime) {
        roundRegretEval++
        computerSelection()
        readUserInput()
        evaluateGame()
    }
        println("Spiel wird beendet")
        printResults()
    }


fun calcRegrets() {
    totalScissors= regretScissors+regretScissorsEnemy
    totalStone= regretStone+ regretStoneEnemy
    totalPaper = regretPaper+regretPaperEnemy

    regretScissors= (totalScissors/roundRegretEval)
    regretStone= (totalStone/roundRegretEval)
    regretPaper= (totalPaper/roundRegretEval)
}

fun printResults() {
    calcRegrets()
    println("Schere Regret = $regretScissors")
    println("Stein Regret = $regretStone")
    println("Papier Regret = $regretPaper")
}

fun computerSelection() {
    computerChoice = (1..3).random().toString()
    selectionEnemy = when (computerChoice) {
        "1" -> "Schere"
        "2" -> "Stein"
        "3" -> "Papier"
        else -> "Unbekannt"
    }
}

fun newGame() {
    println("Schere-Stein-Papier-Game")
    print("Geben Sie die Zahl der gewollten Durchläufe ein: ")
    val a = readln().toInt()
    runtime = a
}

fun readUserInput() {
    val userSelect = (1..3).random().toString()
    selection = when (userSelect) {
        "1" -> "Schere"
        "2" -> "Stein"
        "3" -> "Papier"
        else -> "Unbekannt"
    }
}

fun evaluateGame() {
    if (selection == selectionEnemy) {
        readUserInput()
        evaluateGame()
        return
    }

    when (selection) {
        "Schere" -> {
            if (selectionEnemy == "Stein") {
                regretScissors++
            } else if (selectionEnemy == "Papier") {
                regretScissorsEnemy++
            }
        }
        "Papier" -> {
            if (selectionEnemy == "Schere") {
                regretPaper++
            } else if (selectionEnemy == "Stein") {
                regretPaperEnemy++
            }
        }
        "Stein" -> {
            if (selectionEnemy == "Papier") {
                regretStone++
            } else if (selectionEnemy == "Schere") {
                regretStoneEnemy++
            }
        }
    }
}


