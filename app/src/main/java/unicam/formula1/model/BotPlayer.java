package unicam.formula1.model;

import unicam.formula1.model.abstracts.CellType;
import unicam.formula1.model.abstracts.ITrack;
import unicam.formula1.model.abstracts.Player;
import java.util.List;
import java.util.Random;

/**
 * Represents a bot player in the Formula 1 simulation game.
 * This class implements automatic movement decisions for the bot, using a combination of random choices.
 */
public class BotPlayer extends Player<Cell> {

    private final Random random;

    /**
     * Constructs a new BotPlayer with a specified name, starting position, and associated track.
     *
     * @param name the name of the bot player.
     * @param startPosition the starting position of the bot on the track.
     * @param track the track on which the bot will race.
     */
    public BotPlayer(String name, Cell startPosition, ITrack<Cell> track) {
        super(name, startPosition, track);
        this.random = new Random();
    }

    /**
     * Determines an adjacent move for the bot. This method includes a random element to simulate
     * potential errors or unexpected moves, reflecting realistic bot behavior.
     *
     * @return a {@link Cell} representing the bot's next move. If a crash is randomly chosen,
     * returns a cell that represents going off-track.
     */
    @Override
    public Cell adjacentMove() {
        List<Cell> neighbors = getCurrentPosition().getNeighbors();
        List<Cell> safeNeighbors = neighbors.stream()
                .filter(n -> n.getCellType().equals(CellType.TRACK) || n.getCellType().equals(CellType.FINISH))
                .toList();
        // Random chance of making a move that results in a crash
        if (random.nextInt(40) == 0)
            return new Cell(-1, -1, CellType.OFF_TRACK);

        // Return a randomly chosen safe neighbor if no crash occurs
        return safeNeighbors.get(random.nextInt(safeNeighbors.size()));
    }

    /**
     * Calculates the main move for the bot based on its last movement and the current track conditions.
     * If the calculated move is outside the track bounds or not viable, it falls back to an adjacent move.
     *
     * @return a {@link Cell} representing the bot's next main move.
     */
    @Override
    public Cell mainMove() {
        if (getLastMove() == null) {
            return adjacentMove();
        }
        int mainX = getCurrentPosition().getX() + getLastMove().getDeltaX();
        int mainY = getCurrentPosition().getY() + getLastMove().getDeltaY();

        // Check if the move stays within the track bounds
        if (mainX < 0 || mainY < 0 || mainX >= getTrack().getWidth() || mainY >= getTrack().getHeight()) {
            return adjacentMove();
        } else {
            Cell potentialCell = getTrack().getCell(mainX, mainY);
            if (potentialCell != null && potentialCell.getCellType().equals(CellType.TRACK)) {
                return potentialCell;
            }
        }
        return adjacentMove();
    }
}
