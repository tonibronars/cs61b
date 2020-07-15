package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(10);
        Clorus c_new = (Clorus) c.replicate();
        assertNotEquals(c, c_new);
        assertEquals(5,c.energy(),0.01);
        assertEquals(5,c_new.energy(),0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(10);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Plip());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Sees a plip; attack.
        c = new Clorus(10);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Plip());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);

        // Sees multiple plips; attack one at random.
        c = new Clorus(10);
        HashMap<Direction, Occupant> manyPlips = new HashMap<Direction, Occupant>();
        manyPlips.put(Direction.TOP, new Empty());
        manyPlips.put(Direction.BOTTOM, new Plip());
        manyPlips.put(Direction.LEFT, new Plip());
        manyPlips.put(Direction.RIGHT, new Plip());

        actual = c.chooseAction(manyPlips);
        assertEquals(Action.ActionType.ATTACK, actual.type);


        // Energy >= 1; replicate towards only empty space.
        c = new Clorus(10);
        HashMap<Direction, Occupant> oneEmpty = new HashMap<Direction, Occupant>();
        oneEmpty.put(Direction.TOP, new Impassible());
        oneEmpty.put(Direction.BOTTOM, new Impassible());
        oneEmpty.put(Direction.LEFT, new Empty());
        oneEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(oneEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);
        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space at random.
        c = new Clorus(10);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        assertEquals(Action.ActionType.REPLICATE, actual.type);


        // Energy < 1; move to only empty place.
        c = new Clorus(.99);

        actual = c.chooseAction(oneEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.LEFT);
        assertEquals(expected, actual);


        // Energy < 1; move to an empty place at random.
        c = new Clorus(.99);

        actual = c.chooseAction(allEmpty);
        assertEquals(Action.ActionType.MOVE, actual.type);
    }
}
