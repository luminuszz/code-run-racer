public class Main {
    public static void main(String[] args) {
        Corrida corrida1 = new Corrida();
        corrida1.criaPista(45);
        corrida1.inicia();
    }
}

class Corrida {
    private String[] pista0;
    private String[] pista1;
    private int[] alt;
    private int currentPos1;
    private int currentPos2;

    public void criaPista(int tamanho) {
        pista0 = new String[tamanho];
        pista0[0] = "#";
        pista1 = new String[tamanho];
        pista1[0] = "#";
        alt = new int[tamanho];
        for (int i = 1; i < alt.length; i++) {
            pista0[i] = String.valueOf(' ');
            pista1[i] = String.valueOf(' ');
            if (i % 2 == 0) {
                alt[i] = 0;
            } else {
                alt[i] = 1;
            }
        }
    }

    public void inicia() {
        for (int i = 0; i < alt.length; i++) {
            System.out.print("--");
        }
        System.out.print("\033[B");
        System.out.print("\033[B");
        System.out.println();
        for (int i = 0; i < alt.length; i++) {
            System.out.print("--");
        }
        System.out.print("\033[A");
        System.out.print("\033[A");
        alterna();
        for (String a : pista1) {
            System.out.print(a + " ");
        }
        System.out.print("\r");
        fim(currentPos1, currentPos2);
    }

    private void alterna() {
        for (int j : alt) {
            if (j == 0) {
                currentPos1 = carro(currentPos1, j);
                System.out.print("\r");
                System.out.print("1");
                for (String a : pista0) {
                    System.out.print(a + " ");
                }
                System.out.print("\r");
                System.out.print("\033[B");
            } else {
                currentPos2 = carro(currentPos2, j);
                System.out.print("2");
                for (String a : pista1) {
                    System.out.print(a + " ");
                }
                System.out.print("\r");
                System.out.print("\033[A");
            }
        }
    }

    private int carro(int currentPos, int alt) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int randomSpeed = (int) (Math.random() * 3) + 1;
        for (int i = 0; i < randomSpeed; i++) {
            if (currentPos == pista0.length - 1 || currentPos1 == pista0.length - 1 || currentPos2 == pista0.length - 1) {
                fim(currentPos1, currentPos2);
            } else {
                if (alt == 0) {
                    pista0[currentPos] = " ";
                    pista0[currentPos + 1] = "#";
                    currentPos++;
                } else {
                    pista1[currentPos] = " ";
                    pista1[currentPos + 1] = "#";
                    currentPos++;
                }
            }
        }
        return currentPos;
    }

    private void fim(int pos1, int pos2) {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println((pos1 + 1) + " x " + (pos2 + 1));

        if (pos1 > pos2) {
            System.out.println();
            System.out.print("carro 1 venceu!");
            System.exit(0);
        } else if (pos2 > pos1) {
            System.out.println();
            System.out.print("carro 2 venceu!");
            System.exit(0);
        } else {
            System.out.println();
            System.out.print("empate!");}
    }}