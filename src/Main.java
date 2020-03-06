import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

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


    public static void main(String[] args) throws FileNotFoundException {
        int[] inputConfig = new int[16];
        int[] goalConfig = new int[16];
        AStarSearch astar = new AStarSearch();
        Heuristics H = new Heuristics();
        int[][] inputConfig2D = new int[4][4];
        int[][] goalConfig2D = new int[4][4] ;

        File file = new File("input.txt");
        Scanner scan = new Scanner(file);

        int counter = 0;
        while (scan.hasNextLine()){
            counter++;

            if(counter == 1) scan.nextLine(); //reading the first input value

            if(counter!=1){
                String[]tileValues = scan.nextLine().split(" "); //reading the second lines and spitting them to an array of strings

                if(counter == 2){

                    for (int i = 0; i < tileValues.length ; i++) {
                        goalConfig[i] = Integer.parseInt(tileValues[i]);
                    }

                    goalConfig2D  = convertTo2D(goalConfig);

                }else if(counter > 2){

                    for (int i = 0; i < tileValues.length ; i++) {
                        inputConfig[i] = Integer.parseInt(tileValues[i]);
                    }

                    inputConfig2D  = convertTo2D(inputConfig);


                    //Main Algorithm
                    boolean isInitSolv = astar.checkIsSolvable(inputConfig2D,inputConfig);

                    if(isInitSolv){

                        State initialState = new State(inputConfig2D, goalConfig2D);

                        astar.AstarSearchEff(initialState, "manhattan");

                        astar.AstarSearchEff(initialState, "displacement");

                    }else{
                        System.out.println("Goal State is not reachable from the initial state");
                    }


                }




            }

        }





        }

    }

