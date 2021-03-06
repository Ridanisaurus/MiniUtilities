package onelemonyboi.miniutilities.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BlockItem;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import onelemonyboi.miniutilities.items.BlockList;

import static net.minecraftforge.eventbus.api.EventPriority.LOWEST;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlockColorManager {
    @SubscribeEvent
    public static void onItemColorsInit(ColorHandlerEvent.Item event) {
        final BlockColors blockColors = event.getBlockColors();
        final ItemColors itemColors = event.getItemColors();

        IBlockColor iBlockColor = (blockState, iEnviromentBlockReader, blockPos, i) -> Integer.decode("#222222");
        IBlockColor iBlockColorBlessed = (blockState, iEnviromentBlockReader, blockPos, i) -> Integer.decode("#FFFFED");
        blockColors.register(iBlockColor, BlockList.CursedEarth.get());
        blockColors.register(iBlockColorBlessed, BlockList.BlessedEarth.get());
        IItemColor itemBlockColourHandler = (stack, tintIndex) ->
        {
            BlockState state = ((BlockItem) stack.getItem()).getBlock().getDefaultState();
            return blockColors.getColor(state, null, null, tintIndex);
        };
        if (itemBlockColourHandler != null) {
            itemColors.register(itemBlockColourHandler, BlockList.CursedEarth.get());
            itemColors.register(itemBlockColourHandler, BlockList.BlessedEarth.get());
        }
    }
}