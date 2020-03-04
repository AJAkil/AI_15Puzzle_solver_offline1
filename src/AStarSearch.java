import java.util.*;

class diplacementComparator implements Comparator<State>{
     @Override
     public int compare(State o1, State o2) {
         int f1 = o1.getF_cost();
         int f2 = o2.getF_cost();
         return f1-f2;
     }
 }


public class AStarSearch {

     public boolean AstarSearch(State startState, String heauristic){
         boolean result = false;
         //a PQ to handle the expanded fringes
         PriorityQueue<State>pq = new PriorityQueue<>(new diplacementComparator());
         //a set to handle the visited nodes or states
         Queue<State> visited = new LinkedList<>();

         Heuristics h = new Heuristics();

         pq.add(startState);
         State currentState;

         //the body of the main loop runs till the queue is empty
         while (!pq.isEmpty()){
             //retrieves and returns the head of the queue
             /*System.out.println("Printing the states of PQ-------------------------------------------------");
             printPQStateInfo(pq);
             System.out.println("----------------------------------------------------------------------------------");*/
             currentState = pq.poll();
             //System.out.println(visited.size());
             //System.out.println("The current popped state ");
             ///currentState.printBoard();
             //System.out.println(currentState.getF_cost());
            //adding to the visited Q after popping from the PQ
             visited.add(currentState);

             //checking to see if the current state is the goal state or not
             if(currentState.checkEquality()){
                    result = true;
                    //printPath(visited);
                 printPath(currentState);
                    break;
             }else{
                 //Generate all the possible child state from the current state of the board
                 ArrayList<State>expandedStates = currentState.childStates();

                // System.out.println("List of all expanded states: ");
                 /*for(State x:expandedStates){
                     x.printBoard();
                 }*/

                 //iterate through the list and check if the expanded states already exists on the lists or not
                 for (State childState : expandedStates){
                     //if visited queue contains child State or not
                     //System.out.println("checking if visited Q has a state or not");
                     //System.out.println("---------");
                     //childState.printBoard();
                     boolean isVisited = checkStateQ(childState.getCurrentBoardState(), visited);
                     boolean isInPQ = false;
                     if(pq.size()>0){
                          isInPQ = checkStatePQ(childState.getCurrentBoardState(),pq);
                     }

                     //System.out.println(isVisited);
                     //System.out.println("---------");
                     //System.out.println();
                     if(!isVisited){
                         //if PQ contains the child State or not
                         //System.out.println(isInPQ);
                         if(!isInPQ){
                             //calculating the costs here
                             childState.setG_cost(currentState.getG_cost()+1);
                             childState.setH_cost(h.callHeursitic(heauristic,childState));
                             childState.setF_cost(childState.getG_cost()+childState.getH_cost());
                             childState.setParentState(currentState);
                             //after cost calculations, we add it to the PQ
                             //System.out.println("We are adding this state to the queue");
                             //System.out.println("----------");
                             //childState.printBoard();
                             //System.out.println("----------");
                             pq.add(childState);
                         }
                     }
                 }
             }
         }
        return result;
     }

    public void printPath(Queue<State> visited) {
         State state;
         System.out.println("The total number of steps = "+visited.size());
         while (!visited.isEmpty()){
             state = visited.poll(); //getting the top elements
             System.out.println("-------------");
             state.printBoard();
             System.out.println("g cost = "+state.getG_cost());
             System.out.println("h cost = "+state.getH_cost());
             System.out.println("f cost = "+state.getF_cost());
             System.out.println("-------------");
         }
    }

    public boolean checkStateQ(int[][] currentBoard, Queue<State> visited){
         boolean flag = true;
         Iterator value = visited.iterator();
        while (value.hasNext()){
            State qBoardState = (State)value.next();
            int[][] qBoard = qBoardState.getCurrentBoardState();
            //System.out.println("Printing the current board state of the queue---------");
            //qBoardState.printBoard();
            flag = true;
            for (int i = 0; i < qBoard.length ; i++) {
                for (int j = 0; j < qBoard.length ; j++) {
                    if(qBoard[i][j]!=currentBoard[i][j]){
                        flag = false;
                    }
                }
            }
            if(flag) return true;
        }
        return flag;
    }


    //debugger function to print the PQ stuffs
    public void printPQStateInfo(PriorityQueue<State>pq){
         Iterator value = pq.iterator();
         while (value.hasNext()){
             State state = (State) value.next();
             System.out.println("------------------");
             state.printBoard();
             System.out.println("G cost = "+state.getG_cost());
             System.out.println("H cost = "+state.getH_cost());
             System.out.println("F cost = "+state.getF_cost());
             System.out.println("------------------");

         }
    }

    public boolean checkStatePQ(int[][] currentBoard, PriorityQueue<State> pq){
        Iterator value = pq.iterator();
        boolean flag = true;
        while (value.hasNext()){
            //System.out.println("here");
            State state = (State) value.next();
            int[][] pqBoard = state.getCurrentBoardState();
            //state.printBoard();
            flag = true;
            for (int i = 0; i < pqBoard.length; i++) {
                for (int j = 0; j < pqBoard.length ; j++) {
                    if(currentBoard[i][j]!=pqBoard[i][j]){
                        flag = false;
                    }
                }
            }

            if(flag) return true;

        }
         return flag;
    }

    public void printPath(State state){
         if(state.getParentState()!=null)
            printPath(state.getParentState());

         state.printBoard();
        System.out.println();
        System.out.println();
        System.out.println();
    }


}
