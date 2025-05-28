package util;

import model.Board;
import model.GameStatusEnum;
import model.Space;
import util.BoardTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Board board;
    private static final int BOARD_LIMIT = 9;

    public static void main(String[] args) {

        final Map<String, String> positions = Stream.of(args)
                .map(s -> s.split(";"))
                .filter(arr -> arr.length == 2)
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

        while (true) {
            System.out.println("\nSelecione uma das opções a seguir:");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            int option = runUntilGetValidNumber(1, 8);

            switch (option) {
                case 1 -> startGame(positions);
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)) {
            System.out.println("O jogo já foi iniciado!");
            return;
        }

        List<List<Space>> spaces = new ArrayList<>();
        for (int row = 0; row < BOARD_LIMIT; row++) {
            List<Space> line = new ArrayList<>();
            for (int col = 0; col < BOARD_LIMIT; col++) {
                String key = col + "," + row;
                if (positions.containsKey(key)) {
                    int expected = Integer.parseInt(positions.get(key));
                    line.add(new Space(expected, true));
                } else {
                    line.add(new Space(0, false));
                }
            }
            spaces.add(line);
        }

        board = new Board(spaces);
        System.out.println("Jogo iniciado!");
        renderBoard();
    }

    private static void inputNumber() {
        if (isNull(board)) {
            System.out.println("Inicie o jogo primeiro!");
            return;
        }
        System.out.println("Digite a coluna (0 a 8): ");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Digite a linha (0 a 8): ");
        int row = runUntilGetValidNumber(0, 8);
        System.out.println("Digite o número (1 a 9): ");
        int value = runUntilGetValidNumber(1, 9);

        if (board.changeValue(col, row, value)) {
            System.out.println("Número inserido com sucesso!");
        } else {
            System.out.println("Não foi possível alterar o valor nessa posição (fixo ou inválido).");
        }
        renderBoard();
    }

    private static void removeNumber() {
        if (isNull(board)) {
            System.out.println("Inicie o jogo primeiro!");
            return;
        }
        System.out.println("Digite a coluna (0 a 8) para remover o número: ");
        int col = runUntilGetValidNumber(0, 8);
        System.out.println("Digite a linha (0 a 8) para remover o número: ");
        int row = runUntilGetValidNumber(0, 8);

        if (board.clearValue(col, row)) {
            System.out.println("Número removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o valor nessa posição (fixo ou inválido).");
        }
        renderBoard();
    }

    private static void showCurrentGame() {
        if (isNull(board)) {
            System.out.println("Nenhum jogo iniciado!");
            return;
        }
        renderBoard();
    }

    private static void showGameStatus() {
        if (isNull(board)) {
            System.out.println("Nenhum jogo iniciado!");
            return;
        }
        GameStatusEnum status = board.getStatus();
        System.out.println("Status do jogo: " + status.getLabel());
        System.out.println("Possui erros? " + (board.hasErrors() ? "Sim" : "Não"));
        System.out.println("Jogo finalizado? " + (board.gameIsFinished() ? "Sim" : "Não"));
    }

    private static void clearGame() {
        if (isNull(board)) {
            System.out.println("Nenhum jogo iniciado!");
            return;
        }
        board.reset();
        System.out.println("Jogo limpo!");
        renderBoard();
    }

    private static void finishGame() {
        if (isNull(board)) {
            System.out.println("Nenhum jogo iniciado!");
            return;
        }
        if (board.gameIsFinished()) {
            System.out.println("Parabéns! Você finalizou o jogo corretamente!");
        } else {
            System.out.println("O jogo não está finalizado corretamente ainda.");
        }
    }

    private static int runUntilGetValidNumber(int min, int max) {
        while (true) {
            try {
                System.out.print("Digite um número entre " + min + " e " + max + ": ");
                int num = Integer.parseInt(scanner.nextLine());
                if (num >= min && num <= max) {
                    return num;
                } else {
                    System.out.println("Número fora do intervalo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida, tente novamente.");
            }
        }
    }

    private static void renderBoard() {
        if (board == null) {
            System.out.println("O jogo ainda não foi iniciado");
            return;
        }

        Object[] values = new Object[81]; 
        int index = 0;

        for (int row = 0; row < BOARD_LIMIT; row++) {
            for (int col = 0; col < BOARD_LIMIT; col++) {
                Space space = board.getSpaces().get(col).get(row);
                Integer actual = space.getActual();
                values[index++] = actual == null ? " " : actual.toString();
            }
        }

        System.out.println(String.format(BoardTemplate.BOARD_TEMPLATE, values));
    }

}
