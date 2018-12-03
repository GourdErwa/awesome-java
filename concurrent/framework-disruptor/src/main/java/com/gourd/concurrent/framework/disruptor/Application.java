package com.gourd.concurrent.framework.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

/**
 * The type Application.
 *
 * @author wei.Li by 2018-12-03
 */
public final class Application {

    /**
     * Specify the size of the ring buffer, must be power of 2.
     */
    private static final int BUFFER_SIZE = 1024;
    private static final int SLEEP_MILLIS = 1000;

    private Application() {
        throw new Error("util class");
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {

        // The factory for the event
        final LongEventFactory factory = new LongEventFactory();


        // Construct the Disruptor
        final Disruptor<LongEvent> disruptor = new Disruptor<>(factory, BUFFER_SIZE, DaemonThreadFactory.INSTANCE);

        // Connect the handler
        disruptor.handleEventsWith(new LongEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        final RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        final LongEventProducer producer = new LongEventProducer(ringBuffer);

        final ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(SLEEP_MILLIS);
        }
    }

}
