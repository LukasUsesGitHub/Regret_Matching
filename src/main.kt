/*Dies ist die Version wo der Computer exploitable ist
* ->Der Computer nimmt mehrmals das selbe*/

var selection = "Unbekannt"
var selectionEnemy= "Unbekannt"
var regretList = mutableListOf<String>()        //Dies wird verwendet um die Regret Vorschläge zu speichern
var resultList = mutableListOf<String>()       //Verfahren um eine Liste anzulegen (listOf = Eine fertige Liste ; mutableListOf = Eine veränderbare Liste anlegen , und hier muss ein Datentyp rein)
var regretScissors:Int=0
var regretStone:Int=0
var regretPaper:Int=0
var computerChoice = (1..3).random()
var round:Int=0
var strategyChangeAfterRound:Int =(1..8).random()

fun main(){
    newGame()
    newRound()
}

fun newRound(){
    computerSelection()
    if(round>=1) {
        val bestChoiceFeedback = calcBestChoice() //Aufrufen der Funktion, die Empfehlungen ausgibt
        val badChoiceFeedback = calcBadChoice()     //Aufrufen der Funktion, die vor schlechten Entscheidungen warnt
        println(bestChoiceFeedback)
        println(badChoiceFeedback)
        regretList.add("Empfehlung: $bestChoiceFeedback \nEmpfehlung zum Vermeiden= $badChoiceFeedback")
        readUserInput()
    }
    if(selection!= "Exit") {
        evaluateGame()
    }else{
        println("Spiel wird beendet")
        printResults()
    }
}

fun printRegretValues(){
    println("Schere Regret = $regretScissors \n Stein Regret = $regretStone \n Papier Regret = $regretPaper")
    newRound()
}

fun printResults(){
    println("=========Ergebnisse========")
    resultList.forEachIndexed(){ index ,value ->                            //Eine Schleife, die für jeden Ergebnis der Liste ausgeführt wird (forEach) //forEachIndexed = Möglichkeit für zwei Werte
        val nexIndex= index+1
        println("($nexIndex) $value")
    }
    regretList.forEachIndexed(){ index ,value ->
        val nexIndex= index+1
        println("($nexIndex) $value")
    }
    println("Schere Regret = $regretScissors \n Stein Regret = $regretStone \n Papier Regret = $regretPaper")
}

fun computerSelection(){
    if(round%strategyChangeAfterRound==0){
        computerChoice=(1..3).random()
        strategyChangeAfterRound =(1..8).random()
        round=0
    }
    selectionEnemy = when(computerChoice){
        1 -> "Schere"
        2 -> "Stein"
        3 -> "Papier"
        else -> "Unbekannt"
    }
    round++
}

fun newGame(){
    println("Schere-Stein-Papier-Game")
}

fun readUserInput(){
    println("======================Deine Auswahl====================")
    println("(1)Schere")
    println("(2)Stein")
    println("(3)Papier")
    println("(8)Regret Werte anzeigen lassen")
    println("(9)Spiel beenden")
    var userSelect= readln()                            //readln().toInt() eine Variable zu einen bestimmten datentyp einlesen
    selection = when(userSelect){                       //"Case" Ähnlicher Vorgang in Kotlin
        "1" -> "Schere"
        "2" -> "Stein"
        "3" -> "Papier"
        "8" -> "Regrets"
        "9" -> "Exit"
        else -> "Unbekannt"
    }
    if(selection== "Unbekannt"){
        println("Falsche Eingabe !!!\n")
        readUserInput()
    }
}

fun evaluateGame(){
    if(selection=="Regrets"){
        printRegretValues()
        return
    }
    println("Deine Auswahl: $selection")
    println("Auswahl des Gegners: $selectionEnemy")
    if(selection == selectionEnemy){
        println("Unentschieden")
        resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Unentschieden!!!")
    }else{
        if (selection == "Schere"){                     //Aufbau von If und Else Bedingungen bleiben auch gleich wie in Java
            if (selectionEnemy == "Papier"){
                println("Gewonnen!!!")
                regretScissors--
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren!!")
                regretScissors++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }

        if (selection == "Papier"){
            if (selectionEnemy == "Schere"){
                println("Verloren!!!")
                regretPaper++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }else{
                println("Gewonnen")
                regretPaper--
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }
        }

        if (selection == "Stein"){
            if (selectionEnemy == "Schere"){
                println("Gewonnen!!!")
                regretStone--
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Gewonnen!!!")
            }else{
                println("Verloren")
                regretStone++
                resultList.add("[Du] $selection [Gegner] $selectionEnemy -> Verloren!!!")
            }
        }
    }
    newRound()
}

fun calcBestChoice(): String {
    return when {
        regretScissors < regretStone && regretScissors < regretPaper -> "Du solltest Schere nehmen!!"
        regretStone < regretScissors && regretStone < regretPaper -> "Du solltest Stein nehmen!!"
        regretPaper < regretStone && regretPaper < regretScissors -> "Du solltest Papier nehmen!!"
        else -> "Keine Wahl ist wirklich gut"
    }
}

fun calcBadChoice():String{
    return when {
        regretScissors > regretStone && regretScissors > regretPaper -> "Du solltest nicht die Schere nehmen!!"
        regretStone > regretScissors && regretStone > regretPaper -> "Du solltest nicht den Stein nehmen!!"
        regretPaper > regretStone && regretPaper > regretScissors -> "Du solltest nicht das Papier nehmen!!"
        else -> "Keine Wahl ist wirklich Schlecht."
    }
}

