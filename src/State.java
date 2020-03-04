import java.util.ArrayList;

public class State {

    private int[][] currentBoardState;
    private int[][] goalBoardState;

    public State getParentState() {
        return parentState;
    }

    private State parentState;
    private int g_cost;
    private int h_cost;
    private int f_cost;
    ArrayList<State> childStates;

    public void setG_cost(int g_cost) {
        this.g_cost = g_cost;
    }

    public int getH_cost() {
        return h_cost;
    }

    public void setH_cost(int h_cost) {
        this.h_cost = h_cost;
    }

    public int getF_cost() {
        return f_cost;
    }

    public void setF_cost(int f_cost) {
        this.f_cost = f_cost;
    }

    public void setParentState(State parentState) {
        this.parentState = parentState;
    }

    final int boardRows = 4;
    final int boardColumns = 4;

    /**
     * This constructor actually creates a state object with the current state as start state of the
     * graph
     * @param startBoardState the starting state or configuration of the board
     * @param goalBoardState the ending state or configuration of the board
     */
    State(int[][] startBoardState, int [][] goalBoardState){
        this.currentBoardState = startBoardState;
        this.goalBoardState = goalBoardState;
        this.g_cost = 0;
        this.parentState = null;
    }

    public int[][] getCurrentBoardState() {
        return currentBoardState;
    }

    public int[][] getGoalBoardState() {
        return goalBoardState;
    }

    public int getG_cost() {
        return g_cost;
    }

    public int getBoardRows() {
        return boardRows;
    }

    public int getBoardColumns() {
        return boardColumns;
    }

     /**
     *To check equality between two states(nodes) of a graph. One is goal state and the other is current state.
     *
     * @return
     */
    public boolean checkEquality(){
        boolean flag = true;
        for (int i = 0; i < boardRows ; i++) {
            for (int j = 0; j <  boardColumns; j++) {
                if(this.currentBoardState[i][j] != this.goalBoardState[i][j]){
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method returns a pair of row and column for a certain value in the current state of the game
     * @param val the value to be checked in the current state of the game
     * @return
     */
    public Pair getRowandColumn(int val){
        for (int i = 0; i < boardRows ; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if(this.currentBoardState[i][j] == val){
                    return new Pair(i,j);
                }
            }
        }
        return null;
    }

    /**
     * This method returns an array-list of all the new possible state found with the help of successor functions
     * @return an array-list of State
     */
    public ArrayList<State> childStates(){
        boolean hasExpanded = false;
        this.childStates = new ArrayList<>();
        Successor successor = new Successor(); //for calling the successors functions
        //cases for this
        for (int i = 0; i < boardRows ; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if(!hasExpanded){
                    if(currentBoardState[i][j] == 0){

                        if( i - 1 >= 0){
                            //call moveDown()
                            //System.out.println(i + " " + j);
                            //System.out.println("calling moveDown()");
                            State newState = successor.moveDown(i, j,  this.getCurrentBoardState(), this.getGoalBoardState());

                            //newState.printBoard();
                            childStates.add(newState);
                        }

                        if( i + 1 <= boardRows-1){
                            //call moveUp()
                            //System.out.println(i + " " + j);
                            //System.out.println("calling moveUp()");
                            //this.printBoard();
                            State newState = successor.moveUp(i, j,  this.getCurrentBoardState(), this.getGoalBoardState());
                            //newState.printBoard();
                            childStates.add(newState);
                        }

                        if(j - 1 >= 0){
                            //System.out.println(i + " " + j);
                            //System.out.println("calling moveRight()");
                            //this.printBoard();
                            State newState = successor.moveRIght(i, j,  this.getCurrentBoardState(), this.getGoalBoardState());
                            //newState.printBoard();
                            childStates.add(newState);
                        }

                        if( j + 1 <= boardColumns - 1){
                            //System.out.println(i + " " + j);
                            //System.out.println("calling moveLeft()");
                            //this.printBoard();
                            State newState = successor.moveLeft(i,j,this.getCurrentBoardState(), this.getGoalBoardState());
                            //newState.printBoard();
                            childStates.add(newState);
                        }
                        hasExpanded = true;
                    }
                }
            }
        }
        return childStates;
    }

    /**
     * This method simply prints the current state of the state space graph
     */
    public void printBoard(){
        for (int i = 0; i < boardRows ; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if(this.currentBoardState[i][j] == 0){
                    System.out.print("0 ");
                }else{
                    System.out.print(currentBoardState[i][j] + " ");
                }
            }
            System.out.println();
        }
    }








}
