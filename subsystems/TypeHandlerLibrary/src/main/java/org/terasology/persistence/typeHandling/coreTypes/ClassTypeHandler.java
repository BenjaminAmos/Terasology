// Copyright 2023 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0

package org.terasology.persistence.typeHandling.coreTypes;

import org.terasology.persistence.typeHandling.PersistedData;
import org.terasology.persistence.typeHandling.PersistedDataSerializer;
import org.terasology.persistence.typeHandling.TypeHandler;

import java.util.Optional;

public class ClassTypeHandler<T> extends TypeHandler<Class<T>> {
    @Override
    protected PersistedData serializeNonNull(Class<T> value, PersistedDataSerializer serializer) {
        return serializer.serialize(value.getName());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Class<T>> deserialize(PersistedData data) {
        try {
            return Optional.of((Class<T>) Class.forName(data.getAsString()));
        } catch (ClassNotFoundException ignore) {
            return Optional.empty();
        }
    }
}
