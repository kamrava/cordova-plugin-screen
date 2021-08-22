import Foundation
import UIKit

@objc(Screen)
public class Screen : CDVPlugin {
  @objc
  func sayHello(_ command: CDVInvokedUrlCommand) {
    let echo = command.argument(at: 0) as! String?
    let respo = "Hello Dear " + echo!
    let pluginResult:CDVPluginResult

    if echo != nil && echo!.count > 0 {
        pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK, messageAs: respo)
    } else {
        pluginResult = CDVPluginResult.init(status: CDVCommandStatus_ERROR)
    }

    self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
  }

  @objc
  func sendResponse(command: CDVInvokedUrlCommand, response: Int) {
    let pluginResult:CDVPluginResult
    pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK, messageAs: response)
    self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
  }

  @objc
  func height(_ command: CDVInvokedUrlCommand) {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)
    let height = Int(UIScreen.main.bounds.height) - navbarHeight

    self.sendResponse(command: command, response: height)
  }

  @objc
  func usableScreenHeight(_ command: CDVInvokedUrlCommand) {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)
    let height = Int(UIScreen.main.bounds.height) - navbarHeight

    self.sendResponse(command: command, response: height)
  }

  @objc
  func heightRaw(_ command: CDVInvokedUrlCommand) {
    let height = Int(UIScreen.main.bounds.height)

    self.sendResponse(command: command, response: height)
  }

  @objc
  func width(_ command: CDVInvokedUrlCommand) {
    let width = Int(UIScreen.main.bounds.width)

    self.sendResponse(command: command, response: width)
  }

  @objc
  func navbarHeight(_ command: CDVInvokedUrlCommand) {
    let navbarHeight = Int(UIApplication.shared.statusBarFrame.height)

    self.sendResponse(command: command, response: navbarHeight)
  }

}
