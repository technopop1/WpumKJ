

import kotlin.text.lowercase
import kotlin.collections.mutableListOf
import kotlin.comparisons.maxOf 
class Board {
    public var board : MutableList<String> = mutableListOf( "-", "-", "-", "-", "-", "-", "-", "-", "-" )

    public fun draw() {
       for ( i in 1..(this.board.size)) {
            print(this.board[i-1] + " ")  
            if ( i%3 == 0 ) println()
       }
    }
    public fun set(idx : Int, playerState : String) {
        this.board[idx] = playerState
    }
    public fun check(idx : Int) : Boolean{ 
        if (board[idx] == "-") return false;
        else return true;
    }
}
open class Player(pS : String, myClass : String) {
    public var board : Board = Board()
    public val playerState : String = pS
    private val playerClass : String = myClass
    public fun getState() : String { return playerState }
    public fun win(checkBoard : MutableList<String>, ps : String) : Boolean{
        if ( checkBoard[0] == ps && checkBoard[4] == ps && checkBoard[8] == ps ) return true;
        if ( checkBoard[2] == ps && checkBoard[4] == ps && checkBoard[6] == ps ) return true;
  
        for( i in 0..(2) ) {
            if((checkBoard[i] == ps) && (checkBoard[i+3] == ps) && (checkBoard[i+6] == ps)) return true; // kolumny 
                
            if((checkBoard[i + i*2] == ps) && (checkBoard[i + i*2 + 1] == ps) && (checkBoard[i + i*2 + 2] == ps)) return true; // wiersze
        }
        for (i in 0..8)
            if ( checkBoard[i] == "-") return false;
        
        return true; 
    }
    open fun move() { }
}
public class HumanPlayer(pS : String, myClass : String) : Player(pS, myClass) {
    override fun move() {
        var playerChoice = readLine()!!.lowercase()
        while( playerChoice.length != 1 || playerChoice.toInt() < 0 || playerChoice.toInt() > 8 || board.check(playerChoice.toInt()) ){
            playerChoice = readLine()!!.lowercase()
        }
        board.set(playerChoice.toInt(), playerState)
    }
    
}
public class RandomPlayer(pS : String, myClass : String) : Player(pS, myClass) {
    override fun move() {
        var tmpBoard : MutableList<Int> = mutableListOf()
        for ( i in 0..(board.board.size-1)){
            if ( board.board[i] == "-" ) tmpBoard.add(i)
        } 
        val choise : Int = tmpBoard.random()
        board.set(choise, playerState)
    }
}
public class AiPlayer(pS : String, myClass : String) : Player(pS, myClass) {
    private var whoseMove : String = playerState
    // private var tmpBoard : MutableList<Int> = mutableListOf()

    override fun move() {
        var tmpb : MutableList<Int> = mutableListOf()
        for ( i in 0..(board.board.size-1)){
            if ( board.board[i] == "-" ) tmpb.add(i)
        }
        
        val v : Int = minimax(board.board, 15, true, whoseMove)
        
        // if (v >= 0 && v <= 8) {
        var boards = gen(board.board, playerState)
        board.board = boards[v]
        // }
        //board.set(vm, playerState)
        whoseMove = playerState
    }

    public fun minimax(node : MutableList<String>, depth : Int, maxPlayer : Boolean, PlayerMove : String) : Int
    {
        var foundNode : Int = 0
        // var score : MutableList<Int> = mutableListOf()
        var score : Int
        if (maxPlayer) score = Int.MIN_VALUE 
        else score = Int.MAX_VALUE

        var boards : MutableList<MutableList<String>> = gen(node, whoseMove)

        if (boards.size == 0 ) {
            if (depth == 0) return 0

            var tmpOponentState : String
            if (playerState == "x") tmpOponentState = "o"
            else tmpOponentState = "x" 

            if (win(node, tmpOponentState)) return -1
            else if (win(node, playerState)) return 1
            else return 0 
        }


        for (i in 0..(boards.size-1)) {
            if (PlayerMove == "x") whoseMove = "o"
            else if (PlayerMove == "o")  whoseMove = "x"
            var childValue = minimax(boards[i], depth-1, !maxPlayer, whoseMove)
            // if (score.isEmpty()) score.add(childValue)
            if (maxPlayer) { // jezeli jestesmy na galezi sprawdzajacej max
                if (childValue > score) { //score.toList().maxOf{ it }
                    score = childValue
                    // score.add(childValue)
                    foundNode = i
                }
            }
            else { // jesli galeź sprawdzajaca min
                if (childValue < score){
                    score = childValue
                    foundNode = i
                }
            }
        }

        if (depth > 0) return score //score.last()
        else return foundNode

        // val test : Boolean = win( getState() )
        // if (test) return 10

        // if ( node >= Int.MAX_VALUE || depth == 0 )
        //     return node

        // var tmpBoard : MutableList<Int> = mutableListOf()
        // for ( i in 0..(nodeIN.size-1) ){
        //     if ( nodeIN[i] == "-" ) tmpBoard.add(i)
        // }

        // if ( maxPlayer ) {
        //     var maxEva: Int = Int.MIN_VALUE;           
        //     // for (i in tmpBoard..0)
        //     var eva : Int = minimax(child, depth-1, false)  
        //     var maxEva : Int = max(maxEva,eva)
        //     return maxEva  
        // }
        // else {
        //     minEva= +infinity   
        //     for each child of node do  
        //         eva= minimax(child, depth-1, true)  
        //         minEva= min(minEva, eva)
        //     return minEva
        // }


        // }
        // val test : Boolean = win( getState() )
        // if (test) return node

        // // var max: Int = Int.MIN_VALUE;
        // if ( node >= Int.MAX_VALUE || depth == 0 )
        //     return node

        // // var tmpBoard : MutableList<Int> = mutableListOf()
        // for ( i in 0..(board.board.size-1) ){
        //     if ( board.board[i] == "-" ) tmpBoard.add(i)
        // }

        // if ( maximizingPlayer ) {
        //     var maxEva: Int = Int.MIN_VALUE;           
        //     // for (i in tmpBoard..0)
        //     var eva : Int = minimax(child, depth-1, false)  
        //     var maxEva : Int = max(maxEva,eva)
        //     return maxEva  
        // }
        // else {
        //     minEva= +infinity   
        //     for each child of node do  
        //         eva= minimax(child, depth-1, true)  
        //         minEva= min(minEva, eva)
        //     return minEva
        // }
        
     //return alfabeta(node, głębokość, -∞, +∞)
        // return 1
    }
    private fun gen(inBoard : MutableList<String>, state : String) : MutableList<MutableList<String>> {
        var outBoards : MutableList<MutableList<String>> = mutableListOf()
        var tmpPos : MutableList<Int> = mutableListOf()
        for ( i in 0..(inBoard.size-1) ){
            if ( inBoard[i] == "-" ) tmpPos.add(i)
        }
        tmpPos.forEach {
            var tmpBoard : MutableList<String> = mutableListOf()
            tmpBoard.addAll(inBoard)
            tmpBoard[it] = state // x or o
            outBoards.add(tmpBoard)
        }
        return outBoards
    }
    // private fun alfabeta(node : Int = 1, depth : Int) : Int{
    //     if (node == Int.MAX_VALUE || depth == 0)
    //         return node
    //     jeżeli przeciwnik ma zagrać w węźle
    //         dla każdego potomka węzła
    //             b = min(b, alfabeta(node, depth-1, a, b))
    //             jeżeli a≥b
    //                 przerwij przeszukiwanie  {odcinamy gałąź Alfa}
    //         zwróć b
    //     w przeciwnym przypadku {my mamy zagrać w węźle}
    //         dla każdego potomka węzła
    //             a = max(a, alfabeta(node, depth-1, a, b))
    //             jeżeli a≥b
    //                 przerwij przeszukiwanie  {odcinamy gałąź Beta}
    //         return a
    // }
}
public class Game {
    private var board : Board = Board()
    private var players : MutableList<Player> = mutableListOf()
    constructor() {
        for(i in 0..1){

            println("Wpisz typ gracza: H -Human, R -Random, A -AI Player")
            var playerType = readLine()!!.lowercase()
            while(playerType != "h" && playerType != "r" && playerType != "a") {
                playerType = readLine()!!.lowercase()
            }

            var playerInput : String
            if (i == 0) {
                println("Wpisz litere gracza X lub O")
                playerInput = readLine()!!.lowercase()
                while(playerInput != "x" && playerInput != "o") {
                    playerInput = readLine()!!.lowercase()
                }
            }
            else if (players.last().playerState == "x") playerInput = "o"
            else playerInput = "x"
            
            when{
                playerType == "h" -> players.add(HumanPlayer(playerInput, "HumanPlayer")) 
                playerType == "r" -> players.add(RandomPlayer(playerInput, "RandomPlayer")) 
                playerType == "a" -> players.add(AiPlayer(playerInput, "AiPlayer")) 
                else -> players.add(HumanPlayer(playerInput, "RandomPlayer")) 
            }
            players.last().board = board
            // if (playerInput == "X") {players.add(HumanPlayer("X")); players.add(Player("O")) }
            // else {players.add(Player("O")); players.add(Player("X")) }

            // players.forEach {it.board = board}
        }
        this.Start()
    }
    
    private fun Start() {
        println("GAME STARTED!!! \n")
        board.draw()

        var whoseMove : String
        if ( (0..1).random() == 0 ) whoseMove = "x"
        else whoseMove = "o"

        while( !players[0].win(board.board, players[0].playerState) && !players[1].win(board.board, players[1].playerState) ) {
            println("Ruch gracza - " + whoseMove)
            if (players[0].playerState == whoseMove) {
                players[0].move()
                whoseMove = players[1].playerState
            }
            else {
                players[1].move()
                whoseMove = players[0].playerState
            }
            board.draw()
        }
        if (players[0].win(board.board, players[0].playerState) && players[1].win(board.board, players[1].playerState)) print("It's a draw!!!")
        else if ( players[0].win(board.board, players[0].playerState) ) println("Player " + players[0].playerState.uppercase() + " win!!!")
        else if ( players[1].win(board.board, players[1].playerState) ) println("Player " + players[1].playerState.uppercase() + " win!!!")
    }
    
}
// fun gen(inBoard : MutableList<String>, state : String) : MutableList<MutableList<String>> {
//     var outBoards : MutableList<MutableList<String>> = mutableListOf()
//     var tmpPos : MutableList<Int> = mutableListOf()
//     for ( i in 0..(inBoard.size-1) ){
//         if ( inBoard[i] == "-" ) tmpPos.add(i)
//     }
//     tmpPos.forEach {
//         var tmpBoard : MutableList<String> = mutableListOf()
//         tmpBoard.addAll(inBoard)
//         tmpBoard[it] = state // x or o
//         outBoards.add(tmpBoard)
//     }
//     return outBoards
// }
// fun draw(inBoard : MutableList<String>) {
//     for ( i in 1..(inBoard.size)) {
//          print(inBoard[i-1] + " ")  
//          if ( i%3 == 0 ) println()
//     }
//     println("\n")
//  }
fun main() {
    // val gra = Board()
    // gra.draw()
    Game()

    // var board : MutableList<String> = mutableListOf( "-", "-", "-", "x", "-", "o", "-", "-", "x" )
    // draw(board)
    // var boards : MutableList<MutableList<String>> = gen(board, "o")
    // boards.forEach {
    //     draw(it)
    // }
}