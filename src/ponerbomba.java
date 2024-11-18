import java.util.Scanner;

public class ponerbomba {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear la matriz (ajusta el tamaño según tus necesidades)
        int filas = 10;
        int columnas = 10;
        int[][] matriz = new int[filas][columnas];

        // Inicializar la matriz (puedes cambiar esto)
        // ... (aquí puedes inicializar la matriz con valores aleatorios o de otra forma)

        int opcion;
        do {
            // Mostrar el menú
            System.out.println("Menú:");
            System.out.println("1. Mostrar matriz");
            System.out.println("2. Poner bomba");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    mostrarMatriz(matriz);
                    break;
                case 2:
                    ponerBomba(matriz, scanner);
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    // Función para mostrar la matriz
    public static void mostrarMatriz(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Función para poner una bomba
    public static void ponerBomba(int[][] matriz, Scanner scanner) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        System.out.print("Ingrese la coordenada x (fila): ");
        int x = scanner.nextInt();
        System.out.print("Ingrese la coordenada y (columna): ");
        int y = scanner.nextInt();

        // Validar coordenadas
        if (x < 0 || x >= filas || y < 0 || y >= columnas) {
            System.out.println("Coordenadas inválidas. Intente nuevamente.");
            return;
        }

        // Calcular la explosión
        int explosion = calcularExplosion(matriz, x, y);
        System.out.println("Valor de la explosión: " + explosion);

        // Poner a cero los valores de la fila y columna
        for (int i = 0; i < filas; i++) {
            matriz[i][y] = 0;
        }
        for (int j = 0; j < columnas; j++) {
            matriz[x][j] = 0;
        }

        // Verificar si quedan valores no nulos
        boolean hayValoresNoNulos = false;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] != 0) {
                    hayValoresNoNulos = true;
                    break;
                }
            }
            if (hayValoresNoNulos) {
                break;
            }
        }

        if (!hayValoresNoNulos) {
            System.out.println("¡No quedan valores no nulos! Fin del juego.");
        }
    }

    // Función para calcular la explosión
    public static int calcularExplosion(int[][] matriz, int x, int y) {
        int explosion = 0;
        for (int i = 0; i < matriz.length; i++) {
            explosion += matriz[i][y];
        }
        for (int j = 0; j < matriz[0].length; j++) {
            explosion += matriz[x][j];
        }
        // Restar el valor de la intersección (se suma dos veces)
        explosion -= matriz[x][y];
        return explosion;
    }
}