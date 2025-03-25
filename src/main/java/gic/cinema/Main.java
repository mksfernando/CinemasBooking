package gic.cinema;

import gic.cinema.input.util.InputOutputUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new BookingEngine(new InputOutputUtil(new Scanner(System.in))).start();
    }
}