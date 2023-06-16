package org.roaringbitmap.supplement;

import org.junit.jupiter.api.Test;

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
}
