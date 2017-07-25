import java.util.Scanner;

public class TresEnRaya {
  char[][] tablero = new char[6][6];

  Jugador jugadorCruz;
  Jugador jugadorCirculo;

  boolean rondaTerminada = false;

  public TresEnRaya(){
    this.jugadorCruz = new Jugador('X');
    this.jugadorCirculo = new Jugador('O');
  }

  public static void main(String[] args) {
    TresEnRaya juego = new TresEnRaya();
    Scanner scanner = new Scanner(System.in);
    juego.obtenerNombres(scanner);

    juego.pintarTablero();

    scanner.close();
  }

  public void inicializarPartida() {
    this.rondaTerminada = false;
  }

  public void empezar(Scanner scanner) {
    while (!this.rondaTerminada) {
      this.nuevoTurno(scanner);
    }
  }
  
  public void nuevoTurno(Scanner scanner) {
    int fila = this.jugadorCirculo.pedirFila(scanner);
    int columna = this.jugadorCirculo.pedirColumna(scanner);
  }

  public boolean esUnaPosicionValida(){
    return true;
  }

  public boolean tieneTresEnRaya(Jugador jugador) {
    return false;
  }

  public void comprobarFilas(char simbolo) {}
  public void comprobarColumnas(char simbolo) {}
  public void comprobarDiagonalmenteDeIzquierdaADerecha(char simbolo) {}
  public void comprobarDiagonalmenteDeDerechaAIzquierda(char simbolo) {}

  public void obtenerNombres(Scanner scanner) {
    System.out.println("Introde el nombre del jugador que irá a circulos");
    // this.jugadorCirculo.nombre = scanner.nextLine();
    this.jugadorCirculo.nombre = "Mario";    

    System.out.println("Introde el nombre del jugador que irá a cruces");
    // this.jugadorCruz.nombre = scanner.nextLine();
    this.jugadorCruz.nombre = "Carlos";

    System.out.println("OK!, suerte " + jugadorCirculo.nombre + " y " + jugadorCruz.nombre + "!\n");
  }

  public void pintarTablero() {
    //Primera línea con los índices de columnas
    System.out.print("  ");
    for (int i = 0; i < this.tablero.length; i++) {
      System.out.print(" " + i + "");
    }
    System.out.println();

    //Filas
    for (int i = 0; i < this.tablero.length; i++) {
      System.out.print(i + " |");
      for (int j = 0; j < this.tablero[i].length; j++) {
        if (this.tablero[i][j] != 0) {
          System.out.print(this.tablero[i][j] + "|");
        } else {
          System.out.print(" |");
        }
      }
      System.out.println();
    }
    System.out.println("\n");
  }

}