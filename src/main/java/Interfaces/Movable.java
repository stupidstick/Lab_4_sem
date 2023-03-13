package Interfaces;

import objects.Fish;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Movable {
    public void move(Map<Integer, Integer> moveDirection);
}
