package bl4ckscor3.mod.polarizingbiomes.placement;

import java.util.Random;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class LakeAtSurfacePlacement extends FeatureDecorator<ChanceDecoratorConfiguration>
{
	public LakeAtSurfacePlacement(Codec<ChanceDecoratorConfiguration> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(DecorationContext wdh, Random random, ChanceDecoratorConfiguration config, BlockPos pos)
	{
		if (random.nextInt(config.chance) == 0)
		{
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			int y = wdh.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pos.getX() + x, pos.getZ() + z);

			return Stream.of(pos.offset(x, y, z));
		}
		else
			return Stream.empty();
	}
}