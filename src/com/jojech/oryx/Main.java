package com.jojech.oryx;

import java.lang.String;
import java.lang.Math;
import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {

    public static void main(String[] args) {
        String s = "Java";
        s.concat(" SE 6");
//        s.toLowerCase();
        System.out.print(s);
        //how many?


        Scanner scan = new Scanner(System.in);
        System.out.println("Enter input: ");
        String input = scan.nextLine();
        System.out.println(input);
    }
}
