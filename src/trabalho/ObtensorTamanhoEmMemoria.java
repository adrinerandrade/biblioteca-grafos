package trabalho;

import java.lang.instrument.Instrumentation;

public class ObtensorTamanhoEmMemoria {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long tamanhoObjetoEmMemoria(Object o) {
        return instrumentation.getObjectSize(o);
    }

}
