package com.gaukh.rewoven.blockentities;

import com.gaukh.rewoven.ModBlockEntities;
import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class EggLanternEntity extends BlockEntity {
    private int color = 0xFFFFFF; // Default color (white)

    public EggLanternEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EGG_LANTERN_ENTITY, pos, state);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        this.markDirty();
        if (world != null) {
            BlockState state = getCachedState();
            world.updateListeners(getPos(), state, state, Block.NOTIFY_ALL);
        }
    }

    @Override
    protected void writeData(WriteView view) {
        view.putInt("color", color);
        super.writeData(view);
    }
    @Override
    protected void readData(ReadView view) {
        super.readData(view);
        this.color = view.getInt("color", 0xFFFFFF); // Default to white if not set
    }

    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registries) {
        return createNbt(registries); // or super.writeData into a new NBT
    }

    @Override
    public @Nullable Object getRenderData() {
        return color;  // return the integer color stored
    }
}