fun main(){
    val m =Matrix("1 2\n3 4")
    val l:List<Int> = m.row(2)
    println(l)
}

class Matrix(private val matrixAsString: String) {

    fun column(colNr: Int): List<Int> {
        val internalIndex = colNr-1
        val rows: List<String> = matrixAsString.lines()
        //val rows: List<String> = matrixAsString.split("\n")
        val selectedColumns: MutableList<Int> = mutableListOf()
        rows.forEach{
            val temp: Int = it.trim().split("\\s+".toRegex()).elementAt(internalIndex).toInt()
            selectedColumns.add(temp)}
        return selectedColumns

    }
    //non gestisco la richiesta di una riga oltre la dimensione
    fun row(rowNr: Int): List<Int> {
        val internalIndex = rowNr-1
        val rows: List<String> = matrixAsString.lines()
        val selectedRow: List<Int> = rows[internalIndex].split("\\s+".toRegex()).map{it.toInt()}
        return selectedRow
    }
}
