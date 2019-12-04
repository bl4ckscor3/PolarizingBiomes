package bl4ckscor3.mod.polarizingbiomes.biome.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyDirtBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

/**
 * This is mainly there to add snow under trees
 */
public class SnowOnGroundFeature extends Feature<NoFeatureConfig>
{
	public SnowOnGroundFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactory)
	{
		super(configFactory);
	}

	@Override
	public boolean place(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		MutableBlockPos mPos = new MutableBlockPos();

		for(int xi = 0; xi < 16; xi++)
		{
			for(int zi = 0; zi < 16; zi++)
			{
				int x = pos.getX() + xi;
				int z = pos.getZ() + zi;

				mPos.setPos(x, world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, x, z), z);

				if(world.getBlockState(mPos).isAir(world, mPos))
				{
					MutableBlockPos mPosDown = new MutableBlockPos();
					BlockState below;

					mPosDown.setPos(mPos).move(Direction.DOWN);
					below = world.getBlockState(mPosDown);

					if(below.getBlock() == Blocks.GRASS_BLOCK)
					{
						world.setBlockState(mPos, Blocks.SNOW.getDefaultState(), 2);
						world.setBlockState(mPosDown, below.with(SnowyDirtBlock.SNOWY, true), 2);
					}
				}
			}
		}

		return true;
	}
}
