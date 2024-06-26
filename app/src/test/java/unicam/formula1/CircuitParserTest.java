package unicam.formula1;

import org.junit.jupiter.api.Test;
import unicam.formula1.model.Cell;
import unicam.formula1.model.CircuitParser;
import unicam.formula1.model.Track;
import unicam.formula1.model.abstracts.CellType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static org.junit.jupiter.api.Assertions.*;

public class CircuitParserTest {

    Track track;

    @Test
    void testParseFile() throws IOException {
        // Carica il file dalle risorse
        InputStream is = getClass().getClassLoader().getResourceAsStream("track.txt");
        assertNotNull(is, "Il file track.txt non è stato trovato nelle risorse");

        // Leggi il contenuto del file
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        CircuitParser parser = new CircuitParser();
        this.track = parser.parseFile(reader);

        // Verifica dimensioni del tracciato
        assertEquals(13, track.getWidth(), "Width should be 13.");
        assertEquals(11, track.getHeight(), "Height should be 11.");

       this.printTrack();

        // Verifica i tipi di celle in posizioni specifiche
        assertCellType(track, 0, 0, CellType.OFF_TRACK);
        assertCellType(track, 1, 4, CellType.START);
        assertCellType(track, 1, 6, CellType.FINISH);
        assertCellType(track, 6, 3, CellType.OIL);
        assertCellType(track, 1, 1, CellType.TRACK);
        assertCellType(track, 5, 1, CellType.TRACK);
    }

    private void assertCellType(Track track, int x, int y, CellType expected) {
        Cell cell = track.getCell(x, y);
        assertNotNull(cell, "Cell should not be null at " + x + ", " + y);
        assertEquals(expected, cell.getCellType(), "Incorrect cell type at " + x + ", " + y);
    }

    private void printTrack() {
        Cell[][] matrix = this.track.getCellsAsMatrix();
        for (int y = 0; y < this.track.getHeight(); y++) {
            for (int x = 0; x < this.track.getWidth(); x++) {
                Cell cell = matrix[y][x];
                if (cell != null) {
                    System.out.print(cell.getCellType().getSymbol());
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
