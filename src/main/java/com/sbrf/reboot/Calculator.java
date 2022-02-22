package com.sbrf.reboot;

public class Calculator {
    public double getAddition(double a,double b){
        return  a+b;
    }
    public double getSubtraction(double a,double b){
        return  a-b;
    }
    public  double getMultiplication(double a,double b){
        return  a*b;
    }
    public double getDivision(double a,double b){
        return  a/b;
    }
    public double getAbs(double a){
        return Math.abs(a);
    }
    public double getPow(double a,double b){
        return Math.pow(a,b);
    }
    public double getSin(double a){return  Math.sin(a);}
}
