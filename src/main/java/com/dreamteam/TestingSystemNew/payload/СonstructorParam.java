package com.dreamteam.TestingSystemNew.payload;


import org.springframework.stereotype.Service;

@Service
public class СonstructorParam {

    static final class MyResult {
        private final int numerator_degree;
        private int denominator_degree;
        private int numerator;
        private int denominator;
        private String sin;

        public MyResult(int denominator_degree, int numerator_degree, int denominator, int numerator) {
            this.denominator_degree = denominator_degree;
            this.numerator_degree = numerator_degree;
            this.denominator = denominator;
            this.numerator = numerator;
        }

        public int getDenominator() {
            return denominator_degree;
        }

        public int getNumerator() {
            return numerator;
        }

        public int getNumerator_degree() {
            return numerator_degree;
        }
    }

    public static MyResult something() {

        int denominator_degree;
        int numerator_degree;
        int denominator;
        int numerator;

        int max = 2;
        int min = 100;

        int min1 = -20;
        int max1 = 20;


        numerator = chisl(min1, max1);
        denominator = chisl(min1, max1);

        numerator_degree = numeratorDegree(min, max);
        denominator_degree = numerator_degree + 2;



        return new MyResult(denominator_degree, numerator_degree, denominator, numerator);


        //рандом  степень числитель
    }

    private static int numeratorDegree(int min, int max) {//рандо
        max -= min;

        return (int) (Math.random() * ++max) + min;


    }

    //рандом числитель и знаменатель
    private static int chisl(int min, int max) {
        max -= min;

        return (int) (Math.random() * ++max) + min;
    }


    ///////////////////////

    static final class CheckResult {

        private String message;

        CheckResult(String message) {

            this.message = message;
        }

        public String getNumber1() {
            return message;
        }

    }

    public static CheckResult result(String sing, int numerator_degree, int denominator, int numerator) {


        String message="hi";
        MyResult myResult =something();

        int x= myResult.denominator_degree;
        int y =myResult.numerator_degree;

        int denominator1 = myResult.denominator;
        int numerator1 = myResult.numerator;

        int sign = numerator1/denominator1;

        String str= String.valueOf(sign);

        int avg = (x+y)/2;
        boolean check=true ;

        boolean znak = checkSign(str,check,sing);
        boolean param1=checkNumeratorDegree(avg,numerator_degree,check);
        boolean param2=checkDenominator(avg,numerator,denominator, numerator1,denominator1,check);

        if((param1 | param2 ) == false)
        {
            message= "Задание выполнено не верно!";
        }
        else
        {
            message ="Задание выполнено верно!";
        }
        return new CheckResult(message);

    }

    public static boolean checkNumeratorDegree(int avg, int numerator_degree,boolean check) {

        if(avg!=numerator_degree)
            check=false;

        return check;
    }

    private static boolean checkSign(String str1, boolean check,String sign)
    {
        char[] str = str1.toCharArray();
        char[] str2 = sign.toCharArray();
        if(str[0] != str2[0]) ;
        check =false;

        return check;
    }



    public static boolean checkDenominator(int avg,int numerator, int denominator,int numeratorRand, int denominatorRand,boolean check) {

        if(Math.abs(numeratorRand)>Math.abs(denominatorRand)) {

            if (numeratorRand % denominatorRand == 0) {

                if ((numeratorRand / denominatorRand )!= numerator)
                    check = false;

                if (avg != denominator)
                    check = false;

                if (numeratorRand / denominatorRand == avg) {
                    if ((numerator != 0) && (denominator != 0))
                        check = false;
                }
                ///вроде как все случаи
            }
            else {
                if (numeratorRand == avg) {
                    if ((numerator != 0))
                        check = false;

                    if (denominatorRand != denominator)
                        check = false;
                }

                if (numeratorRand != numerator)
                    check = false;

                if (avg * denominatorRand != denominator)
                    check = false;

                if ((numeratorRand % avg) == 0) {

                    if ((numeratorRand/avg)!=numerator)
                        check=false;

                    if(denominatorRand!=denominator)
                        check=false;
                }
                //вроде как тоже все

            }

        }
        // числитель больше
        else
        {

            if(avg==numeratorRand) {
                if (numeratorRand != 0)
                    check = false;

                if(denominatorRand!=denominator)
                    check=false;

            }

            if( (numeratorRand%2==0) && (denominatorRand%2==0) )
            {
                if(denominatorRand%numeratorRand == 0)
                {
                    if(numeratorRand!=numerator)
                        check=false;

                    if((denominatorRand/numeratorRand) * avg != denominator)
                        check=false;
                }
                else
                if(numeratorRand/2!=numerator)
                    check=false;

                if((denominatorRand/2) * avg != denominator)
                    check=false;


            }

            if(numeratorRand==1)
            {
                if(avg*denominatorRand!=denominator)
                    check=false;
            }

            if(numeratorRand!=numerator)
                check=false;


            if(avg*denominatorRand!=denominator)
                check=false;

        }

        return check;
    }


}
