"""
Purpose: This program is the Connect 4 game implemented
with the console and classes in Java.
"""

class Disk:
  # variables for disk
  row = 0
  column = 0
  color = ""

  # initialize Disk object
  def __init__(self, color, row, column):
    self.color = color
    self.row = row
    self.column = column

  # check connect 4
  def check_connect4(self, grid):
    r = self.row
    c = self.column
    disk_type = self.color

    # check row connect 4
    if (r + 3) < len(grid):
      if (grid[r + 1][c].color == disk_type) and \
        (grid[r + 2][c].color == disk_type) and \
        (grid[r + 3][c].color == disk_type):
        return True

    # check column connect 4
    if (c + 3) < len(grid[r]):
      if (grid[r][c + 1].color == disk_type) and \
        (grid[r][c + 2].color == disk_type) and \
        (grid[r][c + 3].color == disk_type):
        return True

    # check diagonal connect 4
    if (r + 3 < len(grid)) and (c + 3 < len(grid[r])):
      if (grid[r + 1][c + 1].color == disk_type) and \
        (grid[r + 2][c + 2].color == disk_type) and \
        (grid[r + 3][c + 3].color == disk_type):
        return True

    return False
