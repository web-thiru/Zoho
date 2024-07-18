package Zoho;

import java.util.*;

public class Bomberman {

  private static int length;
  public static String matrix[][];

  public static int currentPos[] = new int[2];

  public static List<int[]> bombMatrix = new ArrayList<>();

  public static boolean gameOver = false;
  public static boolean gameWin = false;

  public static void createMatrix(int length) {
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        if (
          i == 0 ||
          j == 0 ||
          i == length - 1 ||
          j == length - 1 ||
          (i % 2 == 0 && j % 2 == 0)
        ) matrix[i][j] = "* "; else matrix[i][j] = "  ";
      }
    }
  }

  public static void print() {
    String[] letters = new String[length];
    for (int i = 0; i < length; i++) {
      letters[i] = (char) ((int) 'A' + i) + "";
    }
    System.out.print("  "); //[0,0]
    for (String i : letters) System.out.print(i + " "); //first row
    System.out.println();
    for (int i = 0; i < length; i++) {
      System.out.print(letters[i] + " ");
      for (int j = 0; j < length; j++) {
        System.out.print(matrix[i][j]);
      }
      System.out.println();
    }
  }

  public static boolean checkIsValid(String pos) {
    int i = (int) pos.charAt(0) - 'A';
    int j = (int) pos.charAt(1) - 'A';
    return matrix[i][j].equals("  ");
  }

  public static boolean isValidRadius(int i, int j, int radius) {
    if (
      i - radius >= 0 &&
      j - radius >= 0 &&
      i + radius < length &&
      j + radius < length
    ) return true;
    return false;
  }

  public static void move(String movement) {
    int i = currentPos[0];
    int j = currentPos[1];
    if (movement.equals("UP")) {
      if ((i - 1) < 0 || !matrix[i - 1][j].equals("  ")) {
        if (matrix[i - 1][j].equals("E ")) {
          System.out.println("You Died......");
          gameOver = true;
          return;
        } else if (matrix[i - 1][j].equals("K ")) {
          System.out.println("You win...");
          gameWin = true;
          return;
        } else System.out.println("not possible");
      } else {
        matrix[i - 1][j] = "P ";
        matrix[i][j] = matrix[i][j].equals("% ") ? "% " : "  ";
        currentPos[0] = i - 1;
      }
    } else if (movement.equals("DOWN")) {
      if ((i + 1) >= length || !matrix[i + 1][j].equals("  ")) {
        if (matrix[i + 1][j].equals("E ")) {
          System.out.println("You Died......");
          gameOver = true;
          return;
        } else if (matrix[i + 1][j].equals("K ")) {
          System.out.println("You win...");
          gameWin = true;
          return;
        } else System.out.println("not possible");
      } else {
        matrix[i + 1][j] = "P ";
        matrix[i][j] = matrix[i][j].equals("% ") ? "% " : "  ";
        currentPos[0] = i + 1;
      }
    } else if (movement.equals("LEFT")) {
      if ((j - 1) < 0 || !matrix[i][j - 1].equals("  ")) {
        if (matrix[i][j - 1].equals("E ")) {
          System.out.println("You Died......");
          gameOver = true;
          return;
        } else if (matrix[i][j - 1].equals("K ")) {
          System.out.println("You win...");
          gameWin = true;
          return;
        } else System.out.println("not possible");
      } else {
        matrix[i][j - 1] = "P ";
        matrix[i][j] = matrix[i][j].equals("% ") ? "% " : "  ";
        currentPos[1] = j - 1;
      }
    } else if (movement.equals("RIGHT")) {
      if ((j + 1) >= length || !matrix[i][j + 1].equals("  ")) {
        if (matrix[i][j + 1].equals("E ")) {
          System.out.println("You Died......");
          gameOver = true;
          return;
        } else if (matrix[i][j + 1].equals("K ")) {
          System.out.println("You win...");
          gameWin = true;
          return;
        } else System.out.println("not possible");
      } else {
        matrix[i][j + 1] = "P ";
        matrix[i][j] = matrix[i][j].equals("% ") ? "% " : "  ";
        currentPos[1] = j + 1;
      }
    }
  }

  public static void main(String[] args) {
    length = 11;
    matrix = new String[length][length];

    createMatrix(length);

    Scanner input = new Scanner(System.in);

    //player position
    while (true) {
      print();
      System.out.print("Set Player position : ");
      String pos = input.next();
      if (checkIsValid(pos)) {
        int i = (int) pos.charAt(0) - 'A';
        int j = (int) pos.charAt(1) - 'A';
        matrix[i][j] = "P ";
        currentPos[0] = i;
        currentPos[1] = j;
        break;
      } else {
        System.out.println("There is wall here, place somewhere");
      }
    }

    //player position
    while (true) {
      print();
      System.out.print("Set Key position : ");
      String pos = input.next();
      if (checkIsValid(pos)) {
        int i = (int) pos.charAt(0) - 'A';
        int j = (int) pos.charAt(1) - 'A';
        matrix[i][j] = "K ";
        break;
      } else {
        System.out.println("There is wall here, place somewhere");
      }
    }

    //enemy position
    print();
    System.out.print("Set Enemy count : ");
    int enemyCount = input.nextInt();
    for (int ind = 0; ind < enemyCount; ind++) {
      System.out.print("Set Enemy " + (ind + 1) + " position : ");
      String pos = input.next();
      while (true) {
        if (checkIsValid(pos)) {
          int i = (int) pos.charAt(0) - 'A';
          int j = (int) pos.charAt(1) - 'A';
          matrix[i][j] = "E ";
          break;
        } else {
          System.out.println("There is wall here, place somewhere");
        }
      }
    }

    //bricks position
    print();
    System.out.print("Set Bricks count : ");
    int bricksCount = input.nextInt();
    for (int ind = 0; ind < bricksCount; ind++) {
      System.out.print("Set Brick " + (ind + 1) + " position : ");
      String pos = input.next();
      while (true) {
        if (checkIsValid(pos)) {
          int i = (int) pos.charAt(0) - 'A';
          int j = (int) pos.charAt(1) - 'A';
          matrix[i][j] = "B ";
          break;
        } else {
          System.out.println("There is wall here, place somewhere");
        }
      }
    }

    while (true) {
      if (gameOver || gameWin) break;
      print();
      System.out.println(
        "1.moveUp\n2.moveDown\n3.moveLeft\n4.moveRight\n5.Bomb(Place/Blast)\n6.Exit"
      );
      int option = input.nextInt();
      if (option == 6) break; else if (option == 1) move("UP"); else if (
        option == 2
      ) move("DOWN"); else if (option == 3) move("LEFT"); else if (
        option == 4
      ) move("RIGHT"); else if (option == 5) {
        System.out.print(
          "-->1.Place Bomb\n-->2.Detonate Bombs\nEnter you choice : "
        );
        int bombOption = input.nextInt();
        if (bombOption == 1) {
          while (true) {
            System.out.print("---->Enter the radius : ");
            int radius = input.nextInt();
            if (isValidRadius(currentPos[0], currentPos[1], radius)) {
              bombMatrix.add(
                new int[] { currentPos[0], currentPos[1], radius }
              );
              matrix[currentPos[0]][currentPos[1]] = "% ";
              break;
            } else System.out.println("give correct radius");
          }
        } else if (bombOption == 2) {
          for (int eachBomb[] : bombMatrix) {
            for (
              int i = eachBomb[0] - eachBomb[2];
              i <= eachBomb[0] + eachBomb[2];
              i++
            ) {
              for (
                int j = eachBomb[1] - eachBomb[2];
                j <= eachBomb[1] + eachBomb[2];
                j++
              ) {
                if (
                  matrix[i][j].equals("* ") || matrix[i][j].equals("K ")
                ) continue;
                if (matrix[i][j].equals("P ")) System.out.println(
                  "You Died..."
                );
                matrix[i][j] = "  ";
              }
            }
          }
        }
      }
    }
  }
}
