package evercraft.NEMESIS13cz.Items.Dusts;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evercraft.NEMESIS13cz.ModInformation;
import evercraft.NEMESIS13cz.Main.ECTabs;

public class ItemElectrumDust extends Item {
public ItemElectrumDust() {
setCreativeTab(ECTabs.tabECWGMaterials); //Tells the game what creative mode tab it goes in
}
public void addInformation(ItemStack par1, EntityPlayer par2, List par3, boolean par4)
{
    par3.add(EnumChatFormatting.WHITE + "1/2" + EnumChatFormatting.YELLOW + " Gold");
    par3.add(EnumChatFormatting.WHITE + "1/2" + EnumChatFormatting.WHITE + " Silver");
}


@SideOnly(Side.CLIENT)
public void registerIcons(IIconRegister par1IconRegister)
{
    this.itemIcon = par1IconRegister.registerIcon(ModInformation.TEXTUREPATH + ":" + ModInformation.ELECTRUM_DUST);
}
}