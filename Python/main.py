from Connect4.Disk import Disk
import sys

def main():
  print(
    "Welcome to Connect 4!\n\n" +

    "Objective: The goal of the game is to connect four of the same-colored disks\n" +
    "in a row, a column, or a diagonal, before your opponent does likewise.\n\n" +

    "The game will ask you for the column that you want to your colored disk to go to.\n" +
    "Once four of the same-colored disks are connected, the game will end.\n")

  # Take start or exit input from the user
  user_input = input("Enter S to start or E to exit: ").upper()

  # play if user says to start
  if user_input == "S":
    print("\nLet's get playing!")
    play()
  # otherwise, exit
  else:
    print("Thank you for playing Connect 4!\n" +
          "You have been successfully exited from the game.")

def play():
  # create grid of disks (2d list)
  rows = 6
  columns = 7
  grid = [[Disk(" ", r, c) for c in range(columns)] for r in range(rows)]

  # variable to keep of track whose turn it is
  turn = "R"

  # variable for user input of drop location
  drop_location = 0

  print("\nThe Game has started! Choose your color and may the best player win!\n")

  # while loop to continue playing gaming until win or exit
  while True:
    # determine stalemate
    stalemate = True
    # check if the top row is not full
    for i in range(0, len(grid[0])):
      if not (grid[5][i].color == "R" or grid[5][i].color == "Y"):
        stalemate = False
        break

    # if stalemate, print the grid and exit
    if stalemate:
      print_grid(grid)
      print("The game is at a stalemate.\n\n" +
            "Thank you for playing Connect 4!\n" +
            "You have been successfully exited from the game.")
      sys.exit()

    # red player's turn
    if turn == "R":
      while True:
        print("\nRED PLAYER'S TURN: ")
        # print grid
        print_grid(grid)

        # try/except statement to handle invalid input errors
        try:
          # get column to drop disk from user
          drop_location = int(input("Drop a Red Disk at column (0 - 6) or Enter -1 to exit: "))

          # if drop location is -1, exit
          if drop_location == -1:
            print("Thank you for playing Connect 4!\n" +
                  "You have been successfully exited from the game.")
            sys.exit()
          # handle invalid drop locations
          elif drop_location > 6 or drop_location < 0:
            print("Invalid Drop Location. Please enter a location in between 0 - 6.")
            continue
          # otherwise
          else:
            # check if the column is full
            if (grid[5][drop_location].color == "R") or (grid[5][drop_location].color == "Y"):
              print("The inputted column is full. Please try another column.")
              continue
            # otherwise
            else:
              i = 0
              # go through each row in the drop location column
              for row in range(len(grid)):
                # add the disk in the first open row
                if not ((grid[row][drop_location].color == "R") or (grid[row][drop_location].color == "Y")):
                  grid[row][drop_location] = Disk("R", row, drop_location)
                  i = row
                  break

              # if there is a connect 4, print win and exit
              if check_connect4(grid, grid[i][drop_location]):
                print_grid(grid)
                print("Congratulations! The Red player has won!\n\n" +
                      "Thank you for playing Connect 4!\n" +
                      "You have been successfully exited from the game.")
                sys.exit()
          break
        # handle invalid input errors
        except ValueError:
          print("Invalid Input. Please input an integer value in between 0 and 6.")
          continue
      # choose yellow player's turn
      turn = "Y"
    elif turn == "Y":
      while True:
        print("\nYELLOW PLAYER'S TURN: ")
        # print grid for yellow player's turn
        print_grid(grid)

        # try/except to handle invalid column input
        try:
          # get column input from the user
          drop_location = int(input("Drop a Yellow Disk at column (0 - 6) or Enter -1 to exit: "))

          # if drop location is -1, exit
          if drop_location == -1:
            print("Thank you for playing Connect 4!\n" +
                  "You have been successfully exited from the game.")
            sys.exit()
          # if drop location is invalid, ask the user for input again
          elif drop_location > 6 or drop_location < 0:
            print("Invalid Drop Location. Please enter a location in between 0 - 6.")
            continue
          # otherwise
          else:
            # check if column is full
            if (grid[5][drop_location].color == "R") or (grid[5][drop_location].color == "Y"):
              print("The inputted column is full. Please try another column.")
              continue
            # otherwise
            else:
              i = 0
              # place the disk in the first available row in the chosen column
              for row in range(len(grid)):
                if not ((grid[row][drop_location].color == "R") or (grid[row][drop_location].color == "Y")):
                  grid[row][drop_location] = Disk("Y", row, drop_location)
                  i = row
                  break

              # if connect 4, print win and exit
              if check_connect4(grid, grid[i][drop_location]):
                print_grid(grid)
                print("Congratulations! The Yellow player has won!\n\n" +
                      "Thank you for playing Connect 4!\n" +
                      "You have been successfully exited from the game.")
                sys.exit()
          break
        # handle invalid input errors for drop location
        except ValueError:
          print("Invalid Input. Please input an integer value in between 0 and 6.")
          continue
      # go to red player's turn
      turn = "R"

# print grid
def print_grid(grid):
  # formatting
  print("\n                Rows             ")
  print("C   | 0 | 1 | 2 | 3 | 4 | 5 | 6 |")
  i = 0
  # print the rows backwards so that the disks are shown to be "dropped" in
  for row_num in range(len(grid) - 1, -1, -1):
    print(f"{'olumns'[i]}   | ", end="")
    i += 1
    # print each disk
    for column_disk in grid[row_num]:
      print(f"{column_disk.color} | ", end="")
      if column_disk == grid[row_num][-1]:
        print("\n", end="")
  print(".............................")

# check connect 4 of the given disk
def check_connect4(grid, disk):
  for row in grid:
    for column_disk in row:
      # if disk has connect 4, return true
      if (column_disk.color == disk.color) and (column_disk.check_connect4(grid)):
        return True
  # otherwise, return false
  return False

# call main
main()
