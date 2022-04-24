package RPNStacker.src;

import stacker.rpn.lexer.Token;
import stacker.rpn.lexer.TokenType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws Exception {
        
        try{
            File RPN = new File ("./Calc1.stk");
            Scanner reader = new Scanner(RPN);

            ArrayList<Token> tokenList = new ArrayList<Token>();
            Token aux = new Token(TokenType.EOF, "");
            boolean notAChar = false;


            while (reader.hasNext()){
                if(reader.hasNextInt()){
                    aux = new Token(TokenType.NUM, String.valueOf(reader.nextInt()));
                    tokenList.add(aux); 
                } else {
                    String op = String.valueOf(reader.next().charAt(0));
                    switch (op){
                        case "+":
                            aux = new Token(TokenType.PLUS, op);
                            break;
                        case "-":
                            aux = new Token(TokenType.MINUS, op);
                            break;
                        case "/":
                            aux = new Token(TokenType.SLASH, op);
                            break;
                        case "*":
                            aux = new Token(TokenType.STAR, op);
                            break;
                        case "%":
                            aux = new Token(TokenType.MOD, op);
                            break;    
                        default:
                            notAChar = true;
                            throw new Exception("Error: '"+ op + "' Is not an Operator.");
                    }
                    if(!notAChar){
                        tokenList.add(aux);
                    }
                }
            }
            reader.close();
            if(!notAChar){
                System.out.println(tokenList.toString());
                System.out.println(execution(tokenList));
            }
        }catch (FileNotFoundException f){
            System.out.println("The execution was stopped due to an error"); 
            f.printStackTrace();
        }
        
    }

    public static int execution(ArrayList<Token> tokenList){
        Stack<Integer> auxInteger = new Stack<Integer>();

        for (Token x: tokenList){
            if(x.type == TokenType.NUM){
                auxInteger.push(Integer.parseInt(x.lexeme));
            }else{
                int variable1 = auxInteger.pop();
                int variable2 = auxInteger.pop();
                
                switch(x.type){
                    case PLUS:
                        auxInteger.push(variable1+variable2);
                        break;
                    case MINUS:
                        auxInteger.push(variable1-variable2);
                        break;
                    case SLASH:
                        auxInteger.push(variable1/variable2);
                        break;
                    case STAR:
                        auxInteger.push(variable1*variable2);
                        break;
                    case MOD:
                        auxInteger.push(variable1%variable2);
                        break;
                    default:
                        break;
                }
            }
        }
        return auxInteger.pop();
    }

}