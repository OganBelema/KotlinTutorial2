package demo

fun main(args: Array<String>){
    val x = addValue{num1,num2 -> num1 * num2}
    println(x)

    val person = Person("Belema", 6, "Male")
    person.greetPerson()

    val tonye = Person("Belema", "Tonye", 20, "Male")
    tonye.showRelationship(person)

    val myMum = Parent("Mina", 49, "Female", 4)
    myMum.greetPerson()
    myMum.displayNoOfChildren()

    val place = Place()
    place.time = "2:30pm"
    println(place.copyPlace().inNigeria)

    myMum.Children().greetPerson()

    val baby = Baby()
    println(baby.canTalk)

}

fun addValue(operation:(Int,Int) -> Int):Int {
    return operation(10,20)
}


open class Person(val name: String,
                  val age: Int,
                  val sex: String){

    private val uppercaseName = name.toUpperCase()

    constructor(brother: String, name: String, age: Int, sex: String) : this(name, age, sex)


    open fun greetPerson(){
        println("Hello $uppercaseName")
    }

    open fun showRelationship(person: Person){
        println("${person.name} is my brother")
    }
}

class Parent(name: String, age: Int, sex: String, private val numberOfChildren : Int) : Person(name, age, sex){

    fun displayNoOfChildren() = println("Number of children is $numberOfChildren")

    override fun greetPerson(){
        println("Hello $name")
    }

    override fun showRelationship(person: Person) {
        super.showRelationship(person)
        println()
    }

    inner class Children{

        fun greetPerson(){
            super@Parent.greetPerson()
            println("Hello")
        }
    }
}

class Place{
    var name: String = "Jos"
    var time : String? = null
    var location: String = "Plateau"
    var country: String = "Nigeria"
    var isItFar: Boolean = true
        get() = this.location == "Lagos"

    val inNigeria: Boolean get() = this.country == "Nigeria"
    var myString: String = ""
    set(value){
        myString = value
    }

    //inferring prop type from boolean
    val hello get() = "Hello"

    //using backing properties
    private var _table: Map<String, Int>? = null
    val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // Type parameters are inferred
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

    fun copyPlace(): Place {
        val result = Place() // there's no 'new' keyword in Kotlin
        result.name = name // accessors are called
        result.location = location
        result.time = time
        return result
    }

}

interface Human{
    var name: String
    var age: Int
    val canTalk: Boolean
     get() = age > 2

    fun eat()
    fun walk(){
        //body
    }

    fun talk(){
        println("my name is $name")
    }
}

class Baby: Human{
    override var name: String = "James"
    override var age: Int = 1

    override fun eat() {
    }
}

interface A{
    fun foo(){
        println("A")
    }
    fun bar()
}

interface B{
    fun foo(){
        println("B")
    }
    fun bar()

    fun hello()
}

class C: A, B{
    override fun foo() {
        super<A>.foo()
    }

    override fun bar() {
    }


    override fun hello() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}


//learning visibility modifiers
open class Outer {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4 // public by default
    protected class Nested {
        public val e: Int = 5
    }
}

class Subclass : Outer() {
    // a is not visible
// b, c and d are visible
// Nested and e are visible
    override val b = 5
// 'b' is protected
}

class Unrelated(o: Outer) {
// o.a, o.b are not visible
// o.c and o.d are visible (same module)
// Outer.Nested is not visible, and Nested::e is not visible either
}

//creating a generic swap function by extending the mutablelist class
fun <T> MutableList<T>.swap(index1: Int, index2: Int){
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}