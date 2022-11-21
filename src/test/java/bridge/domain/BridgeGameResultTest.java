package bridge.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.api.Assumptions.*;

public class BridgeGameResultTest {
    private static final String UP_SIDE_SHAPE = "U";
    private static final String DOWN_SIDE_SHAPE = "D";
    private GameResult gameResult;

    @BeforeEach
    void setUp() {
        gameResult = new GameResult();
        gameResult.putMovingResult();
    }

    @DisplayName("다리 칸 이동 결과를 저장한다.")
    @ParameterizedTest
    @CsvSource({"D,O", "U,O", "D,X", "U,X"})
    void putMovingResult(String shape, String movingResult) {
        gameResult.putMovingResult(shape, movingResult);
        assumingThat(shape.equals(UP_SIDE_SHAPE), () -> {
            assertThat(gameResult.getBridgeByShape(shape))
                    .containsExactly("O", "O", " ", " ", " ", movingResult);
        });
        assumingThat(shape.equals(DOWN_SIDE_SHAPE), () -> {
            assertThat(gameResult.getBridgeByShape(shape))
                    .containsExactly(" ", " ", "O", "O", "O",  movingResult);
        });
    }



    static class GameResult extends BridgeGameResult {
        public void putMovingResult() {
            super.getBridgeByShape(UP_SIDE_SHAPE).addAll(List.of("O", "O", " ", " ", " "));
            super.getBridgeByShape(DOWN_SIDE_SHAPE).addAll(List.of(" ", " ", "O", "O", "O"));
            System.out.println(super.getBridgeByShape(UP_SIDE_SHAPE));
            System.out.println(super.getBridgeByShape(DOWN_SIDE_SHAPE));
        }
    }
}