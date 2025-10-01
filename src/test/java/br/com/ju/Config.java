package br.com.ju;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;

public class Config {
    private static Map<String, Object> config;

    static {
        try {
            InputStream in = Config.class.getClassLoader().getResourceAsStream("config.yaml");
            if (in == null) {
                throw new RuntimeException("Arquivo config.yaml não encontrado no classpath!");
            }
            Yaml yaml = new Yaml();
            config = yaml.load(in);
            in.close();
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
}