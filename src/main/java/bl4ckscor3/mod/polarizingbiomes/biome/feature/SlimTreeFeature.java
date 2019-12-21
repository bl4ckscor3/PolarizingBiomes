package bl4ckscor3.mod.polarizingbiomes.biome.feature;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class SlimTreeFeature extends AbstractTreeFeature<TreeFeatureConfig>
{
	public SlimTreeFeature(Function<Dynamic<?>, ? extends TreeFeatureConfig> configFactory)
	{
		super(configFactory);
	}

	@Override
	protected boolean func_225557_a_(IWorldGenerationReader world, Random random, BlockPos originalPos, Set<BlockPos> changedBlocks, Set<BlockPos> p_225557_5_, MutableBoundingBox bounds, TreeFeatureConfig config)
	{
		int treeHeight = randomRange(random, 12, 18);

		//+1 because there's a leaf at the top
		if(originalPos.getY() + treeHeight + 1 > world.getMaxHeight() || !isSoil(world, originalPos.down(), config.getSapling()))
			return false;

		BlockPos.Mutable pos = new BlockPos.Mutable();

		pos.setPos(originalPos.down());
		setBlockState(world, pos, Blocks.DIRT.getDefaultState());

		for(int i = 0; i < treeHeight; i++)
		{
			pos.move(Direction.UP);
			func_227216_a_(world, random, pos, changedBlocks, bounds, config); //placeLog
		}

		//placeLeaf
		func_227219_b_(world, random, pos.move(Direction.UP), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.DOWN).move(Direction.NORTH), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.DOWN), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.UP).move(Direction.SOUTH).move(Direction.EAST), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.DOWN), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.UP).move(Direction.WEST).move(Direction.SOUTH), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.DOWN), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.UP).move(Direction.NORTH).move(Direction.WEST), changedBlocks, bounds, config);
		func_227219_b_(world, random, pos.move(Direction.DOWN), changedBlocks, bounds, config);
		return true;
	}

	private int randomRange(Random random, int min, int max)
	{
		return random.nextInt(max + 1 - min) + min;
	}
}
