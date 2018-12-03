package com.gourd.concurrent.framework.disruptor;

import com.lmax.disruptor.RingBuffer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.ByteBuffer;

/**
 * The type Long event producer.
 *
 * @author wei.Li by 2018-12-03
 */
@Data
@AllArgsConstructor
class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    /**
     * On data.
     *
     * @param bb the bb
     */
    void onData(ByteBuffer bb) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
            final LongEvent event = ringBuffer.get(sequence);
            // for the sequence
            // Fill with data
            event.setValue(bb.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
