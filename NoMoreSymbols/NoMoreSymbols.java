package NoMoreSymbols;

import java.util.*;

public class NoMoreSymbols {

    // Maps to convert words to numbers and operations
    static Map<String, Integer> wordToDigit = new HashMap<>();
    static Map<String, String> operations = new HashMap<>();

    static {
        // Initialize word-to-digit mapping
        wordToDigit.put("zero", 0);
        wordToDigit.put("one", 1);
        wordToDigit.put("two", 2);
        wordToDigit.put("three", 3);
        wordToDigit.put("four", 4);
        wordToDigit.put("five", 5);
        wordToDigit.put("six", 6);
        wordToDigit.put("seven", 7);
        wordToDigit.put("eight", 8);
        wordToDigit.put("nine", 9);

        // Initialize operation mappings
        operations.put("add", "+");
        operations.put("sub", "-");
        operations.put("mul", "*");
        operations.put("rem", "%");
        operations.put("pow", "^");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        String result = evaluateExpression(input);
        System.out.println(result);
    }

    public static String evaluateExpression(String input) {
        String[] words = input.split(" ");
        List<Object> expression = new ArrayList<>();

        // Step 1: Parse words into operations and operands
        for (String word : words) {
            if (operations.containsKey(word)) {
                expression.add(operations.get(word)); // Convert operation word to symbol
            } else {
                Integer number = convertWordToNumber(word);
                if (number == null) {
                    return "expression evaluation stopped invalid words present";
                }
                expression.add(number);
            }
        }

        // Step 2: Check if the expression is valid and evaluate using prefix evaluation
        try {
            return String.valueOf(evaluatePrefixExpression(expression));
        } catch (Exception e) {
            return "expression is not complete or invalid";
        }
    }

    // Convert word-based number to integer (e.g., "oneconecone" -> 111)
    public static Integer convertWordToNumber(String word) {
        String[] digits = word.split("c");
        int number = 0;

        for (String digit : digits) {
            if (!wordToDigit.containsKey(digit)) {
                return null; // Invalid word encountered
            }
            number = number * 10 + wordToDigit.get(digit);
        }
        return number;
    }

    // Evaluates a prefix expression using a stack
    public static int evaluatePrefixExpression(List<Object> expression) throws Exception {
        Stack<Integer> stack = new Stack<>();

        // Traverse the list from right to left
        for (int i = expression.size() - 1; i >= 0; i--) {
            Object token = expression.get(i);

            if (token instanceof Integer) {
                // If the token is an operand (number), push it to the stack
                stack.push((Integer) token);
            } else if (token instanceof String) {
                // If the token is an operator, pop two operands and apply the operation
                if (stack.size() < 2) {
                    throw new Exception("Invalid expression"); // Not enough operands
                }
                int operand1 = stack.pop();
                int operand2 = stack.pop();
                int result = performOperation((String) token, operand1, operand2);
                stack.push(result); // Push the result back to the stack
            }
        }

        // After processing all tokens, the stack should contain exactly one element, the result
        if (stack.size() != 1) {
            throw new Exception("Invalid expression");
        }
        return stack.pop();
    }

    // Perform a single arithmetic operation
    public static int performOperation(String operation, int operand1, int operand2) {
        switch (operation) {
            case "+": return operand1 + operand2;
            case "-": return operand1 - operand2;
            case "*": return operand1 * operand2;
            case "%": return operand1 % operand2;
            case "^": return (int) Math.pow(operand1, operand2);
            default: throw new IllegalArgumentException("Invalid operation");
        }
    }
}
