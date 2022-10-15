import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map{
  //2D array for map
  private char [][] map;
  private boolean [][] revealed;

  private static Map instance = null;

/**
Constructs the 2d arrays and loads the first map
*/
  public Map(){
    map = new char [5][5];
    revealed = new boolean [5][5];
    loadMap(1);
  }


  public static Map getInstance(){
	  if (instance == null){
		  instance = new Map();
	  }
	  return instance;

  }
/**
@param the map number
Loads the map 
*/
 public void loadMap(int mapNum){
	  File mapFile;
		Scanner input;

		if (mapNum >= 4) {
			mapNum = (mapNum % 4) + 1;
		}
		switch (mapNum) {
			case 1:
				mapFile = new File("Area1.txt");
				break;

			case 2:
				mapFile = new File("Area2.txt");
				break;

			case 3:
				mapFile = new File("Area3.txt");
				break;

			default:
				System.out.println("Back to area 1");
				mapFile = new File("Area1.txt");
				break;
		}
		try {
			input = new Scanner(mapFile);
			String nextLine = "";

			// Y index of row being initialized on map
			int yValue = 0;

			while (input.hasNextLine()) {
				nextLine = input.nextLine();

				// Removes white space from line read in
				nextLine = nextLine.replaceAll("\\s", "");

				// Insert single row into map
				for (int x = 0; x < nextLine.length(); x++) {
					map[yValue][x] = nextLine.charAt(x);
				}
				yValue++;
			}
			// Make map hidden to user
			for (int y = 0; y < 5; y++) {
				for (int x = 0; x < 5; x++) {
					revealed[y][x] = false;
				}
			}
			input.close();
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
		}
 } 
  
  /*
  @param p point coordinates of location
  */
  public char getCharAtLoc(Point p){
	  if (p.getY() < map.length && p.getY() >= 0 && p.getX() < map.length && p.getX() >= 0) {
			return map[p.getX()][p.getY()];
		} else {
			return '0';
		}
  } 

  /*Display map 
  @param p point coordinates of location
  */
  public String mapToString(Point p){
	  String rString = "";
	    int xCoord = p.getX();
	    int yCoord = p.getY();

	    for (int i = 0; i < 5; i++){
	        rString += "\n";
	        for (int j = 0; j < 5; j++){
	            if (i == xCoord & j == yCoord){
	              rString += ("* ");
	            }else if (revealed[i][j] == true){
	                rString += (map[i][j] + " ");
	            }else {
	                rString += ('X' + " ");
	            }
	        }
	    }
	    return rString;
  }
  
  /*
  Find start point on the map
  */
  public Point findStart(){	  
	  Point start = new Point();
	    for (int i = 0; i < map.length; i++) {
	      for (int j = 0; j < map.length; j++) {
	        if (map[i][j] == 's') {
	          start.setLocation(i, j);
	        }
	      }
	    }
	    return start;
  }
  /*reveal the visited location
  */
  public void reveal(Point p){
	  revealed[p.getX()][p.getY()] = true;
  }
  //remove a char on map
  public void removeCharAtLoc(Point p){
	  map[p.getX()][p.getY()] = 'n';
  }


}