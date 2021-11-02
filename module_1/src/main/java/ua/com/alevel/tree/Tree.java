package ua.com.alevel.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tree {

    private Node root;

    public void maxDeepOfTree() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("The first node will be root");
        System.out.println("Enter the row with nodes of tree (example: 42-25-65-250):");
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String onlyNumberInRow = inputRow.replaceAll("[^0-9-]", "");
        String[] arrayOfNodes;
        arrayOfNodes = onlyNumberInRow.split("-");
        for (String nodeString : arrayOfNodes) {
            if (!nodeString.isEmpty()) {
                insert(Integer.parseInt(nodeString));
            }
        }
        System.out.println("Max deep is: " + maxDeep(root));
    }

    private int maxDeep(Node root) {
        if (root != null) {
            return 1 + Math.max(maxDeep(root.leftChild), maxDeep(root.rightChild));
        } else {
            return 0;
        }
    }

    private void insert(int id) {
        Node newNode = new Node();
        newNode.item = id;
        if (root == null)
            root = newNode;
        else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (id < current.item) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }
}
