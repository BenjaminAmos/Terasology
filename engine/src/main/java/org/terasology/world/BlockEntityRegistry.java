/*
 * Copyright 2013 MovingBlocks
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
package org.terasology.world;

import org.joml.Vector3fc;
import org.joml.Vector3ic;
import org.terasology.entitySystem.Component;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.block.Block;

/**
 * Manages creation and lookup of entities linked to blocks
 */
public interface BlockEntityRegistry {

    /**
     * Sets a new entity at the given position.
     * This new entity is not temporary and will overwrite any existing entity at this point.
     * <p>
     * This method will make all interactions with blockPosition default to the entity set.
     * This has implications for blocks being placed onto that position and destroyed at that position.
     *
     * @param blockPosition The position to set the new entity in
     * @param bockEntity    The new entity to set
     * @return The previous entity at the location, or a null entity if one didn't exist yet.
     */
    EntityRef setPermanentBlockEntity(Vector3i blockPosition, EntityRef bockEntity);

    /**
     * This method returns the block entity at the given location, but will not produce a temporary entity if
     * one isn't currently in memory.
     *
     * @param blockPosition absolute position of the block
     * @return The block entity for the location if it exists, or the null entity
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #getExistingBlockEntityAt(Vector3ic)}.
     **/
    @Deprecated
    EntityRef getExistingBlockEntityAt(Vector3i blockPosition);


    /**
     * This method returns the block entity at the given location, but will not produce a temporary entity if
     * one isn't currently in memory.
     *
     * @param blockPosition absolute position of the block
     * @return The block entity for the location if it exists, or the null entity
     */
    EntityRef getExistingBlockEntityAt(Vector3ic blockPosition);


    /**
     * This method is the same as setBlock, except if the old and new block types are part of the same family the
     * entity will be force updated (usually they are not in this situation).
     *
     * @param position absolute position of the block in world space
     * @param type type of block
     * @return The previous block type, or null if the change failed due to the chunk not being available
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #setBlockForceUpdateEntity(Vector3ic, Block)}.
     */
    @Deprecated
    Block setBlockForceUpdateEntity(Vector3i position, Block type);

    /**
     * This method is the same as setBlock, except if the old and new block types are part of the same family the
     * entity will be force updated (usually they are not in this situation).
     *
     * @param position absolute position of the block in world space
     * @param type type of block
     * @return The previous block type, or null if the change failed due to the chunk not being available
     */
    Block setBlockForceUpdateEntity(Vector3ic position, Block type);


    /**
     * This method is the same as setBlock, except the specified components are not altered during the update
     *
     * @param position absolute position of the block in world space
     * @param type type of block
     * @param components components that are assigned to the block
     * @return The previous block type, or null if the change failed due to the chunk not being available
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #setBlockRetainComponent(Vector3ic, Block, Class[])}.
     **/
    @Deprecated
    Block setBlockRetainComponent(Vector3i position, Block type, Class<? extends Component>... components);

    /**
     * This method is the same as setBlock, except the specified components are not altered during the update
     *
     * @param position absolute position of the block in world space
     * @param type type of block
     * @param components components that are assigned to the block
     * @return The previous block type, or null if the change failed due to the chunk not being available
     */
    Block setBlockRetainComponent(Vector3ic position, Block type, Class<? extends Component>... components);


    /**
     * retrieves an EntityRef for a given block.
     *
     * @param position absolute position of the block in world space
     * @return The block entity for the location, creating it if it doesn't exist
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #getBlockEntityAt(Vector3fc)}.
     **/
    @Deprecated
    EntityRef getBlockEntityAt(Vector3f position);

    /**
     * retrieves an {@link EntityRef} for a given block.
     * @param position absolute position of the block in world space rounded {@link org.joml.RoundingMode#HALF_UP}
     * @return The block entity for the location, creating it if it doesn't exist
     **/
    EntityRef getBlockEntityAt(Vector3fc position);

    /**
     * retrieves an EntityRef for a given block.
     *
     * @param blockPosition absolute position of the block in world place
     * @return The block entity for the location, creating it if it doesn't exist
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #getBlockEntityAt(Vector3ic)}.
     */
    @Deprecated
    EntityRef getBlockEntityAt(Vector3i blockPosition);

    /**
     * retrieves an EntityRef for a given block.
     *
     * @param blockPosition absolute position of the block in world place
     * @return The block entity for the location, creating it if it doesn't exist
     */
    EntityRef getBlockEntityAt(Vector3ic blockPosition);

    /**
     * retrieves an entity associated with the given block else return a {@link EntityRef#NULL}
     * @param blockPosition  position of the block in world position.
     * @return The block controller entity for this location, or block entity if it exists.
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #getExistingEntityAt(Vector3ic)}.
     */
    @Deprecated
    EntityRef getExistingEntityAt(Vector3i blockPosition);

    /**
     * retrieves an entity associated with the given block else return a {@link EntityRef#NULL}
     * @param blockPosition position of the block in world position.
     * @return The block controller entity for this location, or block entity if it exists.\
     */
    EntityRef getExistingEntityAt(Vector3ic blockPosition);

    /**
     * retrieves an entity associated with the given block else create a new entity
     * @param blockPosition absolute position of the block.
     * @return The block {@link EntityRef} for this location.
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #getEntityAt(Vector3ic)}.
     */
    @Deprecated
    EntityRef getEntityAt(Vector3i blockPosition);

    /**
     * retrieves an entity associated with the given block else create a new entity
     * @param blockPosition absolute position of the block in.
     * @return The block {@link EntityRef} for this location.
     */
    EntityRef getEntityAt(Vector3ic blockPosition);

    /**
     * tell if the entity assigned to a given block is permanent. non-permanent
     * block entities are cleaned up at the end of the update.
     *
     * @param blockPos absolute position of the block.
     * @return Whether the entity at this position is permanent
     * @deprecated This is scheduled for removal in an upcoming version
     *             method will be replaced with JOML implementation {@link #hasPermanentBlockEntity(Vector3ic)}.
     **/
    @Deprecated
    boolean hasPermanentBlockEntity(Vector3i blockPos);

    /**
     * tell if the entity assigned to a given block is permanent. non-permanent
     * block entities are cleaned up at the end of the update.
     *
     * @param blockPos absolute position of the block.
     * @return Whether the entity at this position is permanent
     *
     **/
    boolean hasPermanentBlockEntity(Vector3ic blockPos);
}
