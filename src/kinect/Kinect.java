package kinect;

import java.util.List;

import javax.swing.JOptionPane;

import org.openni.Device;
import org.openni.DeviceInfo;
import org.openni.OpenNI;
import org.openni.SensorType;
import org.openni.VideoStream;

import com.primesense.nite.NiTE;
import com.primesense.nite.UserTracker;

public class Kinect {

	private Device device;
	public RGBStream rgbStream;
	public DepthStream depthStream;
	public UserStream userStream;
	
	public Kinect(){
		OpenNI.initialize();
		NiTE.initialize();
		
		List<DeviceInfo> devicesInfo = OpenNI.enumerateDevices();
		
		if (devicesInfo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No device is connected", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        device = Device.open(devicesInfo.get(0).getUri());
	}
	
	public void openRGB(){
		rgbStream = new RGBStream(VideoStream.create(device, SensorType.COLOR));
		rgbStream.start();
	}
	
	public void openDepth(){
		depthStream = new DepthStream(VideoStream.create(device, SensorType.DEPTH));
		depthStream.start();
	}
	
	public void openUserTracker(){
		userStream = new UserStream(UserTracker.create());
	}
}
