define([
    'leaflet',
    'models/totalLinesCollection',
    'models/stationsCollection',
    'layers/LinesLayer'
], function(
    L,
    totalLinesCollection,
    stationsCollection,
    LinesLayer
) {
    return new LinesLayer({
        collection: totalLinesCollection,
        stationsCollection: stationsCollection
    })
});