// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.persistence.typeHandling.coreTypes.factories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.persistence.typeHandling.TypeHandler;
import org.terasology.persistence.typeHandling.TypeHandlerContext;
import org.terasology.persistence.typeHandling.TypeHandlerFactory;
import org.terasology.persistence.typeHandling.coreTypes.ClassTypeHandler;
import org.terasology.reflection.ReflectionUtil;
import org.terasology.reflection.TypeInfo;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

/**
 * A {@link TypeHandlerFactory} that generates an {@link ClassTypeHandler} for class types.
 */
public class ClassTypeHandlerFactory implements TypeHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(ClassTypeHandler.class);

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<TypeHandler<T>> create(TypeInfo<T> typeInfo, TypeHandlerContext context) {
        Class<? super T> rawType = typeInfo.getRawType();
        if (!Class.class.equals(rawType)) {
            return Optional.empty();
        }

        Type classType = ReflectionUtil.getTypeParameterForSuper(typeInfo.getType(), Class.class, 0);
        if (classType == null) {
            logger.error("Class is not parameterised and cannot be serialized");
            return Optional.empty();
        }

        return Optional.of((TypeHandler<T>) new ClassTypeHandler());
    }
}
