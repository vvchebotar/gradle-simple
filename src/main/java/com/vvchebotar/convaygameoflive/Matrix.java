package com.vvchebotar.convaygameoflive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Matrix {

    private List<Node> elements;
    private int width;

    public void transform() {
        elements.forEach(Node::transform);
        elements.forEach(Node::flush);
    }
}
