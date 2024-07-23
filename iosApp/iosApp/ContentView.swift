import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        NavigationStack {
            AboutView()
                .navigationTitle("About Device")
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}


struct AboutView: View {
    let platform = Platform()
    
    private struct LabelItem: Hashable {
      let title: String
      let subtitle: String
    }
    
    private let items: [LabelItem] = {
        let platform = Platform()
        platform.logSystemInfo()
        
       return [
          .init(
            title: "Operation System",
            subtitle: "\(platform.osName) \(platform.osVersion)"
          ),
          .init(
            title: "Device",
            subtitle: platform.deviceModel
          ),
          .init(
            title: "Density",
            subtitle: "\(platform.density)"
          ),
        ]
    }()

    var body: some View {
        List {
          ForEach(items, id: \.self) { item in
            VStack(alignment: .leading) {
              Text(item.title)
                .font(.footnote)
                .foregroundStyle(.secondary)
              Text(item.subtitle)
                .font(.body)
                .foregroundStyle(.primary)
            }
            .padding(.vertical, 4)
          }
        }
        
        
//        VStack(alignment: .leading) {
//            Text("Operation System")
//                .font(.footnote)
//                .foregroundStyle(.secondary)
//            Text("\(platform.osName) \(platform.osVersion)")
//                .font(.body)
//                .foregroundStyle(.primary)
//            Text("Device")
//                .font(.footnote)
//                .foregroundStyle(.secondary)
//            Text(platform.deviceModel)
//                .font(.body)
//                .foregroundStyle(.primary)
//            Text("Density")
//                .font(.footnote)
//                .foregroundStyle(.secondary)
//            Text("\(platform.density)")
//                .font(.body)
//                .foregroundStyle(.primary)
//        }
//        .onAppear(perform: {
//            platform.logSystemInfo()
//        })
//        .padding(.vertical, 4)
    }
}
