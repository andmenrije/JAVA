package BattleShip;

import java.util.Scanner;

public class Main {

    private static OceanMap oceanMap = new OceanMap();
    private static Scanner input;

    public static void main(String[] args) {
        input = new Scanner(System.in);

        oceanMap.PrintIntro();
        DisplayGrid();

        System.out.println();
        System.out.println();
        System.out.println("Deploy your ships:");

        PutShipPositions("1");
        DisplayGrid();

        System.out.println();
        System.out.println("Computer is deploying ships");
        PutShipPositions("2");

        RunGame();

    }

    private static void DisplayGrid()
    {
        oceanMap.PrintColumns();
        oceanMap.PrintGrid();
        oceanMap.PrintColumns();
    }

    private static void PutShipPositions(String player)
    {

        int x = 0;
        int y = 0;

        // Ask player for ship coordinates
        for ( int i = 0; i < 5; i++ )
        {

            // Ask if we are doing player 1
            if(player == "1") {
                // keep asking until we get a good answer
                do {

                    System.out.print("Enter x coordinate for your " + (oceanMap.GetParticipantNoOfShips(player) + 1) + " ship: ");
                    x = input.nextInt();
                    System.out.print("Enter y coordinate for your " + (oceanMap.GetParticipantNoOfShips(player) + 1) + " ship: ");
                    y = input.nextInt();

                }
                while (!oceanMap.IsValidInput(x, y));
            }
            else
            {
                do {
                    // We'll generate random numbers within range
                    x = oceanMap.GenerateRandomXCoord();
                    y = oceanMap.GenerateRandomYCoord();
                    System.out.println((oceanMap.GetParticipantNoOfShips(player) + 1) + ". ship DEPLOYED");
                }
                while (!oceanMap.IsValidInput(x, y));
            }

            oceanMap.EnterPlayerInput(player, x, y);
        }

    }

    private static void RunGame()
    {

        int turn = 0;
        int player = 0;
        int x = 0;
        int y = 0;
        // Loop through the game
        while(oceanMap.GetParticipantNoOfShips("1") > 0 && oceanMap.GetParticipantNoOfShips("2") > 0)
        {
            player = (turn % 2) + 1;
            //Player's turn
            if(player == 1) {
                //Ask player for coordinates
                System.out.println("YOUR TURN");
                do {
                    System.out.print("Enter X coordinate: ");
                    x = input.nextInt();
                    System.out.print("Enter Y coordinate: ");
                    y = input.nextInt();
                }
                while (!oceanMap.IsValidAttack(x, y));

            }
            //Com's turn
            else
            {
                System.out.println("COMPUTER'S TURN");
                do {
                    // We'll generate random numbers within range
                    x = oceanMap.GenerateRandomXCoord();
                    y = oceanMap.GenerateRandomYCoord();
                }
                while (!oceanMap.IsValidAttack(x, y));
            }

            //Display result
            oceanMap.EnterAttack(player, x, y);

            DisplayGrid();

            System.out.println("\nYour ships: " + String.valueOf(oceanMap.GetParticipantNoOfShips("1")) +
                    "| Computer ships: " + String.valueOf(oceanMap.GetParticipantNoOfShips("2"))) ;

            turn++;
        }

        if(oceanMap.GetParticipantNoOfShips("1") == 0)
        {
            System.out.println("Sorry, you loose.");
        }
        else
        {
            System.out.println("Hooray, you win!");
        }
    }
}
