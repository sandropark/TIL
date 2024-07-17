package com.sandro.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }


    @DisplayName("공을 굴린다.")
    @Test
    void canRoll() throws Exception {
        game.roll(0);
    }

    private void rollMany(int pins, int rolls) {
        for (int i = 0; i < rolls; i++) {
            game.roll(pins);
        }
    }

    @DisplayName("한 번도 핀을 쓰러트리지 못하는 경우 0점을 반환한다.")
    @Test
    void gutterGame() throws Exception {
        rollMany(0, 20);
        assertThat(game.getScore()).isEqualTo(0);
    }

    @DisplayName("20번 공을 굴려 핀 20개를 쓰러트린 경우 20점을 반환한다.")
    @Test
    void allOnes() throws Exception {
        rollMany(1, 20);
        assertThat(game.getScore()).isEqualTo(20);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    @DisplayName("스페어의 경우 다음 한 번의 roll 점수를 추가로 얻는다.")
    @Test
    void oneSpare() throws Exception {
        rollSpare();
        game.roll(3);
        rollMany(0, 17);
        assertThat(game.getScore()).isEqualTo(16);
    }

    @DisplayName("스트라이크의 경우 다음 2번의 roll 점수를 추가로 얻는다.")
    @Test
    void oneStrike() throws Exception {
        rollStrike();
        game.roll(5);
        game.roll(3);
        rollMany(0, 16);
        assertThat(game.getScore()).isEqualTo(26);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @DisplayName("퍼펙트게임의 점수는 300이다.")
    @Test
    void perfectGame() throws Exception {
        rollPerfect();
        assertThat(game.getScore()).isEqualTo(300);
    }

    private void rollPerfect() {
        rollMany(10, 12);
    }

}
