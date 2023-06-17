package org.roaringbitmap.supplement;

import org.junit.jupiter.api.Test;
import org.roaringbitmap.BitmapContainer;

import java.nio.ByteBuffer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBuffer {
    @Test
    public void testBasicUse(){
        // init, elements are all byte 0
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // now, position = 0, limit,capacity = 10

        buffer.put((byte) 0);
        buffer.put((byte) 1);
        buffer.put((byte) 2);
        // now, position = 3, limit,capacity = 10
        buffer.flip();
        // now, position = 0, limit = 3, capacity = 10
        assertEquals(buffer.get(), (byte) 0);
        assertEquals(buffer.get(), (byte) 1);

        buffer.put((byte) 7);
        assertEquals(buffer.get(), (byte) 2);
        // reset limit
        buffer.limit(8);

        ByteBuffer slice = buffer.slice();
        int position = slice.position();
        System.out.println(position);
        slice.get();

    }

    @Test
    public void testDivide() {
        byte[] buffer = new byte[16];
        final long[] bitmapArray = new long[BitmapContainer.MAX_CAPACITY / 64]; // 2^16个bit位
        System.out.println("bitmapArray len " + bitmapArray.length);
        System.out.println("bitmapArray len /8 " + bitmapArray.length/8);
        System.out.println("bitmapArray len *8 " + bitmapArray.length*8);
        System.out.println("iBlockMax" + bitmapArray.length / buffer.length / 8);
        for (int iBlock = 0; iBlock <= bitmapArray.length / buffer.length / 8; iBlock++) {
            int start = buffer.length * iBlock;
            int end = Math.min(buffer.length * (iBlock + 1) - 1, 8 * bitmapArray.length);

            System.out.println("start " + start + " end " + end);
        }
    }
}
