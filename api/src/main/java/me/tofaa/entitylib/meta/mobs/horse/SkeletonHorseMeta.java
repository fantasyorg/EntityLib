package me.tofaa.entitylib.meta.mobs.horse;

import me.tofaa.entitylib.meta.Metadata;

public class SkeletonHorseMeta extends BaseHorseMeta {

    public static final byte OFFSET = BaseHorseMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 0;

    public SkeletonHorseMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
    }
}
