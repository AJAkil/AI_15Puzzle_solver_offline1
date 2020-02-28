public class Successor {

    /**
     * This method moves a tile up and moves the blank position downwards
     * @param rowIndex
     * @param columnIndex
     * @param currentBoardState
     * @return
     */
    public State moveUp(int rowIndex, int columnIndex,int[][]currentBoardState, int[][]goalBoardState){
        int[][] copiedArray = new int[currentBoardState.length][currentBoardState.length];

        for (int i = 0; i < currentBoardState.length ; i++) {
            for (int j = 0; j < currentBoardState.length; j++) {
                copiedArray[i][j] = currentBoardState[i][j];
            }
        }
        copiedArray[rowIndex][columnIndex] = copiedArray[rowIndex+1][columnIndex];
        copiedArray[rowIndex+1][columnIndex] = 0;
        return new State(copiedArray, goalBoardState);
    }

    /**
     * This method moves a tile down and moves the blank position upwards
     * @param rowIndex
     * @param columnIndex
     * @param currentBoardState
     * @param goalBoardState
     * @return
     */
    public State  moveDown(int rowIndex, int columnIndex,int[][]currentBoardState, int[][]goalBoardState){
        int[][] copiedArray = new int[currentBoardState.length][currentBoardState.length];

        for (int i = 0; i < currentBoardState.length ; i++) {
            for (int j = 0; j < currentBoardState.length; j++) {
                copiedArray[i][j] = currentBoardState[i][j];
            }
        }
        copiedArray[rowIndex][columnIndex] = copiedArray[rowIndex-1][columnIndex];
        copiedArray[rowIndex-1][columnIndex] = 0;
        return new State(copiedArray, goalBoardState);
    }

    /**
     * This method moves a tile right and moves the blank position left
     * @param rowIndex
     * @param columnIndex
     * @param currentBoardState
     * @param goalBoardState
     * @return
     */
    public State moveRIght(int rowIndex, int columnIndex,int[][]currentBoardState, int[][]goalBoardState){
        int[][] copiedArray = new int[currentBoardState.length][currentBoardState.length];

        for (int i = 0; i < currentBoardState.length ; i++) {
            for (int j = 0; j < currentBoardState.length; j++) {
                copiedArray[i][j] = currentBoardState[i][j];
            }
        }
        copiedArray[rowIndex][columnIndex] = copiedArray[rowIndex][columnIndex-1];
        copiedArray[rowIndex][columnIndex-1] = 0;
        return new State(copiedArray, goalBoardState);
    }

    /**
     * This method moves a tile left and moves the blank position right
     * @param rowIndex
     * @param columnIndex
     * @param currentBoardState
     * @param goalBoardState
     * @return
     */
    public State moveLeft(int rowIndex, int columnIndex,int[][]currentBoardState, int[][]goalBoardState){
        int[][] copiedArray = new int[currentBoardState.length][currentBoardState.length];

        for (int i = 0; i < currentBoardState.length ; i++) {
            for (int j = 0; j < currentBoardState.length; j++) {
                copiedArray[i][j] = currentBoardState[i][j];
            }
        }
        copiedArray[rowIndex][columnIndex] = copiedArray[rowIndex][columnIndex+1];
        copiedArray[rowIndex][columnIndex+1] = 0;
        return new State(copiedArray, goalBoardState);
    }

}
