import java.io.IOException;
import java.util.Scanner;

class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.print("Введите выражение: "); // вводим выражения в консоль
        String st = scn.nextLine();
        int result = Integer.parseInt(calc(st));
        //int result = Integer.parseInt(calc((scn.nextLine())));
        System.out.println("Результат операции: "+result);
    }



    public static String calc(String input) throws IOException {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"}; // создали словарь для определения мат операции
        String[] regexActions = {"\\+", "-", "/", "\\*"}; // создали словарь для подстановки в .split()

        String exp = input;

        //Определяем арифметическое действие:
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
             //break;
        }
        //Делим строчку по найденному арифметическому знаку

        String[] data = exp.split(regexActions[actionIndex]);
        if (data.length > 2){
            throw new IOException("ОШИБКА!!! Введено больше 2 переменных");
        }

        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            if (a > 10) {
                throw new IOException("ОШИБКА!!! Введено число больше 10");
            } else if (b > 10) {
                throw new IOException("ОШИБКА!!! Введено число больше 10");
            }
            //выполняем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }

            if(isRoman){
                //если числа были римские, возвращаем результат в римском числе
                //return (converter.intToRoman(result));
                System.out.println(converter.intToRoman(result));
            }
            else{
                //если числа были арабские, возвращаем результат в арабском числе
                return String.valueOf((result));
                //System.out.println(result);
            }
        }else{
            System.out.println("Числа должны быть в одном формате");
        }

        //return Integer.parseInt(result[0]) + Integer.parseInt(result[1]); //возвращаю результат сложения
        return exp;
    }

}
