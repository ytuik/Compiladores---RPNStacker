package RPNstacker.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class Main {

    public static void main(String[] args) {
        Stack<Integer> inputStack = new Stack<Integer>();
        
        try{
            File RPN = new File ("./Calc1.stk");
            Scanner reader = new Scanner(RPN);

            while (reader.hasNextLine()){
                if(reader.hasNextInt()){
                    int num = reader.nextInt();
                    inputStack.push(num); 
                } else {
                    char op = reader.next().charAt(0);
                    inputStack.push(execution(inputStack,op));
                }
            }
            reader.close();
        }catch (FileNotFoundException f){
            System.out.println("The execution was stopped due to an error"); 
            f.printStackTrace();
        }
        //inputStack.forEach(System.out::println);
        System.out.print("The RPN will result in: "+ inputStack.pop());
        
    }

    public static int execution(Stack<Integer> x, char op){
        int variable1 = x.pop();
        int variable2 = x.pop();

        switch(op){
            case '+':
                return(variable1+variable2);
            case '-':
                return(variable1-variable2);
            case '/':
                return(variable1/variable2);
            case '*':
                return(variable1*variable2);
            case '%':
                return(variable1%variable2);
            default:
                return 0;
        }
    }

}