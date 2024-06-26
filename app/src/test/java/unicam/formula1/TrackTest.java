package unicam.formula1;

import org.junit.jupiter.api.Test;
import unicam.formula1.model.Cell;
import unicam.formula1.model.Track;
import unicam.formula1.model.abstracts.CellType;

import static org.junit.jupiter.api.Assertions.*;

public class TrackTest {

    @Test
    void testGetCell() {
        Track track = new Track(10, 10);
        Cell cell = new Cell(5, 5, CellType.TRACK);
        track.addCell(cell);
        assertEquals(cell, track.getCell(5, 5), "The cell should be correctly retrieved from the track.");
    }

    @Test
    void testGetCellOutOfBounds() {
        Track track = new Track(10, 10);
        assertNull(track.getCell(11, 10), "Accessing out-of-bounds should return null.");
    }
}

