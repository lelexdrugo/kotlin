class BankAccount {
    //Per garantire la thread-safeness posso usare o l'annotation synchronized o il metodo synchronized this: ogni esecuzione di ciò che è scritto tra le graffe (o nella funzione annotata) sarà thread safe
    private var accessible = true
    var balance = 0
        get() {
            synchronized(this) {
            check(accessible)
            return field
            //if(accessible)  return field else throw IllegalStateException("Non più accessibile")
            }
        }
        @Synchronized
        private set


    @Synchronized
    fun adjustBalance(amount: Long){
        if(accessible)  balance+=amount.toInt() else throw IllegalStateException("Non più accessibile")
    }

    fun close() {
        accessible = false
    }
}
