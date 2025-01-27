import GameSaver.TicTacToe;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTests {

    @Test
    public void TestEmptyConstructor(){
        var game = new TicTacToe();
        assertNotNull(game.getBoard(), "Board should not be null");
        assertEquals(game.getBoard().length, 3, "Board should have 3 rows");
        for (var row : game.getBoard()){
            assertEquals(row.length, 3, "Each row should have 3 columns");
        }
        for (var row : game.getBoard()){
            for (var cell : row){
                assertEquals(cell, TicTacToe.Piece.EMPTY, "Each cell should be empty on a new board");
            }
        }
        assertTrue(game.getIsXTurn(), "X should start the game");
    }

    @Test
    public void TestCopyConstructor(){
        TicTacToe.Piece[][] board = new TicTacToe.Piece[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = i + j % 2 == 0 ? TicTacToe.Piece.X : i % 2 == 0 ? TicTacToe.Piece.O : TicTacToe.Piece.EMPTY;
            }
        }
        var game = new TicTacToe(board, false);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                assertEquals(board[i][j], game.getBoard()[i][j], "Board should be copied correctly");
            }
        }
        assertFalse(game.getIsXTurn(), "Game start not set correctly");
        assertEquals(game.getWinner(), TicTacToe.Piece.O, "Winner not set on Load");
    }

    @Test
    public void TestToString(){
        TicTacToe.Piece[][] board = new TicTacToe.Piece[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = i + j % 2 == 0 ? TicTacToe.Piece.X : i % 2 == 0 ? TicTacToe.Piece.O : TicTacToe.Piece.EMPTY;
            }
        }
        var game = new TicTacToe(board, false);
        var expected = "X O X \nE E E \nO O O \nfalse\nO\n";
        try {
            assertEquals(expected, game.toString(), "ToString not implemented correctly");
        } catch (AssertionFailedError e){
            expected = "X O X\nE E E\nO O O\nfalse\nO\n";
            assertEquals(expected, game.toString(), "ToString not implemented correctly");
        }
    }

    @Test
    public void TestCheckForWinner(){
        TicTacToe game = new TicTacToe();
        assertFalse(game.checkForWinner(), "No winner on empty board");
        var board = game.getBoard();
        board[0][0] = TicTacToe.Piece.X;
        board[0][1] = TicTacToe.Piece.X;
        board[0][2] = TicTacToe.Piece.X;
        game = new TicTacToe(board, false);
        assertTrue(game.checkForWinner(), "Winner on first row not detected");
        assertEquals(game.getWinner(), TicTacToe.Piece.X, "Winner not set correctly");
        board[0][0] = TicTacToe.Piece.O;
        board[1][0] = TicTacToe.Piece.O;
        board[2][0] = TicTacToe.Piece.O;
        game = new TicTacToe(board, false);
        assertTrue(game.checkForWinner(), "Winner on first column not detected");
        assertEquals(game.getWinner(), TicTacToe.Piece.O, "Winner not set correctly");
        board[0][0] = TicTacToe.Piece.X;
        board[1][1] = TicTacToe.Piece.X;
        board[2][2] = TicTacToe.Piece.X;
        board[0][2] = TicTacToe.Piece.O;
        game = new TicTacToe(board, false);
        assertTrue(game.checkForWinner(), "Winner on diagonal not detected");
        assertEquals(game.getWinner(), TicTacToe.Piece.X, "Winner not set correctly");
        board[0][2] = TicTacToe.Piece.O;
        board[1][1] = TicTacToe.Piece.O;
        board[2][0] = TicTacToe.Piece.O;
        game = new TicTacToe(board, false);
        assertTrue(game.checkForWinner(), "Winner on other diagonal not detected");
        assertEquals(game.getWinner(), TicTacToe.Piece.O, "Winner not set correctly");
    }

    @Test
    public void TestGameSaving() throws IOException {
        var game = new TicTacToe();
        var board = game.getBoard();
        board[0][0] = TicTacToe.Piece.X;
        board[0][1] = TicTacToe.Piece.O;
        board[1][2] = TicTacToe.Piece.X;
        board[2][0] = TicTacToe.Piece.O;
        board[2][2] = TicTacToe.Piece.X;
        game = new TicTacToe(board, false);
        GameSaver.GameSaver.SaveGame(game, "testSave");
        assertTrue(Files.exists(Path.of(GameSaver.GameSaver.savePath + "testSave")), "File not created after saving");
        var loadedGame = GameSaver.GameSaver.LoadGame("testSave");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                assertEquals(board[i][j], loadedGame.getBoard()[i][j], "Board not loaded correctly");
            }
        }
        assertEquals(game.getIsXTurn(), loadedGame.getIsXTurn(), "Turn not loaded correctly");
        Files.deleteIfExists(Path.of(GameSaver.GameSaver.savePath + "testSave"));
    }

    @Test
    public void TestGamePlayLoop1(){
        var game = new TicTacToe();
        var board = game.getBoard();
        var input = new ByteArrayInputStream("1 1\n1 2\n2 2\n2 3\n3 3\n".getBytes());
        System.setIn(input);
        game.playGame();
        assertTrue(game.checkForWinner(), "Winner not detected after game");
        assertEquals(game.getWinner(), TicTacToe.Piece.X, "Winner not set correctly");
    }

    @Test
    public void TestGamePlayLoop2() throws IOException {
        var game = new TicTacToe();
        var board = game.getBoard();
        var input = new ByteArrayInputStream("1 1\n1 2\n2 2\n2 3\nsave TestFile\n".getBytes());
        System.setIn(input);
        game.playGame();
        assertTrue(Files.exists(Path.of(GameSaver.GameSaver.savePath + "TestFile")), "File not created after saving");
        var loadedGame = GameSaver.GameSaver.LoadGame("TestFile");
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                assertEquals(board[i][j], loadedGame.getBoard()[i][j], "Board not loaded correctly");
            }
        }
        assertEquals(game.getIsXTurn(), loadedGame.getIsXTurn(), "Turn not loaded correctly");
        Files.deleteIfExists(Path.of(GameSaver.GameSaver.savePath + "TestFile"));
    }
}
