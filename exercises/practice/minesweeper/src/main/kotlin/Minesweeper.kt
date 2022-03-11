data class MinesweeperBoard(val board: List<String>) {
    //solamente righe di dimensione omogenea
    fun withNumbers(): List<String> {
//        val nrow = board.size
//        val ncol = if(nrow>0) board[0].length else 0
        val boardWithNumbers = mutableListOf<String>()
        //Caso generale
        //controllo la posizione i della stringa precedente e successiva
        //controllo la posizione i-1 e i+1 della stringua attuale, della precedente e delle successiva
        for ((i, row) in board.withIndex()) {
            var newRow = ""
            for((j,column) in row.withIndex()){
                var counter = 0
                if(row[j] == ' ') {
                    counter = checkForBomb(i, j)
                }
                val toAppend = if(counter!=0) {
                    counter
                } else if(board[i][j] == ' ') ' ' else '*'
                newRow += toAppend
            }
            boardWithNumbers.add(i, newRow)
        }
        return boardWithNumbers
    }

    private fun checkForBomb(i: Int, j: Int): Int {
        var counter = 0
        for (i2 in i - 1..i + 1) {
            for (j2 in j - 1..j + 1) {
                if (i2 >= 0 && i2 <= board.lastIndex && j2 >= 0 && j2 <= board[i].lastIndex)
                    if (board[i2][j2] == '*')
                        counter++
            }
        }
        return counter
    }
}


//ALTRA soluzione, più verbosa
/* //riga precedente alla corrente
 if (i >= 1 && board[i-1][j] == '*'){
     counter++
     //diagonale a destra
     if(j<ncol-1 && board[i-1][j+1] == '*')
         counter++
     //diagonale a sinistra
     if(j>=1 && board[i-1][j-1] == '*')
         counter++
 }
 //riga successiva alla corrente
 if(i<nrow-1 && board[i+1][j] == '*') {
     counter++
     if(j<ncol-1 && board[i+1][j+1] == '*')
         counter++
     //diagonale a sinistra
     if(j>=1 && board[i+1][j-1] == '*')
         counter++
 }*/
