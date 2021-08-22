"use strict";

var exec = require('cordova/exec');

var PLUGIN_NAME = 'Screen';
var scale = window.devicePixelRatio || 1;
var Screen = {
  sayHello: function sayHello(cb) {
    exec(cb, null, PLUGIN_NAME, 'sayHello', []);
  },
  height: function height(cb) {
    exec(cb, null, PLUGIN_NAME, 'height', [scale]);
  },
  heightRaw: function heightRaw(cb) {
    exec(cb, null, PLUGIN_NAME, 'height', [1]);
  },
  width: function width(cb) {
    exec(cb, null, PLUGIN_NAME, 'width', [scale]);
  },
  widthRaw: function widthRaw(cb) {
    exec(cb, null, PLUGIN_NAME, 'width', [1]);
  },
  usableScreenHeight: function usableScreenHeight(cb) {
    exec(cb, null, PLUGIN_NAME, 'usableScreenHeight', [scale]);
  },
  usableScreenHeightRaw: function usableScreenHeightRaw(cb) {
    exec(cb, null, PLUGIN_NAME, 'usableScreenHeight', [1]);
  },
  navbarHeight: function navbarHeight(cb) {
    exec(cb, null, PLUGIN_NAME, 'navbarHeight', []);
  },
  statusbarHeight: function statusbarHeight(cb) {
    exec(cb, null, PLUGIN_NAME, 'statusbarHeight', []);
  },
  hasNavbar: function hasNavbar(cb) {
    exec(cb, null, PLUGIN_NAME, 'hasNavbar', []);
  }
};

Screen.sayHi = function (onSuccess, onError) {
  exec(onSuccess, onError, PLUGIN_NAME, "sayHi", []);
};

module.exports = Screen;
