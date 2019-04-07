import java.util.ArrayList;

public class Question02 {
    public static int gunnerCounter = 1;
    public static int maxGunner = 0;
    public static char[][] pattern;
    public static ArrayList<char[][]> numberofWaysMaximumGunnerPattern = new ArrayList<char[][]>();

    public static void main(String[] args) {
        ArrayList<int[][]> indexofEmptySpace = new ArrayList<>();
        /*
        Assume that w is wall, and . is empty space
         */
        String[] map = {
                ".w.w.w.w",
                ".....w..",
                "w.w..w.w",
                "........",
                "...w....",
                ".....w.w",
                ".w......",
                "....w.w."
        };
        int size = map.length;
        char[][] maps = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                maps[i][j] = map[i].charAt(j);
                if (maps[i][j] == '.') {
                    int[][] temp = new int[2][2];
                    temp[0][0] = i;
                    temp[0][1] = j;
                    indexofEmptySpace.add(temp);
                }
            }
        }

        for (int k = 0; k < indexofEmptySpace.size(); k++) {
            System.out.println("\nSet Index: " + indexofEmptySpace.get(k)[0][0] + "," + indexofEmptySpace.get(k)[0][1]);
            Question02 I = new Question02();
            int row = indexofEmptySpace.get(k)[0][0];
            int col = indexofEmptySpace.get(k)[0][1];
            gunnerCounter = 1;
            I.travelAllofSpace(maps, row, col);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(pattern[i][j]);
                }
                System.out.println();
            }
            System.out.println("Number of Gunner: " + gunnerCounter);
            if (gunnerCounter > maxGunner) {
                maxGunner = gunnerCounter;
                numberofWaysMaximumGunnerPattern.clear();
                numberofWaysMaximumGunnerPattern.add(pattern);
            } else if (gunnerCounter == maxGunner) {
                if (!isPatternAlreadyExist(pattern)) {
                    numberofWaysMaximumGunnerPattern.add(pattern);
                }
            }
        }
        System.out.println("\n 01. Maximum Gunman that can be placed in the room: " + maxGunner);
        System.out.println("\n 02.Maximum number of ways: " + numberofWaysMaximumGunnerPattern.size());
        for (int k = 0; k < numberofWaysMaximumGunnerPattern.size(); k++) {
            int p = k + 1;
            System.out.println("\nPattern: " + p + "\n----------");
            for (int l = 0; l < size; l++) {
                for (int m = 0; m < size; m++) {
                    System.out.print(numberofWaysMaximumGunnerPattern.get(k)[l][m]);
                }
                System.out.println("");
            }
            System.out.println("-----------");
        }
    }

    public static boolean isPatternAlreadyExist(char[][] patternCandidate) {
        int match = 0;
        int diff;
        for (int a = 0; a < numberofWaysMaximumGunnerPattern.size(); a++) {
            diff = 0;
            for (int i = 0; i < patternCandidate.length; i++) {
                for (int j = 0; j < patternCandidate.length; j++) {
                    if (patternCandidate[i][j] != numberofWaysMaximumGunnerPattern.get(a)[i][j]) {
                        diff = 1;
                    }
                }
            }
            if (diff == 0) {
                match++;
            }
        }
        return match>0;
    }

    public static char[][] setCurrentPattern(int i, int j) {
        if (checkRow(i, j) && checkColumn(i, j)) {
            if (pattern[i][j] != 'g') {
                gunnerCounter++;
                pattern[i][j] = 'g';
            }

        }
        return pattern;
    }

    public static boolean checkRow(int i, int j) {
        return (checkLeft(i, j) && checkRight(i, j));
    }

    public static boolean checkLeft(int i, int j) {
        boolean gunnerExist = false;
        boolean wallExist = false;
        if (j > 0) {
            if (pattern[i][j - 1] == 'w') {
                return true;
            } else if (pattern[i][j - 1] == 'g') {
                return false;
            } else {
                for (int a = j - 1; a >= 0; a--) {
                    if ((pattern[i][a] == 'w') && gunnerExist == false) {
                        wallExist = true;
                        break;
                    }
                    if (pattern[i][a] == 'g' && wallExist == false) {
                        gunnerExist = true;
                        break;
                    }

                }

                if (wallExist == true && gunnerExist == false) {
                    return true;
                } else if (wallExist == false && gunnerExist == false) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkRight(int i, int j) {
        boolean gunnerExist = false;
        boolean wallExist = false;
        if (j < pattern.length - 1) {
            if (pattern[i][j + 1] == 'w') {
                return true;
            } else if (pattern[i][j + 1] == 'g') {
                return false;
            } else {
                for (int a = j + 1; a < pattern.length; a++) {
                    if ((pattern[i][a] == 'w') && gunnerExist == false) {
                        wallExist = true;
                        break;
                    }
                    if (pattern[i][a] == 'g' && wallExist == false) {
                        gunnerExist = true;
                        break;
                    }
                }
                if (wallExist == true && gunnerExist == false) {
                    return true;
                } else if (wallExist == false && gunnerExist == false) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkColumn(int i, int j) {
        if (checkAbove(i, j) && checkBelow(i, j)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAbove(int i, int j) {
        boolean gunnerExist = false;
        boolean wallExist = false;
        if (i > 0) {
            if (pattern[i - 1][j] == 'w') {
                return true;
            } else if (pattern[i - 1][j] == 'g') {
                return false;
            } else {

                for (int a = i - 1; a >= 0; a--) {
                    if ((pattern[a][j] == 'w') && gunnerExist == false) {
                        wallExist = true;
                        break;

                    }
                    if (pattern[a][j] == 'g' && wallExist == false) {
                        gunnerExist = true;
                        break;
                    }

                }
                if (wallExist == true && gunnerExist == false) {
                    return true;
                } else if (wallExist == false && gunnerExist == false) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkBelow(int i, int j) {
        boolean gunnerExist = false;
        boolean wallExist = false;
        if (i < pattern.length - 1) {
            if (pattern[i + 1][j] == 'w') {
                return true;
            } else if (pattern[i + 1][j] == 'g') {
                return false;
            } else {
                for (int a = i + 1; a < pattern.length - 1; a++) {
                    if ((pattern[a][j] == 'w') && gunnerExist == false) {
                        wallExist = true;
                        break;
                    }
                    if (pattern[a][j] == 'g' && wallExist == false) {
                        gunnerExist = true;
                        break;
                    }

                }
                if (wallExist == true && gunnerExist == false) {
                    return true;
                } else if (wallExist == false && gunnerExist == false) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    static void travelAllofSpace(char[][] maps, int row, int col) {

        int size = maps.length;
        pattern = new char[size][size];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                pattern[i][j] = maps[i][j];
            }
        }
        pattern[row][col] = 'g';
        boolean visited[][] = new boolean[size][size];
        int count = 0;
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (pattern[i][j] == '.' && !visited[i][j]) {
                    setCurrentPattern(i, j);
                    DFS(maps, i, j, visited);

                    ++count;
                }
            }
        }
    }

    static boolean isSafe(char M[][], int row, int col,
                          boolean visited[][]) {
        return (row >= 0) && (row < M.length)
                && (col >= 0) && (col < M.length)
                && (M[row][col] == '.' && !visited[row][col]);
    }

    static void DFS(char M[][], int row, int col, boolean visited[][]) {
        int rowNbr[] = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int colNbr[] = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
        visited[row][col] = true;
        setCurrentPattern(row, col);
        for (int k = 0; k < 8; ++k) {
            if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited)) {
                setCurrentPattern(row, col);
                DFS(M, row + rowNbr[k], col + colNbr[k], visited);
            }
        }

    }
}
