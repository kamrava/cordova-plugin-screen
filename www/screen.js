
var exec = require('cordova/exec');

var PLUGIN_NAME = 'Screen';
const scale = window.devicePixelRatio || 1;

var Screen = {
  sayHello: function(str, cb) {
    exec(cb, null, PLUGIN_NAME, 'sayHello', [str]);
  },
  getDate: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'getDate', []);
  },
  height: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'height', [scale]);
  },
  heightRaw: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'height', [1]);
  },
  width: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'width', [scale]);
  },
  widthRaw: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'width', [1]);
  },
  usableScreenHeight: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'usableScreenHeight', [scale]);
  },
  usableScreenHeightRaw: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'usableScreenHeight', [1]);
  },
  navbarHeight: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'navbarHeight', []);
  },
  statusbarHeight: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'statusbarHeight', []);
  },
  hasNavbar: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'hasNavbar', []);
  }
};

module.exports = Screen;
