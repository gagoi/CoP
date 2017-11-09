package fr.gagoi.cop.core;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

public class Game {

	long window;

	public void run() {
		init();
		loop();

		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

	private void init() {
		glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err).set());
		if (!glfwInit())
			throw new IllegalStateException("Unable to initialize GLFW");
		glfwDefaultWindowHints();

		glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		window = glfwCreateWindow(1280, 720, "Conflict Of Pixels", 0, 0);
		if (window == 0)
			throw new RuntimeException("Failed to create window");
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glOrtho(0, 1280, 0, 720, -1, 1);

		glfwSwapInterval(1);
		glfwShowWindow(window);

	}

	private void loop() {
		// Run the rendering loop until the user has attempted to close
		// the window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(window)) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

			glPushMatrix();
			glColor3i(0, 1, 1);
			glRectf(40, 40, 100, 100);
			glPopMatrix();

			glfwSwapBuffers(window); // swap the color buffers

			// Poll for window events. The key callback above will only be
			// invoked during this call.
			glfwPollEvents();
		}
	}

	public static void main(String[] args) {
		new Game().run();
	}

}