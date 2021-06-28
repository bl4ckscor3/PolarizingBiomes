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

public class SlimTreeFoliagePlacer extends FoliagePlacer
{
	public static final Codec<SlimTreeFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> func_236740_a_(instance).apply(instance, SlimTreeFoliagePlacer::new));
	protected final int height;

	public SlimTreeFoliagePlacer(FeatureSpread fs1, FeatureSpread fs2, int height)
	{
		super(fs1, fs2);

		this.height = height;
	}

	protected static <P extends SlimTreeFoliagePlacer> P3<Mu<P>,FeatureSpread,FeatureSpread,Integer> func_236740_a_(Instance<P> instance)
	{
		return func_242830_b(instance).and(Codec.INT.fieldOf("height").forGetter(o -> o.height));
	}

	@Override
	protected FoliagePlacerType<?> getPlacerType()
	{
		return PolarizingBiomeFoliagePlacerTypes.SLIM_TREE;
	}

	@Override
	protected void func_230372_a_(IWorldGenerationReader world, Random random, BaseTreeFeatureConfig featureConfig, int p_230372_4_, Foliage p_230372_5_, int p_230372_6_, int p_230372_7_, Set<BlockPos> changedBlocks, int p_230372_9_, MutableBoundingBox bounds)
	{
		BlockPos.Mutable pos = new BlockPos.Mutable();

		pos.setAndOffset(p_230372_5_.func_236763_a_(), 0, p_230372_7_ - 3, 0);
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
		world.setBlockState(pos, featureConfig.leavesProvider.getBlockState(random, pos), 19);
		bounds.expandTo(new MutableBoundingBox(pos, pos));
		changedBlocks.add(pos.toImmutable());
	}

	@Override
	public int func_230374_a_(Random random, int p_230374_2_, BaseTreeFeatureConfig featureConfig)
	{
		return height;
	}

	@Override
	protected boolean func_230373_a_(Random random, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_)
	{
		return true;
	}
}
