package trabalho;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class ObjectBytesCounting {

    public static long countBytesOf(Object object) {
        try {
            ByteCountingOutputStream byteCounting = new ByteCountingOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(byteCounting);
            stream.writeObject(object);
            stream.close();
            byteCounting.close();
            return byteCounting.size();
        } catch (IOException e) {
            throw new RuntimeException("Erro obter tamanho de objeto.", e);
        }
    }


    private static class ByteCountingOutputStream extends OutputStream {
        private long size = 0;

        @Override
        public void write(int b) throws IOException {
            size++;
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            size += len;
        }

        public long size() {
            return size;
        }
    }

}
