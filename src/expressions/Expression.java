package expressions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class PrefixExpression extends Expression {

    // Feel free to create other private/protected members, methods, or constructors.

    public PrefixExpression(String expression) {

        // Remember the super constructor
        // This constructor receives a String containing the expression text. It will invoke the constructor for class Expression, passing in the expression text.
        super(expression);

    }

    /**
     * //This method returns true if the first “token” in the tokenList is an operator,
     * AND the last two tokens are operands,
     * AND the number of operands in the expression is equal to the number of operators plus 1.
     * @return
     */
    public boolean isLegal() {
        if(tokens.size() >= 3){ //expecting at least 3 tokens [operand, operator1, operator2]
            String firstToken = tokens.get(0);
            String lastButOneToken = tokens.get(tokens.size() - 2);
            String lastToken = tokens.get(tokens.size() - 1);
            if(isOperator(firstToken) && isOperand(lastButOneToken) && isOperand(lastToken)){ //the first “token” in the tokenList is an operator,
                Integer operatorCount = 0;
                Integer operandCount = 0;
                for(String token : tokens){
                    if(isOperator(token)){
                        operatorCount++;
                    }
                    if(isOperand(token)){
                        operandCount++;
                    }
                }
                return operandCount == operatorCount + 1; //the number of operands in the expression is equal to the number of operators plus 1.
            }else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method uses a queue of Strings to evaluate the prefix expression.
     * o Ensure the expression is legal, if not Stop and throw an exception, using class ArithmeticException
     * o Enqueue all of the tokens in the tokenList into the queue
     * o if the # of tokens in the queue >= 3,
     * ▪ Dequeue the 1st 3 values. Into 3 String variables: ie: a, b, & c
     * ▪ else Stop and throw an exception, using class ArithmeticException
     * o While not done
     * ▪ If A is not an operator, and B & C are not operands, then we do not have a subexpression. So;
     * • Enqueue A back onto the queue, and swap values
     * • A =B
     * • B=C
     * • Dequeue a new value into C
     * • Repeat the previous steps until A is an operator and B & C operands
     * ▪ Once is we have a subexpression
     * • Convert the operands into values of type double
     * • Call the method evaluateSubexpression
     * • Enqueue the result back onto the queue.
     * o If the number of elements in the queue equals 1, we are “done”
     * o Else, dequeued 3 new values into a , b, & c and continue processing.
     * o When done (one value remaining on the queue) dequeue and return the result of the expression.
     *
     * */
    public double evaluate() {
        Queue<String> queue = new LinkedList<String>();
        if(isLegal()){
            for(String token : tokens){
                queue.add(token);
            }
            String a, b, c;
            while(queue.size() >= 3){
                a = queue.poll();
                b = queue.poll();
                c = queue.poll();
                if(!isOperator(a) || !isOperand(b) || !isOperand(c)){
                    queue.add(a);
                    a = b;
                    b = c;
                    c = queue.poll();
                }else{
                    String result = String.valueOf(evaluateSubExpression(getOperandValue(b), a, getOperandValue(c)));
                    queue.add(result);
                }
            }
            return getOperandValue(queue.poll());
        }else{
            throw new ArithmeticException();
        }
    }

    /**
     * Example implementation
     * @param args
     */
    public static void main(String args[]) {
        PrefixExpression prefixExpression = new PrefixExpression("* 2 4");
        System.out.println(prefixExpression.evaluate());
    }

}

public abstract class Expression {

    // Feel free to create other private/protected members, methods, or constructors.


    protected String expression;

    protected ArrayList<String> tokens;


    /**
     * receives a String containing the expression text, break the expressions into “tokens”
     * <p>
     * and store the resulting String array into the data member tokenList.
     *
     * @param expression
     */

    public Expression(String expression) {

        this.expression = expression;

        String[] tokenList = expression.split(" ");

        this.tokens = new ArrayList<String>();

        for (int i = 0; i < tokenList.length; i++) {

            tokens.add(tokenList[i]);

        }

    }


    /**
     * @return the expression string
     */

    public String getExpression() {

        return expression;

    }


    /**
     * @return the token list
     */

    public ArrayList<String> getTokens() {

        return tokens;

    }

    /**
     * to determine a string is numeric using regex
     * @param token
     * @return
     */
    protected static boolean isNumeric(String token) {
        return token != null && token.matches("[-+]?\\d*\\.?\\d+");
    }


    /**
     * determines if the String is a valid operand
     *
     * @param token
     * @return true if the String parameters is a valid operand
     */

    protected static boolean isOperand(String token) {

        return isNumeric(token);

        /*for (int i = 0; i < token.length() - 1; i++) {

            if (token.indexOf(i) != 0) {

                result = false;

            } else {

                result = true;

            }

        }*/

        //return result;

    }


    /**
     * determines if String token is an operator
     *
     * @param token
     * @return true if the String parameter contains one of “+”, “-“, “*”, “/” or “%”.
     */

    protected static boolean isOperator(String token) {

        if (token.equals("-") || token.equals("+") || token.equals("*") || token.equals("/") || token.equals("%")) {

            return true;

        } else

            return false;

    }


    /**
     * given a String containing a valid operand
     * <p>
     * this method returns the value of the operand as type double.
     *
     * @param token
     * @return Double operand
     * @throws NumberFormatException
     */

    protected static double getOperandValue(String token) throws NumberFormatException {

        if (!isOperand(token)) {

            throw new NumberFormatException();

        } else {


            return Double.parseDouble(token);

        }

    }


    /**
     * This method evaluates a subexpression as operand1 operator operand2
     * <p>
     * and returns the result as type double.
     *
     * @param operand1
     * @param operator
     * @param operand2
     * @return result
     */

    protected static double evaluateSubExpression(double operand1, String operator, double operand2) {

        if (operator.equals("-")) {

            return operand1 - operand2;

        } else if (operator.equals("+")) {

            return operand1 + operand2;

        } else if (operator.equals("*")) {

            return operand1 * operand2;


        } else if (operator.equals("/")) {

            return operand1 / operand2;

        } else {

            return operand1 % operand2;

        }

    }
}