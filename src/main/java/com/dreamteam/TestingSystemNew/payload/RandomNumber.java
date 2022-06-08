package com.dreamteam.TestingSystemNew.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class RandomNumber {
    private static int numerator_degree;
    private static int denominator_degree;
    private  static int numerator;
    private static int denominator;

    public RandomNumber(int denominator_degree, int numerator_degree, int denominator, int numerator) {
        this.denominator_degree = denominator_degree;
        this.numerator_degree = numerator_degree;
        this.denominator = denominator;
        this.numerator = numerator;
    }


    public RandomNumber() {
    }

    public RandomNumber(String message) {
    }


    public int getNumerator_degree() {
        return numerator_degree;
    }

    public int getDenominator_degree() {
        return denominator_degree;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }


    public static RandomNumber number()
    {

        int denominator_degree;
        int numerator_degree;
        int denominator;
        int numerator;

        int max = 2;
        int min = 100;

        int min1 = 1;
        int max1 = 20;


        numerator = chisl(min1, max1);
        denominator = chisl(min1, max1);

        numerator_degree = numeratorDegree(min, max);
        denominator_degree = numerator_degree + 2;


        return new RandomNumber(denominator_degree, numerator_degree, denominator, numerator);


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

    public ResponseEntity<?> checkResult(String sing, int numerator_Degree, int Denominator, int Numerator)
    {

        String message = "hi";
        int x = denominator_degree;
        int y = numerator_degree;
        int denominator1 = denominator;
        int numerator1 = numerator;

        int sign = numerator1 / denominator1;

        String str = String.valueOf(sign);

        int avg = (x + y) / 2;
        boolean check = true;

        boolean znak = checkSign(str, check, sing);
        boolean param1 = checkNumeratorDegree(avg, numerator_Degree, check);
        boolean param2 = checkDenominator(avg, Numerator, Denominator, numerator1, denominator1, check);

        if ((param1 | param2) == false) {

            return new ResponseEntity<>(new ApiResponse(false,"Задание выполнено не верно"), HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(new ApiResponse(true,"Задание выполнено верно"), HttpStatus.OK);
        }

    }


    public static boolean checkNumeratorDegree(int avg, int numerator_degree, boolean check) {

        if (avg != numerator_degree)
            check = false;

        return check;
    }

    private static boolean checkSign(String str1, boolean check, String sign) {
        char[] str = str1.toCharArray();
        char[] str2 = sign.toCharArray();
        if (str[0] != str2[0]) ;
        check = false;

        return check;
    }


    public static boolean checkDenominator(int avg, int numerator, int denominator, int numeratorRand, int denominatorRand, boolean check) {

        if(numeratorRand==denominatorRand)
        {
            if(numerator !=1 && denominator !=1)
                check=false;
        }



        if (Math.abs(numeratorRand) > Math.abs(denominatorRand)) {

            if (numeratorRand % denominatorRand == 0) {

                if ((numeratorRand / denominatorRand) != numerator)
                    check = false;

                if (avg != denominator)
                    check = false;

                if (numeratorRand / denominatorRand == avg) {
                    if ((numerator != 0) && (denominator != 0))
                        check = false;
                }
                ///вроде как все случаи
            } else {
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

                    if ((numeratorRand / avg) != numerator)
                        check = false;

                    if (denominatorRand != denominator)
                        check = false;
                }
                //вроде как тоже все

            }

        }
        // числитель больше
        else {

            if (avg == numeratorRand) {
                if (numeratorRand != 0)
                    check = false;

                if (denominatorRand != denominator)
                    check = false;

            }

            if ((numeratorRand % 2 == 0) && (denominatorRand % 2 == 0)) {
                if (denominatorRand % numeratorRand == 0) {
                    if (numeratorRand != numerator)
                        check = false;

                    if ((denominatorRand / numeratorRand) * avg != denominator)
                        check = false;
                } else if (numeratorRand / 2 != numerator)
                    check = false;

                if ((denominatorRand / 2) * avg != denominator)
                    check = false;


            }

            if (numeratorRand == 1) {
                if (avg * denominatorRand != denominator)
                    check = false;
            }

            if (numeratorRand != numerator)
                check = false;


            if (avg * denominatorRand != denominator)
                check = false;

        }

        return check;
    }

}

