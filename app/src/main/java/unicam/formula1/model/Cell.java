package unicam.formula1.model;

import unicam.formula1.model.abstracts.CellType;
import unicam.formula1.model.abstracts.ICell;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single cell on the Formula 1 race track. Each cell is defined by its position,
 * type, and its neighboring cells. Cells are the basic units of the track layout and are crucial
 * for defining the race path and the interaction of players with the track.
 */
public class Cell implements ICell<Cell> {

    private final int x;
    private final int y;
    private final CellType cellType;
    private List<Cell> neighbors;

    /**
     * Constructs a Cell with specified coordinates and type.
     *
     * @param x the x-coordinate of the cell on the track grid.
     * @param y the y-coordinate of the cell on the track grid.
     * @param cellType the type of the cell, which determines its properties and interaction within the game.
     */
    public Cell(int x, int y, CellType cellType) {
        this.x = x;
        this.y = y;
        this.cellType = cellType;
        this.neighbors = new ArrayList<>();
    }

    /**
     * Gets the x-coordinate of the cell.
     *
     * @return the x-coordinate.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the cell.
     *
     * @return the y-coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Returns the type of the cell as defined by the CellType enum.
     * This type is crucial for determining how players interact with the cell.
     *
     * @return the cell type.
     */
    @Override
    public CellType getCellType() {
        return cellType;
    }

    /**
     * Provides a list of neighboring cells. Neighbors are typically adjacent cells on the track
     * that a player can move to from this cell.
     *
     * @return a list of neighboring cells.
     */
    @Override
    public List<Cell> getNeighbors() {
        return neighbors;
    }

    /**
     * Adds a neighboring cell to this cell's list of neighbors. This method is used to set up
     * the track's connectivity, allowing for player movement and interaction.
     *
     * @param neighbor the cell to add as a neighbor.
     */
    @Override
    public void addNeighbor(Cell neighbor) {
        this.neighbors.add(neighbor);
    }
}
