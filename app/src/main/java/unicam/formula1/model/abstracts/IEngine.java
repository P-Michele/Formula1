package unicam.formula1.model.abstracts;

import java.util.List;

/**
 * Defines the game engine interface for managing and executing the game logic for a Formula 1 simulation.
 * This interface handles the game flow, player actions, and determines when the game ends.
 *
 * @param <T> the type of cell the game engine manages, ensuring that all players operate on the same grid type.
 */
public interface IEngine<T extends ICell<T>> {

    /**
     * Executes one cycle or turn of the game. This method should contain the logic to process player moves,
     * check for game-ending conditions, and update the game state accordingly.
     */
    void play();

    /**
     * Retrieves the list of players currently participating in the game.
     * This list is essential for game operations, allowing for iteration over all players for updates and checks.
     *
     * @return a list of {@link Player} objects representing all the players in the game.
     */
    List<Player<T>> getPlayers();

    /**
     * Determines if the game has reached a condition where it should be terminated.
     * This could be due to all players finishing, a player winning, or other end-game conditions being met.
     *
     * @return true if the game should no longer continue, false otherwise.
     */
    boolean isTerminated();
}
