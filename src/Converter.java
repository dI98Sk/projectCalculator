import java.util.TreeMap; // импортируем имплементацию интерфейса Map;

public class Converter {
    TreeMap<Character, Integer> romanKeyMap = new TreeMap<>(); // объявляем "словарь" для римских
    TreeMap<Integer, String> arabianKeyMap = new TreeMap<>(); // объявляем "словарь" для арабских

    public Converter() {                // создаем метод со "словарями" для конвертации
        romanKeyMap.put('I', 1);
        romanKeyMap.put('V', 5);
        romanKeyMap.put('X', 10);
        romanKeyMap.put('L', 50);
        romanKeyMap.put('C', 100);
        romanKeyMap.put('D', 500);
        romanKeyMap.put('M', 1000);

        arabianKeyMap.put(1000, "M");
        arabianKeyMap.put(900, "CM");
        arabianKeyMap.put(500, "D");
        arabianKeyMap.put(400, "CD");
        arabianKeyMap.put(100, "C");
        arabianKeyMap.put(90, "XC");
        arabianKeyMap.put(50, "L");
        arabianKeyMap.put(40, "XL");
        arabianKeyMap.put(10, "X");
        arabianKeyMap.put(9, "IX");
        arabianKeyMap.put(5, "V");
        arabianKeyMap.put(4, "IV");
        arabianKeyMap.put(1, "I");

    }


    public boolean isRoman(String number){   // метод проверки на то что число римское
        return romanKeyMap.containsKey(number.charAt(0));
    }

    //15
    public String intToRoman(int number) {
        String roman = ""; // создали строку для хранения итогового числа
        int arabianKey;
        do {
            arabianKey = arabianKeyMap.floorKey(number); // floorKey(K obj): возвращает самый большой ключ k,
                                                         // который меньше или равен ключу obj.
                                                         // Если такого ключа нет, возвращает null;
            roman += arabianKeyMap.get(arabianKey); // по индексу находим арабское число и записываем в переменную
            number -= arabianKey;
        } while (number != 0); // цикл остановится только когда программа "сотрет" переданое значение до 0
                               // путем вычитания из него ключей полученных ранее.
        return roman;


    }

    public int romanToInt(String s) {  // метод преобразования римского числа в арабское
        int end = s.length() - 1;
        char[] arr = s.toCharArray();
        int arabian;
        int result = romanKeyMap.get(arr[end]);
        for (int i = end - 1; i >= 0; i--) {
            arabian = romanKeyMap.get(arr[i]);

            if (arabian < romanKeyMap.get(arr[i + 1])) {
                result -= arabian;
            } else {
                result += arabian;
            }


        }
        return result;

    }
}