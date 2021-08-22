---
title: Screen
description: Get device screen size information.
---

# cordova-plugin-screen

This plugin defines a global `Screen` (capital S) object, which describes the device's screen size info.
Although the object is in the global scope, it is not available until after the `deviceready` event.

```js
document.addEventListener("deviceready", onDeviceReady, false);
function onDeviceReady() {
    console.log(Screen.height);
}
```

## Installation

    cordova plugin add cordova-plugin-screen
or

    cordova plugin add https://github.com/kamrava/cordova-plugin-screen.git


## Properties

- Screen.height
- Screen.heightRaw
- Screen.width
- Screen.widthRaw
- Screen.navbarHeight
- Screen.statusbarHeight
- Screen.usableScreenHeight

## Screen.height

Get the screen height size of the device in pixels.

### Supported Platforms

- Android
- iOS

## Screen.width

Get the screen width size of the device in pixels.

### Supported Platforms

- Android
- iOS

## Screen.usableScreenHeight

Get the screen height size (without navbar and statusbar) of the device in pixels.

### Supported Platforms

- Android
- iOS

## Screen.navbarHeight

Get the device navbar height in pixels.

### Supported Platforms

- Android
- iOS

## Screen.statusbarHeight

Get the device statusbar height in pixels.

### Supported Platforms

- Android

### Quick Example

```js
Screen.usableScreenHeight( height => {
  var device_screen_height = height;
});

Screen.usableScreenHeight( height => {
  var device_screen_usable_height = height
});

...
```

### Author
Hamed Kamrava
