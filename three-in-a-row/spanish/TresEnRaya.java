import java.util.Scanner;
import java.util.Arrays;

public class TresEnRaya {
  char[][] tablero;

  Jugador jugadorCruz;
  Jugador jugadorCirculo;

  boolean partidaTerminada = false;
  boolean juegoTerminado = false;

  public TresEnRaya(int filas, int columnas){
    this.tablero = new char[filas][columnas];

    this.jugadorCruz = new Jugador('X');
    this.jugadorCirculo = new Jugador('O');
  }

  public static void main(String[] args) {
    int filas = 8;
    int columnas = 8;

    TresEnRaya juego = new TresEnRaya(filas, columnas);
    Scanner scanner = new Scanner(System.in);
    juego.obtenerJugadores(scanner);

    while(!juego.juegoTerminado){
      juego.nuevaPartida(scanner);
      if(juego.jugadoresQuierenSeguir(scanner)){
        juego.reiniciarTablero(filas, columnas);
      }else{
        juego.juegoTerminado = true;
      };
    }

    System.out.println(juego.jugadorCirculo.nombre + " ha ganado " + juego.jugadorCirculo.partidasGanadas + " veces");
    System.out.println(juego.jugadorCruz.nombre + " ha ganado " + juego.jugadorCruz.partidasGanadas + " veces");
    System.out.println("Hasta otra!");

    scanner.close();
  }

  public void nuevaPartida(Scanner scanner) {
    while (!this.partidaTerminada) {
      this.pintarTablero();

      this.nuevoTurno(scanner, this.jugadorCirculo);

      //Si el jugador de circulos tiene tres en raya, el jugador de cruces solo podría empatar, pero la ronda ya ha terminado
      if(this.jugadorCirculo.tieneTresEnRaya){
        System.out.println(this.jugadorCirculo.nombre + " tiene tres en raya, veamos si puedes empatar, " +
        this.jugadorCruz.nombre + "!");
        this.partidaTerminada = true;
      }

      this.nuevoTurno(scanner, this.jugadorCruz);

      //Si el jugador de cruces tiene tres en raya
      if(this.jugadorCruz.tieneTresEnRaya){
        //Y el de circulos también, hay empate
        if(this.jugadorCirculo.tieneTresEnRaya){
          System.out.println("Tenemos un empate!");
        //Y el de circulos no, gana el de cruces
        }else{
          System.out.println("Enhorabuena " + this.jugadorCruz.nombre + ", has ganado!");
          this.jugadorCruz.partidasGanadas += 1;
        }
        this.partidaTerminada = true;
      //Si el jugador de cruces no tiene tres en raya, pero el de circulos sí, es el ganador.
      }else if(this.jugadorCirculo.tieneTresEnRaya){
        System.out.println("Enhorabuena " + this.jugadorCirculo.nombre + ", has ganado!!");
        this.jugadorCirculo.partidasGanadas += 1;
      }
    }
  }

  public void nuevoTurno(Scanner scanner, Jugador jugador) {
    boolean seHaPintadoLaCasilla = false;

    while(!seHaPintadoLaCasilla){
      int fila = jugador.pedirFila(scanner);
      int columna = jugador.pedirColumna(scanner);

      if(this.esUnaCasillaValida(fila, columna)){
        this.pintarCasilla(jugador.simbolo, fila, columna);
        seHaPintadoLaCasilla = true;
        //Después de pintar la casilla comprueba si el jugador tiene Tres en raya
        this.comprobarFilas(jugador);
        this.comprobarColumnas(jugador);
      }else{
        System.out.println("Vuelve a intentarlo");
      }
    }

    System.out.println("Así queda el tablero:");
    this.pintarTablero();
  }

  public void reiniciarTablero(int filas, int columnas){
      this.tablero = new char[filas][columnas];

      this.partidaTerminada = false;
      this.jugadorCirculo.tieneTresEnRaya = false;
      this.jugadorCruz.tieneTresEnRaya = false;
  }

  public boolean esUnaCasillaValida(int fila, int columna){
    if (fila < 0 || columna < 0 || fila >= this.tablero.length || columna >= this.tablero[0].length){
      System.out.println("Esa casilla no está en el tablero!");
      return false;
    }

    if (this.tablero[fila][columna] != 0){
      System.out.println("Esa casilla ya está en uso!");
      return false;
    }
  
    return true;
  }

  public void pintarCasilla(char simbolo, int fila, int columna){
    this.tablero[fila][columna] = simbolo;
  }

  public void comprobarFilas(Jugador jugador) {
    int coincidenciasSeguidas = 0;
    for (int fila = 0; fila < this.tablero.length; fila++) {
      if(jugador.tieneTresEnRaya){
        break;
      }
      for (int columna = 0; columna < this.tablero[fila].length; columna++) {
        if(tablero[fila][columna] == jugador.simbolo){
          coincidenciasSeguidas++;
          if(coincidenciasSeguidas >= 3){
            jugador.tieneTresEnRaya = true;
            break;
          }
        }else{
          coincidenciasSeguidas = 0;
        }
      }
    }
  }

  public void comprobarColumnas(Jugador jugador) {
    int coincidenciasSeguidas = 0;
    for (int columna = 0; columna < this.tablero[0].length; columna++) {
      if(jugador.tieneTresEnRaya){
        break;
      }
      for (int fila = 0; fila < this.tablero.length; fila++) {
        if(tablero[fila][columna] == jugador.simbolo){
          coincidenciasSeguidas++;
          if(coincidenciasSeguidas >= 3){
            jugador.tieneTresEnRaya = true;
            break;
          }
        }else{
          coincidenciasSeguidas = 0;
        }
      }
    }
  }

  //TODO
  public void comprobarDiagonalesDeIzquierdaADerecha(Jugador jugador) {}
  //TODO
  public void comprobarDiagonalesDeDerechaAIzquierda(char simbolo) {}

  public void pintarTablero() {
    //Primera línea con los índices de columnas
    System.out.print("  ");
    for (int i = 0; i < this.tablero[0].length; i++) {
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

  public void obtenerJugadores(Scanner scanner) {
    System.out.println("Introduce el nombre del jugador que irá a circulos");
    //Comentado para probar rápidamente
    // this.jugadorCirculo.nombre = scanner.nextLine();
    this.jugadorCirculo.nombre = "Mario";    

    System.out.println("Introduce el nombre del jugador que irá a cruces");
    //Comentado para probar rápidamente
    // this.jugadorCruz.nombre = scanner.nextLine();
    this.jugadorCruz.nombre = "Carlos";

    System.out.println("OK!, suerte " + jugadorCirculo.nombre + " y " + jugadorCruz.nombre + "!\n");
  }

  public boolean jugadoresQuierenSeguir(Scanner scanner){
    System.out.println("Queréis seguir jugando? 0=No/1=Sí");
    int siguen = scanner.nextInt();
    return siguen == 1;
  }

}