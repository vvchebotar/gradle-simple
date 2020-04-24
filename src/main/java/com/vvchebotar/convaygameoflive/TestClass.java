package com.vvchebotar.convaygameoflive;

import com.google.common.collect.Lists;

import java.util.List;

public class TestClass {

    public static final int ALIVE = 1;
    public static final int DIED = 0;

    public static void main(String[] args) {
        int[][] array = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
        };
        Matrix matrix = convert(array);
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);

        array = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
        };
        matrix = convert(array);
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);

        array = new int[][]{
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
        };
        matrix = convert(array);
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);
        matrix.transform();
        printMatrix(matrix);
    }

    private static Matrix convert(int[][] array) {
        Node[][] nodeArray = convertFromIntArrayToNodeArray(array);
        List<Node> nodes = linkNearbyNodesAndConvertToList(array, nodeArray);
        return new Matrix(nodes, array.length);
    }

    private static Node[][] convertFromIntArrayToNodeArray(int[][] array) {
        Node[][] nodeArray = new Node[array.length][array[0].length];
        for (int row = 0; row < array.length; row++) {
            final int width = array[row].length;
            for (int col = 0; col < width; col++) {
                nodeArray[row][col] = Node.builder()
                        .value(array[row][col])
                        .lastElementInRow(col == width - 1)
                        .build();
            }
        }
        return nodeArray;
    }

    private static List<Node> linkNearbyNodesAndConvertToList(int[][] array, Node[][] nodeArray) {
        List<Node> nodes = Lists.newArrayList();
        for (int row = 0; row < array.length; row++) {
            final int width = array[row].length;
            for (int col = 0; col < width; col++) {
                final Node node = nodeArray[row][col];
                node.linkNodes(
                        getLeftTopElement(nodeArray, row, col),
                        getLeftElement(nodeArray, row, col),
                        getLeftBottomElement(nodeArray, row, col),
                        getTopElement(nodeArray, row, col),
                        getBottomElement(nodeArray, row, col),
                        getRightTopElement(nodeArray, row, col),
                        getRightElement(nodeArray, row, col),
                        getRightBottomElement(nodeArray, row, col)
                );
                nodes.add(node);
            }
        }
        return nodes;
    }

    private static Node getLeftTopElement(Node[][] array, int row, int col) {
        return getElement(array, row - 1, col - 1);
    }

    private static Node getLeftElement(Node[][] array, int row, int col) {
        return getElement(array, row, col - 1);
    }

    private static Node getLeftBottomElement(Node[][] array, int row, int col) {
        return getElement(array, row + 1, col - 1);
    }

    private static Node getTopElement(Node[][] array, int row, int col) {
        return getElement(array, row - 1, col);
    }

    private static Node getBottomElement(Node[][] array, int row, int col) {
        return getElement(array, row + 1, col);
    }

    private static Node getRightTopElement(Node[][] array, int row, int col) {
        return getElement(array, row - 1, col + 1);
    }

    private static Node getRightElement(Node[][] array, int row, int col) {
        return getElement(array, row, col + 1);
    }

    private static Node getRightBottomElement(Node[][] array, int row, int col) {
        return getElement(array, row + 1, col + 1);
    }

    private static Node getElement(Node[][] array, int row, int col) {
        try {
            return array[row][col];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    private static void printMatrix(Matrix matrix) {
        System.out.println();
        final List<Node> elements = matrix.getElements();
        for (Node node : elements) {
            System.out.print(node.getValue());
            if (node.isLastElementInRow()) {
                System.out.println();
            }
        }
    }
}
