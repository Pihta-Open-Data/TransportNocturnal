define(['lib/leaflet/dist/leaflet-src'], function(L) {
    var map = L.map(document.body).setView({lat: 55.7529120574368, lng: 37.622079849243164}, 12);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    return map;
});