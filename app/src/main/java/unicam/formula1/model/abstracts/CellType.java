package unicam.formula1.model.abstracts;

/**
 * Represents the different types of cells that can exist on a race track in the Formula 1 game.
 * Each cell type is associated with a specific symbol that represents it visually.
 */
public enum CellType {
    /**
     * Cell type representing a track where players can drive.
     */
    TRACK('.'),

    /**
     * Cell type representing the starting position on the track.
     */
    START('S'),

    /**
     * Cell type representing the finish line on the track.
     */
    FINISH('F'),

    /**
     * Cell type representing areas off the track where driving is not allowed.
     */
    OFF_TRACK('#'),

    /**
     * Cell type representing areas with oil spills, which may affect the player's movement or control.
     */
    OIL('O');

    // Symbol representing the cell type visually
    private final char symbol;

    /**
     * Constructor for the cell type enum.
     * @param symbol The character symbol that visually represents the cell type.
     */
    CellType(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns the symbol associated with the cell type.
     * @return The character symbol of this cell type.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Returns the {@link CellType} corresponding to the given symbol.
     * If no matching cell type is found, it defaults to OFF_TRACK.
     *
     * @param symbol The character symbol to lookup the cell type.
     * @return The {@link CellType} associated with the specified symbol, or OFF_TRACK if not found.
     */
    public static CellType fromSymbol(char symbol) {
        for (CellType type : values()) {
            if (type.symbol == symbol) {
                return type;
            }
        }
        return OFF_TRACK;
    }
}
