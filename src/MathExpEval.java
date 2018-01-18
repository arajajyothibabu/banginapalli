import java.text.DecimalFormat;
import java.util.*;

public class MathExpEval {

    private Stack<Character> operatorStack = new Stack<Character>();
    private Stack<Double> operandStack = new Stack<Double>();
    private ArrayList<Character> operators;
    private Map<Character, Integer> precedenceOfOperator;
    private final char SEMI_COLON = ';', OPEN_BRACE = '(', CLOSE_BRACE = ')',
            ADD = '+', SUBTRACT = '-', MULTIPLY = '*', DIVIDE = '/', EXPONENT = '^';
    DecimalFormat decimalFormatter =  new DecimalFormat("0.000");

    public MathExpEval() {
        precedenceOfOperator = new HashMap<Character, Integer>();
        precedenceOfOperator.put('(', 3);
        precedenceOfOperator.put(')', 3);
        precedenceOfOperator.put('^', 2);
        precedenceOfOperator.put('*', 1);
        precedenceOfOperator.put('/', 1);
        precedenceOfOperator.put('+', 0);
        precedenceOfOperator.put('-', 0);
        operators = new ArrayList<Character>(precedenceOfOperator.keySet());
    }

    private boolean isOperator(Character c) {
        return operators.indexOf(c) > -1;
    }

    private boolean isBrace(Character c) {
        return c.equals(OPEN_BRACE) || c.equals(CLOSE_BRACE);
    }

    private boolean isMorePrecedent(Character a, Character b){
        return precedenceOfOperator.get(a) > precedenceOfOperator.get(b);
    }

    private Double evaluate(Double a, Double b, Character operator){
        switch (operator){
            case EXPONENT: return Math.pow(b, a);
            case MULTIPLY: return a * b;
            case DIVIDE: return b / a;
            case ADD: return a + b;
            case SUBTRACT: return b - a;
            default: return a + b; //FIXME: figureout something else
        }
    }

    private void evalLastTwo(){
        if(!isBrace(operatorStack.peek())){
            operandStack.push(evaluate(operandStack.pop(), operandStack.pop(), operatorStack.pop()));
        }
    }

    public double eval(String expr){
        int length = expr.lastIndexOf(SEMI_COLON);
        boolean isNewOperandStart = true;
        Character c;
        for(int i = 0; i < length; i++){
            c = expr.charAt(i);
            if(isBrace(c)){
                isNewOperandStart = true;
                if(c.equals(OPEN_BRACE)){
                    operatorStack.push(c);
                }else{
                    while(!operatorStack.isEmpty() && !operatorStack.peek().equals(OPEN_BRACE)){
                        evalLastTwo();
                    }
                    if(!operatorStack.isEmpty()) {
                        operatorStack.pop(); //pop '('
                    }
                }
            }else if(isOperator(c)){
                isNewOperandStart = true;
                if(!operatorStack.isEmpty() && isMorePrecedent(operatorStack.peek(), c)){
                    evalLastTwo();
                }
                operatorStack.push(c);
            }else{
                Double operand = Double.parseDouble(c.toString());
                if(isNewOperandStart){
                    operandStack.push(operand);
                }else{
                    operandStack.push(operandStack.pop() * 10 + operand); //Typecasting
                }
                isNewOperandStart = false;
            }
        }
        while(!operatorStack.isEmpty()){
            evalLastTwo();
        }
        return operandStack.pop();
    }

    public void compute(String expr){
        System.out.println(decimalFormatter.format(eval(expr)));
    }

}
