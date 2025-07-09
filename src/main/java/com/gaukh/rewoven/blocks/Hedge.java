package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.block.enums.WallShape;
import static net.minecraft.state.property.Properties.*;
import net.minecraft.block.enums.WallShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.tick.ScheduledTickView;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;

import java.util.Map;
import java.util.function.Function;

public class Hedge extends WallBlock {

    // Hedge outline shapes matching model geometry exactly
    private static final VoxelShape POST_SHAPE = Block.createCuboidShape(2, 0, 2, 14, 16, 14);
    private static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(3, 0, 0, 13, 15, 8);
    private static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(3, 0, 8, 13, 15, 16);
    private static final VoxelShape EAST_SHAPE = Block.createCuboidShape(8, 0, 3, 16, 15, 13);
    private static final VoxelShape WEST_SHAPE = Block.createCuboidShape(0, 0, 3, 8, 15, 13);
    
    // TALL hedge shapes (height 16 for proper connections)
    private static final VoxelShape NORTH_SHAPE_TALL = Block.createCuboidShape(3, 0, 0, 13, 16, 8);
    private static final VoxelShape SOUTH_SHAPE_TALL = Block.createCuboidShape(3, 0, 8, 13, 16, 16);
    private static final VoxelShape EAST_SHAPE_TALL = Block.createCuboidShape(8, 0, 3, 16, 16, 13);
    private static final VoxelShape WEST_SHAPE_TALL = Block.createCuboidShape(0, 0, 3, 8, 16, 13);
    private final Function<BlockState, VoxelShape> outlineShapeFunction;
    private final Function<BlockState, VoxelShape> collisionShapeFunction;
    public static final Map<Direction, EnumProperty<WallShape>> WALL_SHAPE_PROPERTIES_BY_DIRECTION = ImmutableMap.copyOf(
            Maps.newEnumMap(
                    Map.of(Direction.NORTH, NORTH_WALL_SHAPE, Direction.EAST, EAST_WALL_SHAPE, Direction.SOUTH, SOUTH_WALL_SHAPE, Direction.WEST, WEST_WALL_SHAPE)
            )
    );
    private static final VoxelShape POST_SHAPE_FOR_TALL_TEST = Block.createColumnShape(2.0, 0.0, 16.0);
    private static final Map<Direction, VoxelShape> WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION = VoxelShapes.createHorizontalFacingShapeMap(
        Block.createCuboidZShape(2.0, 16.0, 0.0, 9.0)
    );


    public static final Block HEDGE = ModBlocks.register(
        "hedge",
        Hedge::new,
        AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
            .mapColor(MapColor.DARK_GREEN)
            .sounds(BlockSoundGroup.AZALEA_LEAVES)
            .jumpVelocityMultiplier(0.5f)
            .velocityMultiplier(0.5f),
        true
    );

    public Hedge(Settings settings) {
        super(settings);
        this.outlineShapeFunction = this.createShapeFunction(16.0F, 15.0F);
        this.collisionShapeFunction = this.createShapeFunction(24.0F, 24.0F);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);   // keep vanilla enum properties!
    }

    @SuppressWarnings("unchecked")
    private Function<BlockState, VoxelShape> createShapeFunction(float tallHeight, float lowHeight) {
        VoxelShape voxelShape = Block.createColumnShape(8.0, 0.0, tallHeight);
        int i = 6;
        Map<Direction, VoxelShape> map = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(6.0, 0.0, lowHeight, 0.0, 11.0));
        Map<Direction, VoxelShape> map2 = VoxelShapes.createHorizontalFacingShapeMap(Block.createCuboidZShape(6.0, 0.0, tallHeight, 0.0, 11.0));
        return this.createShapeFunction(state -> {
            VoxelShape voxelShape2 = state.get(UP) ? voxelShape : VoxelShapes.empty();

            for (Map.Entry<Direction, EnumProperty<WallShape>> entry : WALL_SHAPE_PROPERTIES_BY_DIRECTION.entrySet()) {
                voxelShape2 = VoxelShapes.union(voxelShape2, switch ((WallShape)state.get((Property)entry.getValue())) {
                    case NONE -> VoxelShapes.empty();
                    case LOW -> (VoxelShape)map.get(entry.getKey());
                    case TALL -> (VoxelShape)map2.get(entry.getKey());
                });
            }

            return voxelShape2;
        }, new Property[]{WATERLOGGED});
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();

        // Add post shape if UP is true
        if (state.get(UP)) {
            shape = VoxelShapes.union(shape, POST_SHAPE);
        }

        // Add wall shapes based on connection states
        WallShape northShape = state.get(NORTH_WALL_SHAPE);
        if (northShape == WallShape.LOW) {
            shape = VoxelShapes.union(shape, NORTH_SHAPE);
        } else if (northShape == WallShape.TALL) {
            shape = VoxelShapes.union(shape, NORTH_SHAPE_TALL);
        }

        WallShape southShape = state.get(SOUTH_WALL_SHAPE);
        if (southShape == WallShape.LOW) {
            shape = VoxelShapes.union(shape, SOUTH_SHAPE);
        } else if (southShape == WallShape.TALL) {
            shape = VoxelShapes.union(shape, SOUTH_SHAPE_TALL);
        }

        WallShape eastShape = state.get(EAST_WALL_SHAPE);
        if (eastShape == WallShape.LOW) {
            shape = VoxelShapes.union(shape, EAST_SHAPE);
        } else if (eastShape == WallShape.TALL) {
            shape = VoxelShapes.union(shape, EAST_SHAPE_TALL);
        }

        WallShape westShape = state.get(WEST_WALL_SHAPE);
        if (westShape == WallShape.LOW) {
            shape = VoxelShapes.union(shape, WEST_SHAPE);
        } else if (westShape == WallShape.TALL) {
            shape = VoxelShapes.union(shape, WEST_SHAPE_TALL);
        }

        return shape.isEmpty() ? POST_SHAPE : shape;
    }

    @SuppressWarnings("unchecked")
    private BlockState getStateWith(BlockState state, boolean north, boolean east, boolean south, boolean west, VoxelShape aboveShape) {
        return state.with(NORTH_WALL_SHAPE, this.getWallShape(north, aboveShape, (VoxelShape)WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.NORTH)))
                .with(EAST_WALL_SHAPE, this.getWallShape(east, aboveShape, (VoxelShape)WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.EAST)))
                .with(SOUTH_WALL_SHAPE, this.getWallShape(south, aboveShape, (VoxelShape)WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.SOUTH)))
                .with(WEST_WALL_SHAPE, this.getWallShape(west, aboveShape, (VoxelShape)WALL_SHAPES_FOR_TALL_TEST_BY_DIRECTION.get(Direction.WEST)));
    }

    private BlockState getStateWith(
        WorldView world, BlockState state, BlockPos pos, BlockState aboveState, boolean north, boolean east, boolean south, boolean west
    ) {
        VoxelShape voxelShape = aboveState.getCollisionShape(world, pos).getFace(Direction.DOWN);
        BlockState blockState = this.getStateWith(state, north, east, south, west, voxelShape);
        return blockState.with(UP, this.shouldHavePost(blockState, aboveState, voxelShape));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return (VoxelShape)this.collisionShapeFunction.apply(state);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos blockPos2 = blockPos.north();
        BlockPos blockPos3 = blockPos.east();
        BlockPos blockPos4 = blockPos.south();
        BlockPos blockPos5 = blockPos.west();
        BlockPos blockPos6 = blockPos.up();
        BlockState blockState = worldView.getBlockState(blockPos2);
        BlockState blockState2 = worldView.getBlockState(blockPos3);
        BlockState blockState3 = worldView.getBlockState(blockPos4);
        BlockState blockState4 = worldView.getBlockState(blockPos5);
        BlockState blockState5 = worldView.getBlockState(blockPos6);
        boolean bl = this.shouldConnectTo(blockState, blockState.isSideSolidFullSquare(worldView, blockPos2, Direction.SOUTH), Direction.SOUTH);
        boolean bl2 = this.shouldConnectTo(blockState2, blockState2.isSideSolidFullSquare(worldView, blockPos3, Direction.WEST), Direction.WEST);
        boolean bl3 = this.shouldConnectTo(blockState3, blockState3.isSideSolidFullSquare(worldView, blockPos4, Direction.NORTH), Direction.NORTH);
        boolean bl4 = this.shouldConnectTo(blockState4, blockState4.isSideSolidFullSquare(worldView, blockPos5, Direction.EAST), Direction.EAST);
        BlockState blockState6 = this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
        return this.getStateWith(worldView, blockState6, blockPos6, blockState5, bl, bl2, bl3, bl4);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
        BlockState state,
        WorldView world,
        ScheduledTickView tickView,
        BlockPos pos,
        Direction direction,
        BlockPos neighborPos,
        BlockState neighborState,
        net.minecraft.util.math.random.Random random
    ) {
        if ((Boolean)state.get(WATERLOGGED)) {
            tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        if (direction == Direction.DOWN) {
            return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
        } else {
            return direction == Direction.UP
                ? this.getStateAt(world, state, neighborPos, neighborState)
                : this.getStateWithNeighbor(world, pos, state, neighborPos, neighborState, direction);
        }
    }

    private static boolean shouldUseTallShape(VoxelShape aboveShape, VoxelShape tallShape) {
        return !VoxelShapes.matchesAnywhere(tallShape, aboveShape, BooleanBiFunction.ONLY_FIRST);
    }

    private BlockState getStateAt(WorldView world, BlockState state, BlockPos pos, BlockState aboveState) {
        boolean bl = isConnected(state, NORTH_WALL_SHAPE);
        boolean bl2 = isConnected(state, EAST_WALL_SHAPE);
        boolean bl3 = isConnected(state, SOUTH_WALL_SHAPE);
        boolean bl4 = isConnected(state, WEST_WALL_SHAPE);
        return this.getStateWith(world, state, pos, aboveState, bl, bl2, bl3, bl4);
    }

    private BlockState getStateWithNeighbor(WorldView world, BlockPos pos, BlockState state, BlockPos neighborPos, BlockState neighborState, Direction direction) {
        Direction direction2 = direction.getOpposite();
        boolean bl = direction == Direction.NORTH
            ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
            : isConnected(state, NORTH_WALL_SHAPE);
        boolean bl2 = direction == Direction.EAST
            ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
            : isConnected(state, EAST_WALL_SHAPE);
        boolean bl3 = direction == Direction.SOUTH
            ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
            : isConnected(state, SOUTH_WALL_SHAPE);
        boolean bl4 = direction == Direction.WEST
            ? this.shouldConnectTo(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction2), direction2)
            : isConnected(state, WEST_WALL_SHAPE);
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
        return this.getStateWith(world, state, blockPos, blockState, bl, bl2, bl3, bl4);
    }

    private WallShape getWallShape(boolean connected, VoxelShape aboveShape, VoxelShape tallShape) {
        if (connected) {
            return shouldUseTallShape(aboveShape, tallShape) ? WallShape.TALL : WallShape.LOW;
        } else {
            return WallShape.NONE;
        }
    }

    private boolean shouldHavePost(BlockState state, BlockState aboveState, VoxelShape aboveShape) {
        boolean bl = aboveState.getBlock() instanceof WallBlock && (Boolean)aboveState.get(UP);
        if (bl) {
            return true;
        } else {
            WallShape wallShape = state.get(NORTH_WALL_SHAPE);
            WallShape wallShape2 = state.get(SOUTH_WALL_SHAPE);
            WallShape wallShape3 = state.get(EAST_WALL_SHAPE);
            WallShape wallShape4 = state.get(WEST_WALL_SHAPE);
            boolean bl2 = wallShape2 == WallShape.NONE;
            boolean bl3 = wallShape4 == WallShape.NONE;
            boolean bl4 = wallShape3 == WallShape.NONE;
            boolean bl5 = wallShape == WallShape.NONE;
            boolean bl6 = bl5 && bl2 && bl3 && bl4 || bl5 != bl2 || bl3 != bl4;
            if (bl6) {
                return true;
            } else {
                boolean bl7 = wallShape == WallShape.TALL && wallShape2 == WallShape.TALL || wallShape3 == WallShape.TALL && wallShape4 == WallShape.TALL;
                return bl7 ? false : aboveState.isIn(BlockTags.WALL_POST_OVERRIDE) || shouldUseTallShape(aboveShape, POST_SHAPE_FOR_TALL_TEST);
            }
        }
    }

    private boolean shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side) {
        Block block = state.getBlock();
        boolean bl = block instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
        // Custom hedge connection logic: connect to walls and other hedges (including flower hedges via inheritance)
        return state.isIn(BlockTags.WALLS) || block instanceof Hedge || !cannotConnect(state) && faceFullSquare || block instanceof PaneBlock || bl;
    }

    private static boolean isConnected(BlockState state, Property<WallShape> property) {
        return state.get(property) != WallShape.NONE;
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, net.minecraft.server.world.ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        // Check if there are any flowering hedges nearby that could spread to this hedge
        if (world.getLightLevel(pos.up()) >= 9) {
            for (int i = 0; i < 4; i++) {
                BlockPos nearbyPos = pos.add(
                    random.nextInt(3) - 1,
                    random.nextInt(2) - random.nextInt(2),
                    random.nextInt(3) - 1
                );
                
                BlockState nearbyState = world.getBlockState(nearbyPos);
                Block nearbyBlock = nearbyState.getBlock();
                
                // Check if nearby block is any type of flower hedge
                if (isFlowerHedge(nearbyBlock)) {
                    // Convert this hedge to the same flower hedge type
                    BlockState newState = nearbyBlock.getDefaultState();
                    for (Property<?> property : state.getProperties()) {
                        if (newState.contains(property)) {
                            newState = copyProperty(newState, state, property);
                        }
                    }
                    world.setBlockState(pos, newState);
                    break;
                }
            }
        }
    }
    
    private boolean isFlowerHedge(Block block) {
        return block == ModBlocks.DANDELION_HEDGE || 
               block == ModBlocks.POPPY_HEDGE ||
               block == ModBlocks.BLUE_ORCHID_HEDGE ||
               block == ModBlocks.ALLIUM_HEDGE ||
               block == ModBlocks.AZURE_BLUET_HEDGE ||
               block == ModBlocks.RED_TULIP_HEDGE ||
               block == ModBlocks.ORANGE_TULIP_HEDGE ||
               block == ModBlocks.WHITE_TULIP_HEDGE ||
               block == ModBlocks.PINK_TULIP_HEDGE ||
               block == ModBlocks.OXEYE_DAISY_HEDGE ||
               block == ModBlocks.CORNFLOWER_HEDGE ||
               block == ModBlocks.LILY_OF_THE_VALLEY_HEDGE ||
               block == ModBlocks.WITHER_ROSE_HEDGE;
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(player.getActiveHand());
        
        // Check if the held item is a supported flower and convert to specific hedge type
        Block targetHedge = getHedgeForFlower(heldItem.getItem());
        if (targetHedge != null) {
            if (!world.isClient) {
                // Convert to specific flower hedge type
                BlockState newState = targetHedge.getDefaultState();
                for (Property<?> property : state.getProperties()) {
                    if (newState.contains(property)) {
                        newState = copyProperty(newState, state, property);
                    }
                }
                world.setBlockState(pos, newState);
                
                if (!player.isCreative()) {
                    heldItem.decrement(1);
                }
                
                world.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            return world.isClient ? ActionResult.SUCCESS : ActionResult.CONSUME;
        }
        
        return ActionResult.PASS;
    }

    private static Block getHedgeForFlower(Item flower) {
        if (flower == Items.DANDELION) return ModBlocks.DANDELION_HEDGE;
        if (flower == Items.POPPY) return ModBlocks.POPPY_HEDGE;
        if (flower == Items.BLUE_ORCHID) return ModBlocks.BLUE_ORCHID_HEDGE;
        if (flower == Items.ALLIUM) return ModBlocks.ALLIUM_HEDGE;
        if (flower == Items.AZURE_BLUET) return ModBlocks.AZURE_BLUET_HEDGE;
        if (flower == Items.RED_TULIP) return ModBlocks.RED_TULIP_HEDGE;
        if (flower == Items.ORANGE_TULIP) return ModBlocks.ORANGE_TULIP_HEDGE;
        if (flower == Items.WHITE_TULIP) return ModBlocks.WHITE_TULIP_HEDGE;
        if (flower == Items.PINK_TULIP) return ModBlocks.PINK_TULIP_HEDGE;
        if (flower == Items.OXEYE_DAISY) return ModBlocks.OXEYE_DAISY_HEDGE;
        if (flower == Items.CORNFLOWER) return ModBlocks.CORNFLOWER_HEDGE;
        if (flower == Items.LILY_OF_THE_VALLEY) return ModBlocks.LILY_OF_THE_VALLEY_HEDGE;
        if (flower == Items.WITHER_ROSE) return ModBlocks.WITHER_ROSE_HEDGE;
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> BlockState copyProperty(BlockState newState, BlockState oldState, Property<T> property) {
        return newState.with(property, oldState.get(property));
    }

    @Override
    public float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        ItemStack tool = player.getMainHandStack();
        
        // Check if tool should get speed bonus (only shears and hoes, like vanilla leaves)
        if (tool.getItem() instanceof net.minecraft.item.ShearsItem || 
            tool.getItem() instanceof net.minecraft.item.HoeItem) {
            // Use super calculation for these tools (they get speed from block tags)
            return super.calcBlockBreakingDelta(state, player, world, pos);
        }
        
        // For all other tools, use basic leaf mining (no tool effectiveness)
        // This prevents pickaxes from getting WallBlock-inherited speed
        float hardness = 0.2f;
        return hardness == -1.0f ? 0.0f : 1.0f / hardness / 30.0f;
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(HEDGE);
        });

    }
}
