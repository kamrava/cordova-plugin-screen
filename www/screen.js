var argscheck = require('cordova/argscheck');
var channel = require('cordova/channel');
var exec = require('cordova/exec');
var cordova = require('cordova');

channel.createSticky('onCordovaInfoReady');
channel.waitForInitialization('onCordovaInfoReady');

const scale = window.devicePixelRatio || 1;

function Screen () {
    this.available = false;
    this.height = null;
    this.heightRaw = null;
    this.width = null;
    this.widthRaw = null;
    this.usableScreenHeight = null;
    this.navbarHeight = null;
    this.statusbarHeight = null;

    var me = this;

    channel.onCordovaReady.subscribe(function () {
        me.getInfo(
            function (info) {
                me.available = true;
                me.width = info.width;
                me.height = info.height;
                me.widthRaw = info.widthRaw;
                me.heightRaw = info.heightRaw;
                me.navbarHeight = info.navbarHeight;
                me.statusbarHeight = info.statusbarHeight;
                me.usableHeight = info.usableHeight;
                channel.onCordovaInfoReady.fire();
            },
            function (e) {
                me.available = false;
                console.error('[ERROR] Error initializing cordova-plugin-screen: ' + e);
            }
        );
    });
}

/**
 * Get screen info
 *
 * @param {Function} successCallback The function to call when the heading data is available
 * @param {Function} errorCallback The function to call when there is an error getting the heading data. (OPTIONAL)
 */
 Screen.prototype.getInfo = function (successCallback, errorCallback) {
    argscheck.checkArgs('fF', 'Screen.getInfo', arguments);
    exec(successCallback, errorCallback, 'Screen', 'getScreenInfo', [scale]);
};

module.exports = new Screen();
