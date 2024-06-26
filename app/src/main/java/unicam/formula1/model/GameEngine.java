package unicam.formula1.model;

import unicam.formula1.model.abstracts.*;
import unicam.formula1.utils.Printer;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Manages the logic and state of the Formula 1 simulation game, controlling the progression
 * of the game, the movement of players, and determining when the game should end.
 *
 * @param <T> the type of cell used in the game, extending {@link ICell}.
 */
public class GameEngine<T extends ICell<T>> implements IEngine<T> {

    private final List<Player<T>> players;
    private boolean raceOn;
    private final Random random;

    /**
     * Constructs a GameEngine with a list of players.
     *
     * @param players the list of players participating in the game.
     */
    public GameEngine(List<Player<T>> players) {
        this.players = players;
        this.raceOn = true;
        this.random = new Random();
    }

    /**
     * Executes a single round of the game, processing each player's move, updating positions,
     * and checking for any crashes or if a player has finished the race.
     */
    @Override
    public void play() {
        if (!isTerminated()) {
            players.forEach(player -> {
                T newPosition = random.nextBoolean() ? player.mainMove() : player.adjacentMove();
                player.setPosition(newPosition);
                Printer.printPlayerPosition(player);
                if (player.hasFinished()) {
                    Printer.printPlayerVictory(player);
                    raceOn = false;
                }
            });
            deletePlayers(players.stream().filter(Player::hasCrashed).toList());
        }
    }

    /**
     * Returns the list of players currently in the game.
     *
     * @return a list of players.
     */
    @Override
    public List<Player<T>> getPlayers() {
        return this.players;
    }

    /**
     * Determines whether the game has ended, either because the race condition is false
     * or there is only one or no player remaining.
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isTerminated() {
        return !this.raceOn || players.size() <= 1;
    }

    /**
     * Removes players from the game who have crashed or finished. This method also handles
     * notification of their elimination.
     *
     * @param playersToRemove a list of players to be removed from the game.
     */
    private void deletePlayers(List<Player<T>> playersToRemove) {
        this.players.removeAll(playersToRemove);
        playersToRemove.forEach(Printer::printPlayerElimination);
    }
}
