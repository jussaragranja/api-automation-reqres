package br.com.ju.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public final class Config {
    private static final Map<String, Object> config;

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.yaml")) {
            if (in == null) throw new RuntimeException("Arquivo config.yaml não encontrado!");
            config = new Yaml().load(in);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar config.yaml", e);
        }
    }

    public static String getBaseUrl() {
        return (String) config.get("baseUrl");
    }

    public static int getTimeout() {
        return (Integer) config.get("timeout");
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> getDefaultHeaders() {
        return (Map<String, String>) config.get("defaultHeaders");
    }

    public static String getApiKey() {
        return (String) config.get("apiKey");
    }
}