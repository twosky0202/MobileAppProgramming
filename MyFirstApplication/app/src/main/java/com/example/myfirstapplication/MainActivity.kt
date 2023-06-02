package com.example.myfirstapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 출력 XML 명시
        setContentView(R.layout.activity_main)

    }
}

/*
변수와 함수
val: value의 줄임말. 초깃값이 할당되면 바꿀 수 없는 변수를 선언할 때 사용
var: variable의 줄임말. 초깃값이 할당된 후에도 값을 바꿀 수 있는 변수를 선언할 떄 사용
최상위에 선언한 변수나 클래스의 멤버 변수는 선언과 동시에 초깃값을 할당해야함

초기화 미루기
lateinit, lazy
ex) lateinit var data3: String
lateinit은 var에만 사용 가능, Int, Long, Short, Double, Float, Boolean, Byte 타입에는 사용할 수 없음

ex) val data4: Int by lazy {
    println("in lazy..")
    10
} 소스에서 변수가 최초로 이용되는 순간 중괄호로 묶은 부분이 자동으로 실행되어 그 결괏값이 변수의 초깃값으로 할당

데이터 타입
String : """ 줄바꿈 들여쓰기 반영 """
Any: 최상위 클래스
Unit: 반환문이 없는 함수. 함수 선언시 반환 타입 생략하면 자동으로 적용
Nothing: Nothing? -> null, Nothing -> 예외
널 허용 : Int?, 널 불허용 : Int

fun 함수명(매개변수명:타입):반환타입{..}

Array<Iny> = Array(3, {0})
Array.set(2, 30), Array.get(2)
IntArray, BooleanArray, ByteArray, CharArray, DoubleArray, FloatArray, IntArray, LongArray, ShortArray
arrayOf<Int>
booleanArrayOf()...

var data6 = listOf<Int>(1,2,3)
var data7 = mutableListOf<Int>(1,2,3) : 바뀔 수 있는 list. add(), set()

map : 사전식 (단어 : 설명)
var data9 = mapOf<String, Int>(Pair("one", 1), Pair("two", 2))
var data9 = mapOf<String, Int>("one" to 1, "two" to 2)

if문
var data11:Int = if(data10 > 10){
        println("data10 > 10")
        2
    }
    else if(data10 > 5){
        println("data10 > 5")
        1
    }
    else {
        println("else")
        0
    }
    println(data11)

// 조건문 switch~case~default
    var data13:Any = "hello"
    when(data13){
        is String -> println("string")
        is Boolean -> println("boolean")
        5,6,7,8,9,10  -> println("7")
        in 5..10 -> println("7")
        else -> println("else")
    }

// when 데이터 명시 안하고 조건만 명시
    var data14 = when(data12){ 표현식
        7 -> "7"
        5 -> "5"
        else -> "else"
    }
//for
for(i in 1..10) 1부터10까지 1씩증가
for(i in 1 until 10) 1부터9까지 1씩증가(10미포함)
for(i in 2..10 step 2) 2부터10까지 2씩증가
for(i in 10 downTo 1) 10부터1까지 1씩감소
for(i in data.indices) 컬렉션 타입의 데이터 개수만큼 반복
for((index, value) in data.withIndex()) 인덱스와 실제 데이터 함께 가져오려면

////////////
class User {
    constructor(name:String) 생성자
}

// constructor 키워드 생략
class User1(var name:String) { // 매개변수를 var이나 val로 선언하면 멤버변수
	//var name = "lee" // 멤버변수

    constructor(name: String, count:Int) : this(name) // 앞의 상속, count 새로
    constructor(name: String, count:Int, email:String) : this(name, count)

    init{ 객체 생성시 자동 실행
        println("User...init...$name") // 매개변수로 전달되는 값
        this.name = name // 멤버변수, this가 안붙어있으면 매개변수
    }


    fun someFun(){
        println("User...someFun...$name") // 멤버변수로 출력
    }
}

open class User2(name:String){ 상속할 수 있게 선언
    open var someData = 10 // 상위에서 open을 해줘야지만 하위에서 override가 가능
    open fun someFun(){
        println("User--someData--$someData")
    }
}

class Student(name:String) : User2(name){
    override var someData = 20
    override fun someFun(){
        println("Student--someData--$someData")
    }
}                                                                                                                                       ("kim") // 매개변수
    user.someFun()

    val user2 = User1("lee", 3)
    val user3 = User1("choi", 5, "c@ds")

    val obj = Student("lee")
    obj.someData = 20
    obj.someFun()
}

접근제한자 최상위, 클래스
public 모든 파일, 모든 클래스
internal 같은 모듈내
protected 최상위 불가, 상속관계의 하위클래스가능
private 파인 내부, 클래스 내부

데이터 클래스 data 키워드로 선언
equals()함수 주 생성자의 멤버 변수가 같은지만 판단. 데이터-> 안의 값 비교. 같으면 true, 클래스-> 각각의 객체이기 때문에 안의 값이 아무리 같아도 false
toString() 함수 객체가 포함하는 멤버 변수의 데이터 출력
object 클래스: 익명 클래스
companion 클래스: 멤버변수나 함수를 클래스 이름으로 접근하고자 할때

람다 함수: 익명 함수 정의
{매개변수 -> 함수 본문}
val sum = {n1:Int, n2:Int -> n1+n2} (10, 20)
{->println("hi")}
{println("hi")}
val some: (Int)->Unit = {println(it)}
typealias 타입별칭
typealias type=(Int, Int)->Boolean
val some : type = {n1, n2} -> n1>n2 // 타입선언 생략 가능
val some = {n1, n2} -> n1>n2 // 타입선언 생략 가능

널안전성
널허용 ?연산자
var data:String? = "hi"
var length = data?.length
prinln("#{data?.length ?: -1}") 널일 때 대입해야하는 값이나 실행해야하는 구문이 있는 경우

예외발생 !!연산자
객체가 널일 떄 예외를 일으키는 연산자
fun som(data:String?):Int{
    return data!!.legnth
}
*/