package chucknorris;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        while(status) {
            System.out.println("Please input operation (encode/decode/exit): ");
            status = run(scanner.nextLine());
        }
    }

    private static boolean run(String operation) {
        Scanner scanner = new Scanner(System.in);
        switch (operation) {
            case "encode": {
                System.out.println("Input string: ");
                String s = (scanner.hasNextLine()) ? scanner.nextLine() : " ";
                StringBuilder resultInBinary = new StringBuilder();


                for (int x = 0; x < s.length(); x++) {
                    resultInBinary.append(String.format("%07d", Integer.parseInt(Integer.toBinaryString(s.charAt(x)))));
                }
                System.out.println("Encoded string: ");
                System.out.println(encodeAsNorris(resultInBinary.toString()));
                break;
            }
            case "decode": {
                System.out.println("Input encoded string: ");
                String s = (scanner.hasNextLine()) ? scanner.nextLine() : " ";
                if(checkEncoded(s) && !decodeNorris(s).equals("error!")) {
                    System.out.println("Decoded string: ");
                    System.out.println(decodeNorris(s));
                } else {
                    System.out.println("Encoded string is not valid.");
                }
                break;
            }
            case "exit":
                System.out.println("Bye! ");
                return false;
            default:
                System.out.println("There is no " + "'"+operation+"'" + " operation");
                break;
        }
        return true;
    }

    private static boolean checkEncoded(String s) {
        boolean status = false;
        for (int i = 0; i < s.length(); i++) {
            status = (s.charAt(i) == '0') || (s.charAt(i) == ' ');
        }
        String[] sA = s.split(" ");
        if(sA.length % 2 != 0) return false;

        return status;
    }
    private static String encodeAsNorris(String s) {
        StringBuilder sb = new StringBuilder();
        if(s.length() != 0) {
           int counter = 0;
           char current;
           while(counter < s.length()) {
               if(s.charAt(counter) == '1') {
                   sb.append("0 ");
                   current = '1';
               } else {
                   sb.append("00 ");
                   current = '0';
               }
               while(s.charAt(counter) == current) {
                   sb.append("0");
                   counter++;
                   if(counter == s.length()) break;
               }
               sb.append(" ");
           }
           return sb.toString();
        } else{
            return "blank string received";
        }
    }

    private static String decodeNorris(String s) {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        while(counter < s.length()-2) {
            if(s.charAt(counter) == s.charAt(counter+1) && s.charAt(counter+2) == ' ') {
                counter += 3;
                while(s.charAt(counter) == '0') {
                    sb.append("0");
                    counter++;
                    if(counter >= s.length()) break;
                }
            } else if(s.charAt(counter) == ' ') {
                counter++;
            } else {

                counter +=2;
                while(s.charAt(counter) == '0') {
                    sb.append("1");
                    counter++;
                    if(counter >= s.length()) break;
                }
            }
        }
        if(sb.length() % 7 != 0) return "error!";
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < sb.toString().length()/7; i++) {
            int a = Integer.parseInt(sb.substring(7*i,(i+1)*7),2);
            result.append((char) a);
        }
        return result.toString();
    }
}