define(['leaflet', 'json!config.json', 'json!localConfig.json'], function(L, config, localConfig) {
    return L.extend(config, localConfig);
});