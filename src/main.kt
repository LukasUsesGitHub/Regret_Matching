var selection = "Unbekannt"
var selectionEnemy= "Unbekannt"
var resultList = mutableListOf<String>()       //Verfahren um eine Liste anzulegen (listOf = Eine fertige Liste ; mutableListOf = Eine veränderbare Liste anlegen , und hier muss ein Datentyp rein)
var scissorsBestChoice:Double=0.0                //Zahl der Runden die gewonnen wurden
var stoneBestChoice:Double=0.0
var paperBestChoice:Double=0.0

var scissorBadChoice:Double=0.0                  //Zahl der verlorenden Runden
var stoneBadChoice:Double=0.0
var paperBadChoice:Double=0.0

var countRounds:Double=0.0


fun main(){
    newGame()
    newRound()
}

fun newRound(){
    computerSelection()
    if (scissorsBestChoice>0 || stoneBestChoice>0 || paperBestChoice>0){
        val bestChoiceFeedback = calcBestChoice()
        println(bestChoiceFeedback)
    }
    readUserInput()
    if(selection!= "Exit") {
        countRounds++
        evaluateGame()
    }else{
        println("Spiel wird beendet")
        printResults()
    }
}

fun printResults(){
    println("=========Ergebnisse========")
    resultList.forEachIndexed(){ index ,value ->                            //Eine Schleife, die für jeden Ergebnis der Liste ausgeführt wird (forEach) //forEachIndexed = Möglichkeit für zwei Werte
        var nexIndex= index+1
        println("($nexIndex) $value")
    }
    println("+Schere: $scissorsBestChoice \n+Stein: $stoneBestChoice \n+Paper: $paperBestChoice\n") //Gewinne
    println("-Schere: $scissorBadChoice \n-Stein: $stoneBadChoice \n-Paper: $paperBadChoice\n")      //Veloren
}

fun computerSelection(){
    val computerChoice = (1..3).random()            //Möglichkeit, eine willkürliche Zahl in Kotlin zu bekommen (in diesem Fall ist dies vom Intervall zwische 1 und 3)

    selectionEnemy = when(computerChoice){
        1 -> "Schere"                                       //Zahlen bei When nicht in ""
        2 -> "Stein"
        3 -> "Papier"
        else -> "Unbekannt"
    }
}

fun newGame(){
    println("Schere-Stein-Papier-Game")
}

fun readUserInput(){
    println("======================Deine Auswahl====================")
    println("(1)Schere")
    println("(2)Stein")
    println("(3)Papier")
    println("(9)Spiel beenden")
    var userSelect= readln()                            //readln().toInt() eine Variable zu einen bestimmten datentyp einlesen
    selection = when(userSelect){                       //"Case" Ähnlicher Vorgang in Kotlin
        "1" -> "Schere"
        "2" -> "Stein"
        "3" -> "Papier"
        "9" -> "Exit"
        else -> "Unbekannt"
    }
    if(selection== "Unbekannt"){
        println("Falsche Eingabe !!!\n")
        readUserInput()
    }
}

fun evaluateGame(){
    println("Deine Auswahl: $selection")
    println("Auswahl des Gegners: $selectionEnemy")
    if(selection == selectionEnemy){
        println("Unentschieden")
        resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Unentschieden!!!")
    }else{
        if (selection == "Schere"){                     //Aufbau von If und Else Bedingungen bleiben auch gleich wie in Java
            if (selectionEnemy == "Papier"){
                println("Gewonnen!!!")
                scissorsBestChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren!!")
                scissorBadChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }

        if (selection == "Papier"){
            if (selectionEnemy == "Schere"){
                println("Verloren!!!")
                paperBadChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }else{
                println("Gewonnen")
                paperBestChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }
        }

        if (selection == "Stein"){
            if (selectionEnemy == "Schere"){
                println("Gewonnen!!!")
                stoneBestChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren")
                stoneBadChoice++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }
    }
    newRound()
}

fun calcBestChoice(): String {
    val scissorsValue:Double = scissorsBestChoice / countRounds
    val stoneValue:Double = stoneBestChoice / countRounds
    val paperValue:Double = paperBestChoice / countRounds
    return when {
        scissorsValue > stoneValue && scissorsValue > paperValue -> "Du solltest Schere nehmen!!"
        stoneValue > scissorsValue && stoneValue > paperValue -> "Du solltest Stein nehmen!!"
        paperValue > stoneValue && paperValue > scissorsValue -> "Du solltest Papier nehmen!!"
        else -> "Keine klare Empfehlung."
    }
}

