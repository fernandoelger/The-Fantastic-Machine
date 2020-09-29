import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TubeAndBallMachine{

    private class Path{
        public int row, col;
        
        public Path(int i, int j){
            row = i;
            col = j;
        }
    }

    private Path[][] allPaths;
    private int [] totalBallsEachTube;

    private int rowsQuant;
    private int colsQuant;

    private int pathsNumber;


    public void caseTest(String casom){

        long start = System.currentTimeMillis();
        readCaseAndCreatePaths(casom);
        playGame();
        long elapsed = System.currentTimeMillis() - start;

        // just to discover the tube where most balls comes out and how many balls;
        int maior = totalBallsEachTube[0];
        int index = 0;
        for(int i=1; i < colsQuant; i++){
            if(totalBallsEachTube[i] > maior){
                maior = totalBallsEachTube[i];
                index = i;
            }
        }
        
        System.out.println("\nNumber of tubes: " + colsQuant);
        System.out.println("Tubes length: " + rowsQuant);
        System.out.println("Number of paths: " + pathsNumber);
        System.out.println("Tube where most balls comes out: " + index);
        System.out.println("Number of balls out of this tube: " + maior);
        System.out.println("Execution time: " + elapsed + " milliseconds\n\n");
    }

    public void readCaseAndCreatePaths(String casom){

        try (Scanner s = new Scanner(new File("casos-cohen/" + casom))){

            colsQuant = s.nextInt();
            // needs +1 because the tubes length starts in 0;
            rowsQuant = s.nextInt() + 1;

            allPaths = new Path[rowsQuant][colsQuant];

            while(s.hasNext()){
                int starCol  = s.nextInt();
                int startRow = s.nextInt();
                int endCol   = s.nextInt();
                int endRow   = s.nextInt();
                pathsNumber++;

                allPaths[startRow][starCol] = new Path(endRow, endCol); 
            }
            s.close();

        } catch (FileNotFoundException e){
            e.getMessage();
        }
    }

    private void playGame() {
 
        totalBallsEachTube = new int [colsQuant];
        int row = 0;
        int col, rowAux;

        for(int i = 0; i < colsQuant; i++) {
            col = i;

            while(row != rowsQuant){
                
                if(allPaths[row][col] == null){
                    row++;
                } else {
                    rowAux = allPaths[row][col].row;
                    col    = allPaths[row][col].col;

                    row = rowAux;
                }
            }

            totalBallsEachTube[col]++;
            row = 0;
        }
    }
}