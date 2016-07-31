var raster = new ol.layer.Tile({
  source: new ol.source.OSM()
});

var vector = new ol.layer.Vector({
  source: new ol.source.Vector({
    format: new ol.format.GeoJSON(),
    url: 'sa4.geojson'
  })
});

var map = new ol.Map({
  layers: [raster,vector],
  target: 'map',
  view: new ol.View({
    center: ol.proj.transform([133.37325757727623, -27.03384597110646], 'EPSG:4326', 'EPSG:3857'),
    zoom: 4
  })
});

var select = null;  // ref to currently selected interaction

// select interaction working on "singleclick"
var selectSingleClick = new ol.interaction.Select();

// select interaction working on "click"
var selectClick = new ol.interaction.Select({
  condition: ol.events.condition.click
});

// select interaction working on "mousemove"
var selectMouseMove = new ol.interaction.Select({
  condition: ol.events.condition.mouseMove
});
map.addInteraction(selectSingleClick);
map.addInteraction(selectClick);


/*
var selectElement = document.getElementById('type');

var changeInteraction = function() {
  if (select !== null) {
    map.removeInteraction(select);
  }
  var value = selectElement.value;
  if (value == 'singleclick') {
    select = selectSingleClick;
  } else if (value == 'click') {
    select = selectClick;
  } else if (value == 'mousemove') {
    select = selectMouseMove;
  } else {
    select = null;
  }
  if (select !== null) {
    map.addInteraction(select);
  }
};
*/
selectSingleClick.on('select', function(e) {
    var feats = e.selected;
    if( feats.length == 0 ) return;
    var sa4=feats[0];
    showResult(sa4.getProperties()["SA4_CODE11"]);
    });
selectClick.on('select', function(e) {
    var feats = e.selected;
    if( feats.length == 0 ) return;
    var sa4=feats[0];
    showResult(sa4.getProperties()["SA4_CODE11"]);
    });