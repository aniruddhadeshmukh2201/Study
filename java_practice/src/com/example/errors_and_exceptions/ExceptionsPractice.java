package com.example.errors_and_exceptions;

import java.util.logging.LogManager;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}


class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

public class ExceptionsPractice {
    
    public static void A() throws InvalidAgeException {
        if(true) throw new InvalidAgeException("checked");
        if(true) throw new InsufficientBalanceException("unchecked");
    }

    
    
    public static void main(String[] args) throws InvalidAgeException {
        
        /*
        try 
        catch
        finally
        checked exceptions
        unchecked exceptions

        */ 
        
        // try {
            
        //     if(false) {
        //         throw new InvalidAgeException("you are still young for this");
        //     } 
        //     if(false) {
        //         throw new InsufficientBalanceException("paise nikal");
        //     }

        //     if(true) {
        //         int a = 4;
        //         int b = 0;
        //         int c = a/b;
        //     }

        // } catch(InvalidAgeException e) {
        //     e.printStackTrace();
        // } catch(InsufficientBalanceException e) {
        //     e.printStackTrace();
        // } catch(Exception e) {
        //     e.printStackTrace();
        // } finally {
        //     System.out.println("inside finally");
        // }



        // try {
            A();
        // } catch(Exception e) {
        //     e.printStackTrace();
        // }
    }
}
