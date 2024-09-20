package pycmc.pnxcustom;

import cn.nukkit.Player;

public abstract class TranslationProvider {
    @Deprecated
    public abstract String translate(String lang, String key);
    @Deprecated
    public abstract String translate(String lang, String key, String... params);

    public abstract String translate(Player p, String key);
    public abstract String translate(Player p, String key, String... params);
}