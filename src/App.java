import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

        public static void main(String args[]) throws FileNotFoundException {

        Scanner s = new Scanner(System.in);
        TubeAndBallMachine tbMachine = new TubeAndBallMachine();

        int option;

        do {
            System.out.println("--- Choose an case to be tested ---\n");
            System.out.println("1  - caso 1\n"      +
                               "2  - caso 2\n"      +
                               "3  - caso 3\n"      +
                               "4  - caso 4\n"      +
                               "5  - caso 5\n"      +
                               "6  - caso 6\n"      +
                               "7  - caso 7\n"      +
                               "8  - caso 8\n"      +
                               "9  - caso teste\n"  +
                               "0  - End\n"         +
                               "-----------------------------------");
            option = s.nextInt();

            switch(option){
                case 1: tbMachine.caseTest("caso1.txt");
                        break;

                case 2: tbMachine.caseTest("caso2.txt");
                        break;

                case 3: tbMachine.caseTest("caso3.txt");
                        break;

                case 4: tbMachine.caseTest("caso4.txt");
                        break;

                case 5: tbMachine.caseTest("caso5.txt");
                        break;

                case 6: tbMachine.caseTest("caso6.txt");
                        break;

                case 7: tbMachine.caseTest("caso7.txt");
                        break;

                case 8: tbMachine.caseTest("caso8.txt");
                        break;

                case 9: tbMachine.caseTest("caso-teste.txt");
                        break;
            }
        } while(option != 0);
        s.close();
    }
}