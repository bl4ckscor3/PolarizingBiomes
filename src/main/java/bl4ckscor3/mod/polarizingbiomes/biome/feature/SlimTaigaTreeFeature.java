package bl4ckscor3.mod.polarizingbiomes.biome.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.IPlantable;

public class SlimTaigaTreeFeature extends AbstractTreeFeature<NoFeatureConfig>
{
	private static final BlockState TRUNK = Blocks.SPRUCE_LOG.getDefaultState();
	private static final BlockState LEAF = Blocks.SPRUCE_LEAVES.getDefaultState();

	public SlimTaigaTreeFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory, false);
		setSapling((IPlantable)Blocks.SPRUCE_SAPLING);
	}

	@Override
	protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader world, Random random, BlockPos originalPos, MutableBoundingBox bounds)
	{
		int treeHeight = randomRange(random, 12, 18);

		//+1 because there's a leaf at the top
		if(originalPos.getY() + treeHeight + 1 > world.getMaxHeight() || !isSoil(world, originalPos.down(), sapling))
			return false;

		MutableBlockPos pos = new MutableBlockPos();

		pos.setPos(originalPos.down());
		setBlockState(world, pos, Blocks.DIRT.getDefaultState());

		for(int i = 0; i < treeHeight; i++)
		{
			setLogState(changedBlocks, world, pos.move(Direction.UP), TRUNK, bounds);
		}

		setLogState(changedBlocks, world, pos.move(Direction.UP), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.DOWN).move(Direction.NORTH), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.DOWN), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.UP).move(Direction.SOUTH).move(Direction.EAST), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.DOWN), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.UP).move(Direction.WEST).move(Direction.SOUTH), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.DOWN), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.UP).move(Direction.NORTH).move(Direction.WEST), LEAF, bounds);
		setLogState(changedBlocks, world, pos.move(Direction.DOWN), LEAF, bounds);
		return true;
	}

	private int randomRange(Random random, int min, int max)
	{
		return random.nextInt(max + 1 - min) + min;
	}
}
