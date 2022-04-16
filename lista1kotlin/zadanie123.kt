
fun main(){
    // zadania 1, 2 i 3
    val dzielniki = arrayOf(3, 5, 7, 11, 13)
    val dzielnikiSlownie = arrayOf("trzy", "piec", "siedem", "jedenascie", "trzynascie")

    for ( i in 0..100 ) {    
        var wyjscie = ""
        for (j in dzielniki){
            if(i%j==0){
                wyjscie += dzielnikiSlownie[dzielniki.indexOf(j)]
                //println(dzielniki.indexOf(j))
            }
        }   
        if ( wyjscie.length <= 2 ){
            wyjscie = i.toString()
            println(wyjscie)
        }
        else  {  
            println(wyjscie)
        }
    }
}