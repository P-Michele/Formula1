package unicam.formula1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unicam.formula1.model.BotPlayer;
import unicam.formula1.model.Cell;
import unicam.formula1.model.Track;
import unicam.formula1.model.abstracts.CellType;

import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    private Track track;
    private BotPlayer bot;
    private Cell start;
    private Cell end;

    @BeforeEach
    void setUp() {
        track = new Track(5, 5);
        start = new Cell(2, 2, CellType.START);
        end = new Cell(3,2, CellType.TRACK);
        start.addNeighbor(end);
        track.addCell(start);
        bot = new BotPlayer("Bot1", start, track);
    }

    @Test
    void testBotInitialPosition() {
        assertEquals(start, bot.getCurrentPosition(), "Bot should start at the initial position.");
    }

    @Test
    void testBotMove() {
        bot.setPosition(bot.adjacentMove());
        assertNotNull(bot.getCurrentPosition(), "Bot should have moved to a new position.");
        assertNotEquals(start, bot.getCurrentPosition(), "Bot should not be at the start position after moving.");
    }
}

