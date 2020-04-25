/*
 * Copyright 2019 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.terasology.math;

import org.joml.Rectanglei;
import org.joml.Vector2i;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.terasology.nui.Border;
import org.terasology.nui.util.RectUtility;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BorderTest {
    Border border;

    @BeforeEach
    public void initBorder() {
        border = new Border(10, 10, 10, 10);
    }

    @Test
    public void shrinkSameBorderAndRegionSize() {
        assertEquals(border.shrink(RectUtility.createFromMinAndSize(10, 10, 10, 10)), new Rectanglei());
    }

    @Test
    public void shrinkBorder() {
        assertEquals(border.shrink(RectUtility.createFromMinAndSize(30, 30, 30, 30)),
            RectUtility.createFromMinAndSize(40, 40, 10, 10));
    }

    @Test
    public void shrinkVector() {
        assertEquals(border.shrink(new Vector2i(10, 10)), new Vector2i(-10, -10));
    }

    @Test
    public void getTotals() {
        assertEquals(border.getTotals(), new Vector2i(20, 20));
    }

    @Test
    public void growVector() {
        assertEquals(border.grow(new Vector2i(10, 10)), new Vector2i(30, 30));
    }

    @Test
    public void growVectorMax() {
        assertEquals(border.grow(new Vector2i(Integer.MAX_VALUE, Integer.MAX_VALUE)), new Vector2i(
            Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void growBorder() {
        assertEquals(border.grow(RectUtility.createFromMinAndSize(30, 30, 30, 30)),
            RectUtility.createFromMinAndSize(20, 20, 50, 50));
    }

    @Test
    public void growBorderMax() {
        assertEquals(border.grow(RectUtility.createFromMinAndSize(10, 10, Integer.MAX_VALUE,
            Integer.MAX_VALUE)), RectUtility.createFromMinAndSize(0, 0, Integer.MAX_VALUE,
            Integer.MAX_VALUE));
    }
}
