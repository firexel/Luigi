package com.seraph.luigi

import junit.framework.TestCase
import kotlin.test.assertEquals

/**
 * Luigi
 * Created by Alexander Naumov on 21.04.2015.
 */

public class ObserversTest : TestCase() {
    public fun test_requestObserver_new() {
        val producer = CountingTestProducer<String>()
        assertEquals(0, producer.readCount)

        var requestObservations = 0
        producer observeRequests {
            requestObservations++
        }

        assertEquals(0, producer.readCount)
        assertEquals(0, requestObservations)

        3.times { producer.emitReadRequest() }

        assertEquals(0, producer.readCount)
        assertEquals(3, requestObservations)
    }

    public fun test_dataObserver_new() {
        val producer = CountingTestProducer<String>()
        assertEquals(0, producer.readCount)

        var observedData:String = ""
        producer observeData { data:String ->
            observedData = data
        }

        listOf("a", "b", "c").forEach {
            producer.value = it
            producer.emitReadRequest()
            assertEquals(observedData, it)
        }

        assertEquals(3, producer.readCount)
    }
}
