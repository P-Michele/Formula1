package unicam.formula1.model.abstracts;

import unicam.formula1.model.Cell;
import unicam.formula1.model.Move;

/**
 * Abstract class representing a player in the Formula 1 simulation game.
 * This class provides the basic framework for player operations including movement tracking, position updating,
 * and status checks such as whether the player has crashed or finished the race.
 *
 * @param <T> the specific type of cell the player interacts with, extending {@link ICell}.
 */
public abstract class Player<T extends ICell<T>> implements IPlayer<T> {

    private final String name;
    private T currentPosition;
    private Move<T> lastMove;
    private final ITrack<T> track;

    /**
     * Constructs a new Player with the specified name, starting position, and track.
     *
     * @param name The name of the player.
     * @param startPosition The starting cell position of the player on the track.
     * @param track The track on which the player is racing.
     */
    public Player(String name, T startPosition, ITrack<T> track) {
        this.name = name;
        this.currentPosition = startPosition;
        this.track = track;
        this.lastMove = null;
    }

    /**
     * Returns the name of the player.
     *
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the current position of the player on the track.
     *
     * @return The current cell that the player occupies.
     */
    public T getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Returns the last move made by the player.
     *
     * @return The last move object, or null if no moves have been made yet.
     */
    public Move<T> getLastMove() {
        return lastMove;
    }

    /**
     * Provides the track associated with the player.
     *
     * @return The track on which the player is racing.
     */
    public ITrack<T> getTrack() {
        return track;
    }

    /**
     * Sets the player's position to a new cell and updates the last move made to this new position.
     *
     * @param newPosition The new position to set for the player.
     */
    public void setPosition(T newPosition) {
        this.lastMove = new Move<>(this.currentPosition, newPosition);
        this.currentPosition = newPosition;
    }

    /**
     * Determines whether the player has crashed based on the current cell type.
     * A player is considered to have crashed if they are on a cell type that is either OFF_TRACK or OIL.
     *
     * @return true if the player has crashed, false otherwise.
     */
    public boolean hasCrashed() {
        return this.currentPosition == null ||
                this.currentPosition.getCellType().equals(CellType.OFF_TRACK) ||
                this.currentPosition.getCellType().equals(CellType.OIL);
    }

    /**
     * Checks if the player has finished the race by reaching a cell of type FINISH.
     *
     * @return true if the player has finished the race, false otherwise.
     */
    public boolean hasFinished() {
        if (this.currentPosition == null) {
            return false;
        }
        return this.currentPosition.getCellType().equals(CellType.FINISH);
    }

    /**
     * Abstract method to calculate the main move from the player's current position.
     * Subclasses must define this method to implement specific movement logic.
     *
     * @return The new cell position representing the player's main move.
     */
    public abstract T mainMove();

    /**
     * Abstract method to calculate an adjacent move from the player's current position.
     * Subclasses must define this method to implement specific tactical or evasive movement logic.
     *
     * @return The new cell position representing the player's adjacent move.
     */
    public abstract T adjacentMove();
}
