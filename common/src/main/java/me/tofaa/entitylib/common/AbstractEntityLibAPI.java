package me.tofaa.entitylib.common;

import com.github.retrooper.packetevents.PacketEventsAPI;
import me.tofaa.entitylib.APISettings;
import me.tofaa.entitylib.EntityLibAPI;
import me.tofaa.entitylib.Platform;
import me.tofaa.entitylib.tick.TickContainer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public abstract class AbstractEntityLibAPI<P, W, T> implements EntityLibAPI<W, T> {

    protected final Platform<P> platform;
    protected final PacketEventsAPI<?> packetEvents;
    protected final APISettings settings;
    protected final Collection<TickContainer<?, T>> tickContainers;

    protected AbstractEntityLibAPI(Platform<P> platform, APISettings settings) {
        this.platform = platform;
        this.packetEvents = settings.getPacketEvents();
        this.settings = settings;
        this.tickContainers = settings.shouldTickTickables() ? new HashSet<>() : Collections.emptyList();
    }

    @NotNull
    @Override
    public APISettings getSettings() {
        return settings;
    }

    @Override
    public PacketEventsAPI<?> getPacketEvents() {
        return packetEvents;
    }

    @NotNull
    @Override
    public Collection<TickContainer<?, T>> getTickContainers() {
        return tickContainers;
    }
}
