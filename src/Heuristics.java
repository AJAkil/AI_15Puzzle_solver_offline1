import java.util.Map;

public class Heuristics {

    /**
     * This method calculates the manhattan distance between the goal and the current state
     * @param state
     * @return
     */
    public int manHattan(State state){
        int mHdistance = 0;
        int [][] currentState = state.getCurrentBoardState();
        int [][] goalState = state.getGoalBoardState();

        for (int i = 0; i < state.getBoardRows() ; i++) {
            for (int j = 0; j < state.getBoardColumns(); j++) {
                int tileValue = goalState[i][j];
                if(tileValue != 0){
                     Pair rowCol = state.getRowandColumn(tileValue);
                     mHdistance  += Math.abs(i - rowCol.getFirstVal())+Math.abs(j - rowCol.getSecondVal());
                }
            }
        }
        return mHdistance;
    }

    /**
     * returns the number of displaced tiles on the board
     * @param state
     * @return
     */
    public int displacementH(State state){
        int displacement = 0;

        int [][] currentState = state.getCurrentBoardState();
        int [][] goalState = state.getGoalBoardState();

        /*for (int i = 0; i < state.getBoardRows() ; i++) {
            for (int j = 0; j < state.getBoardColumns(); j++) {
                System.out.print(goalState[i][j]+ " ");
            }
            System.out.println();
        }*/

        for (int i = 0; i < state.getBoardRows() ; i++) {
            for (int j = 0; j < state.getBoardColumns(); j++) {
                if(currentState[i][j]!=goalState[i][j]){
                   if(currentState[i][j]!=0){
                       displacement++;
                   }

                }
            }
        }
        return Math.abs(displacement);
    }

    public int callHeursitic(String name, State state){
        if(name.equalsIgnoreCase("displacement")){
            return this.displacementH(state);
        }else if(name.equalsIgnoreCase("manhattan")){
            return this.manHattan(state);
        }
        return 0;
    }

}
