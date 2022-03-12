class Reactor<T>() {
    // Your compute cell's addCallback method must return an object
    // that implements the Subscription interface.
    interface Subscription {
        fun cancel()
    }

    open inner class Cell<T>(value: T){
        var value :T = value
            set(newVal) {
                field = newVal
                //listOfCallBackObjects.forEach { it() }
                associatedComputeCells.forEach { it.updateValue() }
            }
        val associatedComputeCells= mutableListOf<ComputeCell>()  //lista di compute celle che usano me come input
        //val listOfCallBackObjects= mutableListOf<MyCallBackObject>()
    }

    inner class InputCell<T>(value:T): Cell<T>(value){

    }

    inner class ComputeCell(vararg arguments : Cell<T>, val computeValue :(List<T>)->T): Cell<T>(computeValue(arguments.map{it.value})){
        val inputCells :List<Cell<T>> =arguments.toList()
        init{
            inputCells.forEach { it.associatedComputeCells.add(this)}
        }
        //override var value: T = computeValue(inputCells.map{it.value})
        //    get() = computeValue(inputCells.map{it.value})

        fun updateValue(){
            val oldValue = value
            value = computeValue(inputCells.map{it.value})
            //chiama lista callbacks
            if(oldValue?.equals(value)!=true)
                listOfCallBackObjects.forEach { it.call(value) }
        }

        val listOfCallBackObjects= mutableListOf<MyCallBackObject>()
        fun addCallback(myFun: (T)->Unit): MyCallBackObject{
            val cb = MyCallBackObject(myFun)
            listOfCallBackObjects.add(cb)
            return cb
        }

    }

     inner class MyCallBackObject(val myFun: (T)->Unit): Subscription{
         override fun cancel() {
            println("Tobe implemented")
        }
         fun call(argument: T){
             return myFun(argument)
         }
    }
}
