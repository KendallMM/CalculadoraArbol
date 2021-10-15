package Tree;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Esta clase es para crear un servidor con interfaz gráfica simple
 *
 * @author Kendall Marín Muñoz
 */
public class Server extends javax.swing.JFrame {
    ServerSocket ss;
    HashMap clientColl = new HashMap();
    /**
     * Crea un nuevo Server Socket
     */
    public Server() {
        try {
            initComponents();
            ss = new ServerSocket(8080);
            this.estado.setText("Iniciado");
            new ClientAccept().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Esta clase es para aceptar a diferentes clientes
     */
    class ClientAccept extends Thread {
        public void run() {
            while (true) {
                try {
                    Socket s = ss.accept();
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (clientColl.containsKey(i)) {
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("Ya estas registrado!");
                    } else {
                        clientColl.put(i, s);
                        msgBox.append(i + " Joined!\n");
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("");
                        new MsgRead(s, i).start();
                        new PrepareClientList().start();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * Esta clase es para leer y mostrar los diferentes mensajes enviados por los
     * clientes, además detecta si se solicita un monto
     */
    class MsgRead extends Thread {

        Socket s;
        String ID;

        /**
         *
         * @param s el socket del cliente que envía el mensaje
         * @param ID el nombre de usuario del cliente que envía el mensaje
         */
        MsgRead(Socket s, String ID) {
            this.s = s;
            this.ID = ID;
        }

        /**
         * Este método se encarga de recibir los paquetes de datos (mensajes)
         * y los envía a los clientes correspondientes
         */
        public void run() {
            while (!clientColl.isEmpty()) {
                try {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    String monto = i;
                    List<String> calc = Arrays.asList(monto.split(","));
                    if (calc.get(0).equals("monto")){ //Este if separa una solicitud de monto de un mensaje normal
                        new ExpressionTree(calc.get(1)).start();
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    msgBox.append(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                        
                    }
                    else if (i.equals("mkoihgteazdcvgyhujb096785542AXTY")) {
                        clientColl.remove(ID);
                        msgBox.append(ID + ": salió! \n");
                        new PrepareClientList().start();
                        Set<String> k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    msgBox.append(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    } else if (i.contains("#4344554@@@@@67667@@")) {
                        i = i.substring(20);
                        StringTokenizer st = new StringTokenizer(i, ":");
                        String id = st.nextToken();
                        i = st.nextToken();
                        try {
                            new DataOutputStream(((Socket) clientColl.get(id)).getOutputStream()).writeUTF("< " + ID + " para tí > " + i);
                        } catch (Exception ex) {
                            clientColl.remove(id);
                            msgBox.append(id + ": salió!");
                            new PrepareClientList().start();
                        }
                    } else {
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + ID + " para todos > " + i);
                                } catch (Exception ex) {
                                    clientColl.remove(key);
                                    msgBox.append(key + ": salió!");
                                    new PrepareClientList().start();
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    public class ExpressionTree extends Thread{

	static int decider;
	static char checker;
	static Node root = null;
        String expressionString;
        ExpressionTree(String expressionString) {
            this.expressionString = expressionString;
        }
	public void run(){
                if (expressionString == expressionString){
                    System.out.print("HOLA");
                }
		// Convert it to a character array and call it 'infix'
		char[] infix = expressionString.toCharArray();
		
		// pass the infix array in to the postfixer which is a
		// function to create the postfix form of the expression
		char[] postfix = postfixer(infix);
		System.out.println("The infix form is: " + expressionString);
		System.out.print("The postfix form is: ");
		
		// Print the postfix array on the screen
		for (int i = 0; i < postfix.length && postfix[i] != '_'; i++) {
			
		}
                System.out.println(postfix);
		// Create tree out of the postfix form
		Stack<Node> stack = new Stack<Node>();

		for (int i = 0; i < postfix.length; i++) {
			Node node = new Node(postfix[i]);
			switch (postfix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				stack.push(node);
                                
                                System.out.print("aaa"+node.left);
				System.out.println("Pushed");
				break;
			case '+':case '-':case '/':case '*':
                                System.out.print(postfix[i]);
				System.out.println("Pushed");
				node.right = stack.pop();
				node.left = stack.pop();
				stack.push(node);
				break;
			default:
			}
		}

		root = stack.pop();
                BTreePrinter.printNode(root);
		System.out.println("rl"+root.left.data);
                System.out.println("rr"+root.right.data);

		// Solve the Values!
		Stack<NodeInt> intStack = new Stack<NodeInt>();

		for (int i = 0; i < postfix.length; i++) {
			NodeInt nodeInt = new NodeInt(0);
			switch (postfix[i]) { //(20)4+
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				nodeInt = new NodeInt(Character.getNumericValue(postfix[i]));
				intStack.push(nodeInt);
				System.out.println(nodeInt.data);
				break;
			case '+':
				System.out.println("Pushed");
				double x = intStack.pop().data;
				double y = intStack.pop().data;
				
				nodeInt.data = y + x;
				
				intStack.push(nodeInt);
				
				break;

			case '-':
				System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y - x;
				
				intStack.push(nodeInt);
				
				break;

			case '/':
				System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y / x;
				
				intStack.push(nodeInt);
				
				break;

			case '*':
				System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y * x;
				
				intStack.push(nodeInt);
				
				break;
                                
                                
                        
				
			default:
			}
		}
		System.out.println(intStack.pop().data);

	}

	// function to convert postfix to infix
	public static char[] postfixer(char[] infix) {

		// create a new stack of character type
		Stack<Character> stack = new Stack<Character>();

		// create an array 'result' which is the same length as of infix and
		// type char
		char[] result = new char[infix.length];

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

				break;

			case '(':
				stack.add(infix[i]);

				break;

			case ')':
				while ((!stack.empty()) && (stack.peek() == '(')) {
					stack.pop();
				}

				if ((!stack.empty())) {
					result[count] = stack.pop();
					count++;
				}
				
				break;
					
			case '+':case '*':case '-':case '/':
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
                
	}

		
	public static void checkPrecedence(char a, char b) {

		switch (a) {

		case '/':
			switch (b) {
			case '/':decider = 0;break;
			case '*':decider = 0;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '*':
			switch (b) {
			case '/':decider = 0;break;
			case '*':decider = 0;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '+':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 0;break;
			case '-':decider = 0;break;
			default:
			}
			break;
		case '-':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 0;break;
			case '-':decider = 0;break;
			default:
			}
			break;
		default:
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

class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(Node<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Node<T>> newNodes = new ArrayList<Node<T>>();
        for (Node<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(Node<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}


    /**
     * Esta clase es para crear una lista con los clientes conectados
     * ademas permite implementar un método para enviar los mensajes a un
     * cliente en concreto
     */
    class PrepareClientList extends Thread {
        /**
         * Este método se encarga de añadir los clientes conectados a una lista
         * proyectada en la interfaz de los clientes
         */
        public void run() {
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);
                    } catch (Exception ex) {
                        clientColl.remove(key);
                        msgBox.append(key + ": salió!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        estado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        msgBox.setColumns(20);
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel1.setText("Servidor:");

        estado.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        estado.setText("................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(estado, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estado))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args los argumentos de la línea de comando
     */
    public static void main(String args[]) {
        /* Configura el aspecto y sensación del Nimbus */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Crea y proyecta la interfaz */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgBox;
    // End of variables declaration//GEN-END:variables
}
