package me.tofaa.entitylib.meta.mobs.horse;

import me.tofaa.entitylib.meta.Metadata;
import me.tofaa.entitylib.meta.types.AgeableMeta;

public abstract class BaseHorseMeta extends AgeableMeta {

    public static final byte OFFSET = AgeableMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 1;

    private final static byte TAMED_BIT = 0x02;
    private final static byte SADDLED_BIT = 0x04;
    private final static byte HAS_BRED_BIT = 0x08;
    private final static byte EATING_BIT = 0x10;
    private final static byte REARING_BIT = 0x20;
    private final static byte MOUTH_OPEN_BIT = 0x40;

    protected BaseHorseMeta(int entityId, Metadata metadata) {
        super(entityId, metadata);
    }

    public boolean isTamed() {
        return getMaskBit(OFFSET, TAMED_BIT);
    }

    public void setTamed(boolean value) {
        setMaskBit(OFFSET, TAMED_BIT, value);
    }

    public boolean isSaddled() {
        return getMaskBit(OFFSET, SADDLED_BIT);
    }

    public void setSaddled(boolean value) {
        setMaskBit(OFFSET, SADDLED_BIT, value);
    }

    public boolean isHasBred() {
        return getMaskBit(OFFSET, HAS_BRED_BIT);
    }

    public void setHasBred(boolean value) {
        setMaskBit(OFFSET, HAS_BRED_BIT, value);
    }

    public boolean isEating() {
        return getMaskBit(OFFSET, EATING_BIT);
    }

    public void setEating(boolean value) {
        setMaskBit(OFFSET, EATING_BIT, value);
    }

    public boolean isRearing() {
        return getMaskBit(OFFSET, REARING_BIT);
    }

    public void setRearing(boolean value) {
        setMaskBit(OFFSET, REARING_BIT, value);
    }

    public boolean isMouthOpen() {
        return getMaskBit(OFFSET, MOUTH_OPEN_BIT);
    }

    public void setMouthOpen(boolean value) {
        setMaskBit(OFFSET, MOUTH_OPEN_BIT, value);
    }
}
