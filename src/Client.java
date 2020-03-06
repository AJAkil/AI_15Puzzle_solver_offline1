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
        AStarSearch astar = new AStarSearch();
        Heuristics H = new Heuristics();
        int[][] inputConfig2D;
        int[][] goalConfig2D ;

        System.out.print("Give the value of instances: n = ");
        int n = scan.nextInt();

        System.out.println("Please Give the input configuration(fixed)");
        for (int i = 0; i < 16; i++) {
            inputConfig[i] = scan.nextInt();
        }

        int counter = n-1;
        for (int i = 2; i <=n ; i++) {

            System.out.println("Please Give the goal configuration( Total "+Integer.toString(n-1)+" )"+" times. (left : "+counter +" ) times");

            for (int j = 0; j < 16; j++) {
                goalConfig[j] = scan.nextInt();
            }

            inputConfig2D = convertTo2D(inputConfig);
            goalConfig2D = convertTo2D(goalConfig);

            boolean isInitSolv = astar.checkIsSolvable(inputConfig2D,inputConfig);
            boolean isGoalSov = astar.checkIsSolvable(goalConfig2D,goalConfig);

            if (isInitSolv && isGoalSov ) {
                System.out.println("yes solvable proceed");
                State initialState = new State(inputConfig2D, goalConfig2D);

                //initialState.printBoard();


                //System.out.println(H.manHattan(initialState));
                //System.out.println(H.displacementH(initialState));

                //initialState.childStates();

                long startTime = System.currentTimeMillis();

                astar.AstarSearchEff(initialState, "manhattan");

                long endTime = System.currentTimeMillis();

                System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");

                 long startTime2 = System.currentTimeMillis();

                astar.AstarSearchEff(initialState, "displacement");

                 long endTime2 = System.currentTimeMillis();

                System.out.println("Time taken: " + (endTime2 - startTime2) + " milliseconds");

                System.out.println("Summary: ");
                System.out.println("Time taken: ");
                System.out.println("Manhattan: "+(endTime - startTime)+" "+"Displacement: "+(endTime2 - startTime2));

                //System.out.println(astar.checkIsSolvable(inputConfig2D,inputConfig));

            }else{
                if(!isGoalSov && !isInitSolv)
                    System.out.println("Both of the state is not solvable");
                else if(!isGoalSov)
                    System.out.println("Goal configuration is not solvable");
                else
                    System.out.println("Initial configuration is not solvable");
            }

            counter--;
        }







        /*for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {
                System.out.print(inputConfig2D[i][j]+ " ");
            }
        }*/







    }


}
