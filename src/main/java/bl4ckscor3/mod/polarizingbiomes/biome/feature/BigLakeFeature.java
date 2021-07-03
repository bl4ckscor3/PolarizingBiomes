package bl4ckscor3.mod.polarizingbiomes.biome.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.SectionPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.LightType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.Structure;

public class BigLakeFeature extends Feature<BlockStateFeatureConfig>
{
	private static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();

	public BigLakeFeature(Codec<BlockStateFeatureConfig> codec)
	{
		super(codec);
	}

	@Override //slightly modified vanilla code
	public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config)
	{
		while(pos.getY() > 5 && world.isAirBlock(pos))
		{
			pos = pos.down();
		}

		if(pos.getY() <= 4)
			return false;
		else
		{
			pos = pos.down(4);

			if(world.func_241827_a(SectionPos.from(pos), Structure.VILLAGE).findAny().isPresent())
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
								Material material = world.getBlockState(pos.add(x, y, z)).getMaterial();

								if(y >= 4 && material.isLiquid())
									return false;

								if(y < 4 && !material.isSolid() && world.getBlockState(pos.add(x, y, z)) != config.state)
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
								world.setBlockState(pos.add(x, y, z), y >= 4 ? AIR : config.state, 2);
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
								BlockPos blockPos = pos.add(x, y - 1, z);

								if(isDirt(world.getBlockState(blockPos).getBlock()) && world.getLightFor(LightType.SKY, pos.add(x, y, z)) > 0)
								{
									Biome biome = world.getBiome(blockPos);

									if(biome.getGenerationSettings().getSurfaceBuilderConfig().getTop().matchesBlock(Blocks.MYCELIUM))
										world.setBlockState(blockPos, Blocks.MYCELIUM.getDefaultState(), 2);
									else
										world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState(), 2);
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