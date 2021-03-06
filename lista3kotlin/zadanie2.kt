class Wybory{
    val kandydaci : Array<String>
    var glosy : MutableList<MutableList<String>> = mutableListOf()
    var pary : MutableMap<String, Array<Int>> = mutableMapOf()//mapOf<String, Int>()
    var graf : MutableMap<String, Array<Int>> = mutableMapOf()
    constructor( _kandydaci : Array<String>) {
        this.kandydaci = _kandydaci
    }
    fun glosuje( _glosy : MutableList<String>) {
        glosy.add(_glosy)   
    }
    fun tally() {
        kandydaci.forEach {
            i -> kandydaci.forEach {
                j -> if (i != j && !pary.contains("$i-$j") && !pary.contains("$j-$i") ) pary.put("$i-$j", arrayOf(0, 0))
            } 
        }
        println("PRZED LICZENIEM ------")
        pary.forEach{ println(it.key + "\t" + it.value[0] + "\t" + it.value[1]) }
        for ( glos in glosy ) {
            for ( para in pary ) {
                val pp = para.key.split("-")
                if ( glos.indexOf(pp[0]) < glos.indexOf(pp[1]) )para.value[0]++
                else para.value[1]++
            }
        }
        println("PO LICZENIU ----------")
        pary.forEach{ println(it.key + "\t" + it.value[0] + "\t" + it.value[1]) }
    }
    fun sort() {
        var paryCopied : MutableMap<String, Array<Int>> = mutableMapOf()
        pary.forEach{
            if (it.value[0] < it.value[1]) { 
                var pp = it.key.split("-")
                paryCopied.put(pp[1]+"-"+pp[0], arrayOf(it.value[1], it.value[0]))
            }
            else 
                paryCopied.put(it.key, arrayOf(it.value[0], it.value[1]))
        }
        pary = paryCopied
        println("PO POSORTOWANIU ----------")
        pary.forEach{ println(it.key + "\t" + it.value[0] + "\t" + it.value[1]) }
    }
    fun winner() {

        // var ranking : MutableMap<String, Array<Int>> = mutableMapOf()
        // pary.forEach {
            
        // }
        var ranking : MutableMap<String, Int> = mutableMapOf()
        pary.forEach {
            var pp = it.key.split("-")
            for ( i in pp )
            if (!ranking.keys.contains(i)){
                ranking.put(i, 0)
            }
        }
        pary.forEach {
            var pp = it.key.split("-")
            ranking[pp[0]] = ranking[pp[0]]!! + 1
        }
        // println(ranking)
        val winner = ranking.toList().sortedBy { (k, v) -> v }.reversed().toMap() 
        // println(winner)
        println("!!! !!! !!! !!! !!! !!! !!!")
        println("ZWYCI??ZCA - " + winner.keys.elementAt(0))
        println("!!! !!! !!! !!! !!! !!! !!!")
    }
    
}
fun main() {
    val wybory = Wybory(arrayOf("Ania", "Rafa??", "Robert"))

    wybory.glosuje(mutableListOf("Rafa??", "Ania", "Robert"))
    wybory.glosuje(mutableListOf("Rafa??", "Ania", "Robert"))
    wybory.glosuje(mutableListOf("Robert", "Ania", "Rafa??"))
    wybory.glosuje(mutableListOf("Robert", "Ania", "Rafa??"))
    wybory.glosuje(mutableListOf("Ania", "Rafa??", "Robert"))

    wybory.glosuje(mutableListOf("Ania", "Robert", "Rafa??"))
    wybory.glosuje(mutableListOf("Robert", "Ania", "Rafa??"))

    wybory.tally()
    wybory.sort()
    // wybory.winner()

    //rob < ani > raf > rob
}