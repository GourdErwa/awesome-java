package com.gourd.concurrent.framework.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * The type Long event producer with translator.
 *
 * @author wei.Li by 2018-12-03
 */
public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
        (event, sequence, bb) -> event.setValue(bb.getLong(0));
    private final RingBuffer<LongEvent> ringBuffer;

    /**
     * Instantiates a new Long event producer with translator.
     *
     * @param ringBuffer the ring buffer
     */
    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * On data.
     *
     * @param bb the bb
     */
    public void onData(ByteBuffer bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
