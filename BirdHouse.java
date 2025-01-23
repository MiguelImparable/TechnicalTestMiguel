//Dame solo las respuestas
//1. ¿Cuáles de los siguientes es verdadero? (Seleccione todos los que apliquen)
public class Bunny {
    public static void main(String[] args) {
        Bunny bun = new Bunny();
    }
}
// A. Bunny es una clase.
// B. bun es una clase.
// C. main es una clase.
// D. Bunny es una referencia a un object.
// E. bun es una referencia a un object.
// F. main es una referencia a un object.
// G.Ninguna de las anteriores.

// 2. Dado:
public class BirdHouse {
    public static void main(String[] args) {
        String r = "0";
        int x = -1, y = -5;
        if (x < 5)
            if (y > 0)
                if (x > y)
                    r += "1";
                else
                    r += "2";
            else
                r += "3";
        else
            r += "4";
        System.out.println(r);
    }
}
// ¿Cuál es el resultado?
// A. 0
// B. 01
// C. 02
// D. 03
// E. 013
// F. 023
// G. Error de compilación.

// 3 Dado:
public class Bunnies {
    static int count = 0;

    Bunnies() {
        while (count < 10)
            new Bunnies(++count);
    }

    Bunnies(int x) {
        super();
    }

    public static void main(String[] args) {
        new Bunnies();
        new Bunnies(count);
        System.out.println(count++);
    }
}
// ¿Cuál es el resultado?
// A. 9
// B. 10
// C. 11
// D. 12
// E. Error de compilación.
// F. Una excepción es lanzada en tiempo de ejecución

// 4.Dado:
public class _C {
    private static int $;

    public static void main(String[] args) {
        String a_b;
        System.out.println($);
        System.out.println(a_b);
    }
}
// ¿Cuáles de las siguientes respuestas son correctas? (Seleccione
// todas las que apliquen)
// A. 0
// B. Error de compilación línea 2
// C. Error de compilación línea 4
// D. Error de compilación línea 5
// E. Error de compilación línea 6
// F. 0null
// G. nullnull

// 5. ¿Cuál de las siguientes afirmaciones es correcta?
// A. "X extends Y" is correct if and only if X is a class and Y is an
// interface.
// B. "X extends Y" is correct if and only if X is an interface and Y is a
// class.
// C. "X extends Y" is correct if X and Y are either both classes or both
// interfaces.
// D. "X extends Y" is correct for all combinations of X and Y being classes
// and/or interfaces.

// 6.Dado:
public class Starter extends Thread {
    private int X = 2;

    public static void main(String[] args) throws Exception {
        new Starter().makeltSo();
    }

    public Starter() {
        X = 5;
        start();
    }

    public void makeltSo() throws Exception {
        join();
        X = X - 1;
        System.out.println(X);
    }

    public void run() {
        X *= 2;
    }
}
// ¿Cuál es el resultado?
// A. 4
// B. 5
// C. 8
// D. 9
// E. Error de compilación
// F. Se lanza una excepción en tiempo de ejecución

// 7.Dado:

//¿Cuál es el resultado? 
//A. 0
//B. 4
/C. Error de compilación en la línea 3
D. Error de compilación en la línea 4
E. Error de compilación en la línea 7
F. Error de compilación en la línea 3