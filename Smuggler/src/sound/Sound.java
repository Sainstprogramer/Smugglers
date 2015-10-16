package sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;

import sun.net.www.content.audio.wav;
import toolbox.Maths;

public class Sound {
	int buffer;

	public Sound(String path) throws FileNotFoundException {
		int error;
		// Load wav data into a buffer.
		buffer = AL10.alGenBuffers();
		
		error = AL10.alGetError();
		if (error != AL10.AL_NO_ERROR) {
			System.err.println("ERROR: " + Maths.getALErrorString(error));
		}
		InputStream fin = null;

		File f = new File(path);
		fin = new FileInputStream(f.getAbsoluteFile());

		error = AL10.alGetError();
		if (error != AL10.AL_NO_ERROR) {
			System.err.println("ERROR: " + Maths.getALErrorString(error));
		}

		WaveData waveFile = WaveData.create(new BufferedInputStream(fin));
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		

		error = AL10.alGetError();
		if (error != AL10.AL_NO_ERROR) {
			System.err.println(error);
			System.err.println("ERROR: " + Maths.getALErrorString(error));
		}

		waveFile.dispose();
	}

	public int getBuff() {
		return buffer;
	}

	public void setBuff(int buff) {
		this.buffer = buff;
	}

	public void destroy() {
		AL10.alDeleteBuffers(buffer);
	}

}