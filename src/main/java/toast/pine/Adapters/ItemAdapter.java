package toast.pine.Adapters;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import toast.pine.Classes.Items.Item;

public class ItemAdapter implements PersistentDataType<Item, Item> {

    @Override
    public @NotNull Class<Item> getPrimitiveType() {
        return Item.class;
    }

    @Override
    public @NotNull Class<Item> getComplexType() {
        return Item.class;
    }

    @Override
    public @NotNull Item toPrimitive(@NotNull Item item, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return item;
    }

    @Override
    public @NotNull Item fromPrimitive(@NotNull Item item, @NotNull PersistentDataAdapterContext persistentDataAdapterContext) {
        return item;
    }
}
