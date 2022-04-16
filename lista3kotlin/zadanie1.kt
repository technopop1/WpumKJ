
fun draw( wrongTries : Int, pass : String, usedChars : String) : Void? {

    val kid : Array<Array<String>> = arrayOf( arrayOf("   ", " o ", " o ", " o ", " o ", " o ", " o "), arrayOf("   ", "   ", " | ", "/| ", "/|\\", "/|\\", "/|\\"), arrayOf("   ", "   ", "   ", "   ", "   ", "/  ", "/ \\") )
    println("\t "+   "+---+")
    println("\t"+kid[0][wrongTries]+"  |")
    println("\t"+kid[1][wrongTries]+"  |")
    println("\t"+kid[2][wrongTries]+"  |")
    println("\t    ===\n\n\n")
    println("HASŁO: " + pass)
    println("ŻYCIA: " + (6-wrongTries))
    println("WYKORZYSTANE LITERY: " + usedChars)
    return null
}

fun game() : Void? {
    val words = (java.io.File("slowa.txt").useLines { it.toList() }).filter{ !it.any { "ąćęłńóśźż".contains(it) } }
    val wordTo : String = words[(0..(words.size-1)).random()]
    var word : String = wordTo
    var alive : Boolean = true  ; 
    var alreadyUsedChar : Boolean = true
    var wrongTries : Int = 0
    var pass = Array(word.length) {"_"}
    var usedChars : String = ""
    var inputChar : String = ""

    draw(0, pass.joinToString(" "), usedChars)
    while ( alive ) {
        print("PODAJ LITERĘ: ")
        while (alreadyUsedChar){
            inputChar = readLine()!!.lowercase()
            if (!usedChars.contains(inputChar)) alreadyUsedChar = false
            else print(" Wykorzystałeś już tę literę!!! Spróbuj z inną: ")
        } ; alreadyUsedChar = true
        usedChars += (" " + inputChar)
        if (word.contains(inputChar))
            while (word.contains(inputChar)) {
                pass[word.indexOf(inputChar)] = word[word.indexOf(inputChar)] + ""
                word = word.substring(0, word.indexOf(inputChar)) + "-" + word.substring(word.indexOf(inputChar) + 1)
                draw(wrongTries, pass.joinToString(" "), usedChars)
            }
        else {
            wrongTries += 1 
            draw(wrongTries, pass.joinToString(" "), usedChars)
            if (wrongTries == 6) { println("Niestety przegrałęś... \nODPOWIEDŹ: " + wordTo) ; alive = false ;}
        }
        if (!pass.contains("_")) {println("Gratulacje wygrałeś!!!") ; alive = false}
    } 
    
    return null
}

fun main() { 
    game()
}