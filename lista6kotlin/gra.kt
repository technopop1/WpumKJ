import kotlin.collections.mutableListOf
import kotlin.text.lowercase

class Board {
    public var board: MutableList<String> =
            mutableListOf("1", "2", "3", "4", "5", "6", "7", "8", "9")

    public fun draw() {
        for (i in 1..(this.board.size)) {
            print(this.board[i - 1] + " ")
            if (i % 3 == 0) println()
        }
    }
    public fun set(idx: Int, playerState: String) {
        this.board[idx] = playerState
    }
    public fun check(idx: Int): Boolean {
        if (board[idx] == "-") return false else return true
    }
    fun getWinner(): String {

        for (i in 0..2) {
            if (board[i + i * 2] == board[i + i * 2 + 1] && board[i + i * 2] == board[i + i * 2 + 2] && board[i + i * 2] != "-" )
                return board[i * 3]
            if (board[i] == board[i + 3] && board[i] == board[i + 6] && board[i] != "-")
                return board[i]
        }

        if (board[0] == board[4] && board[0] == board[8] && board[4] != "-") return board[0]
        if (board[2] == board[4] && board[2] == board[6] && board[4] != "-") return board[2]

        for (i in 0..8) if (board[i] == "-") return "-" // nie ma wygranych, nie ma przegranych, gramy dalej 

        return "" // remis
    }
}

open class Player(pS: String, myClass: String) {
    public var board: Board = Board()
    public val playerState: String = pS
    private val playerClass: String = myClass
    public fun getState(): String {
        return playerState
    }
    // public fun win(checkBoard: MutableList<String>, ps: String): Int {
    //     if (checkBoard[0] == ps && checkBoard[4] == ps && checkBoard[8] == ps) return 1
    //     if (checkBoard[2] == ps && checkBoard[4] == ps && checkBoard[6] == ps) return 1

    //     for (i in 0..(2)) {
    //         if ((checkBoard[i] == ps) && (checkBoard[i + 3] == ps) && (checkBoard[i + 6] == ps))
    //                 return 1

    //         if ((checkBoard[i + i * 2] == ps) &&
    //                         (checkBoard[i + i * 2 + 1] == ps) &&
    //                         (checkBoard[i + i * 2 + 2] == ps)
    //         )
    //                 return 1
    //     }
    //     var tmpPs: String = if (ps == "x") "o" else "x"
    //     if (checkBoard[0] == tmpPs && checkBoard[4] == tmpPs && checkBoard[8] == tmpPs) return -2
    //     if (checkBoard[2] == tmpPs && checkBoard[4] == tmpPs && checkBoard[6] == tmpPs) return -2

    //     for (i in 0..(2)) {
    //         if ((checkBoard[i] == tmpPs) && (checkBoard[i + 3] == tmpPs) && (checkBoard[i + 6] == tmpPs) ) return -2
    //         if ((checkBoard[i + i * 2] == tmpPs) && (checkBoard[i + i * 2 + 1] == tmpPs) && (checkBoard[i + i * 2 + 2] == tmpPs) ) return -2
    //     }

    //     if (!checkBoard.contains("-")) return -1

    //     return 0
    // }
    open fun move() {}
}

public class HumanPlayer(pS: String, myClass: String) : Player(pS, myClass) {
    override fun move() {
        var playerChoice = readLine()!!.lowercase()
        while (playerChoice.length != 1 || playerChoice.toInt() < 1 || playerChoice.toInt() > 9 || board.check(playerChoice.toInt() -1 )) {
            playerChoice = readLine()!!.lowercase()
        }
        board.set( (playerChoice.toInt() - 1 ), playerState)
    }
}

public class RandomPlayer(pS: String, myClass: String) : Player(pS, myClass) {
    override fun move() {
        var tmpBoard: MutableList<Int> = mutableListOf()
        for (i in 0..(board.board.size - 1)) {
            if (board.board[i] == "-") tmpBoard.add(i)
        }
        val choise: Int = tmpBoard.random()
        board.set(choise, playerState)
    }
}

public class AiPlayer(pS: String, myClass: String) : Player(pS, myClass) {
    private var whoseMove: String = playerState
    //  private var countMoves : Int = 0
    override fun move() {
        // var boards : MutableList<MutableList<String>> : mutableListOf()
        var score: Int = if (playerState == "x") Int.MIN_VALUE else Int.MAX_VALUE
        var index: Int = -1
        for (i in 0..8) {
            if (board.board[i] == "-") {
                board.board[i] = playerState
                var childScore: Int = minimax(board, 0, if (playerState == "x") false else true)
                board.board[i] = "-"
                if (playerState == "x") {
                    if (childScore > score) {
                        score = childScore
                        index = i
                    }
                } else {
                    if (childScore < score) {
                        score = childScore
                        index = i
                    }
                }
            }
        }
        board.board[index] = playerState
        //  println(countMoves)
        //  countMoves = 0
        // whoseMove = playerState
    }

    public fun minimax(node: Board, depth: Int, maximizing: Boolean): Int {
        return alfabeta(node, depth, maximizing, Int.MIN_VALUE, Int.MAX_VALUE)
    }
    private fun alfabeta(node: Board, depth: Int, maximizing: Boolean, alfa : Int, beta : Int) : Int {
        //  countMoves += 1
        var tmpAlfa = alfa
        var tmpBeta = beta
        if (!node.board.contains("-")) {
            var ewin = node.getWinner()
            if (ewin == "x") return 1 else if (ewin == "o") return -1 else return 0
        }
        var score = if(maximizing) -10 else 10
        if (maximizing) {
            for (i in 0..8) {
                if (node.board[i] == "-") {
                    node.board[i] = "x"
                    var childScore: Int = alfabeta(node, depth, false, tmpAlfa, tmpBeta)
                    node.board[i] = "-"
                    // score = 
                    if (childScore > score) score = childScore 
                    if (childScore > tmpAlfa) tmpAlfa = childScore
                    if (beta <= tmpAlfa) break // zmiejszenie liczby sprawdzanych wezlow
                }
            }
            return tmpAlfa
        } else {
            for (i in 0..8) {
                if (node.board[i] == "-") {
                    node.board[i] = "o"
                    var childScore: Int = alfabeta(node, depth, true, tmpAlfa, tmpBeta)
                    node.board[i] = "-"
                    if (childScore < score) score = childScore 
                    if (childScore < tmpBeta) tmpBeta = childScore
                    if (beta <= tmpAlfa) break
                }
            }
            return tmpBeta
        }

    }
    private fun gen(inBoard: MutableList<String>, state: String): MutableList<MutableList<String>> {
        var outBoards: MutableList<MutableList<String>> = mutableListOf()
        var tmpPos: MutableList<Int> = mutableListOf()
        for (i in 0..(inBoard.size - 1)) {
            if (inBoard[i] == "-") tmpPos.add(i)
        }
        tmpPos.forEach {
            var tmpBoard: MutableList<String> = mutableListOf()
            tmpBoard.addAll(inBoard)
            tmpBoard[it] = state
            outBoards.add(tmpBoard)
        }
        return outBoards
    }
}

public class Game {
    private var board: Board = Board()
    private var players: MutableList<Player> = mutableListOf()
    constructor() {
        for (i in 0..1) {

            println("Wpisz typ gracza: H -Human, R -Random, A -AI Player")
            var playerType = readLine()!!.lowercase()
            while (playerType != "h" && playerType != "r" && playerType != "a") {
                playerType = readLine()!!.lowercase()
            }

            var playerInput: String
            if (i == 0) {
                println("Wpisz litere gracza X lub O")
                playerInput = readLine()!!.lowercase()
                while (playerInput != "x" && playerInput != "o") {
                    playerInput = readLine()!!.lowercase()
                }
            } else if (players.last().playerState == "x") playerInput = "o" else playerInput = "x"

            when {
                playerType == "h" -> players.add(HumanPlayer(playerInput, "HumanPlayer"))
                playerType == "r" -> players.add(RandomPlayer(playerInput, "RandomPlayer"))
                playerType == "a" -> players.add(AiPlayer(playerInput, "AiPlayer"))
                else -> players.add(HumanPlayer(playerInput, "RandomPlayer"))
            }
            players.last().board = board
        }
        this.Start()
    }

    private fun Start() {
        println("GAME STARTED!!! \n")
        board.draw()
        board.board = mutableListOf("-", "-", "-", "-", "-", "-", "-", "-", "-")

        var whoseMove: String
        if ((0..1).random() == 0) whoseMove = "x" else whoseMove = "o"

        while (!(board.getWinner() == "x") && !(board.getWinner() == "o") && !(board.getWinner() == "")) {
            println("Ruch gracza - " + whoseMove)
            if (players[0].playerState == whoseMove) {
                players[0].move()
                whoseMove = players[1].playerState
            } else {
                players[1].move()
                whoseMove = players[0].playerState
            }
            board.draw()
        }
        if (board.getWinner() == "") print("It's a draw!!!")
        else if (board.getWinner() == "x" || board.getWinner() == "o")
                println("Player " + board.getWinner() + " win!!!")
    }
}

fun main() {

    Game()
}
