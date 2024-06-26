package unicam.formula1.model;

import unicam.formula1.model.abstracts.CellType;
import unicam.formula1.model.abstracts.ITrack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the racetrack in the Formula 1 simulation game, encapsulating the dimensions of the track
 * and managing the cells that constitute the track's layout.
 */
public class Track implements ITrack<Cell> {

    private final List<Cell> cells;
    private final int width;
    private final int height;

    /**
     * Constructs a Track with specified dimensions.
     *
     * @param width the width of the track in number of cells.
     * @param height the height of the track in number of cells.
     */
    public Track(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new ArrayList<>();
    }

    /**
     * Adds a cell to the track. This method is primarily used during the initialization phase to populate the track.
     *
     * @param cell the cell to be added to the track.
     */
    @Override
    public void addCell(Cell cell) {
        cells.add(cell);
    }

    /**
     * Retrieves a cell from the track at the specified coordinates.
     *
     * @param x the x-coordinate of the cell to retrieve.
     * @param y the y-coordinate of the cell to retrieve.
     * @return the cell at the given coordinates, or null if no cell exists at those coordinates.
     */
    @Override
    public Cell getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;  // Return null for coordinates outside the track boundaries.
        }
        return cells.stream()
                .filter(cell -> cell.getX() == x && cell.getY() == y)
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns the width of the track.
     *
     * @return the width of the track in number of cells.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the track.
     *
     * @return the height of the track in number of cells.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Retrieves a list of cells designated as start positions on the track.
     *
     * @return a list of cells marked as starting positions.
     */
    @Override
    public List<Cell> getStartPositions() {
        return cells.stream()
                .filter(cell -> cell.getCellType() == CellType.START)
                .toList();
    }

    /**
     * Converts the list of cells into a two-dimensional array, reflecting the layout of the track.
     * This can be useful for visualization or more structured access.
     *
     * @return a 2D array of cells representing the track.
     */
    @Override
    public Cell[][] getCellsAsMatrix() {
        Cell[][] matrix = new Cell[this.height][this.width];
        for (Cell cell : cells) {
            if (cell != null) {
                matrix[cell.getY()][cell.getX()] = cell;
            }
        }
        return matrix;
    }
}
