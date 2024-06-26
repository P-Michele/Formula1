package unicam.formula1;

import org.junit.jupiter.api.Test;
import unicam.formula1.model.Cell;
import unicam.formula1.model.Move;
import unicam.formula1.model.Track;
import unicam.formula1.model.abstracts.CellType;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void testMoveValidity() {
        Track track = new Track(10, 10);
        Cell start = new Cell(3, 3, CellType.TRACK);
        Cell end = new Cell(4, 3, CellType.TRACK);
        track.addCell(start);
        track.addCell(end);
        Move<Cell> move = new Move<>(start, end);
        assertTrue(move.isValid(track), "The move should be valid when moving to a TRACK type cell.");
    }

    @Test
    void testInvalidMove() {
        Track track = new Track(10, 10);
        Cell start = new Cell(3, 3, CellType.TRACK);
        Cell end = new Cell(3, 4, CellType.OFF_TRACK);
        track.addCell(start);
        track.addCell(end);
        Move<Cell> move = new Move<>(start, end);
        assertFalse(move.isValid(track), "The move should be invalid when moving to an EMPTY type cell.");
    }
}

