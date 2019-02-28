package BattleShip;

import java.util.Random;

public class OceanMap {

    private static final int NUM_OF_ROWS = 10;
    private static final int NUM_OF_COLUMNS  = 10;
    private static final String PLAYER = "1";
    private static final String COM_PLAYER = "2";
    private static final String HIT_PLAYER_ENEMY_SHIP = "!";
    private static final String HIT_PLAYER_OWN_SHIP = "X";
    private static final String HIT_MISS = "-";

    // Ocean map representation
    private String[][] map = new String[NUM_OF_ROWS][NUM_OF_COLUMNS];

    private int currentPlayerNoOfShips = 0;
    private int currentComNoOfShips = 0;

    public OceanMap()
    {

        // Initialize the ocean map
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[0].length; j++)
            {
                map[i][j] = " ";
            }
        }
    }


    public void PrintIntro()
    {
        System.out.println("**** Welcome to the Battle Ships game ****\n");
        System.out.println("Right now the sea is empty\n");
    }

    public void PrintGrid()
    {
        // print the body...
        System.out.println();
        for(int i = 0; i < map[0].length; i++)
        {
            System.out.print(i + "|");
            for(int j = 0; j < map.length; j++)
            {
                if(map[i][j] == PLAYER) {
                    System.out.print("@");
                }
                else if(map[i][j] == COM_PLAYER) {
                    System.out.print("#");
                }
                else
                {
                    System.out.print(map[i][j]);
                }
            }
            System.out.println("|" + i);

        }

    }

    public void PrintColumns()
    {

        System.out.print("  ");
        for(int i = 0; i < map.length; i++)
        {
            System.out.print(i);
        }
    }

    public boolean IsValidInput(int x, int y)
    {
        // We will only check the the coordinate's availability only if it is
        // in range
        if(IsValidCoordinates(x, y))
        {
            return IsCoordinateAvailable(x, y);
        }

        return false;
    }

    public boolean IsValidAttack(int x, int y)
    {
        if(IsValidCoordinates(x, y))
        {
            return IsCoordinatesAlreadyGuessed(x, y);
        }
        else
        {
            return false;
        }
    }

    private boolean IsCoordinatesAlreadyGuessed(int x, int y)
    {
        if(map[x][y] == HIT_PLAYER_ENEMY_SHIP ||
           map[x][y] == HIT_PLAYER_OWN_SHIP ||
           map[x][y] == HIT_MISS)
        {
            return false;
        }

        return true;
    }


    private boolean IsValidCoordinates(int x, int y)
    {

        if((x >= 0 && x < NUM_OF_ROWS) && (y >= 0 && y < NUM_OF_COLUMNS))
        {
            return true;
        }

        return false;
    }

    private boolean IsCoordinateAvailable(int x, int y)
    {
        if(map[x][y] == PLAYER || map[x][y] == COM_PLAYER)
        {
            System.out.println("Error: Ship present at location. Collision eminent");
            return false;
        }
        else
        {
            return true;
        }
    }

    public void EnterPlayerInput(String player, int x, int y)
    {
        map[x][y] = player;
        if(player == PLAYER) {
            currentPlayerNoOfShips++;
        }
        else
        {
            currentComNoOfShips++;
        }
    }

    public int GetParticipantNoOfShips(String player)
    {
        return player == PLAYER ? currentPlayerNoOfShips : currentComNoOfShips;
    }

    public String GetPlayerCode()
    {
        return PLAYER;
    }

    public int GenerateRandomXCoord() {
        return new Random().nextInt(NUM_OF_ROWS);
    }

    public int GenerateRandomYCoord() {
        return new Random().nextInt(NUM_OF_COLUMNS);
    }

    public void EnterAttack(int player, int x, int y)
    {
        if(String.valueOf(player).equals(PLAYER))
        {
            if(map[x][y] == COM_PLAYER)
            {
                System.out.println("Boom! You sank the ship");
                map[x][y] = HIT_PLAYER_ENEMY_SHIP;
                currentComNoOfShips--;
            }
            else if (map[x][y] == PLAYER)
            {
                System.out.println(("Oh no, you sink your own ship :("));
                map[x][y] = HIT_PLAYER_OWN_SHIP;
                currentPlayerNoOfShips--;
            }
            else
            {
                System.out.println("Sorry, you missed");
                map[x][y] = HIT_MISS;
            }
        }
        else
        {
            if(map[x][y] == COM_PLAYER)
            {
                System.out.println("The computer sank one of its own ships");
                map[x][y] = HIT_PLAYER_ENEMY_SHIP;
                currentComNoOfShips--;
            }
            else if (map[x][y] == PLAYER)
            {
                System.out.println("The computer sank one of your ships");
                map[x][y] = HIT_PLAYER_OWN_SHIP;
                currentPlayerNoOfShips--;
            }
            else
            {
                System.out.println("Computer missed");
                map[x][y] = HIT_MISS;
            }


        }
    }

}
