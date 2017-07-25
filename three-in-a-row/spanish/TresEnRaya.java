import java.util.Scanner;

public class TresEnRaya {
  char[][] tablero = new char[5][5];

  Jugador jugadorCruz;
  Jugador jugadorCirculo;

  boolean partidaTerminada = false;
  boolean juegoTerminado = false;

  public TresEnRaya(){
    this.jugadorCruz = new Jugador('X');
    this.jugadorCirculo = new Jugador('O');
  }

  public static void main(String[] args) {
    TresEnRaya juego = new TresEnRaya();
    Scanner scanner = new Scanner(System.in);
    juego.obtenerNombres(scanner);

    while(!juego.juegoTerminado){
      juego.nuevaPartida(scanner);
      boolean quierenSeguir = juego.jugadoresQuierenSeguir(scanner);
      if(quierenSeguir){
        juego.partidaTerminada = false;
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
      System.out.println("Así queda el tablero:");
      this.pintarTablero();

      if(this.jugadorCirculo.tieneTresEnRaya){
        System.out.println(this.jugadorCirculo.nombre + " tiene tres en raya, veamos si puedes empatar, " +
        this.jugadorCruz.nombre + "!");
        this.partidaTerminada = true;
      }

      this.nuevoTurno(scanner, this.jugadorCruz);
      System.out.println("Así queda el tablero:");
      this.pintarTablero();

      if(this.jugadorCruz.tieneTresEnRaya){
        if(this.jugadorCirculo.tieneTresEnRaya){
          System.out.println("Tenemos un empate!");
        }else{
          System.out.println("Enhorabuena " + this.jugadorCruz.nombre + ", has ganado!");
          this.jugadorCruz.partidasGanadas += 1;
        }
        this.partidaTerminada = true;
      }else if(this.jugadorCirculo.tieneTresEnRaya){
        System.out.println("Enhorabuena " + this.jugadorCirculo.nombre + ", has ganado!!");
        this.jugadorCirculo.partidasGanadas += 1;
      }
    }
  }

  public boolean jugadoresQuierenSeguir(Scanner scanner){
    System.out.println("Queréis seguir jugando? 0=No/1=Sí");
    int siguen = scanner.nextInt();
    return siguen == 1;
  }
  
  public void nuevoTurno(Scanner scanner, Jugador jugador) {
    boolean seHaPintadoLaCasilla = false;

    while(!seHaPintadoLaCasilla){
      int fila = jugador.pedirFila(scanner);
      int columna = jugador.pedirColumna(scanner);

      if(this.esUnaCasillaValida(fila, columna)){
        this.pintarCasilla(jugador.simbolo, fila, columna);
        seHaPintadoLaCasilla = true;
        this.comprobarFilas(jugador);
        this.comprobarColumnas(jugador);
      }else{
        System.out.println("Vuelve a intentarlo");
      }
    }
   
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

  public void comprobarDiagonalesDeIzquierdaADerecha(char simbolo) {
  }
  public void comprobarDiagonalesDeDerechaAIzquierda(char simbolo) {}

  public void obtenerNombres(Scanner scanner) {
    System.out.println("Introduce el nombre del jugador que irá a circulos");
    // this.jugadorCirculo.nombre = scanner.nextLine();
    this.jugadorCirculo.nombre = "Mario";    

    System.out.println("Introduce el nombre del jugador que irá a cruces");
    // this.jugadorCruz.nombre = scanner.nextLine();
    this.jugadorCruz.nombre = "Carlos";

    System.out.println("OK!, suerte " + jugadorCirculo.nombre + " y " + jugadorCruz.nombre + "!\n");
  }

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

}