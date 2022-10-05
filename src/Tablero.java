
import java.util.Scanner;


public class Tablero {



    static Scanner sc = new Scanner(System.in);
    static boolean[][] universe;
    static boolean[][] tempUniverse;
    static boolean continuar = true;
    static int genCounter = 0;
    static byte repCounter = 0;

    public static void main (String[] args) throws InterruptedException{

        universe = universeInicializer();
        tempUniverse = ArrayCloner(universe);
        universePrinter(universe);
        universePrinter(tempUniverse);

        while(continuar){
            genCounter += 1;
            Thread.sleep(200);
            clearScreen();

            tempUniverse = ArrayCloner(universe);
            CheckEachCellOfArray(universe);

            //universe = CheckEachCellOfArray(universe);

            universePrinter(universe);
            System.out.println("Generacion: " + genCounter);

            boolean result = ArrayComparator(universe,tempUniverse);

            if(result)
                repCounter +=1;

            else
                repCounter = 0;

            if (repCounter >= 4)
                continuar = false;

        }

        System.out.println("Salió del sistema");

    }

    static boolean[][] universeInicializer(){

        byte length = 2;
        byte mode = 0;
        boolean errorProof = true;

        while (errorProof){
            System.out.println(" Por favor, introduzca el lado de su array en un rango de 0 a 25: ");
            length = sc.nextByte();
            if(length < 2 || length > 25)
                System.out.println("Error: Numero no valido.");
            else
                errorProof = false;
        }


        boolean[][] arrayUniverse = new boolean[length][length];
        errorProof = true;
        while (errorProof){

            System.out.println("Por favor, Seleccione uno de los siguientes metodos de inicializacion de espacio: ");
            System.out.println("1. Manual");
            System.out.println("2. Automatico");
            System.out.println("3. Predeterminado");
            mode = sc.nextByte();

            if(mode > 3 || mode <= 0)
                System.out.println("Seleccion no valida.");
            else
                errorProof = false;


        }


        switch (mode){
            case 1:
                System.out.println("Modo Manual seleccionado. ");
                universeManualFiller(arrayUniverse);

                break;
            case 2:
                System.out.println("Modo Automatico seleccionado. ");
                universeAutomaticFiller(arrayUniverse);
                break;
            case 3:
                System.out.println("Modo Predeterminado seleccionado. ");
                universePredeterminedFiller(arrayUniverse);
                break;
        }

        return arrayUniverse;
    }


    static void universePrinter(boolean[][] arrayUniverse){
        System.out.print("╔");
        for (int i = 0; i < arrayUniverse.length; i++) {
            System.out.print("===");
        }
        System.out.print("╗");
        for (int i = 0; i < arrayUniverse.length; i++) {
            System.out.println();
            System.out.print("║");
            for (int j = 0; j < arrayUniverse.length; j++) {
                if(arrayUniverse[i][j])
                    System.out.print("▓▓ ");
                else
                    System.out.print("   ");
            }
            System.out.print("║");
        }

        System.out.println();

        System.out.print("╚");
        for (int i = 0; i < arrayUniverse.length; i++) {
            System.out.print("===");
        }
        System.out.println("╝");
    }

    static  boolean[][] ArrayCloner(boolean[][] universe){

        boolean[][] clonedUniverse = new boolean[universe.length][universe.length];

        for (int i = 0; i < universe.length ; i++) {
            System.arraycopy(universe[i], 0, clonedUniverse[i], 0, universe.length);
        }

        return clonedUniverse;

    }

    static boolean ArrayComparator(boolean[][] universe, boolean[][] tempUniverse){

        boolean logicValue = true;
        for (int i = 0; i <  universe.length; i++) {
            for (int j = 0; j < universe.length ; j++) {
                if(universe[i][j] != tempUniverse[i][j]){
                    logicValue = false;
                    break;
                }
            }
        }

        return logicValue;
    }

    static boolean[][] universeAutomaticFiller(boolean[][] arrayUniverse){
        boolean errorProof = true;
        byte percentAlive = 0;

        while (errorProof){
            System.out.println("Introduzca de 0 a 100 el porcentaje de celulas en total que deben de aparecer: ");
            percentAlive = sc.nextByte();
            if(percentAlive<0 || percentAlive>100)
                System.out.println("Error: Porcentaje no valido");
            else
                errorProof = false;
        }

        for (int i = 0; i < arrayUniverse.length ; i++) {
            for (int j = 0; j < arrayUniverse.length ; j++) {
                if(Math.floor(Math.random()*100+1) <= percentAlive){
                    arrayUniverse[i][j] = true;
                }
                else
                    arrayUniverse[i][j] = false;
            }
        }

        return arrayUniverse;
    }

    static boolean[][] universePredeterminedFiller(boolean[][] arrayUniverse){


        for (int i = 0; i < arrayUniverse.length; i++) {
            for (int j = 0; j < arrayUniverse.length; j++) {
                arrayUniverse[i][j] = false;
            }
        }

        System.out.println("Figuras predeterminadas disponibles: ");
        for (int i = 0; i < arrayOfPredeterminedShapes.length; i++) {
            System.out.println((i+1)+"."+ predeterminedShapesNames[i]);
        }

        byte figureIndex;
        System.out.print("Escriba el numero (1 a " + arrayOfPredeterminedShapes.length + " ) de la figura que desea visualizar: ");
        figureIndex = sc.nextByte();
        figureIndex = (byte) (figureIndex - 1);
        for (int i = 0; i <arrayOfPredeterminedShapes[figureIndex].length; i++) {
            arrayUniverse[arrayOfPredeterminedShapes[figureIndex][i][0]][arrayOfPredeterminedShapes[figureIndex][i][1]] = true;
        }



        return arrayUniverse;
    }

    static boolean[][] universeManualFiller(boolean[][] arrayUniverse) {
        boolean errorProof = true;
        byte percentAlive = 0;
        byte decision = 0;

        for (int i = 0; i < arrayUniverse.length; i++) {
            for (int j = 0; j < arrayUniverse.length; j++) {
                arrayUniverse[i][j] = false;
            }
        }
        while (errorProof){
            System.out.println("Introduzca de 0 a 100 el porcentaje de celulas en total que deben de aparecer: ");
            percentAlive = sc.nextByte();
            if(percentAlive<0 || percentAlive>100)
                System.out.println("Error: Porcentaje no valido");
            else
                errorProof = false;
        }

        errorProof = true;

        int cellQuantity = ((arrayUniverse.length) * (arrayUniverse.length) * (percentAlive))/100;

        System.out.println("Cantidad de celdas a ingresar: " + cellQuantity + "En un espacio de: " + arrayUniverse.length * arrayUniverse.length);

        System.out.println(" ¿Desea cambiar el numero de celdas a ingresar? ");

        while (errorProof){
            System.out.println("Seleccione: ");
            System.out.println("1. Si");
            System.out.println("2. No");

            decision = sc.nextByte();

            if(decision !=1 || decision != 2 )
                System.out.println("Opción no valida.");
            else
                errorProof = false;
        }



        switch (decision){
            case 1:
                System.out.println("Numero de celdas a ingresar deseado: ");
                cellQuantity = sc.nextInt();
                break;
            case 2:
                System.out.println("Ok, pa.");
                break;
        }

        int xCord = 0;
        int yCord = 0;
        for (int i = 0; i < cellQuantity; i++) {
            System.out.println("Celda: " + i);
            System.out.println("Coordenada X: ");
            xCord = sc.nextInt();
            System.out.println("Coordenada Y: ");
            yCord = sc.nextInt();
            arrayUniverse[yCord][xCord] = true;
        }


        return  arrayUniverse;
    }

    static boolean[][] CheckEachCellOfArray(boolean[][] arrayOfCells){
        boolean[][] arrayOfCellsClone = new boolean[arrayOfCells.length][arrayOfCells.length];
        byte counter = 0;

        for (int i = 0; i < arrayOfCells.length; i++) {
            for (int j = 0; j < arrayOfCells.length; j++) {
                arrayOfCellsClone[i][j] = arrayOfCells[i][j];
            }

        }

        for (int i = 0; i < arrayOfCells.length ; i++) { // i sería el indice que nos ayude a recorrer el eje de las Y
            System.out.println();
            for (int j = 0; j < arrayOfCells.length; j++) { // j sería el indice que nos ayude recorrer el eje X
                counter = CheckNeighborsOfCell(i,j,arrayOfCellsClone);
                arrayOfCells[i][j] = setCellState(i,j,counter,arrayOfCellsClone);

            }
        }
        return arrayOfCells;
    }

    static byte CheckNeighborsOfCell(int y, int x, boolean[][] arrayOfCellsClone){
        byte aliveCounter = 0;
        //Este Loop For nos servirá para revisar las 8 posibles celdas de nuestra celda con coordenadas x y y
        for (int i = 0; i < arrayOfDirections.length; i++) {
            //Condición If que nos permite quedarnos dentro de los limites de nuestro arrayTablero
            if(y+arrayOfDirections[i][0] < 0 || y+arrayOfDirections[i][0] >= arrayOfCellsClone.length || x+arrayOfDirections[i][1] < 0 || x+arrayOfDirections[i][1] >= arrayOfCellsClone.length)
                continue;
            else if (arrayOfCellsClone[y+arrayOfDirections[i][0]][x+arrayOfDirections[i][1]])
                aliveCounter += 1;
        }
        return aliveCounter;
    }


    static boolean setCellState(int y, int x, byte aliveCounter, boolean[][] arrayOfCellsClone){
        boolean value = arrayOfCellsClone[y][x];
        if(!arrayOfCellsClone[y][x] && aliveCounter==3)
            value = true;
        else if (arrayOfCellsClone[y][x] && (aliveCounter==2 || aliveCounter==3))
            value = true;
        else if (arrayOfCellsClone[y][x] && (aliveCounter==0 || aliveCounter==1))
            value = false;
        else if (aliveCounter >= 4)
            value = false;

        return value;
    }

    //Metodo según para borrar la consola (Cosa que no hace)
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    //reglas para las condiciones de vivir o morir:
    // Si MUERTA y 3 celulas VIVAS entonces NACE
    // Si VIVA y 3 o 2 celulas VIVAS entonces SIGUE VIVA
    // Si VIVA y 0 o 1 celulas MUERTAS entonces MUERTA
    // Si 4 o más celulas VIVAS entonces MUERTA



    //procedimiento para revisado de celulas vecinas
    //Empezando por el lado derecho en contra se las manechillas del relog:
    // (x+1) --> (x+1, y+1) --> (y+1) --> (x-1,y+1) --> (x-1) --> (x-1, y-1) --> (y-1) --> (x+1, y-1)

    //El siguiente array nos ayuda como auxiliar para seguir el orden de comprobacion anteriormente mencionado.
     static byte[][] arrayOfDirections = {
            { 0, 1}, //(x+1)
            { 1, 1}, //(x+1, y+1)
            { 1, 0}, //(y+1)
            { 1,-1}, //(x-1,y+1)
            { 0,-1}, //(x-1)
            {-1,-1}, //(x-1, y-1)
            {-1, 0}, //(y-1)
            {-1,+1} //(x+1, y-1)

    };

    static byte[][] arraySpacePlane = {
            {0,1},
            {1,2},
            {2,0},
            {2,1},
            {2,2},
    };
    static byte[][] arrayCaos = {
            {0,1},
            {0,2},
            {1,2},
            {2,0},
            {2,1},
            {2,2},
    };

    static  byte[][][] arrayOfPredeterminedShapes = {
        arraySpacePlane,
        arrayCaos
    };

    static String[] predeterminedShapesNames = {
            "SpacePlane",
            "Caos"
    };



}


/*Suponga tenga un array de [3][3]


  array = El primer indice nos sirve para navegar en el eje de las Y y el segundo indice nos permite revisar el eje de las X
  {1,2,3}, primer indice = 0
  {1,2,3}, primer indice = 1
  {1,2,3} primer indice = 2
  }


 */
