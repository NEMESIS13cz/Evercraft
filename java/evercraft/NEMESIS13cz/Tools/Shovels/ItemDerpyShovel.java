package evercraft.NEMESIS13cz.Tools.Shovels;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evercraft.NEMESIS13cz.ModInformation;

public class ItemDerpyShovel extends ItemSpade
{

    public ItemDerpyShovel(Item.ToolMaterial par2EnumToolMaterial)
    {
        super(par2EnumToolMaterial);
    }

    public void addInformation(ItemStack par1, EntityPlayer par2, List par3, boolean par4)
    {
        par3.add("Uses: " + (this.getMaxDamage() - this.getDamage(par1)));
        par3.add("Digging Speed: 12.0");
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
    
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }



@SideOnly(Side.CLIENT)
public void registerIcons(IIconRegister par1IconRegister)
{
    this.itemIcon = par1IconRegister.registerIcon(ModInformation.TEXTUREPATH + ":" + ModInformation.DERPY_SPADE);
}
}
