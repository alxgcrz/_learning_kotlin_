package web;

import com.example.kotlin.Singleton;

public class CallSingletonInstance {

    public static void main(String args[]) {
        Singleton.INSTANCE.printSomething();
        Singleton.printSomethingJava();
    }

}
