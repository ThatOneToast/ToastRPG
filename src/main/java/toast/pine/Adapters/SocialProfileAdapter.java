package toast.pine.Adapters;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import toast.pine.SocialSystem.PlayerSocial;

public class SocialProfileAdapter implements PersistentDataType<PlayerSocial, PlayerSocial> {
    @Override
    public @NotNull Class<PlayerSocial> getPrimitiveType() {
        return PlayerSocial.class;
    }

    @Override
    public @NotNull Class<PlayerSocial> getComplexType() {
        return PlayerSocial.class;
    }

    @Override
    public @NotNull PlayerSocial toPrimitive(@NotNull PlayerSocial complex, @NotNull PersistentDataAdapterContext context) {
        return complex;
    }

    @Override
    public @NotNull PlayerSocial fromPrimitive(@NotNull PlayerSocial primitive, @NotNull PersistentDataAdapterContext context) {
        return primitive;
    }
}
