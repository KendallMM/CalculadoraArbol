/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tree;

import java.util.Stack;

class StackImpl {
  
  public String infixToPostfix(String s) {
    Stack<Character> st = new Stack<Character>();
    String postfix = "";
    char ch[] = s.toCharArray();
    
    for(char c: ch) {
      if(c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')') {
        postfix = postfix + c;
      } else if (c == '(') {
        postfix = postfix + c;
        st.push(c);
      } else if (c == ')') {
        postfix = postfix + c;
        while(!st.isEmpty()) {
          char t = st.pop();
          if(t != '(') {
            postfix = postfix + t;
          } else {
            break;
          }
        }
      } else if(c == '+' ||c == '-' ||c == '*' ||c == '/') {
        postfix = postfix + ' ';  
        if(st.isEmpty()) {
          st.push(c);
        } else {
          while(!st.isEmpty()) {
            char t = st.pop();
            if(t == '(') {
              st.push(t);
              break;
            } else if(t == '+' || t == '-' || t == '*' || t == '/') {
              if(getPriority(t) <  getPriority(c)) {
                st.push(t);
                break;
              } else {
                postfix = postfix + t;
              }
            }
          }
          st.push(c);
        }
      }
    }
    while(!st.isEmpty()) {
      postfix = postfix + st.pop();
    }
    return postfix;
  }
  
  public int getPriority(char c) {
    if(c == '+' || c == '-') {
      return 1;
    } else {
      return 2;
    } 
  }
  
}

public class Postfix {

  public static void main(String[] args) {
  }

}
/*public static char[] postfixer(char[] infix) {

		// create a new stack of character type
		Stack<Character> stack = new Stack<Character>();

		// create an array 'result' which is the same length as of infix and
		// type char
                char[] result = new char[infix.length+5];
                
		// initialize the value of the result array '_'
		for (int i = 0; i < result.length; i++) {
			result[i] = '_';
		}

		// set count as the counter for the operations on result
		// increment after every use
		int count = 0;

		for (int i = 0; i < infix.length; i++) {

			// System.out.println(result);

			switch (infix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				result[count] = infix[i];
				count++;
                                stack.add(' ');

				break;

			case '(':
                                result[count] = infix[i];
				count++;
				stack.add(' ');

				break;

			case ')':
                                result[count] = infix[i];
				count++;
				while ((!stack.empty()) && (stack.peek() == '(')) {
					stack.pop();
				}

				if ((!stack.empty())) {
					result[count] = stack.pop();
					count++;
				}
				
				break;
					
			case '+':case '*':case '-':case '/':
                                stack.pop();
				while ((!stack.empty()) && (stack.peek() == '(')) {
					stack.pop();
				}
				
				if ((!stack.empty())) {
					checker = stack.peek();

					// if 0, same. if 1, more. if 2, less.
					checkPrecedence(infix[i], checker);

					if (decider == 1) {
						stack.add(infix[i]);
					} else {
						result[count] = stack.pop();
						count++;
						stack.add(infix[i]);
					}
				
				} else {
					stack.add(infix[i]);
				}

				break;

			default:
				System.out.println("Invalid Data");
			}

		}

		while ((!stack.empty())) {
			result[count] = stack.pop();
			count++;
		}
		return result; 
                
	}*/