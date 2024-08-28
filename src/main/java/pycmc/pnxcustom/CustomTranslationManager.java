package pycmc.pnxcustom;

import cn.nukkit.Player;

import java.util.Objects;

public class CustomTranslationManager {
    private static TranslationProvider[] providers;

    public static void setProviders(TranslationProvider... providerss) {
        providers = providerss;
    }

    public static void addProvider(TranslationProvider provider) {
        if (providers == null) {
            providers = new TranslationProvider[1];
            providers[0] = provider;
        } else {
            TranslationProvider[] newProviders = new TranslationProvider[providers.length + 1];
            System.arraycopy(providers, 0, newProviders, 0, providers.length);
            newProviders[providers.length] = provider;
            providers = newProviders;
        }
    }

    public static String translate(String lang, String key) {
        if (providers == null) {
            return key;
        }
        for (TranslationProvider provider : providers) {
            String result = provider.translate(lang, key);
            if (!Objects.equals(result, key)) {
                return result;
            }
        }
        return key;
    }

    public static String translate(String lang, String key, String... params) {
        if (providers == null) {
            return key;
        }
        for (TranslationProvider provider : providers) {
            String result = provider.translate(lang, key, params);
            if (!Objects.equals(result, key)) {
                return result;
            }
        }
        return key;
    }

    public static String translate(Player p, String key) {
        if (providers == null) {
            return key;
        }
        for (TranslationProvider provider : providers) {
            String result = provider.translate(p, key);
            if (!Objects.equals(result, key)) {
                return result;
            }
        }
        return key;
    }

    public static String translate(Player p, String key, String... params) {
        if (providers == null) {
            return key;
        }
        for (TranslationProvider provider : providers) {
            String result = provider.translate(p, key, params);
            if (!Objects.equals(result, key)) {
                return result;
            }
        }
        return key;
    }
}