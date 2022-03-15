import java.util.Stack

class Forth {
    val number = Regex("[0-9]+")
    val arithmetic = Regex("[*+/-]")
    val stack : Stack<String> = Stack()

    fun evaluate(vararg line: String): List<Int> {
        val listOfParameters = line.toList()[0]
        val parameters = listOfParameters.split(" ").map { it.lowercase() }
        parameters.forEach {
            if(it.matches(number)) stack.push(it)
            else if(it.matches(arithmetic)) stack.push(performArithmetic(it).toString())
            else performStackManipulation(it)
        }
        return stack.toList().map { it.toInt() }
    }

    fun performArithmetic(operator:String) : Int{
        if(stack.empty())
            throw Exception("empty stack")
        if(stack.size < 2)
            throw Exception("only one value on the stack")
        val second = stack.pop()
        val first = stack.pop()
        if(second.toInt()==0)
            throw Exception("divide by zero")
        when(operator) {
            "+" -> return first.toInt()+second.toInt()
            "-" -> return first.toInt()-second.toInt()
            "*" -> return first.toInt()*second.toInt()
            "/" -> return first.toInt()/second.toInt()
            else -> throw Exception("Operatore invalido")
        }
    }

    fun performStackManipulation(word:String){
        if(stack.empty())
            throw Exception("empty stack")
        //everything is lowercase
        when(word) {
            "dup" -> dup()
            "drop" -> drop()
            "swap" -> swap()
            "over" -> over()
            else -> throw Exception("Manipolatore invalido $word")
        }
    }
    fun dup(){
        val toDup = stack.peek()
        stack.push(toDup)
    }
    fun drop(){
        stack.pop()
    }
    fun swap(){
        if(stack.size < 2)
            throw Exception("only one value on the stack")
        val second = stack.pop()
        val first = stack.pop()
        stack.push(second)
        stack.push(first)
    }
    fun over(){
        if(stack.size < 2)
            throw Exception("only one value on the stack")
        val second = stack.pop()
        val first = stack.pop()
        stack.push(first)
        stack.push(second)
        stack.push(first)
    }
}
