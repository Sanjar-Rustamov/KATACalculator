import java.util.Scanner;

public class Main {
    static int a, b;
    static boolean rome;
    static String arif;

    public static void main(String[] args) throws CalcExeption {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в программу КАЛЬКУЛЯТОР !!!");
        System.out.println("Введите выражение в формате: (первое число)(пробел)(арифметическое действие)(пробел)(второе число)");
        try {
            String exp = scanner.nextLine();
            String result = calc(exp);
            System.out.println(result);
        } catch (CalcExeption e) {
            System.out.println(e.getMessage());
        }

    }

    public static String calc(String input) throws CalcExeption {
        if (input.isEmpty())
            throw new CalcExeption("Ошибка ! Вы ввели пустую строку!");
        String[] inputArr = input.split(" ");

        if (inputArr.length != 3)
            throw new CalcExeption("Ошибка ! Формат ввода должен быть: (первое число)(пробел)(арифметическое действие)(пробел)(второе число)");

        if (inputArr[1].equals("+") || inputArr[1].equals("-") || inputArr[1].equals("*") || inputArr[1].equals("/"))
            arif = inputArr[1];
        else throw new CalcExeption("Ошибка! Арифметическое действие (+ , - , * , /) введено неправильно.");





        if(isRome(inputArr[0]) && isRome(inputArr[2])){
            a = RomeToArab(inputArr[0]);
            b = RomeToArab(inputArr[2]);

            if(a < b && arif.equals("-"))
                throw new CalcExeption("Ошибка! В римской СС отрицательных значений не бывает.");

            if(a == b && arif.equals("-"))
                throw new CalcExeption("Ошибка! В римской СС нет цифры 0");

            if((a > 0 && a <= 10) && (b > 0 && b<=10)) {
                return ArabToRom(Integer.parseInt(calculation(a , b , arif)));

            }
            else {
                throw new CalcExeption("Ошибка ! Цифры должны быть в пределе диапазона от 1 до 10 включительно.");
            }
        } else if (isNum(inputArr[0]) && isNum(inputArr[2])) {
            a = Integer.parseInt(inputArr[0]);
            b = Integer.parseInt(inputArr[2]);
            if((a > 0 && a <= 10) && (b > 0 && b<=10)) return calculation(a , b , arif);
            else {
                throw new CalcExeption("Ошибка ! Цифры должны быть в пределе диапазона от 1 до 10 включительно.");
            }
        } else {
            throw new CalcExeption("Ошибка! Введите целые числа (арабские либо римские).");
        }

    }


     static boolean isNum(String str_int) throws CalcExeption {
        try {
            Integer.parseInt(str_int);
            rome = false;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

     static boolean isRome(String str_rome) throws CalcExeption {
        for (Rome rn : Rome.values()) {
            if (str_rome.equals(rn.toString())){
                rome = true;
                return true;
            }
        }
        return false;
    }

    static int RomeToArab(String str_rome){
        for(Rome rn : Rome.values()){
            if (str_rome.equals(rn.toString()))
                return rn.i;
        }
        return 0;
    }

    static String calculation(int x, int y, String ar){
        switch (ar){
            case "+" : return String.valueOf(x + y);
            case "-" : return String.valueOf(x - y);
            case "*" : return String.valueOf(x * y);
            case "/" : return String.valueOf(x / y);
        }
        return "";
    }

    static String ArabToRom (int resultIntRome){
        int[] arabNum = {100,90,50,40,10,9,5,4,1};
        String[] romNum = {"C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder resultStrRome = new StringBuilder();
        for (int i = 0 ; i < arabNum.length ; i++){
            while (resultIntRome - arabNum[i] >= 0){
                resultIntRome -= arabNum[i];
                resultStrRome.append(romNum[i]);
            }
        }
        return resultStrRome.toString();
    }
}




enum Rome {
    I("I", 1), II("II", 2), III("III", 3),
    IV("IV", 4), V("V", 5), VI("VI", 6), VII("VII", 7),
    VIII("VIII", 8), IX("IX", 9), X("X", 10);
    String s;
    int i;

    Rome(String s, int i) {
        this.s = s;
        this.i = i;

    }
}

