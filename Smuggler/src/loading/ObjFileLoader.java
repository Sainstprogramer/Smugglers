package loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import math3d.Vector2f;
import math3d.Vector3f;
import models.RawModel;

public class ObjFileLoader {
	private static float[] vertecies;
	private static float[] texturecoords;
	private static float[] normals;
	private static int[] indecies;

	private static void loadvaribles(File file, ModelLoader loader) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);

		} catch (FileNotFoundException e) {
			System.err.println("Error while loading " + file.getAbsolutePath());
			e.printStackTrace();
		}
		try{
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		ArrayList<Vector3f> vertices = new ArrayList<Vector3f>();
		ArrayList<Vector2f> texturecoords = new ArrayList<Vector2f>();
		ArrayList<Vector3f> normals = new ArrayList<Vector3f>();
		ArrayList<Integer> indeces = new ArrayList<Integer>();
		float[] verticesarray = null;
		float[] normalarray = null;
		float[] texturearray = null;
		int[] indesiecarray = null;

			while (true) {

				line = br.readLine();
				String[] current = line.split(" ");
				if (line.startsWith("v ")) {
					Vector3f vertex = new Vector3f(
							Float.parseFloat(current[1]),
							Float.parseFloat(current[2]),
							Float.parseFloat(current[3]));
					vertices.add(vertex);
				} else if (line.startsWith("vt ")) {
					Vector2f textcoord = new Vector2f(
							Float.parseFloat(current[1]),
							Float.parseFloat(current[2]));
					texturecoords.add(textcoord);
				} else if (line.startsWith("vn ")) {
					Vector3f normal = new Vector3f(
							Float.parseFloat(current[1]),
							Float.parseFloat(current[2]),
							Float.parseFloat(current[3]));
					normals.add(normal);
				} else if (line.startsWith("f ")) {
					texturearray = new float[vertices.size() * 2];
					normalarray = new float[vertices.size() * 3];
					break;
				}
			}

			while (line != null) {
				if (!line.startsWith("f ")) {
					line = br.readLine();
					continue;
				}

				String[] current = line.split(" ");
				String[] vertex1 = current[1].split("/");
				String[] vertex2 = current[2].split("/");
				String[] vertex3 = current[3].split("/");

				prosessvertex(vertex1, indeces, texturecoords, normals,
						texturearray, normalarray);
				prosessvertex(vertex2, indeces, texturecoords, normals,
						texturearray, normalarray);
				prosessvertex(vertex3, indeces, texturecoords, normals,
						texturearray, normalarray);
				line = br.readLine();
			}
			br.close();
			
			verticesarray = new float[vertices.size() * 3];
			indesiecarray = new int[indeces.size()];

			int vertexpointer = 0;
			for (Vector3f vertex : vertices) {
				verticesarray[vertexpointer++] = vertex.x;
				verticesarray[vertexpointer++] = vertex.y;
				verticesarray[vertexpointer++] = vertex.z;
			}

			for (int i = 0; i < indeces.size(); i++) {
				indesiecarray[i] = indeces.get(i);
			}

			ObjFileLoader.vertecies = verticesarray;
			ObjFileLoader.texturecoords = texturearray;
			ObjFileLoader.normals = normalarray;
			ObjFileLoader.indecies = indesiecarray;

		} catch (IOException e) {
			System.err.println("Unknown file format");
			e.printStackTrace();
		}
	}

	public static RawModel loadObjModel(File file, ModelLoader loader) {
		loadvaribles(file, loader);

		return loader.LoadToVAO(vertecies, texturecoords, normals, indecies);
	}

	private static void prosessvertex(String[] vertexData,
			ArrayList<Integer> indeces, ArrayList<Vector2f> textures,
			ArrayList<Vector3f> normals, float[] texturearray,
			float[] normalsarray) {
		int currentvertexPointer = Integer.parseInt(vertexData[0]) - 1;
		indeces.add(currentvertexPointer);
		Vector2f currenttex = textures.get(Integer.parseInt(vertexData[1]) - 1);
		texturearray[currentvertexPointer * 2] = currenttex.x;
		texturearray[currentvertexPointer * 2 + 1] = 1 - currenttex.y;

		Vector3f currentnorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
		normalsarray[currentvertexPointer * 3] = currentnorm.x;
		normalsarray[currentvertexPointer * 3 + 1] = currentnorm.y;
		normalsarray[currentvertexPointer * 3 + 2] = currentnorm.z;

	}

}
