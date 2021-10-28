package Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Esta clase es para crear un árbol de expresión a partir de una expresión matemática de forma infix
 * @author Kendall Marín Muñoz
 * @author Carolina Rodríguez Hall
 * @version 1.0
 */

public class ExpressionTree extends Thread {
    private double res;
    static int decider;
    static char checker;
    static Nodon root = null;
    public String expressionString;
    
    /**
     * Crea un nuevo árbol de expresión a partir de la expresión infix
     * @param expressionString la expresión en forma infix
     */
    ExpressionTree(String expressionString) {
        this.expressionString = expressionString;
    }
    /**
    * Este método utiza otra clase para convertir la expresión en forma infix a su forma postfix,
    * luego crea el árbol de expresión a partir de un string array creado del la string postfix
    */
    public void run() {
        // Se pasa la expresión infix en forma de stack, por el método infixToPostfix,
        // el cual se encarga de convertir la expresión a su forma postfix
        StackImpl a = new StackImpl();
        String s1 = a.infixToPostfix(expressionString);
        System.out.println("La expresión infix: " + expressionString);
        System.out.println("La expresión postfix: " + s1);
        System.out.println(s1);
        // Se crea el árbol a partir de la expresión en forma postfix
        Stack<Nodon> stack = new Stack<>();
        List<String> calc = Arrays.asList(s1.split(""));
        System.out.println("Lista = " + calc);
        int e = 0;
        while (e < calc.size()) {
            switch (calc.get(e)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> {
                    String nodo = "";
                    while (calc.get(e).equals("1") || calc.get(e).equals("2") || calc.get(e).equals("3") || calc.get(e).equals("4") || calc.get(e).equals("5") || calc.get(e).equals("6") || calc.get(e).equals("7") || calc.get(e).equals("8") || calc.get(e).equals("9") || calc.get(e).equals("0")) {
                        nodo += calc.get(e);
                        System.out.println("El nodo es: " + nodo);
                        e++;
                    }       Nodon node = new Nodon(nodo);
                    stack.push(node);
                }
                case " " -> e++;
                case "(", ")" -> e++;
                case "+", "-", "*", "/", "%" -> {
                    Nodon node = new Nodon(calc.get(e));
                    System.out.println("El nodo es: " + node.data);
                    node.right = stack.pop();
                    node.left = stack.pop();
                    stack.push(node);
                    e++;
                }
                default -> {
                }
            }
        }
        root = stack.pop();
        BTreePrinter.printNode(root);
        System.out.println("root: " + root.data);
        System.out.println("rl: " + root.left.data);
        System.out.println("rr: " + root.right.data);

        // Se resuelven las operaciones a partir del árbol de expresión
        Stack<NodeInt> intStack = new Stack<>();
        double x;
        double y;
        int i = 0;
        while (i < calc.size()) {
            NodeInt nodeInt = new NodeInt(0);
            switch (calc.get(i)) {
                case "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" -> {
                    String nodo = "";
                    while (calc.get(i).equals("1") || calc.get(i).equals("2") || calc.get(i).equals("3") || calc.get(i).equals("4") || calc.get(i).equals("5") || calc.get(i).equals("6") || calc.get(i).equals("7") || calc.get(i).equals("8") || calc.get(i).equals("9") || calc.get(i).equals("0")) {
                        nodo += calc.get(i);
                        i++;
                    }   nodeInt = new NodeInt(Integer.parseInt(nodo));
                    intStack.push(nodeInt);
                }
                case "(", ")" -> i++;
                case "+" -> {
                    x = intStack.pop().data;
                    y = intStack.pop().data;
                    nodeInt.data = y + x;
                    intStack.push(nodeInt);
                    i++;
                }
                case "-" -> {
                    x = intStack.pop().data;
                    y = intStack.pop().data;
                    nodeInt.data = y - x;
                    intStack.push(nodeInt);
                    i++;
                }
                case "/" -> {
                    x = intStack.pop().data;
                    y = intStack.pop().data;
                    nodeInt.data = y / x;
                    intStack.push(nodeInt);
                    i++;
                }
                case "*" -> {
                    x = intStack.pop().data;
                    y = intStack.pop().data;
                    nodeInt.data = y * x;
                    intStack.push(nodeInt);
                    i++;
                }
                case "%" -> {
                    x = intStack.pop().data;
                    y = intStack.pop().data;
                    nodeInt.data = (y / 100) * x;
                    intStack.push(nodeInt);
                    i++;
                }
                case " " -> i++;
                default -> {
                }
            }
        }
        this.res = intStack.pop().data;
        System.out.println(this.res);
    }
    public double getRes(){
        run();
        return res;
    }

    // function to convert infix to postfix
    public static void checkPrecedence(char a, char b) {

        switch (a) {

            case '/' -> {
                switch (b) {
                    case '/':
                        decider = 0;
                        break;
                    case '*':
                        decider = 0;
                        break;
                    case '+':
                        decider = 1;
                        break;
                    case '-':
                        decider = 1;
                        break;
                    default:
                }
            }
            case '%' -> {
                switch (b) {
                    case '/':
                        decider = 0;
                        break;
                    case '*':
                        decider = 0;
                        break;
                    case '+':
                        decider = 1;
                        break;
                    case '-':
                        decider = 1;
                        break;
                    default:
                }
            }
            case '*' -> {
                switch (b) {
                    case '/':
                        decider = 0;
                        break;
                    case '*':
                        decider = 0;
                        break;
                    case '+':
                        decider = 1;
                        break;
                    case '-':
                        decider = 1;
                        break;
                    default:
                }
            }
            case '+' -> {
                switch (b) {
                    case '/':
                        decider = 2;
                        break;
                    case '*':
                        decider = 2;
                        break;
                    case '+':
                        decider = 0;
                        break;
                    case '-':
                        decider = 0;
                        break;
                    default:
                }
            }
            case '-' -> {
                switch (b) {
                    case '/':
                        decider = 2;
                        break;
                    case '*':
                        decider = 2;
                        break;
                    case '+':
                        decider = 0;
                        break;
                    case '-':
                        decider = 0;
                        break;
                    default:
                }
            }
            default -> {
            }
        }
    }

    class NodeInt {

        double data;

        NodeInt(double data) {
            this.data = data;
        }
    }

    class Node<T extends Comparable<?>> {

        char data;
        Node left;
        Node right;

        Node(char data) {
            this.data = data;
        }
    }

    class Nodon<T extends Comparable<?>> {

        String data;
        Nodon left;
        Nodon right;

        Nodon(String data) {
            this.data = data;
        }
    }

    class BTreePrinter {

        public static <T extends Comparable<?>> void printNode(Nodon<T> root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Nodon<T>> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes)) {
                return;
            }

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Nodon<T>> newNodes = new ArrayList<>();
            nodes.stream().map(node -> {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }
                return node;
            }).forEachOrdered(_item -> {
                BTreePrinter.printWhitespaces(betweenSpaces);
            });
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null) {
                        System.out.print("/");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null) {
                        System.out.print("\\");
                    } else {
                        BTreePrinter.printWhitespaces(1);
                    }

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++) {
                System.out.print(" ");
            }
        }

        private static <T extends Comparable<?>> int maxLevel(Nodon<T> node) {
            if (node == null) {
                return 0;
            }

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            return list.stream().noneMatch(object -> (object != null));
        }
    }
}
