package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    public Clorus() {
        this(1); // calls above constructor with energy level 1
    }

    public Color color() {
        return color(r,g,b);
    }


    @Override
    public void move() {
        energy = energy - 0.03;
        if(energy < 0) { energy = 0; }
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        // to do
        energy = energy/2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy = energy - 0.01;
        if(energy < 0) { energy = 0; }
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plips = new ArrayDeque<>();
        for (Direction dir : neighbors.keySet()) {
            if(neighbors.get(dir).name().equals("empty")) {
                emptyNeighbors.addLast(dir);
            }
            if(neighbors.get(dir).name().equals("plip")) {
                plips.addLast(dir);
            }
        }
        if(emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if(!plips.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plips));
        } else if(energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
