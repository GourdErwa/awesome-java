package com.gourd.concurrent.framework.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author wei.Li by 2018-12-03
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }

}
