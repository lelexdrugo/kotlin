object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        //molto interessante: non è semplicemente un filter{not null}: in questo modo la collection ritornata sarebbe comunque di tipo Any?
        //Internamente questo fa un filter e dopo un map a->!!a così da poter avere come valore di ritorno una collection di Any senza ?
        val step1 = source.filterNotNull()
        //Ricorda: la flatmap vuole una lambda che ritorni oggetti iterable (liste ecc) e lui si occupa semplicemente di concatenare tali valori di ritorno
        //delle chiamate alla lambda che fa per ogni elemento dell'iterable che l'ha usata
        val step2 = step1.flatMap {
            if(it is Collection<Any?>)
                flatten(it)
            else
                listOf(it)
        }
        return step2
    }
}
