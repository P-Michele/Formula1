package unicam.formula1.controller;

import unicam.formula1.model.*;
import unicam.formula1.model.abstracts.IEngine;
import unicam.formula1.model.abstracts.ITrack;
import unicam.formula1.model.abstracts.Player;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the initialization and control of the Formula 1 simulation game.
 * This controller sets up the game environment, including the track and players, and handles game progression.
 */
public class GameController {

    private GameEngine<Cell> gameEngine;
    private ITrack<Cell> track;

    /**
     * Returns the game engine associated with the game.
     *
     * @return the current game engine instance.
     */
    public IEngine<Cell> getGameEngine() {
        return gameEngine;
    }

    /**
     * Retrieves the list of players participating in the game from the game engine.
     *
     * @return a list of players.
     */
    public List<Player<Cell>> getPlayers() {
        return this.gameEngine.getPlayers();
    }

    /**
     * Gets the track currently being used in the game.
     *
     * @return the track instance.
     */
    public ITrack<Cell> getTrack() {
        return track;
    }

    /**
     * Initializes the game by parsing the track configuration, setting up the track,
     * and initializing bots as players based on the start positions defined in the track.
     *
     * @throws IOException if there is an issue reading the track configuration file.
     */
    public void initialize() throws IOException {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("track.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            CircuitParser parser = new CircuitParser();
            this.track = parser.parseFile(reader);
            List<Player<Cell>> players = new ArrayList<>();
            initializeBots(players, track, track.getStartPositions().size());
            this.gameEngine = new GameEngine<>(players);
        } catch (IOException e) {
            throw new IOException("Failed to initialize the game due to an input/output error.", e);
        }
    }

    /**
     * Initializes bot players and assigns them to start positions on the track.
     *
     * @param players the list of players to be filled with newly created bots.
     * @param track the track from which start positions are taken.
     * @param numBots the number of bots to initialize, typically equal to the number of start positions.
     */
    private static void initializeBots(List<Player<Cell>> players, ITrack<Cell> track, int numBots) {
        List<Cell> startPositions = track.getStartPositions();
        for (int i = 0; i < numBots && i < startPositions.size(); i++) {
            String botName = "Bot " + (i + 1);
            BotPlayer bot = new BotPlayer(botName, startPositions.get(i), track);
            players.add(bot);
        }
    }
}
