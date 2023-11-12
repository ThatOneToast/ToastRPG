package toast.pine.Adapters;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import toast.pine.Monsters.Monster;

public class MonsterAdapter implements PersistentDataType<Monster, Monster> {

    @Override
    public Class<Monster> getPrimitiveType() {
        return Monster.class;
    }

    @Override
    public Class<Monster> getComplexType() {
        return Monster.class;
    }

    @Override
    public @NotNull Monster fromPrimitive(Monster complex, PersistentDataAdapterContext context) {
        return complex; // Implement this based on your requirement to retrieve Monster from a primitive form if needed.
    }

    @Override
    public @NotNull Monster toPrimitive(Monster complex, PersistentDataAdapterContext context) {
        return complex; // Implement this based on your requirement to convert Monster to a primitive form if needed.
    }

}
