package unicam.formula1.model.abstracts;

/**
 * Defines the interface for players in the Formula 1 simulation game. This includes methods for managing
 * player position, movement, and status within the game environment.
 *
 * @param <T> the type of cell the player interacts with, extending {@link ICell}.
 */
public interface IPlayer<T extends ICell<T>> {

    /**
     * Retrieves the name of the player.
     *
     * @return A string representing the player's name.
     */
    String getName();

    /**
     * Gets the current position of the player on the track.
     *
     * @return The current cell where the player is located.
     */
    T getCurrentPosition();

    /**
     * Returns the last move made by the player.
     *
     * @return An {@link IMove} object representing the last move made by the player, or null if no moves have been made.
     */
    IMove<T> getLastMove();

    /**
     * Retrieves the track on which the player is racing.
     *
     * @return An {@link ITrack} object representing the track associated with the player.
     */
    ITrack<T> getTrack();

    /**
     * Sets the position of the player to a new cell.
     *
     * @param newPosition The new cell to which the player will move.
     */
    void setPosition(T newPosition);

    /**
     * Determines whether the player has crashed, based on the game's rules and the player's current cell.
     *
     * @return true if the player has crashed, false otherwise.
     */
    boolean hasCrashed();

    /**
     * Checks if the player has finished the race by reaching the finish line.
     *
     * @return true if the player has finished the race, false otherwise.
     */
    boolean hasFinished();

    /**
     * Calculates an adjacent move from the player's current position. This method is typically used
     * to determine possible small adjustments or evasive maneuvers adjacent to the main track path.
     *
     * @return An {@link ICell} object representing a cell adjacent to the current position, or null if no such move is valid.
     */
    ICell<T> adjacentMove();

    /**
     * Calculates the main move for the player based on their last movement and current position. This is generally
     * the primary method of progression on the track.
     *
     * @return An {@link ICell} object representing the cell to which the player will move next, under normal conditions.
     */
    ICell<T> mainMove();
}
