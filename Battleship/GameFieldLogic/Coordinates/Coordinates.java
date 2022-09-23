package Battleship.GameFieldLogic.Coordinates;

public class Coordinates implements CoordinatesInterface{

    private static int rowFirst;
    private static int columnFirst;
    private static int rowSecond;
    private static int columnSecond;
    private static int biggestRow;
    private static int biggestColumn;
    private static int smallestRow;
    private static int smallestColumn;
    private int row;
    private int column;

    private int amountOfCells;
    private static Aligned position;
    private final int[][] field;

    public void setAmountOfCells(int amountOfCells) {
        this.amountOfCells = amountOfCells;
    }

    public Coordinates(int[][] field) {
        this.field = field;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    public static int getRowFirst() {
        return rowFirst;
    }

    public static int getColumnFirst() {
        return columnFirst;
    }

    public static Aligned getPosition() {
        return position;
    }

    public static int getSmallestRow() {
        return smallestRow;
    }

    public static int getColumnSecond() {
        return columnSecond;
    }

    public static int getSmallestColumn() {
        return smallestColumn;
    }

    public static int getRowSecond() {
        return rowSecond;
    }

    public boolean checkCoordinate(String coordinate) {
        boolean result = false;

        try{
            row = charToInt(coordinate.charAt(0));
            column = (coordinate.length() == 2) ? charToInt(coordinate.charAt(1))+16 : 10;

            if(row <= field.length && column <= field[0].length) {
                result = true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Something is wrong with a string provided!");
        }

        return result;
    }

    public boolean checkCoordinates(String coords, int length) {
        boolean result = false;
        String[] coordinate;
        boolean horizontallyAligned = false;
        boolean verticallyAligned = false;

        try {
            coordinate = coords.split(" ");

            if(coordinate.length > length) {
                return false;
            }

            rowFirst = charToInt(coordinate[0].charAt(0));
            columnFirst = (coordinate[0].length() == 2) ? charToInt(coordinate[0].charAt(1))+16 : 10;

            rowSecond = charToInt(coordinate[1].charAt(0));
            columnSecond = (coordinate[1].length() == 2) ? charToInt(coordinate[1].charAt(1))+16 : 10;

            biggestRow = Math.max(rowFirst, rowSecond);
            biggestColumn = Math.max(columnFirst, columnSecond);

            smallestColumn = Math.min(columnFirst, columnSecond);
            smallestRow = Math.min(rowFirst, rowSecond);

            if(rowFirst == rowSecond) {
                horizontallyAligned = true;
            } else if(columnFirst == columnSecond) {
                verticallyAligned = true;
            }

            if(verticallyAligned) {
                if(Math.abs(rowSecond - rowFirst) == amountOfCells-1) {
                    position = Aligned.VERTICALLY;
                    result = checkSpacing();
                }
            } else if(horizontallyAligned) {
                if(Math.abs(columnSecond - columnFirst) == amountOfCells-1) {
                    position = Aligned.HORIZONTALLY;
                    result = checkSpacing();
                }
            }
        } catch (Exception e) {
            System.out.println("Something is wrong with coords! " + e.getMessage());
        }

        return result;
    }

    private boolean checkSpacing() {
        boolean result;

        if(smallestColumn >= 2 && biggestColumn < 9) {
            result = checkLeft();
            if(!result) {
                return false;
            }
            result = checkRight();
            if(!result) {
                return false;
            }
        } else if(smallestColumn < 2){
            result = checkRight();
            if(!result) {
                return false;
            }
        } else {
            result = checkLeft();
            if(!result) {
                return false;
            }
        }

        if(smallestRow > 2 && biggestRow < 10) {
            result = checkUp();
            if(!result) return false;

            result = checkDown();
            if(!result) return false;

        } else if(smallestRow < 2){
            result = checkDown();
            if(!result) return false;

        } else {
            result = checkUp();
            if(!result) return false;
        }
        return true;
    }

    private boolean checkUp() {
        boolean result = false;
        try{
            if(position == Aligned.VERTICALLY) {
                if (field[smallestRow-2][columnFirst - 1] == 0) {
                    result = true;
                } else {
                    return false;
                }
            } else {
                for (int i = smallestRow - 1; i < amountOfCells + smallestRow - 1; i++) {
                    if (field[rowFirst - 2][i] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private boolean checkDown() {
        boolean result = false;
        try{
            if(position == Aligned.VERTICALLY) {
                if (field[biggestRow][columnFirst - 1] == 0) {
                    result = true;
                } else {
                    return false;
                }
            } else {
                for (int i = smallestRow-1; i < amountOfCells + smallestRow - 1; i++) {
                    if (field[rowFirst][i] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private boolean checkLeft() {
        boolean result = false;
        try{
            if(position == Aligned.VERTICALLY) {
                for (int i = smallestRow - 1; i < amountOfCells + smallestRow - 1; i++) {
                    if (field[i][columnFirst - 2] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
                }
            } else {

                    if (field[rowFirst - 1][smallestColumn-2] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
            }
            return result;
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private boolean checkRight() {
        boolean result = false;
        try{
            if(position == Aligned.VERTICALLY) {
                for (int i = smallestRow - 1; i < amountOfCells + smallestRow - 1; i++) {
                    if (field[i][columnFirst] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
                }
            } else {
                    if (field[rowFirst-1][biggestColumn] == 0) {
                        result = true;
                    } else {
                        return false;
                    }
            }
            return result;
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    private int charToInt(char c) {
        int res = c;
        res -= 64;
        return res;
    }
}