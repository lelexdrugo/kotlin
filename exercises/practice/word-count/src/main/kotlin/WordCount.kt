object WordCount {

    fun phrase(phrase: String): Map<String, Int> {
        //val words = phrase.split(("\\s+").toRegex()).map{it.lowercase()}
        val words = phrase.split(("[^a-zA-Z1-9']+").toRegex()).map{it.lowercase().trim('\'')}.filter{it.isNotEmpty()}
        //splitta quando incontra qualcosa che NON sia carattere, numero, o carattere ' (anche ripetuto)

        val myMap = words.groupBy { it }.mapValues { it.value.size }
        println(myMap)
        return myMap
    }
}
