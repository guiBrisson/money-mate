import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        HelperDIKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
