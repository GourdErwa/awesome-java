package com.gourd.concurrent.framework.disruptor;

import com.lmax.disruptor.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wei.Li by 2018-12-03
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongEventHandler.class);

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        LOGGER.debug("Event: {}", event);
    }

}
