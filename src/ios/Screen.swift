import Foundation
import UIKit

@objc(Screen)
public class Screen : CDVPlugin {
  @objc
  func getScreenInfo(_ command: CDVInvokedUrlCommand) {
    do {
      let messageDictionary : [AnyHashable: Any] = [
        "width": self.getWidth(),
        "height": self.getHeight(),
        "widthRaw": self.getWidthRaw(),
        "heightRaw": self.getHeightRaw(),
        "usableHeight": self.getUsableHeight(),
        "navbarHeight": self.getNavbarHeight(),
      ]

      let pluginResult:CDVPluginResult
      pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK, messageAs: messageDictionary)
      self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    } catch {
      print("JSON serialization failed: ", error)
    }
  }

  @objc
  func getHeight() -> Int {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)
    let height = Int(UIScreen.main.bounds.height) - navbarHeight
    return height
  }

  @objc
  func getUsableHeight() -> Int {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)
    let height = Int(UIScreen.main.bounds.height) - navbarHeight
    return height
  }

  @objc
  func getHeightRaw() -> Int {
    let height = Int(UIScreen.main.bounds.height)
    return height
  }

  @objc
  func getWidth() -> Int {
    let width = Int(UIScreen.main.bounds.width)
    return width
  }

  @objc
  func getWidthRaw() -> Int {
    let width = Int(UIScreen.main.bounds.width)
    return width
  }

  @objc
  func getNavbarHeight() -> Int {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)
    return navbarHeight
  }

}
