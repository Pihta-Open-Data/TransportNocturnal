define(['lib/leaflet/dist/leaflet-src'], function(L) {
    var IC = L.Icon.extend({
        options: {
            iconUrl: 'app/markers/subway-icon.png',
            iconSize: [25, 25],
            iconAnchor: [12, 12],
            popupAnchor: [12, 12]
        }
    });

    return L.Marker.extend({
        options: {
            icon: new IC()
        }
    });
});