var selection = "Unbekannt"
var selectionEnemy= "Unbekannt"
var resultList = mutableListOf<String>()       //Verfahren um eine Liste anzulegen (listOf = Eine fertige Liste ; mutableListOf = Eine veränderbare Liste anlegen , und hier muss ein Datentyp rein)

fun main(){
    newGame()
    newRound()
}

fun newRound(){
    computerSelection()
    readUserInput()
    if(selection!= "Exit") {
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
    println("Herzlich wilkommen zu diesem Spiel.")
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
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren!!")
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }

        if (selection == "Papier"){
            if (selectionEnemy == "Schere"){
                println("Verloren!!!")
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }else{
                println("Gewonnen")
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }
        }

        if (selection == "Stein"){
            if (selectionEnemy == "Schere"){
                println("Gewonnen!!!")
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren")
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }
    }
    newRound()
}