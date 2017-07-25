import java.util.Scanner;

class Jugador {
  String nombre;
  boolean tieneTresEnRaya;
  int partidasGanadas;
  char simbolo;

  public Jugador (char simbolo){
    this.simbolo = simbolo;
    this.tieneTresEnRaya = false;
    this.partidasGanadas = 0;
  }

  public int pedirFila(Scanner scanner){
    System.out.println(this.nombre + ", tu turno! Dime fila");
    return scanner.nextInt();
  }
  public int pedirColumna(Scanner scanner){
    System.out.println("Y la columna");
    return scanner.nextInt();
  }
}