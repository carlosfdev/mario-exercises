import java.util.Scanner;

class Jugador {
  String nombre;
  boolean tieneTresEnRaya;
  int rondasGanadas = 0;
  char simbolo;

  public Jugador (char simbolo){
    this.simbolo = simbolo;
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