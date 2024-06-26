package unicam.formula1.model;

import unicam.formula1.model.abstracts.CellType;
import unicam.formula1.model.abstracts.ICell;
import unicam.formula1.model.abstracts.IMove;
import unicam.formula1.model.abstracts.ITrack;

/**
 * Represents a move in the Formula 1 simulation game, encapsulating the starting and ending cells
 * of the move. This class implements logic to determine the displacement caused by the move and
 * to validate the move based on the rules of the track.
 *
 * @param <T> the specific type of cell involved in the move, extending {@link ICell}.
 */
public class Move<T extends ICell<T>> implements IMove<T> {

    private final T start;
    private final T end;

    /**
     * Constructs a Move with specified start and end cells.
     *
     * @param start The starting cell of the move.
     * @param end The ending cell of the move.
     */
    public Move(T start, T end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the starting cell of the move.
     *
     * @return the starting cell.
     */
    public T getStart() {
        return start;
    }

    /**
     * Gets the ending cell of the move.
     *
     * @return the ending cell.
     */
    public T getEnd() {
        return end;
    }

    /**
     * Calculates the horizontal displacement caused by the move.
     *
     * @return the difference in the x-coordinate between the end and start cells.
     */
    public int getDeltaX() {
        return end.getX() - start.getX();
    }

    /**
     * Calculates the vertical displacement caused by the move.
     *
     * @return the difference in the y-coordinate between the end and start cells.
     */
    public int getDeltaY() {
        return end.getY() - start.getY();
    }

    /**
     * Determines if the move is valid according to the rules of the track. A move is considered valid if
     * the end cell is not null, and it is not an 'OFF_TRACK' or 'OIL' type cell.
     *
     * @param track The track on which the move is being made, used to verify the validity of the end cell.
     * @return true if the move is valid, false otherwise.
     */
    public boolean isValid(ITrack<Cell> track) {
        Cell cell = track.getCell(end.getX(), end.getY());
        return cell != null && cell.getCellType() != CellType.OFF_TRACK && cell.getCellType() != CellType.OIL;
    }

}
