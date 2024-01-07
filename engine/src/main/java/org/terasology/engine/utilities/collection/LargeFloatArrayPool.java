// Copyright 2023 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.engine.utilities.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class LargeFloatArrayPool {
    private final Map<Integer, Queue<float[]>> arrays;

    public LargeFloatArrayPool() {
        arrays = new HashMap<>();
    }

    public float[] borrowArray(int size) {
        Queue<float[]> arrayPool;
        if (!arrays.containsKey(size)) {
            arrayPool = new ArrayBlockingQueue<float[]>(16);
            arrays.put(size, arrayPool);
        } else {
            arrayPool = arrays.get(size);
        }

        if (!arrayPool.isEmpty()) {
            return arrays.get(size).remove();
        }

        return new float[size];
    }

    public void returnArray(float[] array) {
        arrays.get(array.length).add(array);
    }
}
