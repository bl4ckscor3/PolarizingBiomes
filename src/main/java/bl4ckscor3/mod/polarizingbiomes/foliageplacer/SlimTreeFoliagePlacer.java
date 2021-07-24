package bl4ckscor3.mod.polarizingbiomes.foliageplacer;

import java.util.Random;
import java.util.Set;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomeFoliagePlacerTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import net.minecraft.world.gen.foliageplacer.FoliagePlacer.Foliage;

public class SlimTreeFoliagePlacer extends FoliagePlacer
{
	public static final Codec<SlimTreeFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> blobParts(instance).apply(instance, SlimTreeFoliagePlacer::new));
	protected final int height;

	public SlimTreeFoliagePlacer(FeatureSpread fs1, FeatureSpread fs2, int height)
	{
		super(fs1, fs2);

		this.height = height;
	}

	protected static <P extends SlimTreeFoliagePlacer> P3<Mu<P>,FeatureSpread,FeatureSpread,Integer> blobParts(Instance<P> instance)
	{
		return foliagePlacerParts(instance).and(Codec.INT.fieldOf("height").forGetter(o -> o.height));
	}

	@Override
	protected FoliagePlacerType<?> type()
	{
		return PolarizingBiomeFoliagePlacerTypes.SLIM_TREE;
	}

	@Override
	protected void createFoliage(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig featureConfig, int p_230372_4_, Foliage p_230372_5_, int p_230372_6_, int p_230372_7_, Set<BlockPos> changedBlocks, int p_230372_9_, MutableBoundingBox bounds)
	{
		BlockPos.Mutable pos = new BlockPos.Mutable();

		pos.setWithOffset(p_230372_5_.foliagePos(), 0, p_230372_7_ - 3, 0);
		setLeaf(changedBlocks, world, pos.move(Direction.UP), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.DOWN).move(Direction.NORTH), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.DOWN), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.UP).move(Direction.SOUTH).move(Direction.EAST), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.DOWN), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.UP).move(Direction.WEST).move(Direction.SOUTH), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.DOWN), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.UP).move(Direction.NORTH).move(Direction.WEST), featureConfig, bounds, random);
		setLeaf(changedBlocks, world, pos.move(Direction.DOWN), featureConfig, bounds, random);
	}

	private void setLeaf(Set<BlockPos> changedBlocks, IWorldGenerationReader world, BlockPos.Mutable pos, BaseTreeFeatureConfig featureConfig, MutableBoundingBox bounds, Random random)
	{
		world.setBlock(pos, featureConfig.leavesProvider.getState(random, pos), 19);
		bounds.expand(new MutableBoundingBox(pos, pos));
		changedBlocks.add(pos.immutable());
	}

	@Override
	public int foliageHeight(Random random, int p_230374_2_, BaseTreeFeatureConfig featureConfig)
	{
		return height;
	}

	@Override
	protected boolean shouldSkipLocation(Random random, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_)
	{
		return true;
	}
}
