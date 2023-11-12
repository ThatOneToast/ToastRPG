package toast.pine.Adapters;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import toast.pine.Monsters.MonsterType;

public class MonsterTypeAdapter implements PersistentDataType<MonsterType, MonsterType> {
    @Override
    public Class<MonsterType> getPrimitiveType() {
        return MonsterType.class;
    }

    @Override
    public Class<MonsterType> getComplexType() {
        return MonsterType.class;
    }

    @Override
    public MonsterType fromPrimitive(MonsterType complex, PersistentDataAdapterContext context) {
        return complex; // Implement this based on your requirement to retrieve MonsterType from a primitive form if needed.
    }

    @Override
    public MonsterType toPrimitive(MonsterType complex, PersistentDataAdapterContext context) {
        return complex; // Implement this based on your requirement to convert MonsterType to a primitive form if needed.
    }
}
