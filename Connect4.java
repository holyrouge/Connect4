/*
@author Prangon Ghose
*/
import java.util.*;

public class Connect4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char userInput = 'E';
        System.out.print(
                "Welcome to Connect 4!\n" +
                        "By Prangon Ghose\n\n" +

                        "Objective: The goal of the game is to connect four of the same-colored disks\n" +
                        "in a row, a column, or a diagonal, before your opponent does likewise.\n\n" +

                        "The game will ask you for the column that you want to your colored disk to go to.\n" +
                        "Once four of the same-colored disks are connected, the game will end.\n\n" +

                        "Enter S to start or E to exit: ");
        userInput = Character.toUpperCase(sc.next().charAt(0));
        if (userInput == 'E') {
            System.out.println("Thank you for playing Connect 4!\n" +
                    "You have been successfully exited from the game.");
            System.exit(0);
        }
        else {
            game();
        }
        game();
    }

    public static void game() {
        Scanner sc = new Scanner(System.in);
        Disk[][] grid = new Disk[6][7];
        int alt = 0, dropLoc = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Disk(i, j);
            }
        }
        System.out.println("\n\nThe Game has started! Choose your color and may the best player win!");
        while (true) {
            if (alt == 0) {
                while (true) {
                    boolean stalemate = true;
                    for (int i = 0; i < grid[0].length; i++) {
                        if (!(grid[5][i] instanceof RedDisk || grid[5][i] instanceof YellowDisk)) {
                            stalemate = false;
                            break;
                        }
                    }
                    if (stalemate) {
                        printGrid(grid);
                        System.out.println("The game is at a stalemate.\n\n" +
                                "Thank you for playing Connect 4!\n" +
                                "You have been successfully exited from the game.");
                        System.exit(0);
                    }
                    System.out.println("\nRED PLAYER'S TURN: ");
                    printGrid(grid);
                    System.out.print("Drop a Red Disk at column (0 - 6) or Enter -1 to exit: ");
                    try {
                        dropLoc = sc.nextInt();
                        if (dropLoc == -1) {
                            System.out.println("Thank you for playing Connect 4!\n" +
                                    "You have been successfully exited from the game.");
                            System.exit(0);
                        }
                        else if (dropLoc > 6 || dropLoc < 0) {
                            System.out.println("Invalid Drop Location. Please enter a location in between 0 - 6.");
                            continue;
                        }
                        else {
                            if (grid[5][dropLoc] instanceof YellowDisk || grid[5][dropLoc] instanceof RedDisk) {
                                System.out.println("The inputted column is full. Please try another column.");
                                continue;
                            }
                            else {
                                int i;
                                for (i = 0; i < grid.length; i++) {
                                    if (!(grid[i][dropLoc] instanceof YellowDisk || grid[i][dropLoc] instanceof RedDisk)) {
                                        grid[i][dropLoc] = new RedDisk(i, dropLoc);
                                        break;
                                    }
                                }
                                if (checkConnect4(grid, grid[i][dropLoc])) {
                                    printGrid(grid);
                                    System.out.println("Congratulations! The Red player has won!\n\n" +
                                            "Thank you for playing Connect 4!\n" +
                                            "You have been successfully exited from the game.");
                                    System.exit(0);
                                }
                            }
                        }
                        break;
                    }
                    catch (InputMismatchException ex) {
                        System.out.println("Invalid Input. Please input an integer value in between 0 and 6.");
                        sc.next();
                        continue;
                    }
                }
                alt++;
            }
            else if (alt == 1) {
                while (true) {
                    boolean stalemate = true;
                    for (int i = 0; i < grid[0].length; i++) {
                        if (!(grid[5][i] instanceof RedDisk || grid[5][i] instanceof YellowDisk)) {
                            stalemate = false;
                            break;
                        }
                    }
                    if (stalemate) {
                        printGrid(grid);
                        System.out.println("The game is at a stalemate.\n\n" +
                                "Thank you for playing Connect 4!\n" +
                                "You have been successfully exited from the game.");
                        System.exit(0);
                    }
                    System.out.println("\nYELLOW PLAYER'S TURN: ");
                    printGrid(grid);
                    System.out.print("Drop a Yellow Disk at column (0 - 6) or Enter -1 to exit: ");
                    try {
                        dropLoc = sc.nextInt();
                        if (dropLoc == -1) {
                            System.out.println("Thank you for playing Connect 4!\n" +
                                    "You have been successfully exited from the game.");
                            System.exit(0);
                        }
                        else if (dropLoc > 6 || dropLoc < 0) {
                            System.out.println("Invalid Drop Location. Please enter a location in between 0 - 6.");
                            continue;
                        }
                        else {
                            if (grid[5][dropLoc] instanceof YellowDisk || grid[5][dropLoc] instanceof RedDisk) {
                                System.out.println("The inputted column is full. Please try another column.");
                                continue;
                            }
                            else {
                                int i;
                                for (i = 0; i < grid.length; i++) {
                                    if (!(grid[i][dropLoc] instanceof YellowDisk || grid[i][dropLoc] instanceof RedDisk)) {
                                        grid[i][dropLoc] = new YellowDisk(i, dropLoc);
                                        break;
                                    }
                                }
                                if (checkConnect4(grid, grid[i][dropLoc])) {
                                    printGrid(grid);
                                    System.out.println("Congratulations! The Yellow player has won!\n\n" +
                                            "Thank you for playing Connect 4!\n" +
                                            "You have been successfully exited from the game.");
                                    System.exit(0);
                                }
                            }
                        }
                        break;
                    }
                    catch (InputMismatchException ex) {
                        System.out.println("Invalid Input. Please input an integer value in between 0 and 6.");
                        sc.next();
                        continue;
                    }
                }
                alt--;
            }
        }
    }

    public static void printGrid(Disk[][] grid) {
        System.out.println("| 0 | 1 | 2 | 3 | 4 | 5 | 6 |");
        for(int i = grid.length - 1; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j].toString() + " | ");
                if (j == grid[i].length - 1) {
                    System.out.println();
                }
            }
        }
        System.out.println(".............................");
    }

    public static boolean checkConnect4(Disk[][] grid, Disk disk) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].getColor().equals(disk.getColor()) && grid[i][j].checkConnect4(grid)) {
                    return true;
                }
            }
        }
        return false;
    }
}

/**
 * Main Parent Class for Disks
 */
class Disk {
    private int row, column;
    private String color;

    public Disk() {
        this.color = " ";
        this.row = 0;
        this.column = 0;
    }

    public Disk(int row, int column) {
        this.color = " ";
        this.row = row;
        this.column = column;
    }

    public int getRow() {return this.row;}
    public void setRow(int row) {this.row = row;}

    public int getColumn() {return this.column;}
    public void setColumn(int column) {this.column = column;}

    public String getColor() {return this.color;}
    public void setColor(String color) {this.color = color;}

    public boolean checkConnect4(Disk[][] grid) {return false;}

    public String toString() {return this.color;}
}

/**
 * Subclass for RedDisk
 */
class RedDisk extends Disk {
    public RedDisk() {
        setColor("R");
        setRow(0);
        setColumn(0);
    }

    public RedDisk(int row, int column) {
        setColor("R");
        setRow(row);
        setColumn(column);
    }

    public boolean checkConnect4(Disk[][] grid) {
        int r = getRow(), c = getColumn();
        if (r + 3 < grid.length) {
            if (grid[r + 1][c] instanceof RedDisk && grid[r + 2][c] instanceof RedDisk
                    && grid[r + 3][c] instanceof RedDisk) {
                return true;
            }
        }

        if (c + 3 < grid[r].length) {
            if (grid[r][c + 1] instanceof RedDisk && grid[r][c + 2] instanceof RedDisk
                    && grid[r][c + 3] instanceof RedDisk) {
                return true;
            }
        }

        if (r + 3 < grid.length && c + 3 < grid[r].length) {
            if (grid[r + 1][c + 1] instanceof RedDisk && grid[r + 2][c + 2] instanceof RedDisk
                    && grid[r + 3][c + 3] instanceof RedDisk) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Subclass for YellowDisk
 */
class YellowDisk extends Disk {
    public YellowDisk() {
        setColor("Y");
        setRow(0);
        setColumn(0);
    }

    public YellowDisk(int row, int column) {
        setColor("Y");
        setRow(row);
        setColumn(column);
    }

    public boolean checkConnect4(Disk[][] grid) {
        int r = getRow(), c = getColumn();
        if (r + 3 < grid.length) {
            if (grid[r + 1][c] instanceof YellowDisk && grid[r + 2][c] instanceof YellowDisk
                    && grid[r + 3][c] instanceof YellowDisk) {
                return true;
            }
        }

        if (c + 3 < grid[r].length) {
            if (grid[r][c + 1] instanceof YellowDisk && grid[r][c + 2] instanceof YellowDisk
                    && grid[r][c + 3] instanceof YellowDisk) {
                return true;
            }
        }

        if (r + 3 < grid.length && c + 3 < grid[r].length) {
            if (grid[r + 1][c + 1] instanceof YellowDisk && grid[r + 2][c + 2] instanceof YellowDisk
                    && grid[r + 3][c + 3] instanceof YellowDisk) {
                return true;
            }
        }
        return false;
    }
}
