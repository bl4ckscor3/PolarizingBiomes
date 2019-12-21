package bl4ckscor3.mod.polarizingbiomes.placement;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import com.mojang.datafixers.Dynamic;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public class LakeAtSurface extends Placement<ChanceConfig>
{
	public LakeAtSurface(Function<Dynamic<?>, ? extends ChanceConfig> configFactory)
	{
		super(configFactory);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld world, ChunkGenerator<? extends GenerationSettings> generator, Random random, ChanceConfig config, BlockPos pos)
	{
		if (random.nextInt(config.chance) == 0)
		{
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			int y = world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, pos.add(x, 0, z)).getY();

			return Stream.of(pos.add(x, y, z));
		}
		else
			return Stream.empty();
	}
}