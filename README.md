![](./images/calc.png)

[Версия на русском языке](#center-проект-консольный-калькулятор-center)
# <center> Project Console Calculator </center>

## Contents
1. [Project description](#Project-description)
2. [About Structure](#Structure)
3. [Download Project](#Download-Project)
4. [Implementation](#Implementation)
5. [Authors](#Author)
6. [Findings](#Findings)

## Project description

In this project, the task was to write a console
Java calculator which:

* Can work with both Arabic and Roman numbers.
* Takes a single line operation as input.
* Performs an operation with 2 numbers and one operation
* The calculator can only work with Arabic
  or Roman numerals at the same time, when entering
  user string like 3+II calculator
  should throw an exception and terminate its work.
* When entering Roman numerals, the answer should be displayed
  Roman numerals, respectively, when entering Arabic
  the answer is expected by the Arabs.
* When the user enters inappropriate numbers, the application
  throws an exception and exits.
* When the user enters a string that does not match
  one of the above arithmetic operations,
  the application throws an exception and exits.
* The result of the division operation is an integer,
  the remainder is discarded.
  The result of the calculator with Arabic numbers
  can be negative numbers and zero.
* The result of the calculator with Roman
  numbers can only be positive numbers,
  if the result of the work is less than one,
  an exception is thrown

## Main Stage of project:

* Receiving and checking numbers.
* Separation of an expression by a mathematical sign
* Checking the number system
* Roman number conversion

**Getting and checking numbers** - Entering math into the console
  operations in one line, getting information about that
  whether the numbers are Roman, and finding the arithmetic sign.

**Separation of expression by mathematical sign** - from
of the previously entered expression, only the numbers above which
it is necessary to carry out the operation by separating them by sign.

**Checking the number system** - First of all, we do not do it ourselves
definitions of the number system, but only that both numbers
belong to one and you can continue the calculation.

**Roman numeral conversion** - In a separate step
we can highlight the work with the conversion of Roman numbers, since the process
as follows:
* Get roman number from console
* Convert roman number to arabic
* Mathematical operation on Arabic numbers
* Reverse conversion to get a response in the form of Roman
  numbers


## Structure

* [Converted](./Converted) - The class which
  conversion methods are described.
* [Calculator](./Calculator) - The main block of code for
  calculations.
* [images](./images) - Additional directory storing information
  for documentation.


## Download Project

```
git clone https://github.com/dI98Sk/projectCalculator
```

## Implementation

The first step in the development of the program
Let's create a part of the code responsible for working with Arabic numbers.

The first part will create a "Converted" class, in which there will be
have a "word" in which the key is a symbol
Roman numeral and its Arabic equivalent:

    public Converter() {
       romanKeyMap.put('I', 1);
       romanKeyMap.put('V', 5);
       romanKeyMap.put('X', 10);
       romanKeyMap.put('L', 50);
       romanKeyMap.put('C', 100);
       romanKeyMap.put('D', 500);
       romanKeyMap.put('M', 1000);

We also have the **isRoman** method which takes as input
string and checks that this string is the key of our
"dictionary".

If the method finds the key, it will return True, and False in
otherwise.

Now we can move on to our main class.

To proceed further, we need to prepare
two arrays whose elements will be
options for bases on our numbers.

    String[] actions = {"+", "-", "/", "*"};
    String[] regexActions = {"\\+", "-", "/", "\\*"};

After that, we create a scanner and ask you to enter the expression:

    Scanner scn = new Scanner(System.in);
    System.out.print("Введите выражение: ");

After receiving the number, we need to understand what action
we want to execute on this data.

At this stage, an important component is to check
that the program managed to find the arithmetic sign, since
The user may have entered an invalid character:

    if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;

Briefly, such a check makes it possible to clarify
that the value of "actionIndex" was changed in the process
program work, we change it to an index
and the index cannot be less than 0.

After completing the initial verification of the operation,
we can start extracting numbers from the operation:

    String[] data = exp.split(regexActions[actionIndex]);

To do this, we will perform the .split() operation into which we will pass
separator, and in return it will return an array that is divided
this symbol, according to which we transmit the code as
delimiter array element "regexActions" indexed by actionIndex
which we got in steps earlier. It's necessary
since we can't use "+" as the method uses
regular expression, and you can't pass it to a regular expression
"+" since it has its own meaning, so we escape it.

Upon completion of the division, an array was returned to us
with numbers for the operation.

Now we need to check if both of our
numbers in one number system, in Roman or Arabic:
Determine if numbers are in
same format (both Roman or both Arabic):

     if(converter.isRoman(data[0]) == converter.isRoman(data[1])){

We pass our number to the method and check it
to belong to the number system:

    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }

Logically, everything is very simple if it is not found in the "dictionary"
the key passed in the method will be returned ***False***
If the numbers are in different systems of calculation, then we do not
let's go into the conditions and do not perform the transformations.

If the numbers are in the same number system, then we pass
according to the condition and perform the corresponding action.

In case of different systems, an error will be raised,
and warning text.

The next steps are to write the part to work
with Roman numerals.

To achieve the goal, we need to add the work code
with Roman numerals, after we figured out
that they are, namely:

    if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

used to calculate Roman numerals
algorithm for converting them into Arabic numbers and after
this re-conversion to Roman to display the answer.
All operations are carried out with Arabic numbers.

After the result of the operation is received, we check
if the number is Roman, then we call the method that converts the received
result back to a Roman number (since having received Roman
numbers, we initially performed the operation on the Arabic number format).

Also, to solve the problem, we supplemented the class with an additional
"dictionary" in which the keys are Arabic numbers
and their meanings are the Roman equivalent.

To convert Arabic to Roman we use
following algorithm:

    public String intToRoman(int number) {
        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return roman;

Where we pass the Arabic number, then we run the loop where
we pass a number to the ***floorKey()*** method, the method will
search for the most similar key in relation to the passed one
number, it is important that close to the bottom ** bottom **, this
due to the fact that from the side of logic, Roman numbers are worth
in the form of a string (some constituent parts), each character of which
denotes a specific equivalent in Arabic format.
The string must be "collected" from the largest number to the smallest
our program implements the same algorithm
so the program
"collects" the Roman number from the largest component
to the smallest, for this the ***floorKey()*** method is needed.

Getting a Roman number is done by concatenation
previously received value in the string.






## Author

* [Skakun Dmitrii](https://www.instagram.com/skakun_dr/)

## Findings

In conclusion, we can say that
we managed to create a successfully functioning console
calculator with the ability to work as with
Roman and Arabic numerals.






![](./images/calc.png)
# <center> Проект Консольный калькулятор  </center>
## Содержание
1. [Описание проекта](#Описание-проекта)
2. [Структура](#Структура)
3. [Скачать проект](#Скачать-проект)
4. [Реализация](#Реализация)
5. [Авторы](#Авторы)
6. [Итоги](#Итоги)

## Описание проекта

В данном проекте стояла задача написать консольный
калькулятор на языке Java который:

* Может работать как с арабскими числами, так и с римскими.
* Принимает на вход операцию в одну строку.
* Выполняет операцию с 2 числами и одной операцией
* Калькулятор умеет работать только с арабскими 
  или римскими цифрами одновременно, при вводе 
  пользователем строки вроде 3 + II калькулятор
  должен выбросить исключение и прекратить свою работу.
* При вводе римских чисел, ответ должен быть выведен
  римскими цифрами, соответственно, при вводе арабских 
  ответ ожидается арабскими. 
* При вводе пользователем неподходящих чисел приложение
  выбрасывает исключение и завершает свою работу. 
* При вводе пользователем строки, не соответствующей 
  одной из вышеописанных арифметических операций, 
  приложение выбрасывает исключение и завершает свою работу. 
* Результатом операции деления является целое число, 
  остаток отбрасывается.
  Результатом работы калькулятора с арабскими числами 
  могут быть отрицательные числа и ноль. 
* Результатом работы калькулятора с римскими
  числами могут быть только положительные числа,
  если результат работы меньше единицы, 
  выбрасывается исключение


## Основные этапы работы:

* Получение и проверка чисел.
* Разделение выражения по математическому знаку
* Проверка системы изчисления
* Конвертация римского числа

**Получение и проверка чисел** - Ввод в консоль математической
операции в одну строку, получение информациии о том не 
являюся ли числа римскими, и нахождение арифметического знака.

**Разделение выражения по математическому знаку** -  из 
введеного ранее выражения выделяются только числа над которыми 
необходимо провести операцию путем разделения их по знаку.

**Проверка системы изчисления** - В первую очередь выполняем не само 
определения системы изчисления, а только то что оба числа 
принадлежат к одной и можно продолжить вычисления.

**Конвертация римского числа** - В отдельный этап 
можем выделить работу с конвертацией римских чисел так как процесс 
выглядит следующим образом:
* Получение римского числа из консоли
* Конвертация римского числа в арабское
* Математическая операция над арабскими числами
* Обратная конвертация для получения ответа в виде римского
числа

## Структура 

* [Converted](./Converted) - Класс описывающий методы конвертации.
* [Calculator](./Calculator) - Основная часть кода для вычислений.
* [images](./images) - Вспомогательная директория для хранения 
* информации для документирования.


## Скачать Проект

```
git clone https://github.com/dI98Sk/projectCalculator
```

## Реализация

Первым этапом в работе над программой 
создатим часть кода отвечающего за работу с арабскими числами.

В первую часть создадит класс "Converted", в котором будет 
иметь некий "словать" в котором ключем является символ
римской цифры а значением его арабский эквивалент:

    public Converter() {
       romanKeyMap.put('I', 1);
       romanKeyMap.put('V', 5);
       romanKeyMap.put('X', 10);
       romanKeyMap.put('L', 50);
       romanKeyMap.put('C', 100);
       romanKeyMap.put('D', 500);
       romanKeyMap.put('M', 1000);

Так же мы имеем метод **isRoman** который принимает на вход
строку и проверяет что данная строка является ключем нашего 
"словаря".

Если метод найдет ключ то вернет True, и False в 
обратном случае.

Теперь можем перейти к нашему основному классу.

Для дальнейшего решения нам необходимо подготовить 
два массива элиментами которых будут являться 
варианты опираций над нашими числами.

    String[] actions = {"+", "-", "/", "*"};
    String[] regexActions = {"\\+", "-", "/", "\\*"};

После этого создаем сканер и просим ввести выражение :

    Scanner scn = new Scanner(System.in);
    System.out.print("Введите выражение: ");

После получения числа нам необходимо понять какое дейсвие
мы хотим выполнить над этими данными. 

На данным этапе важной составляющей является проверка
на то что программе удалось найти арифметический знак, так как 
пользователь мог ввести не верный знак:

        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;

Если кратко такая проверка позволяет уточнить 
то что значение "actionIndex" было изменено в процессе 
работы программы, меняем мы его на индекс 
а индекс не может быть меньще 0.

После завершения первичной проверки операции, 
мы можем приступить к выделению чисел из операции:

    String[] data = exp.split(regexActions[actionIndex]);

Для  этого выполним операцию .split() в которую передадим 
разделитель, а в ответ он вернет масив который разделен
этим символом, согласно которому коду мы передаем в качесве 
разделителя элимент масива "regexActions" под индексом actionIndex
который мы получили на этапах ранее. Это необходимо 
так как мы не можем использовать "+" , так как метод использует 
регулярное выражение, а в регулярное выражения нельзя передать
"+" так как он имеет свое значение, поэтому мы его экранируем.
 
По завершению разделения нам был возращен массив 
с числами для операции.

Теперь нам необходимо проверить находятся ли оба наших 
числа в одной системе изчисления, в римской или в арабской:
Определяем, находятся ли числа в 
одном формате (оба римские или оба арабские):

    if(converter.isRoman(data[0]) == converter.isRoman(data[1])){

Мы передаем в метод наше число и проверяем его 
на принадлежность к системе изчисления:

    public boolean isRoman(String number){
        return romanKeyMap.containsKey(number.charAt(0));
    }

Логически все очень просто если в "словаре" не будет найден 
ключ передаваемый в методе то будет возращен ***False*** 
Если числа в разных системах изчесления то мы не 
зайдем в условия и не выполним преобразований.

Если числа в одной системе изчисления то мы проходим
по условию и выполняем соответсвующее действие.

В случае разных систем будет вызвана ошибка, 
 и текст предупреждения.

Следующим этапам необходимо написать часть для работы 
с римскими числами. 

Для достижения цели нам необходимо добавить код работы 
с римскими числами, после того как мы выяснили 
что они таковыми являются, а именно:

    if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);


Для вычислени римских чисел был применен
алгоритм конвертации их в арабские числа и после 
этого повторная конвертация в римские для вывода ответа.
Все операции проводятся с арабскими числами.

После того как получен результат от операции,  мы проверяем 
если число римское то вызываем метод конвертирующий полученый 
результат обратно в римское число (так как получив римские 
числа изначально операцию мы производили над арабским форматом числа).

Так же для решения задачи мы дополнили класс дополнительным 
"словарем" в котором ключами являются арабские числа 
а  значениями их римский эквивалент.

Для конвертации арабского числа в римское мы используем 
следующий алгоритм: 
  
    public String intToRoman(int number) {
        String roman = "";
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number);
            roman += arabianKeyMap.get(arabianKey);
            number -= arabianKey;
        } while (number != 0);
        return roman;

Где мы передаем арабское число, далее запускаем цикл где 
в метод ***floorKey()*** передаем число, метод осуществит
поиск наиболее похожего ключа по отношению к переданому
числу, важно что близкому снизу **снизу**, это 
обусловленно тем что со стороны логики римские числа стоятся
в виде строки (неких составных частей), каждый символ которой 
обозначает определенный эквивалент в арабском формате.
Строка должна "собираться" от большого числа к меньшему 
в нашей программе реализован тот же алгоритм 
поэтому программа 
"собирает" римкое число от самой большой составляющей 
к самой малой, для этого и  необходим метод ***floorKey()***.

Получение римского числа происходит путен конкатинации
полученых ранее значение в строке.

## Авторы

* [Скакун Дмитрий](https://www.instagram.com/skakun_dr/)

## Итоги

В завершении можем сказать что 
нам удалось создать успешно функционирующий консольный
калькулятор с возможность работы как с 
римскими так и с арабскими числами.


