package unicam.formula1.model.abstracts;

import unicam.formula1.model.Cell;

/**
 * Provides an abstraction for movements within the game, encapsulating the start and end positions,
 * as well as the displacement in terms of x and y coordinates. This interface allows for validation
 * of movements to ensure they comply with the game's rules and track layout.
 *
 * @param <T> the type of cell involved in the movement, extending {@link ICell}.
 */
public interface IMove<T extends ICell<T>> {

    /**
     * Retrieves the starting cell of the movement.
     *
     * @return The starting cell of the move, where the movement originates.
     */
    T getStart();

    /**
     * Retrieves the ending cell of the movement.
     *
     * @return The cell where the movement concludes.
     */
    T getEnd();

    /**
     * Calculates the horizontal displacement caused by the move.
     *
     * @return The difference in the x-coordinate between the end and start cells.
     */
    int getDeltaX();

    /**
     * Calculates the vertical displacement caused by the move.
     *
     * @return The difference in the y-coordinate between the end and start cells.
     */
    int getDeltaY();

    /**
     * Validates the move within the context of the specified track. This method checks if the move
     * is allowed based on the track's conditions and rules, such as barriers, track boundaries,
     * or specific track rules.
     *
     * @param track The track on which the move is being made, typically involving specific layout and rules.
     * @return true if the move is valid within the given track context; false otherwise.
     */
    boolean isValid(ITrack<Cell> track);
}

