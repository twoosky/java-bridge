package bridge.domain.value;

import static bridge.utils.ErrorMessages.ERROR_INVALID_GAME_COMMAND;

public class GameCommand {
    private static final String RETRY = "R";
    private static final String END = "Q";
    private final String gameCommand;

    public GameCommand(String gameCommand) {
        validate(gameCommand);
        this.gameCommand = gameCommand;
    }

    public void validate(String gameCommand) {
        validateGameCommand(gameCommand);
    }

    private void validateGameCommand(String gameCommand) {
        if (!isGameCommand(gameCommand)) {
            throw new IllegalArgumentException(ERROR_INVALID_GAME_COMMAND);
        }
    }

    private boolean isGameCommand(String gameCommand) {
        return gameCommand.equals(RETRY) || gameCommand.equals(END);
    }

    public boolean isRetry() {
        return gameCommand.equals(RETRY);
    }
}