import java.util.Scanner;


public class Client {

    public static int[][]  convertTo2D(int[] array){
        int[][] result = new int[4][4];

        int k = 0;
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 4 ; j++) {
                result[i][j] = array[k];
                k++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] inputConfig = new int[16];
        int[] goalConfig = new int[16];

        for (int i = 0; i < 16; i++) {
            inputConfig[i] = scan.nextInt();
        }

        for (int i = 0; i < 16; i++) {
            goalConfig[i] = scan.nextInt();
        }

        int[][] inputConfig2D = new int[4][4];
        int[][] goalConfig2D  = new int[4][4];

        inputConfig2D = convertTo2D(inputConfig);
        goalConfig2D = convertTo2D(goalConfig);

        /*for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                System.out.print(inputConfig2D[i][j]+ " ");
            }
        }*/

        State initialState = new State(inputConfig2D, goalConfig2D);

        //initialState.printBoard();
        Heuristics H = new Heuristics();

        //System.out.println(H.manHattan(initialState));
        //System.out.println(H.displacementH(initialState));

        //initialState.childStates();

        AStarSearch astar = new AStarSearch();
        //astar.AstarSearch(initialState, "displacement");
        System.out.println(astar.checkIsSolvable(inputConfig2D,inputConfig));





    }


}
