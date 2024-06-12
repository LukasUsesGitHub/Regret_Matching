import kotlin.random.Random
import kotlin.math.max

enum class Action {
    ROCK,
    PAPER,
    SCISSORS
}

//Gibt 1, 0 oder -1 je nach Win, Draw oder Loss wieder
fun getPayoff(action1: Action, action2: Action): Int {
    val mod3Val = (action1.ordinal - action2.ordinal + 3) % 3
    return if (mod3Val == 1) 1 else if (mod3Val == 2) -1 else 0
}


//Berechnet Strategie basierend auf kumulierten Regrets
fun getStrategy(cumulativeRegrets: IntArray): DoubleArray {
    val posCumulativeRegrets = cumulativeRegrets.map { max(0, it) }.toIntArray()
    val sumOfPosRegrets = posCumulativeRegrets.sum()

    return if (sumOfPosRegrets > 0) {
        posCumulativeRegrets.map { it.toDouble() / sumOfPosRegrets }.toDoubleArray()
    } else {
        DoubleArray(Action.entries.size) { 1.0 / Action.entries.size }
    }
}

//Gibt Regretwerte basierend auf Payoff für alle drei Actions wieder
fun getRegrets(payoff: Int, action2: Action): List<Int> {
    return Action.entries.map { getPayoff(it, action2) - payoff }
}


fun selectAction(probabilities: DoubleArray): Action {
    val rand = Random.nextDouble()
    var cumulativeProbability = 0.0

    for (i in probabilities.indices) {
        cumulativeProbability += probabilities[i]
        if (rand <= cumulativeProbability) {
            return Action.entries[i]
        }
    }
    return Action.entries.last() //falls es zu einem Error kommt, soll er die letzte Action nehmen
}

fun main() {
    val numIterations = 10000
    val actionCount = Action.entries.size

    var cumulativeRegrets = IntArray(actionCount) { 0 }
    var strategySum = DoubleArray(actionCount) { 0.0 }

    //Gegner Strategie kann hier geändert werden, die Actions sind → (Stein, Papier, Schere)
    val opponentStrategy = doubleArrayOf(0.1, 0.6, 0.3)

    for (i in 0..<numIterations) {
        val strategy = getStrategy(cumulativeRegrets)
        strategySum = strategySum.zip(strategy).map { it.first + it.second }.toDoubleArray()

        val ourAction = selectAction(strategy)
        //println("Unsere: $ourAction")

        //Anstelle von strategy, opponentStrategy eingeben für Exploitability
        val oppAction = selectAction(strategy)
        //println("Gegner: $oppAction")

        val ourPayoff = getPayoff(ourAction, oppAction)

        val regrets = getRegrets(ourPayoff, oppAction).toIntArray()

        cumulativeRegrets = cumulativeRegrets.zip(regrets).map { it.first + it.second }.toIntArray()


    }

    for (i in strategySum.indices) {
        println("Strategy for ${Action.entries[i]}: %.6f".format(strategySum[i] / numIterations))
    }
}

