package bl4ckscor3.mod.polarizingbiomes.biome.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.material.Material;

public class BigLakeFeature extends Feature<BlockStateConfiguration>
{
	private static final BlockState AIR = Blocks.CAVE_AIR.defaultBlockState();

	public BigLakeFeature(Codec<BlockStateConfiguration> codec)
	{
		super(codec);
	}

	@Override //slightly modified vanilla code
	public boolean place(FeaturePlaceContext<BlockStateConfiguration> ctx)
	{
		BlockPos pos = ctx.origin();
		WorldGenLevel level = ctx.level();
		Random rand = ctx.random();
		BlockStateConfiguration config = ctx.config();

		while(pos.getY() > 5 && level.isEmptyBlock(pos))
		{
			pos = pos.below();
		}

		if(pos.getY() <= 4)
			return false;
		else
		{
			pos = pos.below(4);

			if(level.startsForFeature(SectionPos.of(pos), StructureFeature.VILLAGE).findAny().isPresent())
				return false;
			else
			{
				boolean[] shouldPlace = new boolean[Short.MAX_VALUE];
				int i = rand.nextInt(4) + 4;

				for(int j = 0; j < i; ++j)
				{
					double d0 = rand.nextDouble() * 12.0D + 6.0D;
					double d1 = rand.nextDouble() * 8.0D + 4.0D;
					double d2 = rand.nextDouble() * 12.0D + 6.0D;
					double d3 = rand.nextDouble() * (32.0D - d0 - 4.0D) + 2.0D + d0 / 4.0D;
					double d4 = rand.nextDouble() * (16.0D - d1 - 8.0D) + 1.0D + d1 / 4.0D;
					double d5 = rand.nextDouble() * (32.0D - d2 - 4.0D) + 2.0D + d2 / 4.0D;

					for(int x = 1; x < 31; ++x)
					{
						for(int z = 1; z < 31; ++z)
						{
							for(int y = 1; y < 15; ++y)
							{
								double d6 = (x - d3) / (d0 / 2.0D);
								double d7 = (y - d4) / (d1 / 2.0D);
								double d8 = (z - d5) / (d2 / 2.0D);
								double d9 = d6 * d6 + d7 * d7 + d8 * d8;

								if(d9 < 1.0D)
									shouldPlace[(x * 32 + z) * 16 + y] = true;

							}
						}
					}
				}

				for(int x = 0; x < 32; ++x)
				{
					for(int z = 0; z < 32; ++z)
					{
						for(int y = 0; y < 16; ++y)
						{
							boolean flag = !shouldPlace[(x * 32 + z) * 16 + y] && (x < 32 && shouldPlace[((x + 1) * 32 + z) * 16 + y] || x > 0 && shouldPlace[((x - 1) * 32 + z) * 16 + y] || z < 15 && shouldPlace[(x * 32 + z + 1) * 16 + y] || z > 0 && shouldPlace[(x * 32 + (z - 1)) * 16 + y] || y < 7 && shouldPlace[(x * 32 + z) * 16 + y + 1] || y > 0 && shouldPlace[(x * 32 + z) * 16 + (y - 1)]);

							if(flag)
							{
								Material material = level.getBlockState(pos.offset(x, y, z)).getMaterial();

								if(y >= 4 && material.isLiquid())
									return false;

								if(y < 4 && !material.isSolid() && level.getBlockState(pos.offset(x, y, z)) != config.state)
									return false;
							}
						}
					}
				}

				for(int x = 0; x < 32; ++x)
				{
					for(int z = 0; z < 32; ++z)
					{
						for(int y = 0; y < 16; ++y)
						{
							if(shouldPlace[(x * 32 + z) * 16 + y])
								level.setBlock(pos.offset(x, y, z), y >= 4 ? AIR : config.state, 2);
						}
					}
				}

				for(int x = 0; x < 32; ++x)
				{
					for(int z = 0; z < 32; ++z)
					{
						for(int y = 4; y < 16; ++y)
						{
							if(shouldPlace[(x * 32 + z) * 16 + y])
							{
								BlockPos blockPos = pos.offset(x, y - 1, z);

								if(isDirt(level.getBlockState(blockPos)) && level.getBrightness(LightLayer.SKY, pos.offset(x, y, z)) > 0)
								{
									Biome biome = level.getBiome(blockPos);

									if(biome.getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial().is(Blocks.MYCELIUM))
										level.setBlock(blockPos, Blocks.MYCELIUM.defaultBlockState(), 2);
									else
										level.setBlock(blockPos, Blocks.GRASS_BLOCK.defaultBlockState(), 2);
								}
							}
						}
					}
				}

				return true;
			}
		}
	}
}