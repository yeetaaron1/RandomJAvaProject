package Util.Math;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Scanner;

/**
 * A simple command-line calculator that evaluates mathematical expressions.
 */
public class Calculator {

    private final ScriptEngine engine;

    public Calculator() {
        // Ensure the ScriptEngine is initialized
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript"); // Using JavaScript engine for eval
    }

    /**
     * Starts the calculator application.
     */
    public void startCalculator(Scanner scanner) {
        System.out.println("Welcome to the Calculator! Type 'exit' to quit.");
        String input;

        while (true) {
            System.out.print("Enter an expression: ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting calculator. Goodbye!");
                break; // Exit the loop and terminate the program
            }

            try {
                double result = evaluateExpression(input);
                System.out.printf("Result: %.2f\n", result);
            } catch (ScriptException e) {
                System.out.println("Invalid expression. Please try again.");
            }
        }
    }

    /**
     * Evaluates a mathematical expression.
     *
     * @param expression the expression to evaluate
     * @return the result of the evaluated expression
     * @throws ScriptException if the expression is invalid
     */
    private double evaluateExpression(String expression) throws ScriptException {
        return (double) engine.eval(expression); // Evaluate the expression using the JavaScript engine
    }
}
