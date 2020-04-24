package com.vvchebotar.convaygameoflive;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

import static com.vvchebotar.convaygameoflive.TestClass.ALIVE;
import static com.vvchebotar.convaygameoflive.TestClass.DIED;

@Getter
@Setter
@Builder
public class Node {

    private Node leftTop;
    private Node left;
    private Node leftBottom;
    private Node top;
    private Node bottom;
    private Node rightTop;
    private Node right;
    private Node rightBottom;
    private int value;
    private int nextValue;
    private boolean lastElementInRow;

    public void transform() {
        int countOfAliveNearbyElements = getCountOfAliveNearbyElements();
        if (value == DIED) {
            nextValue = countOfAliveNearbyElements == 3 ? ALIVE : DIED;
        } else {
            nextValue = countOfAliveNearbyElements < 2 || countOfAliveNearbyElements > 3 ? DIED : ALIVE;
        }
    }

    public void flush() {
        value = nextValue;
    }

    public void linkNodes(Node leftTop, Node left, Node leftBottom, Node top, Node bottom, Node rightTop, Node right, Node rightBottom) {
        this.leftTop = leftTop;
        this.left = left;
        this.leftBottom = leftBottom;
        this.top = top;
        this.bottom = bottom;
        this.rightTop = rightTop;
        this.right = right;
        this.rightBottom = rightBottom;
    }

    private int getCountOfAliveNearbyElements() {
        return
                Optional.ofNullable(leftBottom).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(left).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(leftTop).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(bottom).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(top).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(rightBottom).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(right).map(Node::getValue).orElse(0) +
                        Optional.ofNullable(rightTop).map(Node::getValue).orElse(0);
    }
}
