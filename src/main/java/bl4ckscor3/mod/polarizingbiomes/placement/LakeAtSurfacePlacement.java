package bl4ckscor3.mod.polarizingbiomes.placement;

import java.util.Random;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class LakeAtSurfacePlacement extends Placement<ChanceConfig>
{
	public LakeAtSurfacePlacement(Codec<ChanceConfig> codec)
	{
		super(codec);
	}

	@Override
	public Stream<BlockPos> func_241857_a(WorldDecoratingHelper wdh, Random random, ChanceConfig config, BlockPos pos)
	{
		if (random.nextInt(config.chance) == 0)
		{
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			int y = wdh.func_242893_a(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.getX() + x, pos.getZ() + z);

			return Stream.of(pos.add(x, y, z));
		}
		else
			return Stream.empty();
	}
}