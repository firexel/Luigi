package com.seraph.pipeline

/**
 * CTL
 * Created by seraph on 23.02.2015 0:23.
 */

public abstract class ObservableProducer<T> : Producer<T> {
    private var observer: (() -> Unit)? = null

    synchronized protected fun invokeObserver(): Boolean {
        observer?.invoke()
        return observer != null
    }

    synchronized override fun observe(observer: () -> Unit) {
        this.observer = observer
    }
}