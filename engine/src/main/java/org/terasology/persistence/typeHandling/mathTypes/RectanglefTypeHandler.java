/*
 * Copyright 2020 MovingBlocks
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
package org.terasology.persistence.typeHandling.mathTypes;

import gnu.trove.list.TIntList;
import org.joml.Rectanglef;
import org.terasology.persistence.typeHandling.PersistedData;
import org.terasology.persistence.typeHandling.PersistedDataSerializer;

import java.util.Optional;

public class RectanglefTypeHandler extends org.terasology.persistence.typeHandling.TypeHandler<Rectanglef> {
    /**
     * Serializes a single non-null value.
     *
     * @param value      The value to serialize - will never be null.
     * @param serializer The serializer used to serialize simple values
     * @return The serialized value.
     */
    @Override
    protected PersistedData serializeNonNull(Rectanglef value, PersistedDataSerializer serializer) {
        return serializer.serialize(value.minX, value.minY, value.maxX, value.maxY);
    }

    /**
     * Deserializes a single value to the type {@link Rectanglef}.
     *
     * @param data The persisted data to deserialize from.
     * @return The deserialized value. {@link Optional#empty()} if the value could not be deserialized.
     */
    @Override
    public Optional<Rectanglef> deserialize(PersistedData data) {
        TIntList dataArray = data.getAsArray().getAsIntegerArray();
        return Optional.of(new Rectanglef(dataArray.get(0), dataArray.get(1), dataArray.get(2), dataArray.get(3)));
    }
}
