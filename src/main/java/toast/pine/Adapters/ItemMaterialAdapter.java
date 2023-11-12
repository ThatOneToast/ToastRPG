package toast.pine.Adapters;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import toast.pine.Classes.Items.ItemMaterial;

public class ItemMaterialAdapter implements PersistentDataType<ItemMaterial, ItemMaterial> {

    @Override
    public @NotNull Class<ItemMaterial> getPrimitiveType() {
        return ItemMaterial.class;
    }

    @Override
    public @NotNull Class<ItemMaterial> getComplexType() {
        return ItemMaterial.class;
    }

    @Override
    public @NotNull ItemMaterial toPrimitive(@NotNull ItemMaterial item, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return item;
    }

    @Override
    public @NotNull ItemMaterial fromPrimitive(@NotNull ItemMaterial item, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return item;
    }
}